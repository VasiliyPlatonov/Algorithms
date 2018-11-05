package io.vasiliyplatonov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AppStart {
    private static final Logger logger = LoggerFactory.getLogger(AppStart.class);

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int choice;
        do {
            choice = -1;
            System.out.println("Выбирете программу для запуска: \n" +
                    "1 - Практическая работа 1 (множество как связанный список) \n" +
                    "2 - Практическая работа 2 (множество как отображение на универсум)\n" +
                    "3 - Практическая работа 3 (рандомное заполнение)\n" +
                    "4 - Практическая работа 4 (сравнение по времени выполнения) \n" +
                    "5 - Практическая работа 5 (запись чтение в файл)\n" +
                    "0 - Выход");


            try {
                choice = in.nextInt();
            } catch (InputMismatchException e) {
                clearScreen();
                logger.error("Произошла ошибка ввода. Повторите ввод.", e);
                in.nextLine(); // clear scanners buffer
                continue;
            }

            if (choice == 1) {
                clearScreen();
                System.out.println("\n\n ------- Практическая работа 1 ------- \n\n");
                io.vasiliyplatonov.practical_1_1_1.Main.main(null);
                System.out.println("\n\n --------------------------------------- \n\n");

            }
            if (choice == 2) {
                clearScreen();
                System.out.println("\n\n ------- Практическая работа 2 ------- \n\n");
                io.vasiliyplatonov.practical_1_2_1.Main.main(null);
                System.out.println("\n\n --------------------------------------- \n\n");
            }
            if (choice == 3) {
                clearScreen();
                System.out.println("\n\n ------- Практическая работа 3 ------- \n\n");
                io.vasiliyplatonov.practical_1_3_5.Main.main(null);
                System.out.println("\n\n --------------------------------------- \n\n");
            }
            if (choice == 4) {
                clearScreen();
                System.out.println("\n\n ------- Практическая работа 4 ------- \n\n");
                io.vasiliyplatonov.practical_1_4_2.Main.main(null);
                System.out.println("\n\n --------------------------------------- \n\n");
            }
            if (choice == 5) {
                clearScreen();
                System.out.println("\n\n ------- Практическая работа 5 ------- \n\n");
                System.out.println("Введите имя файла или 'rand' для рандомного заполнения");
                String[] arg = {in.next()};

                try {
                    io.vasiliyplatonov.practical_1_5.Main.main(arg);
                } catch (IllegalArgumentException e) {
                    logger.error("Произошла ошибка: " + e.getMessage(), e);
                } catch (FileNotFoundException e) {
                    logger.info("Файл с именем [ " + arg[0] + " ] не найден.", e);
                }

                System.out.println("\n\n --------------------------------------- \n\n");
            }

        } while (!(choice == 0));
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
