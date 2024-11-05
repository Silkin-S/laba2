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

Данную задачу можно разделить на 2 подзадачи: нахождение максимального числа и получение модуля этого числа.

- Для 1 подзадачи нужно рассмотреть 2 случая:
    1. `X >= Y`
    2. `X < Y` (отрицание 1 случая)
- Пусть `Z = max(X, Y)`, тогда для 2 подзадачи нужно также рассмотреть 2 случая:
    1. `Z >= 0`
    2. `Z < 0` (отрицание 1 случая)

Всего надо рассмотреть `2 * 2 = 4` случая.

### 2. Входные и выходные данные

#### Данные на вход

На вход программа должна получать 2 числа, при этом в условии не сказано, к какому множеству
принадлежать получаемые числа, поэтому будем считать их вещественными. Также даны верхняя и нижняя границы получаемых
чисел.

|             | Тип                | min значение    | max значение   |
|-------------|--------------------|-----------------|----------------|
| X (Число 1) | Вещественное число | 10<sup>9</sup>  | 10<sup>9</sup> |
| Y (Число 2) | Вещественное число | -10<sup>9</sup> | 10<sup>9</sup> |

#### Данные на выход

Т.к. программа должна вывести модуль максимального из получаемых чисел, то на выход мы получим
единственное вещественное неотрицательное число, не превышающее 10<sup>9</sup>.

|         | Тип                                | min значение | max значение   |
|---------|------------------------------------|--------------|----------------|
| Число 1 | Вещественное неотрицательное число | 0            | 10<sup>9</sup> |

### 3. Выбор структуры данных

Программа получает 2 вещественных числа, не превышающих по модулю 10<sup>9</sup> < 2<sup>30</sup>. Поэтому для их хранения
можно выделить 2 переменных (`x` и `y`) типа `double`.

|             | название переменной | Тип (в Java) | 
|-------------|---------------------|--------------|
| X (Число 1) | `x`                 | `double`     |
| Y (Число 2) | `y`                 | `double`     | 

Для вывода результата необязательно его хранить в отдельной переменной.

### 4. Алгоритм

#### Алгоритм выполнения программы:

1. **Ввод данных:**  
   Программа считывает два вещественных числа, обозначенные как `x` и `y`.

2. **Сравнение чисел:**  
   Программа сравнивает значения `x` и `y`. Если `x` больше или равно `y`, программа переходит к следующему шагу для
   работы с `x`. Если `y` больше, программа выполняет действия для работы с `y`.

3. **Проверка знака для выбранного числа:**
    - Если было выбрано число `x` (так как оно больше или равно `y`), проверяется, положительное оно или отрицательное.
      Если `x` положительное, оно выводится на экран. Если отрицательное, выводится его модуль (т.е. противоположное
      по знаку значение).
    - Если было выбрано число `y` (поскольку оно больше `x`), выполняется аналогичная проверка. Если `y` положительное,
      оно выводится на экран. Если отрицательное, выводится его модуль.

4. **Вывод результата:**  
   На экран выводится либо большее из чисел, либо его модуль, если это число отрицательное.


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

1. Тест на `X > Y > 0`:

    - **Input**:
        ```
        5 1.3
        ```

    - **Output**:
        ```
        5
        ```

2. Тест на `X < Y < 0`:

    - **Input**:
        ```
        -4 -2.2
        ```

    - **Output**:
        ```
        2.2
        ```

3. Тест на `X < 0 < Y`:

    - **Input**:
        ```
        -4 5
        ```

    - **Output**:
        ```
        5
        ```

4. Тест на `X = 0` или `Y = 0`:

    - **Input**:
        ```
        0 -3
        ```

    - **Output**:
        ```
        3
        ```

5. Тест на ограничение задачи:

    - **Input**:
        ```
        -1000000000 1000000000
        ```

    - **Output**:
        ```
        1000000000
        ```