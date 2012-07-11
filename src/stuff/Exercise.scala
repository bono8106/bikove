package stuff

object Exercise extends App {

  val y = 1 :: 2 :: 3 :: Nil

  val z = Nil :+ 1

  val zz = 1 +: 2 +: 3 +: Nil

  println(zz)

  def reverse[T](list: List[T]): List[T] = list match {
    case h :: tail => reverse(tail) :+ h // lamely inefficient
    case Nil => Nil
  }

  def reverse2[T](list: List[T], carry: List[T] = Nil): List[T] = list match {
    case h :: tail => reverse2(tail, h :: carry)
    case Nil => carry
  }

  println(reverse(y))
  println(reverse2(y))

  trait Huge {
    def at(i: Int): String
  }

  class LargeOne extends Huge {
    def at(i: Int): String = if (i > 1000234123) null else i.toString.reverse
  }

  def lookup(word: String, h: Huge): Int = {
    var lo = 0
    var hi = Int.MaxValue

    while (lo <= hi) {
      val mid = lo + ((hi - lo)/2);

      val v = h.at(mid)

      if (v == null || v.compareTo(word) > 0) {
        hi = mid - 1
      } else if (v.compareTo(word) == 0) {
        return mid
      } else {
        lo = mid + 1
      }

    }
    return -1;
  }

}