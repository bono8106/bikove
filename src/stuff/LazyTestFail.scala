package stuff

object LazyTestFail extends App {

  class Whole {
    lazy val partA = new PartA(this)
    lazy val partB = new PartB(this)
  }

  class PartA(val whole: Whole) {
    val y = whole.partB.Y

    object X {
      override def toString = "object X"
    }
  }

  class PartB(val whole: Whole) {
    val x = whole.partA.X

    object Y {
      override def toString = "object Y"
    }
  }

  val whole = new Whole

  println(whole.partA.y)
  println(whole.partB.x)


}