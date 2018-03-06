package com.example.endpoint

import com.example.boom.api.BoomService
import com.example.endpoint.api.EndpointService
import com.lightbend.lagom.scaladsl.api.ServiceCall

import scala.concurrent.{ExecutionContext, Future}


class EndpointServiceImpl(boomService: BoomService)(implicit ec: ExecutionContext) extends EndpointService {

  override def hello(n: Int) = ServiceCall { _ =>
    Future.sequence{
      (0 to  n).map(i => boomService.hello(s"call $i").invoke())
    }.map(l => s"Last ${l.last}")
  }
}
