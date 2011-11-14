package stuff

/**
 * Demo the utility of self types in Scala, which can be used for
 * applying type-safe before, after and around method advice,
 * or dependency injection.
 */
object SelfTypes extends App {

  trait Required {
    def x: Int = 1
  }

  class Composable {
    _: Required =>

    def y = 3

    def z = x + y + 2

  }

  //val obj0 = new Composable // does not compile - Composable does not conform to its self type

  val obj = new Composable with Required { override def x = 10 }
  println(obj.z) // prints 15

  val obj2 = new Composable with Required { override def x = 20 }
  println(obj2.z) // prints 25

  val obj3 = new Composable with Required
  println(obj3.z) // prints 6

  val obj4 = new Composable with Required { override def x = super.x + 2 }
  println(obj4.z) // prints 8


}