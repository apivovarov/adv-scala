package world

/**
  * Created by alexanderpivovarov on 5/3/16.
  */
class World {
  val fish1 = new Fish()
  val fish2 = new Fish()
  val fish3 = new Fish()
  val fish4 = new Fish()

  val cow1 = new Cow[Grass]()
  val cow2 = new Cow[Grass]()
  val cow3 = new Cow[Grass]()

  val fineCow1 = new Cow[FineGrass]

  val bird1 = new Bird()
  val bird2 = new Bird()
  val bird3 = new Bird()

  val grass1 = new Grass()
  val grass2 = new Grass()
  val grass3 = new Grass()

  val fineGrass1 = new FineGrass()
  val fineGrass2 = new FineGrass()

  val grain1 = new Grain()
  val grain2 = new Grain()
  val grain3 = new Grain()

  fish1.eat(fish2).eat(fish3)

  cow1.eat(grass1)
  fineCow1.eat(new FineGrass).eat(new FineGrass)

  bird1.eat(grain1).eat(grain2)

  // List[Animal[Grass with CanSwim with Grain]]
  val li = List(cow1, fish1, bird1)

  li.foreach {
    case b: Bird => println(b)
    case c: Cow[_] => println(c)
    case f: Fish => println(f)
    case _ => println("any")
  }

  val f1: Int => Int = (x: Int) => x + 3
  val f2: Long => Int = (x: Long) => (x + 3).toInt
  val f3: Double => Int = (x: Double) => (x + 3).toInt

  // List[(Long with Double with Int) => Int]
  val fLi = List(f1, f2, f3)

  // For optimization reason Int does not extend Long
  // So, Lower Bound result of Int :: List[Long] in Covar List is List[Anyval]
  val liInt = List[Int](1, 2, 3)
  val liLong = List[Long](1L, 2L, 3L)

  // result is List[List[Anyval]]
  val liCol = List(liInt, liLong)

  // result is List[Anyval] for both:
  val liLo = 1 :: liLong
  val liIn = 1L :: liInt

  val liGrass = List[Grass](grass1, grass2)
  val liGrain = List[Grain](grain1, grain2)

  val liFood = List(liGrass, liGrain)
  val liFood2 = new FineGrass() :: liGrass
  val liFood3 = new Grain() :: liGrass

  val a1 = List(new Cow[Grass], new Cow[FineGrass], new Bird)

  Factory.proc(cow1)
  Factory.proc(fineCow1)

  val ff: Grass => Cow[Grass] = (g: Grass) => cow1.eat(g)
}

trait Animal[A <: Animal[A, F], F <: Food] {
  def eat(food: F): A = {
    println(s"Just ate: $food")
    this.asInstanceOf[A]
  }
}

class Bird extends Animal[Bird, Grain]

class Cow[F <: Grass] extends Animal[Cow[F], F]

class Fish extends Animal[Fish, CanSwim] with CanSwim

trait Food

class Grain extends Food

class Grass extends Food

class FineGrass extends Grass

trait CanSwim extends Food

object Factory {
  def proc(cow: Cow[_]): Unit = {
    println(s"proc $cow")
  }
}
