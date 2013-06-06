package org.conbere.gearman

class Arg0Matcher(magic:Magic, command:Command) {
  def apply() = Packet.build(magic, command)

  def unapply(packet:Packet) = {
    packet match {
      case Packet(`magic`, `command`, _) =>
        Some()
      case _ =>
        None
    }
  }
}

class Arg1Matcher(magic:Magic, command:Command) {
  def apply(arg1:String) =
    Packet.build(magic, command, List(arg1))

  def unapply(packet:Packet) = {
    packet match {
      case Packet(`magic`, `command`, List(arg1)) =>
        Some(arg1)
      case _ =>
        None
    }
  }
}

class Arg2Matcher(magic:Magic, command:Command) {
  def apply(arg1:String, arg2:String) =
    Packet.build(magic, command, List(arg1, arg2))

  def unapply(packet:Packet) = {
    packet match {
      case Packet(`magic`, `command`, List(arg1, arg2)) =>
        Some((arg1, arg2))
      case _ =>
        None
    }
  }
}

class Arg3Matcher(magic:Magic, command:Command) {
  def apply(arg1:String, arg2:String, arg3:String) =
    Packet.build(magic, command, List(arg1, arg2, arg3))

  def unapply(packet:Packet) = {
    packet match {
      case Packet(`magic`, `command`, List(arg1, arg2, arg3)) =>
        Some((arg1, arg2, arg3))
      case _ =>
        None
    }
  }
}

class Arg4Matcher(magic:Magic, command:Command) {
  def apply(arg1:String, arg2:String, arg3:String, arg4:String) =
    Packet.build(magic, command, List(arg1, arg2, arg3, arg4))

  def unapply(packet:Packet) = {
    packet match {
      case Packet(`magic`, `command`, List(arg1, arg2, arg3, arg4)) =>
        Some((arg1, arg2, arg3, arg4))
      case _ =>
        None
    }
  }
}

class Arg5Matcher(magic:Magic, command:Command) {
  def apply(arg1:String, arg2:String, arg3:String, arg4:String, arg5:String) =
    Packet.build(magic, command, List(arg1, arg2, arg3, arg4, arg5))

  def unapply(packet:Packet) = {
    packet match {
      case Packet(`magic`, `command`, List(arg1, arg2, arg3, arg4, arg5)) =>
        Some((arg1, arg2, arg3, arg4, arg5))
      case _ =>
        None
    }
  }
}
