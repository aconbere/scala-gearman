package org.conbere.gearman

import akka.actor.{ Actor, IO, IOManager }
import akka.util.ByteString

import com.typesafe.scalalogging.log4j.Logging
import java.net.InetSocketAddress

trait Responseu

trait ServiceLike extends Actor with Logging {
  val serverName:String
  val port:Int

  type MessageHandler = PartialFunction[Packet, Option[Response]]

  def handleMessage:(p:Packet)


  val state = IO.IterateeRef.Map.async[IO.Handle]()(context.dispatcher)

  var handle:Option[IO.SocketHandle] = None
  val address = new InetSocketAddress(serverName, port)

  override def preStart {
    p("Connecting to: " + address)
    IOManager(context.system).connect(address)
  }

  def handlePacket(packet:Packet) = {
    p("Recieved: " + packet.toString)
    Some(packet)
  }

  def p(msg:String):Unit = println(id + ": " + msg)
  def p(msg:ByteString):Unit = p(msg.toString)
  def p(msg:Packet):Unit = p(msg.toString)

  def receive = {
    case IO.Connected(socket, address) =>
      p("Connected")
      handle = Some(socket)

      state(socket).flatMap { _ =>
        IO.repeat {
          for {
            headerBytes <- IO.take(12)
            size = headerBytes.takeRight(4).asByteBuffer.getInt
            bodyBytes <- IO.take(size)
          } yield {
            for (header <- PacketHeader.parse(headerBytes)) {
              handlePacket(new Packet(header, bodyBytes))
            }
          }
        }
      }

    case IO.Read(socket, bytes) =>
      state(socket)(IO.Chunk(bytes))

    case IO.Closed(socket, cause) =>
      state(socket)(IO.EOF)
      state -= socket
      handle = None

    case packet:Packet =>
      handle foreach { socket => 
        p("Sending: " + packet.toByteString)
        socket.write(packet.toByteString)
      }
  }
}

