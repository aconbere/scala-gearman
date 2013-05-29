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
    worker ! Messages.Worker.CAN_DO("reverse")
    Thread.sleep(2000)
    worker ! Messages.Worker.GRAB_JOB()
    Thread.sleep(2000)
    worker ! Messages.Worker.PRE_SLEEP()
    Thread.sleep(2000)
    
    client ! Messages.Client.SUBMIT_JOB("reverse", "", "test")
    Thread.sleep(2000)
    worker ! Messages.Worker.GRAB_JOB()
    Thread.sleep(2000)
    println("waiting")
  }
}
