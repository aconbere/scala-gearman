package org.conbere.gearman

import akka.util.ByteString
import akka.util.ByteStringBuilder

trait Response {
  def +(r:Response):ResponseCollection = new ResponseCollection(List(this, r))

  def toByteString:ByteString
}

class ResponseCollection(val responseList:List[Response]=List())
extends Response {
  override def +(r:Response) = new ResponseCollection(responseList :+ r )

  def toByteString = responseList.foldLeft(new ByteStringBuilder) {
    (acc, m) => acc ++= m.toByteString
  }.result

  override def toString = responseList.map( _.toString).mkString("|")
}
