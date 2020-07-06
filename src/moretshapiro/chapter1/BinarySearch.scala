package moretshapiro.chapter1

object BinarySearch {
  def binarySearch[T](a: List[T], last: Int, k: T)(implicit o: Ordering[T]): Int = {
    var l = 0
    var r = last
    while (l <= r) {
      val m = (l+r)/2
      o.compare(k, a(m)) match {
        case -1 => r = m - 1
        case 0  => return m
        case 1  => l = m + 1
      }
    }
    -1
  }
}

object BSTest extends App {
  import BinarySearch.binarySearch

  val i = binarySearch(List(1, 2, 4, 5, 9, 10), 5, 4)
  println(i)
}
