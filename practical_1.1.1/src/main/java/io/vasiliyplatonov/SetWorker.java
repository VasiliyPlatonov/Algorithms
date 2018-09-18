package main.java.io.vasiliyplatonov;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class SetWorker {
    /**
     * Метод заполнения множеств
     */
    public  Map<Character, String> getFilledSetsByNames(char[] nameOfSets) {
        Map<Character, String> sets = new HashMap<>();
        Scanner in = new Scanner(System.in);

        for (int i = 0; i < nameOfSets.length; i++) {

            System.out.println("Введите множество " + nameOfSets[i] + ": ");
            sets.put(nameOfSets[i], in.next());
        }

        return sets;
    }

    /**
     * Метод вычисляющий множество E = (A & B) / (C & D)
     */
    public String calculateE(Map<Character, String> sets) {

        //(A & B)
        String AB = intersection(sets.get('A'), sets.get('B'));

        //(C & D)
        String CD = intersection(sets.get('C'), sets.get('D'));

        //(A & B) / (C & D)
        String E = difference(AB, CD);


        return E;
    }

    /**
     * Метод вычисляющий пересечение двух множеств
     */
    public String intersection(String A, String B) {
        // изменяемая, но не потокобезопасная строка
        StringBuilder result = new StringBuilder(0);
        boolean containsLetter;

        // проверить каждый элемент множества A, есть ли он в множестве B
        for (int i = 0; i < A.length(); i++) {
            containsLetter = false;
            for (int j = 0; j < B.length(); j++) {
                if (A.charAt(i) == B.charAt(j)) {

                    // если в множество Е пустое, то просто добавить элемент
                    if (result.length() == 0) result.append(A.charAt(i));

                        // иначе проверить есть ли уже такой элемент в множестве E
                    else {
                        for (int k = 0; k < result.length(); k++) {
                            if (A.charAt(i) == result.charAt(k)) {
                                containsLetter = true;
                            }
                        }
                        // если не содержит добавить
                        if (!containsLetter) {
                            result.append(A.charAt(i));
                        }
                    }
                }

            }
        }
        return result.toString();
    }


    /**
     * Метод вычисляющий разность двух множеств
     */
    public String difference(String A, String B) {

        // изменяемая, но не потокобезопасная строка
        StringBuilder result = new StringBuilder(0);
        boolean containsLetter;

        // проверить каждый элемент множества A, есть ли он в множестве B
        for (int i = 0; i < A.length(); i++) {
            containsLetter = false;
            for (int j = 0; j < B.length(); j++) {
                // если есть -> выйти из данного цикла
                if (A.charAt(i) == B.charAt(j)) {
                    containsLetter = true;
                    break;
                }
            }

            // если i-й буквы множества A нет во множестве B
            if (!containsLetter) {
                result.append(A.charAt(i));
            }

        }
        return result.toString();
    }
}
