package com.example.endpoint.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}


trait EndpointService extends Service {


  def hello(n: Int): ServiceCall[NotUsed, String]

  override final def descriptor = {
    import Service._
    named("endpoint")
      .withCalls(
        pathCall("/api/endpoint/:n", hello _)
      ).withAutoAcl(true)
  }
}
