package org.conbere.gearman

import akka.util.ByteString
import java.nio.ByteBuffer

trait Command {
  val value:Int

  def toByteString = {
    val buffer = ByteBuffer.allocate(4).putInt(value)
    buffer.rewind
    ByteString(buffer)
  }
}

object Command {
  case object CAN_DO extends Command { val value = 1 }
  case object CANT_DO extends Command { val value = 2 }
  case object RESET_ABILITIES extends Command { val value = 3 }
  case object PRE_SLEEP extends Command { val value = 4 }
  case object NOOP extends Command { val value = 6 }
  case object SUBMIT_JOB extends Command { val value = 7 }
  case object JOB_CREATED extends Command { val value = 8 }
  case object GRAB_JOB extends Command { val value = 9 }
  case object NO_JOB extends Command { val value = 10 }
  case object JOB_ASSIGN extends Command { val value = 11 }
  case object WORK_STATUS extends Command { val value = 12 }
  case object WORK_COMPLETE extends Command { val value = 13 }
  case object WORK_FAIL extends Command { val value = 14 }
  case object GET_STATUS extends Command { val value = 15 }
  case object ECHO_REQ extends Command { val value = 16 }
  case object ECHO_RES extends Command { val value = 17 }
  case object SUBMIT_JOB_BG extends Command { val value = 18 }
  case object ERROR extends Command { val value = 19 }
  case object STATUS_RES extends Command { val value = 20 }
  case object SUBMIT_JOB_HIGH extends Command { val value = 21 }
  case object SET_CLIENT_ID extends Command { val value = 22 }
  case object CAN_DO_TIMEOUT extends Command { val value = 23 }
  case object ALL_YOURS extends Command { val value = 24 }
  case object WORK_EXCEPTION extends Command { val value = 25 }
  case object OPTION_REQ extends Command { val value = 26 }
  case object OPTION_RES extends Command { val value = 27 }
  case object WORK_DATA extends Command { val value = 28 }
  case object WORK_WARNING extends Command { val value = 29 }
  case object GRAB_JOB_UNIQ extends Command { val value = 30 }
  case object JOB_ASSIGN_UNIQ extends Command { val value = 31 }
  case object SUBMIT_JOB_HIGH_BG extends Command { val value = 32 }
  case object SUBMIT_JOB_LOW extends Command { val value = 33 }
  case object SUBMIT_JOB_LOW_BG extends Command { val value = 34 }
  case object SUBMIT_JOB_SCHED extends Command { val value = 35 }
  case object SUBMIT_JOB_EPOCH extends Command { val value = 36 }

  val commandList = List(
    CAN_DO,
    CANT_DO,
    RESET_ABILITIES,
    PRE_SLEEP,
    NOOP,
    SUBMIT_JOB,
    JOB_CREATED,
    GRAB_JOB,
    NO_JOB,
    JOB_ASSIGN,
    WORK_STATUS,
    WORK_COMPLETE,
    WORK_FAIL,
    GET_STATUS,
    ECHO_REQ,
    ECHO_RES,
    SUBMIT_JOB_BG,
    ERROR,
    STATUS_RES,
    SUBMIT_JOB_HIGH,
    SET_CLIENT_ID,
    CAN_DO_TIMEOUT,
    ALL_YOURS,
    WORK_EXCEPTION,
    OPTION_REQ,
    OPTION_RES,
    WORK_DATA,
    WORK_WARNING,
    GRAB_JOB_UNIQ,
    JOB_ASSIGN_UNIQ,
    SUBMIT_JOB_HIGH_BG,
    SUBMIT_JOB_LOW,
    SUBMIT_JOB_LOW_BG,
    SUBMIT_JOB_SCHED,
    SUBMIT_JOB_EPOCH)

  val commands = commandList.map{ c:Command => (c.value, c) }.toMap
}
