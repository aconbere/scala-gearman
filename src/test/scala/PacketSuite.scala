import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import akka.util.{ ByteStringBuilder, ByteString }

import org.conbere.gearman._

@RunWith(classOf[JUnitRunner])
class PacketSuite extends FunSuite {
  test("Packet.build") {
    val p = Packet.build(Magic.REQ, Command.CAN_DO, List("reverse"))
    assert(Magic.REQ === p.header.magic)
    assert(Command.CAN_DO === p.header.command)
    assert(7 === p.header.size)
    assert(ByteString("reverse") === p.body)

    val p2 = Packet.build(Magic.REQ, Command.SUBMIT_JOB, List("reverse", "test"))
    assert(Magic.REQ === p2.header.magic)
    assert(Command.SUBMIT_JOB === p2.header.command)
    assert(12 === p2.header.size)
    assert((new ByteStringBuilder ++=
                ByteString("reverse") ++=
                ByteString(0.toByte) ++=
                ByteString("test")).result === p2.body)
  }

  test("Packet.toString") {
    val p2 = Packet.build(Magic.REQ, Command.SUBMIT_JOB, List("reverse", "test"))
    assert(p2.toString === "REQ,7,12:reverse,test")
  }

  test("ByteStringHelper.split") {
    val b = (new ByteStringBuilder ++=
        ByteString("abcd") ++=
        ByteString(0.toByte) ++=
        ByteString("efgh")).result

    val splits = ByteStringHelper.split(0.toByte)(b)
    splits match {
      case List(left, right) =>
        assert(ByteString("abcd") === left)
        assert(ByteString("efgh") === right)
      case _ => assert(false)
    }
  }


}
