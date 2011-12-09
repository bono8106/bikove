package scoping

object SubSup extends App {

  class Sup {
    def sqrt(x: Int) = x*x
  }

  import java.lang.Math.sqrt

  class Sub extends Sup {
    sqrt(10)
  }

}