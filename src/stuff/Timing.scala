package stuff

object Timing extends App {

  val S = 1
  val M = 60 * S
  val H = 60 * M
  val D = 24 * H
  val W = 7 * D

  val U = List(
    (W, "week"),
    (D, "day"),
    (H, "hour"),
    (M, "minute"),
    (S, "second")
  )

  def pluralize(n: Int, l: String): String = n match {
    case n if n > 1 => s"$n ${l}s "
    case 1 => s"1 ${l} "
    case 0 => ""
  }

  def ago(s: Int): String =
    U.scanLeft((0, s, ""))((a, b) => (a._2 / b._1, a._2 % b._1, b._2)).
      flatMap(e => pluralize(e._1, e._3)).
      mkString("", "", "ago")

  def ago2(s: Int): String = s match {
    case 0 => val (_, label) = U.last; s"0 ${label}s"
    case _ =>
      var n = s
      (for (tt <- U) yield {
        val (unit, label) = tt
        val v = n / unit
        n = n % unit
        pluralize(v, label)
      }).flatten.mkString
  }

  println(ago(0))
  println(ago(1))
  println(ago(30))
  println(ago(60))
  println(ago(61))
  println(ago(76))
  println(ago(121))
  println(ago(3600))
  println(ago(3602))
  println(ago(7294))
  println(ago(2 * W + 2 * H + 5 * S))
}
