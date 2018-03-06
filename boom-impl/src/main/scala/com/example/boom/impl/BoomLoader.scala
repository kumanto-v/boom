package com.example.boom.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.example.boom.api.BoomService
import com.softwaremill.macwire._

class BoomLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new BoomApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new BoomApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[BoomService])
}

abstract class BoomApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = serverFor[BoomService](wire[BoomServiceImpl])

}
