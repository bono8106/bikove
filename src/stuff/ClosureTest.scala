package stuff

object ClosureTest extends App {
  var escaped: () => Unit = _

  def enclose(block: => Unit): Unit = {
    try {
      println("enclosure before")
      escaped = () => block
      block
      println("enclosure after")
    } finally {
      println("enclosure finally")
    }
  }

  def driver(): Unit = {
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

  driver()

  escaped()
}