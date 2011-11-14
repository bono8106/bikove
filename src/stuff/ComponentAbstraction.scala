package stuff

object ComponentAbstraction extends App {

  abstract class SubjectObserver {
    type S <: Subject
    type O <: Observer

    abstract class Subject {
      _: S =>

      private var observers: List[O] = List()

      def subscribe(obs: O) =
        observers = obs :: observers

      def publish =
        for (val obs <- observers)
          obs.notify(this)
    }

    abstract class Observer {
      def notify(sub: S): Unit
    }
  }

  lazy val x = "hello"

  println(x)

  for (x <- List(1, 2, 3) if x % 2 == 0) println(x);

  """
  Hello
  """

  val z = classOf[App]

}