package org.conbere.gearman

import akka.actor.{ ActorSystem, Props }
import akka.pattern.{ ask }
import akka.util.Timeout
import scala.concurrent.{ ExecutionContext, Promise }
import scala.concurrent.duration._

import scala.concurrent.Await
import scala.language.postfixOps

object Main {
  val system = ActorSystem("Gearman")
  implicit val timeout = Timeout(5 seconds)

  def main(args:Array[String]) {
    import ExecutionContext.Implicits.global
    val client = system.actorOf(Props(new Service("example.com", 4730, "client")))
    val worker = system.actorOf(Props(new Service("example.com", 4730, "worker")))



    Thread.sleep(2000)
    worker ! Packet.build(Magic.REQ, Command.CAN_DO, List("reverse"))
    Thread.sleep(2000)
    worker ! Packet.build(Magic.REQ, Command.GRAB_JOB)
    Thread.sleep(2000)
    worker ! Packet.build(Magic.REQ, Command.PRE_SLEEP)
    Thread.sleep(2000)
    
    client ! Packet.build(Magic.REQ, Command.SUBMIT_JOB, List("reverse", "", "test"))
    Thread.sleep(2000)
    worker ! Packet.build(Magic.REQ, Command.GRAB_JOB)
    Thread.sleep(2000)
    println("waiting")
  }
}
