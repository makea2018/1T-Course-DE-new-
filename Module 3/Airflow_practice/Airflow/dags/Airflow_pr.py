from datetime import datetime
from airflow import DAG
from airflow.operators.bash import BashOperator
from airflow.operators.python_operator import PythonOperator
from airflow.models import Variable
import pandas as pd
from random import randint
import csv

# Я выполнил часть заданий старых и решил выполнить новые задания в старом DAG-е
# Поэтому старые таски названы old_(№ task) а новые new_(№ task) (Касается только п. "a" и "b")!
# Остальные называются просто task_(№ task)
# Functions

# Previous tasks
# 12_a
def csv_procedure(ti):
    df = pd.read_csv('/opt/airflow/dags/hw_200.csv')
    rows_number = df.shape[0]
    # Подсчет строк
    print(f"Data from hw_200.csv file:\n{df}")
    print(f"Кол-во строк в df: {rows_number}")

    # Push кол-ва строк в xcom
    ti.xcom_push(key="csv_rn", value = rows_number)

    # Добавление variable в Airflow_variable
    try:
        Variable.get("csv_path", deserialize_json=False)
    except KeyError:
        Variable.set(key = "csv_path", value = "/opt/airflow/dags/hw_200.csv")

# 12_b
def old_b(ti):
    csv_rows = ti.xcom_pull(key = "csv_rn", task_ids='old_a')
    path_to_csv = Variable.get("csv_path", deserialize_json=False)
    df = pd.read_csv(path_to_csv)
    df['new_column'] = [i for i in range(csv_rows, 0, -1)]
    print(f"New Dataframe:\n{df}")

# New tasks
# New 12_a
def new_a():
    num1 = randint(0, 100)
    num2 = randint(0, 100)
    print(f"Число 1: {num1}\nЧисло 2: {num2}")

# New 12_b
# Запускаю DAG и убеждаюсь, что все работает верно! Все отработало хорошо.

# 12_c
def task_c():
    # Make a path variable to practice.txt file
    try:
        Variable.get("txt_path", deserialize_json=False)
    except KeyError:
        Variable.set(key = "txt_path", value = "/opt/airflow/dags/practice.txt")
    # Use a txt_path variable
    path_to_txt = Variable.get("txt_path", deserialize_json = False)
    num1 = randint(0, 100)
    num2 = randint(0, 100)

    # Print num1 and num2
    print("Число 1: " , num1)
    print("Число 2: " , num2)

    # Если 1.txt file не пустой то удаляем последнюю строку, в которой содержится результат выражения из task_d
    # Если 1.txt file пустой, то просто пропускаем данные действия и сразу переходим к шагу записи двух чисел в файл
    with open(path_to_txt, 'r') as f:
        lines = f.readlines()
        if len(lines) != 0:
            del lines[len(lines) - 1]
            with open(path_to_txt, 'w') as f:
                for line in lines:
                    f.write(line)

    # Write num1 and num2 into practice.txt file
    with open(path_to_txt, 'a') as f:
        f.write(f"{num1} {num2}\n")

# 12_d and 12_e (12_e как бы решается в 12_c первым шагом, чтобы можно было посмотреть файл 1.txt и убедиться, что значение выражения записывается в конец файла
# иначе по отработке DAG-а в 12_c два числа будут записаны в 1.txt
# в task_d будет расчиатано выражение и результат записан в 1.txt
# а в task_e этот результат будет удален из файла 1.txt и будет не понятно, какой результат был получен
# Надеюсь понятно объяснил, почему task_e как бы опустил в ДЗ и перекинул шаг удаления строки в 12_c)
def task_d_e():
    path_to_txt = Variable.get("txt_path", deserialize_json = False)
    # Считаем выражение (сумма 1 столбца - сумма 2 столбца = результат)
    with open(path_to_txt, 'r') as f:
        reader = csv.reader(f, delimiter=" ")
        my_list = list(zip(*reader))
        col1 = list(map(int, list(my_list[0])))
        col2 = list(map(int, list(my_list[1])))
        result = sum(col1) - sum(col2)
        print(f"Столбец 1: {col1}\n \
                Столбец 2: {col2}\n \
                Cумма 'столбца 1' - Cумма 'столбца 2' = {result}")

    # Запись результата выражения в файл
    with open(path_to_txt, 'a') as f:
        f.write(f"{result}\n")

# task_f (Настроил запуск DAG каждую мин - и остановил вручную, когда DAG отработал 5 раз)

# Define DAG
with DAG (dag_id='Practice_Airflow', start_date = datetime (2022, 12, 5), schedule= '*/1 * * * *') as dag:
    # Tasks are represented as operators
    bash_task = BashOperator(task_id = "hello", bash_command = "echo hello", do_xcom_push = False)
    read_csv_task = PythonOperator(task_id = "old_a", python_callable = csv_procedure)
    practice_b_task = PythonOperator(task_id = "old_b", python_callable = old_b, do_xcom_push = False)
    random_numbers = PythonOperator(task_id = "new_a", python_callable = new_a, do_xcom_push = False)
    write_nums_in_txt = PythonOperator(task_id = "task_c", python_callable = task_c, do_xcom_push = False)
    write_res_in_txt = PythonOperator(task_id = "task_d_e", python_callable = task_d_e, do_xcom_push = False)
    # Set dependencies between tasks
    bash_task >> read_csv_task >> practice_b_task >> random_numbers >> write_nums_in_txt >> write_res_in_txt