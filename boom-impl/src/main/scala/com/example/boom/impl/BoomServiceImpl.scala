package com.example.boom.impl

import com.example.boom.api
import com.example.boom.api.BoomService
import com.lightbend.lagom.scaladsl.api.ServiceCall

import scala.concurrent.Future

/**
  * Implementation of the BoomService.
  */
class BoomServiceImpl() extends BoomService {

  override def hello(id: String) = ServiceCall { _ =>
    Future.successful(s"Hello $id")
  }

}
