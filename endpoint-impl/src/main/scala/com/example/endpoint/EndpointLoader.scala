package com.example.endpoint

import com.example.boom.api.BoomService
import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.example.endpoint.api.EndpointService
import com.softwaremill.macwire._

class EndpointLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new EndpointApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new EndpointApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[EndpointService])
}

abstract class EndpointApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = serverFor[EndpointService](wire[EndpointServiceImpl])

  lazy val boomService = serviceClient.implement[BoomService]
}
