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

        // Инициализация переменных для самой ранней и самой поздней даты
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

        // Вывод самой ранней и самой поздней даты
        out.println();
        out.println("Самая ранняя дата: " + mindate);
        out.println("Самая поздняя дата: " + maxdate);

        out.println();

        // Вывод элементов массива, в виде "yyyy-MMdd".
        out.println("Элементы отсортированного массива, в виде yyyy-MMdd");
        for (int i = 0; i < N; i++) {
            out.println();
            for (int j = 0; j < M; j++) {
                out.print(dates[i][j].substring(6, 10) + "-" + dates[i][j].substring(3, 5) + dates[i][j].substring(0, 2) + " ");
            }
        }

        out.println();
        out.println();

        // Добавление к каждой дате одного дня и вывод обновлённого массива
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

                // Форматируем дату обратно в строку
                dates[i][j] = day + "." + month + "." + year + " " + dates[i][j].substring(11);

            }
        }
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

