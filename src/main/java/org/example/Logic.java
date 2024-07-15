package org.example;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Logic {

    static int attempt;
    static StringBuilder wordPattern;

    static String[] immages = {"|-------|\n|\t\t|\n|\t\tO\n|\t   /O\\\n|\t   ||\n|\n|",
            "|-------|\n|\t\t|\n|\t\tO\n|\t   /O\\\n|\n|\n|",
            "|-------|\n|\t\t|\n|\t\tO\n|\n|\n|\n|",
            "|-------|\n|\t\t|\n|\n|\n|\n|\n|",
            "|-------\n|\n|\n|\n|\n|\n|",
            "|\n|\n|\n|\n|\n|\n|"};

    static void runGame() {
        boolean ready = true;
        while (ready) {
            System.out.println("1. Начать новую игру");
            System.out.println("2. Выйти");
            System.out.print("Сделайте выбор: ");

            int choose = 0;
            try {
                Scanner in = new Scanner(System.in);
                choose = in.nextInt();
                switch (choose) {
                    case 1: {
                        chooseCategory();
                        break;
                    }
                    case 2: {
                        ready = false;
                        break;
                    }
                    default: {
                        System.out.println("Похоже, Вы сделали неправильный ввод. Попробуйте еще раз.");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Похоже, Вы сделали неправильный ввод. Попробуйте еще раз.");
            }
        }
    }

    static void chooseCategory() {
        int category;

        boolean check = true;

        while (check) {
            try {
                System.out.println("1. Животные");
                System.out.println("2. Фрукты");
                System.out.println("3. Столицы");
                System.out.print("Выберете категорию слов: ");

                category = new Scanner(System.in).nextInt();

                switch (category) {
                    case 1: {
                        mainGameLoop(generateWord(Words.animal));
                        check = false;
                        break;

                    }
                    case 2: {
                        mainGameLoop(generateWord(Words.fruit));
                        check = false;
                        break;
                    }
                    case 3: {
                        mainGameLoop(generateWord(Words.capital));
                        check = false;
                        break;
                    }
                    default: {
                        System.out.println("Похоже, Вы сделали неправильный ввод. Попробуйте еще раз.");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Похоже, Вы сделали неправильный ввод. Попробуйте еще раз.");
            }
        }
    }

    public static String generateWord(List<String> words) {
        int number = new Random().nextInt(30);
        return words.get(number).toLowerCase();
    }

    static void mainGameLoop(String word) {
        System.out.println("\nКомпьютер загадал слово из выбранной категории." +
                "Тебе нужно отгадать его, но после 6 неправильных попыток ввода букв ты проиграешь. Удачи!");
        System.out.println("Искомое слово состоит из " + word.length() + " букв.");
        wordPattern = new StringBuilder(getSpaces(word.length()));
        attempt = 6;
        makeTry(word);
    }

    static void makeTry(String word) {
        while ((attempt != 0)) {
            System.out.println("Искомое слово: " + wordPattern);
            System.out.print("Введите букву: ");
            String letter = new Scanner(System.in).next();

            if (word.contains(letter)) {
                //there is letter
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == letter.charAt(0)) {
                        wordPattern.replace(i, i + 1, word.substring(i, i + 1));
                    }
                }

                if (!new String(wordPattern).contains("_")) {
                    System.out.println("Вы справились! Это было слово \"" + word + "\". Поздравляем!\n");
                    break;
                }
            } else {
                //there is no letter
                System.out.println(immages[attempt - 1]);
                attempt--;

                if (attempt == 0) {
                    System.out.println("Вы проиграли!");
                    System.out.println("Искомое слово было: " + word + "\n");
                }
            }
        }
    }

    static String getSpaces(int count) {
        String space = "";

        for (int i = 0; i < count; i++) {
            space += "_";
        }

        return space;
    }
}
