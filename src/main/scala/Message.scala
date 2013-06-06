package org.conbere.gearman

object Messages {
  object Worker {
    val CAN_DO = new Arg1Matcher(Magic.REQ, Command.CAN_DO)
    val CAN_DO_TIMEOUT = new Arg2Matcher(Magic.REQ, Command.CAN_DO_TIMEOUT)
    val CANT_DO = new Arg1Matcher(Magic.REQ, Command.CANT_DO)
    val RESET_ABILITIES = new Arg1Matcher(Magic.REQ, Command.RESET_ABILITIES)
    val GRAB_JOB = new Arg0Matcher(Magic.REQ, Command.GRAB_JOB)
    val GRAB_JOB_UNIQ = new Arg0Matcher(Magic.REQ, Command.GRAB_JOB_UNIQ)

    val NOOP = new Arg0Matcher(Magic.RES, Command.NOOP)
    val PRE_SLEEP = new Arg0Matcher(Magic.REQ, Command.PRE_SLEEP)
    val NO_JOB = new Arg0Matcher(Magic.RES, Command.NO_JOB)

    val WORK_STATUS    = new Arg3Matcher(Magic.REQ, Command.WORK_STATUS)
    val WORK_COMPLETE  = new Arg2Matcher(Magic.REQ, Command.WORK_COMPLETE)
    val WORK_EXCEPTION = new Arg2Matcher(Magic.REQ, Command.WORK_EXCEPTION)
    val WORK_DATA      = new Arg2Matcher(Magic.REQ, Command.WORK_DATA)
    val WORK_WARNING   = new Arg2Matcher(Magic.REQ, Command.WORK_WARNING)
    val WORK_FAIL      = new Arg1Matcher(Magic.REQ, Command.WORK_FAIL)

    val ECHO_REQ = new Arg1Matcher(Magic.REQ, Command.ECHO_REQ)
    val ECHO_RES = new Arg1Matcher(Magic.RES, Command.ECHO_RES)
    val ERROR = new Arg1Matcher(Magic.RES, Command.ERROR)
    val ALL_YOURS = new Arg0Matcher(Magic.REQ, Command.ALL_YOURS)

    val OPTION_REQ = new Arg1Matcher(Magic.REQ, Command.OPTION_REQ)
    val OPTION_RES = new Arg1Matcher(Magic.RES, Command.OPTION_RES)

    val JOB_ASSIGN = new Arg3Matcher(Magic.RES, Command.JOB_ASSIGN)
    val JOB_ASSIGN_UNIQ = new Arg4Matcher(Magic.RES, Command.JOB_ASSIGN_UNIQ)
  }

  object Client {
    val SUBMIT_JOB = new Arg3Matcher(Magic.REQ, Command.SUBMIT_JOB)
    val SUBMIT_JOB_BG = new Arg3Matcher(Magic.REQ, Command.SUBMIT_JOB_BG)
    val SUBMIT_JOB_HIGH_BG = new Arg3Matcher(Magic.REQ, Command.SUBMIT_JOB_HIGH_BG)
    val SUBMIT_JOB_LOW = new Arg3Matcher(Magic.REQ, Command.SUBMIT_JOB_LOW)
    val SUBMIT_JOB_LOW_BG = new Arg3Matcher(Magic.REQ, Command.SUBMIT_JOB_LOW_BG)
    val SUBMIT_JOB_SCHED = new Arg3Matcher(Magic.REQ, Command.SUBMIT_JOB_SCHED)
    val SUBMIT_JOB_EPOCH = new Arg3Matcher(Magic.REQ, Command.SUBMIT_JOB_EPOCH)

    val WORK_STATUS = new Arg3Matcher(Magic.RES, Command.WORK_STATUS)
    val WORK_COMPLETE = new Arg3Matcher(Magic.RES, Command.WORK_COMPLETE)
    val WORK_FAIL = new Arg3Matcher(Magic.RES, Command.WORK_FAIL)
    val WORK_EXCEPTION = new Arg2Matcher(Magic.RES, Command.WORK_EXCEPTION)
    val WORK_DATA = new Arg2Matcher(Magic.RES, Command.WORK_DATA)
    val WORK_WARNING = new Arg2Matcher(Magic.RES, Command.WORK_WARNING)

    val JOB_CREATED = new Arg1Matcher(Magic.RES, Command.JOB_CREATED)
    val GET_STATUS = new Arg1Matcher(Magic.RES, Command.GET_STATUS)
    val ECHO_REQ = new Arg1Matcher(Magic.REQ, Command.ECHO_REQ)
    val ECHO_RES = new Arg1Matcher(Magic.RES, Command.ECHO_RES)
    val ERROR = new Arg1Matcher(Magic.RES, Command.ERROR)
    val STATUS_RES = new Arg5Matcher(Magic.RES, Command.STATUS_RES)
    val SET_CLIENT_ID = new Arg1Matcher(Magic.REQ, Command.SET_CLIENT_ID)

    val OPTION_REQ = new Arg1Matcher(Magic.REQ, Command.OPTION_REQ)
    val OPTION_RES = new Arg1Matcher(Magic.RES, Command.OPTION_RES)
  }
}
