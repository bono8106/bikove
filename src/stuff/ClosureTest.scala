package stuff

object ClosureTest extends App {

  var escaped: Function0[Unit] = _

  def enclose(block: => Unit) {
    try {
      println("enclosure before")
      escaped = block _
      block
      println("enclosure after")
    } finally {
      println("enclosure finally")
    }
  }

  def driver {
    try {
      println("driver start")
      enclose {
        try {
          println("closure start")
          if (true) {
            return
          }
          println("closure end")
        } finally {
          println("closure finally")
        }
      }
      println("driver end")
    } finally {
      println("driver finally")
    }
  }

  driver

  escaped()

}