package com.example.boom.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}


trait BoomService extends Service {


  def hello(id: String): ServiceCall[NotUsed, String]


  override final def descriptor = {
    import Service._

    named("boom")
      .withCalls(
        pathCall("/api/boom/:id", hello _)
      ).withAutoAcl(true)
  }
}