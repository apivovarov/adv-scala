package impl

/**
  */
object Appender {

  trait Appendable[T] {
    def zero: T

    def append(a: T, b: T): T

    def revAppend(a: T, b: T): T = append(b, a)
  }

  implicit object IntAppend extends Appendable[Int] {
    def zero = 0
    def append(a: Int, b: Int) = a + b
  }

  implicit object StrAppend extends Appendable[String] {
    def zero = ""
    def append(a: String, b: String) = a + b
  }

  def appendAll[T](xs: List[T])(implicit app: Appendable[T]) =
    xs.foldLeft(app.zero)(app.append)
}
