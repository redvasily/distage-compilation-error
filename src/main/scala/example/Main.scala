package example

import distage._

class Greeter {
  def greet(): Unit = {
    println("Greetings")
  }
}

class GreetingsApp(greeter: Greeter) {
  def run(): Unit = {
    greeter.greet()
  }
}

object GreeterModule extends ModuleDef {
  make[Greeter]
  make[GreetingsApp]
}

object Main extends App {
  val injector = Injector()
  val plan = injector.plan(GreeterModule, GCMode.NoGC)
  val objects = injector.produceUnsafe(plan)
  val app = objects.get[GreetingsApp]
  app.run()
}