package main.java.io.vasiliyplatonov.helpers;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static main.java.io.vasiliyplatonov.helpers.Universe.LOW_RUS_LETTERS;


public class SetWorker {
    public static final char[] NAMES_OF_SETS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    /**
     * Метод заполнения множеств
     */
    public Map<Character, String> getFilledSets(int size) {

        if (size > NAMES_OF_SETS.length) {
            throw new IllegalArgumentException("Максимальное количество множеств 10, было принято : " + size);
        }

        Map<Character, String> sets = new HashMap<>();
        Scanner in = new Scanner(System.in);

        for (int i = 0; i < size; i++) {
            System.out.println("Введите множество " + NAMES_OF_SETS[i] + ": ");
            sets.put(NAMES_OF_SETS[i], in.next());
        }

        return sets;
    }

    /**
     * Метод вычисляющий пересечение двух множеств
     */
    public String intersection(String A, String B) {
        // изменяемая, но не потокобезопасная строка
        StringBuilder result = new StringBuilder();
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


    public char[] convertToSetOfLowRusLatter(BitSet bitSet) {
        // изменяемая, но не потокобезопасная строка
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < bitSet.length(); i++) {
            if (bitSet.get(i))
                result.append(LOW_RUS_LETTERS[i]);
        }
        return result.toString().toCharArray();
    }

    /**
     * Конвертация множеств содержищие символы в множества содержащие массивы битов
     */
    public Map<Character, BitSet> convertToBits(Map<Character, String> sets) {
        Map<Character, BitSet> result = new HashMap<>();

        for (Map.Entry<Character, String> entry : sets.entrySet()) {  // пройти по каждому вхождению в мапе чтобы
            BitSet bitSet = new BitSet(LOW_RUS_LETTERS.length);       // установить в результат имя множества и полученный массив бит
            fillBitSet(entry.getValue(), bitSet);                     //  { {A : 0100101 ... 001},
            result.put(entry.getKey(), bitSet);                       //     B : {1001001 ...01}
        }                                                             //     ... }

        return result;
    }

    /**
     * Заполнить массива бит в зависимости от того, есть ли в переданном множестве буква
     */
    public static void fillBitSet(String set, BitSet bitSet) {          //  пройти по каждой букве в LOW_RUS_LETTERS (универсум)
        for (int i = 0; i < LOW_RUS_LETTERS.length; i++) {              //  если буква есть в множестве, то записать в результат 1
            for (int j = 0; j < set.length(); j++) {                    //  иначе 0
                if (LOW_RUS_LETTERS[i] == set.charAt(j)) {
                    bitSet.set(i); // bitSet[i] == 1
                }
            }
        }
    }
}
