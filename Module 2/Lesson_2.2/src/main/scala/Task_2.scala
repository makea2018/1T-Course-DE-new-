import scala.collection.mutable.ListBuffer

object Task_2 {
  // Глобальные переменные
  var new_list = new ListBuffer[Int]

  /*В данном объекте представлены решения на задачи "b", "c", "d", "e", "f", "g"*/
  def main(args: Array[String]): Unit = {
    // Задача "b"
    println("Задача \"b\"")
    task_b(1200000, 20, 100000, 13)
    println("============================================")
    // Задача "c"
    val list_1 = List[Float](100, 150, 200, 80, 120, 75)
    println("Задача \"c\"")
    task_c(list_1)
    println("============================================")
    // Задача "d"
    println("Задача \"d\"")
    task_d(list_1, 22)
    println("============================================")
    // Задача "e"
    println("Задача \"e\"")
    task_e(350, 90)
    println("============================================")
    // Задача "f"
    println("Задача \"f\"")
    task_f(130, new_list)
    println("============================================")
    // Задача "g"
    println("Задача \"g\"")
    task_g(new_list, 130)
    println("============================================")
    // Задача "h"
    println("Задача \"h\"")
    task_h(new_list, 7)
    println("============================================")
    // Задача "i"
    println("Задача \"i\"")
    task_i(new_list, 110, 220, 320)
    println("============================================")
    // Задача "k"
    println("Задача \"k\"")
    task_k()
    println("============================================")

  }
  // ==============================================================================
  // Функция для задачи "b"
  /*Напишите программу, которая вычисляет ежемесячный оклад сотрудника
    после вычета налогов. На вход вашей программе подается значение годового
    дохода до вычета налогов, размер премии – в процентах от годового дохода и
    компенсация питания*/
  val task_b = (annual_sal: Int, premium: Int, comp_power: Int, taxes: Int) => {
    val full_annual_salary = annual_sal + ((premium / 100) * annual_sal) + comp_power
    val full_taxes = (taxes / 100) * full_annual_salary
    val month_salary = (full_annual_salary - full_taxes) / 12
    println (s"Ежемесячный оклад = $month_salary руб")
  }
  // =====================================================================================
  // Функция для задачи "c"
  /* Напишите программу, которая рассчитывает для каждого сотрудника отклонение
  (в процентах)от среднего значения оклада на уровень всего отдела. В итоговом
  значении должно учитываться в большую или меньшую сторону отклоняется размер оклада.
  На вход вышей программе подаются все значения, аналогичные предыдущей программе, а также
  список со значениями окладов сотрудников отдела 100, 150, 200, 80, 120, 75*/
  val task_c = (office: List[Float]) => {
    val mean: Float = office.sum / office.size
    println(s"$mean - средний оклад")
    for (el <- 0 to office.length - 1) {
      if (office(el) == mean) println(s"Оклад ${el + 1} сотрудника равен значению среднего оклада")
      else if (office(el) > mean) {
        val deviation = 100 - (office(el) / mean) * 100
        println(s"Оклад ${el + 1} сотрудника больше на ${f"${deviation.abs}%.2f"} % среднего оклада")
      }
      else {
        val deviation = 100 - (office(el) / mean) * 100
        println(s"Оклад ${el + 1} сотрудника меньше на ${f"$deviation%.2f"} % среднего оклада")
      }
    }
  }
  // =====================================================================================
  // Функция для задачи "d"
  /* Попробуйте рассчитать новую зарплату сотрудника, добавив(или отняв, если сотрудник плохо себя вел)
  необходимую сумму с учетом результатов прошлого задания. Добавьте его зарплату в список и вычислите значение
  самой высокой зарплаты и самой низкой.*/
  val task_d = (prev_list: List[Float], s: Int) => {
    for (el <- 0 to prev_list.length - 1) {
      if (prev_list(el) <= 130) {
        val element = (prev_list(el) + s).toInt
        println(s"Сотрудник ${el + 1} получает бонус в размере ${s} тыс.\nНовый оклад = ${element} тыс.р в месяц")
        new_list += element
      }
      else {
        val element = (prev_list(el) - s).toInt
        println(s"Сотрудник ${el + 1} оштрафован в размере ${s} тыс.\nНовый оклад = ${element} тыс.р в месяц")
        new_list += element
      }
    }
    println(new_list.toList)
    println(s"Max. оклад = ${new_list.max} тыс.руб\nMin. оклад = ${new_list.min} тыс.руб")
  }
  // =====================================================================================
  // Функция для задачи "e"
  /* Также в вашу команду пришли два специалиста с окладами 350 и 90 тысяч рублей.
  Попробуйте отсортировать список сотрудников по уровню оклада от меньшего к большему.*/
  val task_e = (worker_1: Int, worker_2: Int) => {
    new_list += worker_1
    new_list += worker_2
    new_list = new_list.sorted
    println(new_list)
  }
  // =====================================================================================
  // Функция для задачи "f"
  /* Кажется, вы взяли в вашу команду еще одного сотрудника и предложили ему оклад 130 тысяч. Вычислите самостоятельно
  номер сотрудника в списке так, чтобы сортировка не нарушилась и добавьте его на это место.*/
  val task_f = (worker_3: Int, list: ListBuffer[Int]) => {
    val list_p_1 = list.slice(0, 5) :+ worker_3
    val list_p_2 = list.slice(5, list.length)
    val list_result = list_p_1 ++ list_p_2
    new_list = list_result
    println(new_list)
  }
  // =====================================================================================
  // Функция для задачи "g"
  /* Попробуйте вывести номера сотрудников из полученного списка, которые попадают под категорию middle.
  На входе программе подается «вилка» зарплаты специалистов уровня middle.*/
  val task_g = (list: ListBuffer[Int], middle_salary: Int) => {
    for (el <- 0 until list.length) {
      if (list(el) >= middle_salary) println(s"$el сотрудник попадает под категорию \"Middle\"")
    }
  }
  // =====================================================================================
  // Функция для задачи "h"
  /* Однако наступил кризис и ваши сотрудники требуют повысить зарплату.
  Вам необходимо проиндексировать зарплату каждого сотрудника на уровень инфляции – 7% .*/
  val task_h = (list: ListBuffer[Int], inflation: Double) => {
    val inflation_new = (1 + (inflation / 100))
    val list_1 = list.map(el => el.toDouble)
    val list_2 = list_1.map(el => f"${el * inflation_new}%.1f")
    println(list_2)
  }
  // =====================================================================================
  // Функция для задачи "i"
  /* Ваши сотрудники остались недовольны и просят индексацию на уровень рынка.
  Попробуйте повторить ту же операцию, как и в предыдущем задании, но теперь вам нужно
  проиндексировать зарплаты на процент отклонения от среднего по рынку с учетом уровня специалиста.
  На вход вашей программе подается 3 значения – среднее значение зарплаты на рынке
  для каждого уровня специалистов(junior, middle и senior)*/
  val task_i = (list: ListBuffer[Int],junior_salary: Int, middle_salary: Int, senior_salary: Int) => {
    println("Исходный список:")
    println(list)
    val prof_list = List.apply("junior", "middle", "senior")
    val dict = collection.mutable.Map[Int, String]()
    for (el_1 <- list) {
      if (el_1 < 130) {
        dict += (el_1 -> prof_list(0))
      }
      else if (el_1 >= 130 && el_1 <= 200) {
        dict += (el_1 -> prof_list(1))
      }
      else if (el_1 > 200) {
        dict += (el_1 -> prof_list(2))
      }
    }
    val dict_final = dict.toSeq.sortBy(_._1)

    // Выводим словарь
    println("Отсортированный словарь: ")
    println(dict_final)

    // Создаем словарь, проиндексированный! И выводим результат
    val dict_indexed = collection.mutable.Map[Double, String]()

    // Находим средние значения окладов у junior, middle, senior
    var sum_jun_salary:Double = 0
    var sum_mid_salary:Double = 0
    var sum_sen_salary:Double = 0

    var amount_juns = 0
    var amount_middle = 0
    var amount_senior = 0

    for ((a, b) <- dict_final) {
      if (b == "junior") {
        sum_jun_salary += a
        amount_juns += 1
      }
      else if (b == "middle") {
        sum_mid_salary += a
        amount_middle += 1
      }
      else if (b == "senior") {
        sum_sen_salary += a
        amount_senior += 1
      }
    }

    val mean_jun_salary = sum_jun_salary / amount_juns
    val mean_mid_salary = sum_mid_salary / amount_middle
    val mean_sen_salary = sum_sen_salary / amount_senior

    // Определяем отклонения наших средних зарплат от средних значений зарплат по рынку
    val deviation_junior = junior_salary / mean_jun_salary
    val deviation_middle = middle_salary / mean_mid_salary
    val deviation_senior = senior_salary / mean_sen_salary

    for ((i, j) <- dict_final) {
      var a:Double = 0
      var b:String = ""
      if (j == "junior" && i <= junior_salary) {
        a = BigDecimal(deviation_junior * i).setScale(1, BigDecimal.RoundingMode.HALF_DOWN).toDouble
        b = j
        dict_indexed += (a -> b)
      }
      else if (j == "middle" && i <= middle_salary) {
        a = BigDecimal(deviation_middle * i).setScale(1, BigDecimal.RoundingMode.HALF_DOWN).toDouble
        b = j
        dict_indexed += (a -> b)
      }
      else if (j == "senior" && i <= senior_salary) {
        a = BigDecimal(deviation_senior * i).setScale(1, BigDecimal.RoundingMode.HALF_DOWN).toDouble
        b = j
        dict_indexed += (a -> b)
      }
      else {
        dict_indexed += (i.toDouble -> j)
      }
    }
    println("Проиндексированный словарь:")
    val dict_indexed_final = dict_indexed.toSeq.sortBy(_._1)
    println(dict_indexed_final)
  }
  // =====================================================================================
  // Задача "j" решена в объекте "Task_j_Parser"!
  // Единственно, не понимаю, как могу импортировать данные спарсенных зарплат из объекта "Task_j_Parser" в данный объект Task_2

  // =====================================================================================
  // Функция для задачи "k"
  /* Попробуйте деанонимизировать ваших сотрудников – составьте структуру,
  которая позволит иметь знания о том, сколько зарабатывает каждый сотрудник(Фамилия и имя)*/
  val task_k = () => {

  }
  // =====================================================================================
}
