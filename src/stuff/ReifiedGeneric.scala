package stuff

object ReifiedGeneric extends App {
  object Generics {
    class Holder[@specialized T](private var value: T) {
      def get: T = value

      def set(n: T): Unit = { value = n }
    }
  }

  object VirtualTypes {
    class Holder {
      type T <: AnyVal

      private var value: T = _

      def get: T = value

      def set(n: T): Unit = { value = n }
    }
  }

  {
    val test = new Generics.Holder[Int](0)
    test.set(1)
    println(test.get)
  }

  {
    val test = new VirtualTypes.Holder { type T = Int }
    test.set(0)
    println(test.get)
  }
}