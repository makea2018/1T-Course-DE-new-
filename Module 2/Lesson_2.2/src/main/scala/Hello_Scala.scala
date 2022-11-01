object Hello_Scala {
  def main(args: Array[String]): Unit = {
    val phrase = "Hello, Scala!"

    // Результат а.i
    println(hello_Scala_1(phrase))
    // Результат а.ii
    println((hello_Scala_2(phrase)))
    // Результат а.iii
    println((hello_Scala_3(phrase)))
    // Результат а.iv
    println((hello_Scala_4(phrase)))
  }


  // Вывод строки задом наперед
  val hello_Scala_1 = (par_1:String) => par_1.reverse

  // Перевод строки в нижний регистр строки
  val hello_Scala_2 = (par_2:String) => par_2.toLowerCase

  //  удаляет символ!
  val hello_Scala_3 = (par_3: String) => par_3.init

  // добавляет в конец фразы «and goodbye python!»
  val hello_Scala_4 = (par_4: String) => par_4.init + " and goodbye python!"
}
