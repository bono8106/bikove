package stuff

object LazyTest extends App {
  class Whole {
    lazy val partA = new PartA(this)
    lazy val partB = new PartB(this)
  }

  class PartA(val whole: Whole) {
    lazy val y: whole.partB.Y.type = whole.partB.Y

    object X {
      override def toString = "object X"
    }
  }

  class PartB(val whole: Whole) {
    lazy val x: whole.partA.X.type = whole.partA.X

    object Y {
      override def toString = "object Y"
    }
  }

  val whole = new Whole

  println(whole.partA.y)
  println(whole.partB.x)
}