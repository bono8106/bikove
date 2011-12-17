package stuff

/**
 * Demo the utility of self types in Scala, which can be used for
 * applying type-safe before, after and around method advice,
 * or dependency injection.
 */
object SelfTypes extends App {

  trait Required {
    _: Composable =>

    class Extendable {
      def n: String = "joe"
    }

    def x: Int = 1
    def xx = x
  }

  class Composable {
    _: Required =>

    def y = 3

    def z = x + y + 2

    class Sub extends this.Extendable {
      override def n = "hi " + super.n
    }

  }

  //val obj0 = new Composable // does not compile - Composable does not conform to its self type

  val obj = new Composable with Required { override def x = 10 }
  println(obj.z) // prints 15
  println(obj.x) // prints 10

  val obj2: Composable = new Composable with Required { override def x = 20 }
  println(obj2.z) // prints 25
  //println(obj2.xx) // does not compile

  val obj3 = new Composable with Required
  println(obj3.z) // prints 6
  println(obj3.x) // prints 1
  println((new obj3.Sub).n) // prints "hi joe"

  val obj4 = new Composable with Required { override def x = super.x + 2 }
  println(obj4.z) // prints 8

  val obj5 = new Composable with Required {
    class Extendable { def n = "peter" }
  }
  println((new obj5.Sub).n) // prints "hi joe" because no virtual classes

}
