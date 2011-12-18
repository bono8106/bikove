package stuff

package actor {

  class ActorRef(val s: String)

}

package object actor {

  implicit def anyRef2Askable(o: ActorRef) = new dispatch.Askable(o)

}

package dispatch {

  import actor.ActorRef

  class Askable(private val o: ActorRef) {
    def ?(message: String)(implicit timeout: Int): Unit = println(o.s + " ! " + message + " / " + timeout)
    def ?(message: String, timeout: Int)(implicit x: Long = 0): Unit = ?(message)(timeout)
  }

}

object ImplicitTwoLevel extends App {

  //import actor.ActorRef
  //import dispatch.Askable

  implicit def defaultTimeout = 42

  // import of actor._ or actor.anyRef2Askable not needed.
  // package-level implicit is automatically visible for ActorRef receivers
  new actor.ActorRef("hello") ? ("world", 10)


  new actor.ActorRef("hello") ? "world"

}