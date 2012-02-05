package threewaymerge

object ThreeWayMergeScalaIterators extends App {

  val input1 = Array[Int](1, 3, 5)
  val input2 = Array[Int](2, 4)
  val input3 = Array[Int](0, 6, 7, 10)

  val res = sortn(input1, input2, Array[Int](), input3, input2, input3);
  for (i <- res) {
    println(i)
  }

  def sortn(input: Array[Int]*): Array[Int] = {
    val len = input map(_.length) sum

    val result = new Array[Int](len)

    val ii = input map (_.toStream) toArray;
    for (i <- 0 until result.length) {
      var min = Int.MaxValue
      var ni = -1
      for (n <- 0 until ii.length) {
        if (!ii(n).isEmpty && ii(n).head < min) {
          min = ii(n).head
          ni = n
        }
      }
      assert(ni != -1)
      result(i) = min
      ii(ni) = ii(ni).tail
    }
    return result;
  }

}