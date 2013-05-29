package org.conbere.gearman

import akka.util.{ ByteString, ByteStringBuilder }
import java.nio.ByteBuffer

// This is what it's called in gearman lingo
// it identifies the packet as either a Request or Response,
// \0REQ and \0RES repspectively
trait Magic {
  val value:String
  def toByteString =
    (new ByteStringBuilder ++= ByteString(0.toByte) ++= ByteString(value)).result
}

object Magic {
  case object RES extends Magic { val value = "RES" }
  case object REQ extends Magic { val value = "REQ" }

  val values = Map[String,Magic](
    "RES" -> RES,
    "REQ" -> REQ
  )
}

// a Packet begins with a 12 byte header
// composed of three 4 byte sections
// 1. magic (\0RES or \0REQ)
// 2. a type (or command here to avoid scala name conflicts)
// 3. the size of the remaining body
class PacketHeader(
  val magic:Magic,
  val command:Command,
  val size:Int
) {
  val hasBody = size > 0

  def intToBuffer(i:Int) = {
    val buffer = ByteBuffer.allocate(4).putInt(i)
    buffer.rewind
    buffer
  }

  def toByteString =
    (new ByteStringBuilder ++=
      magic.toByteString ++=
      command.toByteString ++=
      ByteString(intToBuffer(size))).result

  override def toString =
    magic.value + "," + command.value + "," + size
}

object PacketHeader {
  def parse(str:ByteString) = {
    for {
      magic <- Magic.values.get(str.slice(1, 4).decodeString("ASCII"))
      command <- Command.commands.get(str.slice(4, 8).asByteBuffer.getInt)
    } yield {
      val size = str.slice(8, 12).asByteBuffer.getInt
      new PacketHeader(magic, command, size)
    }
  }
}

object ByteStringHelper {
  def splitAt(p:(Byte => Boolean))(bytes:ByteString) = {
    def inner(acc:List[ByteString], bs:ByteString):List[ByteString] = {
      if (bs.isEmpty) {
        acc
      } else {
        val left = bs.takeWhile(! p(_))
        val right = bs.drop(left.length + 1)

        inner(acc :+ left, right)
      }
    }
    inner(List(), bytes)
  }

  def split(b:Byte) = splitAt(_.equals(b)) _
}

class Packet(
  val header: PacketHeader,
  val body: ByteString
) {
  def this(header: PacketHeader) = this(header, ByteString(""))

  def toByteString =
    (new ByteStringBuilder ++=
      header.toByteString ++=
      body).result

  override def toString =
    header.toString + ":" + arguments.map(_.decodeString("UTF-8")).mkString(",")

  val sep = 0.toByte
  val arguments = ByteStringHelper.split(sep)(body)
}

object Packet {
  def build(magic:Magic, command:Command, body:List[String] = List()) = {
    // we start with a list of strings which are aguments to be passed along
    // we first interlace them with \0 bytes
    val interlacedBody = body.flatMap(s => List(ByteString(s), ByteString(0.toByte)))

    // drop the last one since it doesn't belong
    // and concatonate them into a full bytestring
    val bodyBytes = interlacedBody
      .take(interlacedBody.length - 1)
      .foldLeft(new ByteStringBuilder)((acc, i) => acc ++= i)
      .result
    new Packet(new PacketHeader(magic, command, bodyBytes.length), bodyBytes)
  }

  def unapply(packet:Packet) =
    Some(packet.header.magic, packet.header.command, packet.arguments)
}
