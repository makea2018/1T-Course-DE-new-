import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import collection.mutable.ListBuffer

object Task_j_Parser {
  // Task "j" решена в данном объекте

  // Global variables
  val browser = JsoupBrowser()
  var salaries_junior = new ListBuffer[Int]
  var salaries_middle = new ListBuffer[Int]
  var salaries_senior = new ListBuffer[Int]

  def main(args: Array[String]): Unit = {
    // Получаем данные по зарплатам с помощью парсера
    get_data("Junior", 5, salaries_junior)
    get_data("Middle", 5, salaries_middle)
    get_data("Senior Programmer", 5, salaries_senior)

  }


  // Парсим данные со страницы
  val get_data = (position: String, max_pages: Int, salaries_list: ListBuffer[Int]) => {
    var num = 0
    for (page <- 1 to max_pages){
      val doc = browser.get(s"https://hh.ru/search/vacancy?no_magic=true&L_save_area=true&text=$position&excluded_text=&area=113&salary=&currency_code=RUR&only_with_salary=true&experience=doesNotMatter&order_by=relevance&search_period=0&items_on_page=100&page=$page&hhtmFrom=vacancy_search_list")
      val list = doc >> elementList("div .vacancy-serp-item__layout") >> text ("[data-qa=vacancy-serp__vacancy-compensation]")
      for (el <- list) {
        val nu = new ListBuffer[Char]
        for (ch <- el) {
          if(ch.isDigit) {
            nu += ch
          }
        }
        if (nu.length >= 6) {
          val nu_new  = nu.slice(0, 6).mkString("")
          if (nu_new.last != '0') {
            val nu_n = nu_new.init.toInt
            salaries_list.append(nu_n)
            //print(nu_n)
          }
          else {
            salaries_list.append(nu_new.toInt)
            //print(nu_new)
          }
        }
        else {
          val nu_new = nu.mkString("").toInt
          salaries_list.append(nu_new)
          //print(nu_new)
        }
        num += 1
        //print("\n")
      }
      Thread.sleep(1000)
    }
    println(s"Список зарплат $position:\n${salaries_list}\nДлина списка: ${salaries_list.length}")
  }
}
