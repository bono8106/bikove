package threewaymerge

object ThreeWayMergeScala extends App {
  val input1 = Array[Int](1, 3, 5)
  val input2 = Array[Int](2, 4)
  val input3 = Array[Int](0, 6, 7, 10)

  val res = sortn(input1, input2, Array[Int](), input3, input2, input3)
  for (i <- res) {
    println(i)
  }

  def sortn(input: Array[Int]*): Array[Int] = {
    val len = input.map(_.length).sum

    val result = new Array[Int](len)

    val ii = new Array[Int](input.length)
    for (i <- result.indices) {
      var min = Int.MaxValue
      var ni = -1
      for (n <- ii.indices) {
        if (ii(n) < input(n).length && input(n)(ii(n)) < min) {
          min = input(n)(ii(n))
          ni = n
        }
      }
      assert(ni != -1)
      result(i) = min
      ii(ni) += 1
    }
    result
  }
}