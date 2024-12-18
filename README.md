## Отчет по лабораторной работе № 1

#### № группы: `ПМ-2401`

#### Выполнил: `Силкин Семён Антонович`

#### Вариант: `26`

### Cодержание:

- [Постановка задачи](#1-постановка-задачи)
- [Входные и выходные данные](#2-входные-и-выходные-данные)
- [Выбор структуры данных](#3-выбор-структуры-данных)
- [Алгоритм](#4-алгоритм)
- [Программа](#5-программа)
- [Анализ правильности решения](#6-анализ-правильности-решения)

### 1. Постановка задачи

> Напишите программу на Java, которая выполняет следующие действия
с двумерным массивом строк, представляющих даты:
> 1. Считывает с консоли размеры массива N и M, затем элементы
   массива размером N × M (каждый элемент — дата в формате
   "dd.MM.yyyy HH:mm").
> 2. Сортирует строки массива по возрастанию дат. Если даты равны,
   сортирует по возрастанию времени, если время указано.
> 3. Находит и выводит самую раннюю и самую позднюю дату в массиве.
> 4. Выводит элементы массива, форматируя даты в виде "yyyy-MMdd".
> 5. Добавляет к каждой дате один день и выводит обновлённый массив.

Данную задачу можно разделить на 4 подзадачи: сортировка строк массива по возрастанию дат, поиск самой ранней и самой поздней даты в массиве, формирование дат виде "yyyy-MMdd", добавление к каждой дате по одному дню.

- Для 1 подзадачи выполним сортировку пузырьком
- Для 2 подзадачи сравним даты в крайнем левом и крайнем правом столбике для поиска наименьшей и наибольшей даты в массиве соответственно.
- Для 3 подзадачи достанем из каждой ячейки массива нужные нам значения и выведем их в правильном порядке.
- Для 4 подзадачи прибавим к каждой дате по одному дню, не забывая про то что может быть последний день месяца, года, а также что в феврале високосного года на 1 день больше.

### 2. Входные и выходные данные

#### Данные на вход

На вход программа должна получать размеры массива N и M, затем элементы
массива размером N × M (каждый элемент — дата в формате
"dd.MM.yyyy HH:mm"). N и M целочисленные и неотрицательные, так как они обозначают размеры массива.
Так как M и N удут принадлежать типу данных int, то их максимальное значение ограничивается максимальным значением для int, то есть 2<sup>31</sup>-1

|             | Тип                          | min значение     | max значение     |
|-------------|------------------------------|------------------|------------------|
| N           | Целое неотрицательное число  | 0                | 2<sup>31</sup>-1 |
| M           | Целое неотрицательное число  | 0                | 2<sup>31</sup>-1 |
| dates[i][j] | Строка вида dd.MM.yyyy HH:mm | 00.00.0000 00:00 | 99.99.9999 23:59 |

#### Данные на выход

Т.к. программа должна вывести модуль максимального из получаемых чисел, то на выход мы получим
единственное вещественное неотрицательное число, не превышающее 10<sup>9</sup>.

|                                      | Тип                                | min значение     | max значение                                                |
|--------------------------------------|------------------------------------|------------------|-------------------------------------------------------------|
| Самая ранняя дата                    | Строка вида dd.MM.yyyy HH:mm       | 00.00.0000 00:00 | 99.99.9999 23:59                                            |
| Самая поздняя дата                   | Строка вида dd.MM.yyyy HH:mm       | 00.00.0000 00:00 | 99.99.9999 23:59                                            |
| Массив, с датами в виде yyyy-MMdd    | Массив строк вида yyyy-MMdd        | пустой массив    | 2<sup>31</sup>-1 строк и столбцов с датами 99.99.9999 23:59 |
| Массив, с датами с прибавленным днём | Массив строк вида dd.MM.yyyy HH:mm | пустой массив    | 2<sup>31</sup>-1 строк и столбцов с датами 99.99.9999 23:59 |

### 2.5 Математическая модель

- Для сортировки строк по возрастанию используется сортировка пузырьком. Выполняется некоторое количество проходов по строке массива — начиная от начала строки, перебираются пары соседних элементов. Если 1-й элемент пары больше 2-го, элементы переставляются (выполняется обмен). Для этого сравниваем численные значения дат начиная с года, после, если дата слева больше, она меняется местами с датой справа.
- Для проверки на последний день месяца и года, необходимо учитывать, что в разных месяцах разное количество дней. В 1, 3, 5, 7, 8, 10 месяце 31 день и при прибавлении одного дня начинается новый месяц.
  В 4, 6, 9, 11 месяце 30 день и при прибавлении одного дня начинается новый месяц. В 12 месяце 31 день и при прибавлении одного дня начинается 1 месяц и новый год. Во 2 месяце количество дней зависит от того, високосный год или нет. В високосном году там 29 дней. Високосный год, это тот год, номер которого делится на 4 и не делится на 100 или делится на 400. 

### 3. Выбор структуры данных

Программа получает 2 целых неотрицательных числа, не превышающих по модулю 2<sup>31</sup>-1. Поэтому для их хранения
можно выделить 2 переменных (`N` и `M`) типа `int`, также на вход идут элементы массива в формате строки вида dd.MM.yyyy HH:mm, и их удобно записать в переменные dates[i][j] типа string.

|                               | название переменной | Тип (в Java) | 
|-------------------------------|---------------------|--------------|
| N (Число 1)                   | `N`                 | `int`        |
| M (Число 2)                   | `M`                 | `int`        |
| dates[i][j] (Элемент массива) | `dates[i][j]`       | `string`     | 


### 4. Алгоритм

#### Алгоритм выполнения программы:

1. **Ввод данных:**  
-   Программа считывает два вещественных числа, обозначенные как `N` и `M`.
-   Программа создаёт массив `dates` и считывает его элементы.

2. **Сортировка строк массива по возрастанию пузырьком.**  
- Программа извлекает компоненты дат для сравнения.
- Программа сравнивает даты и, если требуется, меняет их местами.

3. **Получение и вывод самой ранней и самой поздней даты**
- Программа создаёт переменные для самой ранней и самой поздней даты.
- Программа преобразует первую и последнюю даты в числовой формат для сравнения.
- Программа сравнивает все даты в самом левом и самом правом столбце массива и находит минимальную дату.
- Программа выводит самую раннюю и самую позднюю дату.

4. **Преобразование и вывод элементов массива, в виде "yyyy-MMdd".**  
- Программа выводит даты массива меняя расположение частей строки и убирая лишнее.

5. **Добавление к каждой дате одного дня и вывод обновлённого массива.**
- Программа преобразует день, месяц и год в числовой формат.
- Программа прибавляет один день.
- Программа проверяет числовое значение дня и меняет его, а если понадобится, то ещё месяц и год, в зависимости от количества дней в каждом месяце, того, какой это месяц и какой год (високосный или нет)
- Программа возвращает дату обратно в строку.
- Программа выводит обновлённый массив на экран.

### 5. Программа

```java
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
   public static Scanner in = new Scanner(System.in);
   public static PrintStream out = System.out;

   public static void main(String[] args) {
      // Считываем размеры массива
      out.println("Введите  размеры массива N и M");
      int N = in.nextInt();
      int M = in.nextInt();
      in.nextLine(); // Очистка буфера после nextInt()

      // Создание массива
      String[][] dates = new String[N][M];

      // Считываем элементы массива
      out.println("Введите даты в формате 'dd.MM.yyyy HH:mm':");
      for (int i = 0; i < N; i++) {
         for (int j = 0; j < M; j++) {
            dates[i][j] = in.nextLine();
         }
      }

      // Сортировка пузырьком
      for (int i = 0; i < N; i++) {
         for (int j = 0; j < dates[i].length - 1; j++) {
            for (int k = 0; k < dates[i].length - 1 - j; k++) {
               String date1 = dates[i][k];
               String date2 = dates[i][k + 1];

               // Извлекаем компоненты дат для сравнения
               int year1 = Integer.parseInt(date1.substring(6, 10));
               int year2 = Integer.parseInt(date2.substring(6, 10));
               int month1 = Integer.parseInt(date1.substring(3, 5));
               int month2 = Integer.parseInt(date2.substring(3, 5));
               int day1 = Integer.parseInt(date1.substring(0, 2));
               int day2 = Integer.parseInt(date2.substring(0, 2));
               int hour1 = Integer.parseInt(date1.substring(11, 13));
               int hour2 = Integer.parseInt(date2.substring(11, 13));
               int minute1 = Integer.parseInt(date1.substring(14, 16));
               int minute2 = Integer.parseInt(date2.substring(14, 16));

               // Сравнение дат
               if (year1 > year2 ||
                       (year1 == year2 &&
                               (month1 > month2 ||
                                       (month1 == month2 &&
                                               (day1 > day2 ||
                                                       (day1 == day2 &&
                                                               (hour1 > hour2 ||
                                                                       (hour1 == hour2 &&
                                                                               minute1 > minute2)))))))) {
                  // Меняем местами, если date1 больше date2
                  String t = dates[i][k];
                  dates[i][k] = dates[i][k + 1];
                  dates[i][k + 1] = t;
               }
            }
         }
      }

      // Создание переменных для самой ранней и самой поздней даты
      String mindate = dates[0][0];
      String maxdate = dates[0][M - 1];

      // Преобразуем первую дату в числовой формат для сравнения
      int minyear = Integer.parseInt(mindate.substring(6, 10));
      int minmonth = Integer.parseInt(mindate.substring(3, 5));
      int minday = Integer.parseInt(mindate.substring(0, 2));

      // Преобразуем последнюю дату в числовой формат для сравнения
      int maxyear = Integer.parseInt(maxdate.substring(6, 10));
      int maxmonth = Integer.parseInt(maxdate.substring(3, 5));
      int maxday = Integer.parseInt(maxdate.substring(0, 2));

      // Поиск самой ранней даты
      for (int i = 0; i < N; i++) {
         int year = Integer.parseInt(dates[i][0].substring(6, 10));
         int month = Integer.parseInt(dates[i][0].substring(3, 5));
         int day = Integer.parseInt(dates[i][0].substring(0, 2));

         if (year < minyear || (year == minyear && (month < minmonth || (month == minmonth && day < minday)))) {
            minyear = year;
            minmonth = month;
            minday = day;
            mindate = dates[i][0];
         }
      }

      // Поиск самой поздней даты
      for (int i = 0; i < N; i++) {
         int year = Integer.parseInt(dates[i][M - 1].substring(6, 10));
         int month = Integer.parseInt(dates[i][M - 1].substring(3, 5));
         int day = Integer.parseInt(dates[i][M - 1].substring(0, 2));

         if (year > maxyear || (year == maxyear && (month > maxmonth || (month == maxmonth && day > maxday)))) {
            maxyear = year;
            maxmonth = month;
            maxday = day;
            maxdate = dates[i][M - 1];
         }
      }

      out.println(); // для красоты

      // Вывод самой ранней и самой поздней даты
      out.println("Самая ранняя дата: " + mindate);
      out.println("Самая поздняя дата: " + maxdate);

      out.println(); // для красоты

      // Вывод элементов массива, в виде "yyyy-MMdd".
      out.println("Элементы массива, в виде yyyy-MMdd");
      for (int i = 0; i < N; i++) {
         out.println();
         for (int j = 0; j < M; j++) {
            out.print(dates[i][j].substring(6, 10) + "-" + dates[i][j].substring(3, 5) + dates[i][j].substring(0, 2) + " ");
         }
      }

      out.println(); // для красоты
      out.println(); // для красоты

      // Добавление к каждой дате одного дня
      out.println("Отсортированный массив с добавленным одним днем:");
      for (int i = 0; i < N; i++) {
         for (int j = 0; j < M; j++) {
            int day = Integer.parseInt(dates[i][j].substring(0, 2));
            int month = Integer.parseInt(dates[i][j].substring(3, 5));
            int year = Integer.parseInt(dates[i][j].substring(6, 10));

            // Добавляем один день
            day += 1;

            // Проверка не последний день месяца и года и решение этой проблемы
            if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10) && day > 31) {
               day = 1;
               month++;
            } else if (month == 12 && day > 31) {
               day = 1;
               month = 1;
               year++;
            } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
               day = 1;
               month++;
            } else if (month == 2) { // Февраль
               if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) { // Високосный год
                  if (day > 29) {
                     day = 1;
                     month++;
                  }
               } else {
                  if (day > 28) {
                     day = 1;
                     month++;
                  }
               }
            }

            // Возвращаем дату обратно в строку
            dates[i][j] = day + "." + month + "." + year + " " + dates[i][j].substring(11);

         }
      }

      // Выводим отсортированный массив с добавленным одним днем
      for (int i = 0; i < N; i++) {
         out.println();
         for (int j = 0; j < M; j++) {
            out.print(dates[i][j] + " ");
         }
      }

      out.println(); // для красоты
      out.println(); // для красоты

   }
}
```

### 6. Анализ правильности решения

Программа работает корректно на всем множестве решений с учетом ограничений.
В тесте 1 проверяются сразу несколько моментов: корректная сортировка, корректный поиск максимальной и минимальной даты в массиве, корректность вывода даты в другом формате, а также
проверяется корректность в прибавлении дня в високосный год в феврале (28.02.2020 10:00),
в невисокосный год в феврале (28.02.2021 13:00),
в конце месяца (28.02.2021 13:00), в конце месяца и года (31.12.2021 20:30).
1. Тест:

    - **Input**:
        ```
      3 3
        28.02.2020 10:00
        30.04.2021 09:45
        31.12.2020 23:59
        01.01.2022 00:01
        31.12.2021 20:30
        29.02.2020 15:00
        28.02.2021 13:00
        01.03.2021 09:15
        25.12.2021 18:00
        ```

    - **Output**:
        ```
      Самая ранняя дата: 28.02.2020 10:00
      Самая поздняя дата: 01.01.2022 00:01
      
      Элементы массива, в виде yyyy-MMdd
      
      2020-0228 2020-1231 2021-0430
      2020-0229 2021-1231 2022-0101
      2021-0228 2021-0301 2021-1225
      
      Отсортированный массив с добавленным одним днем:
      
      29.2.2020 10:00 1.1.2021 23:59 1.5.2021 09:45
      1.3.2020 15:00 1.1.2022 20:30 2.1.2022 00:01
      1.3.2021 13:00 2.3.2021 09:15 26.12.2021 18:00
      ```

В тесте 2 проверяются сразу несколько моментов: корректная сортировка, корректный поиск максимальной и минимальной даты в массиве, корректность вывода даты в другом формате, а также
проверяется корректность в прибавлении дня в високосный год в феврале (29.02.2024 12:00),
в невисокосный год в феврале (28.02.2023 09:30),
в конце месяца (30.11.2023 18:30), в конце месяца (31.03.2023 11:20), в конце месяца и года (31.12.2022 21:00).

2. Тест:

   - **Input**:
       ```
     4 2
      29.02.2024 12:00
      01.03.2025 00:00
      30.11.2023 18:30
      31.01.2022 22:15
      28.02.2023 09:30
      31.03.2023 11:20
      31.12.2022 21:00
      28.02.2020 23:59
       ```

   - **Output**:
       ```
      Самая ранняя дата: 28.02.2020 23:59
      Самая поздняя дата: 01.03.2025 00:00
      
      Элементы отсортированного массива, в виде yyyy-MMdd
      
      2024-0229 2025-0301
      2022-0131 2023-1130
      2023-0228 2023-0331
      2020-0228 2022-1231
      
      Отсортированный массив с добавленным одним днем:
      
      1.3.2024 12:00 2.3.2025 00:00
      1.2.2022 22:15 1.12.2023 18:30
      1.3.2023 09:30 1.4.2023 11:20
      29.2.2020 23:59 1.1.2023 21:00
     ```
