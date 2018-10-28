package io.vasiliyplatonov.helpers;

import java.util.*;

import static io.vasiliyplatonov.helpers.Universe.LOW_RUS_LETTERS;

public class SetWorker {
    private static final char[] NAMES_OF_SETS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};


    /**
     * Метод заполнения множеств.
     * Множество представлено как строка.
     *
     * @param nSets количество множеств
     * @return множества в виде { ключ : значение },
     * где ключ имеет тип char, значение строка введенных пользователем букв
     * @throws IllegalArgumentException в случае, если параметр nSets больше длины NAMES_OF_SETS
     *                                  или меньше или равен нулю
     */
    public static Map<Character, String> getFilledStringSets(int nSets) {

        if (nSets > NAMES_OF_SETS.length)
            throw new IllegalArgumentException("Максимальное количество множеств " + NAMES_OF_SETS.length + ", было принято : " + nSets);

        if (nSets <= 0)
            throw new IllegalArgumentException("Минимальное количество множеств 1, было принято : " + nSets);


        Map<Character, String> sets = new HashMap<>();
        Scanner in = new Scanner(System.in);

        System.out.println("Все вводимые множества должны быть включены в универсум: " + Arrays.toString(LOW_RUS_LETTERS));

        for (int i = 0; i < nSets; i++) {
            System.out.println("Введите множество " + NAMES_OF_SETS[i] + ": ");
            String set = in.nextLine();

            if (!isSetIncludedInUniverse(LOW_RUS_LETTERS, set)) {
                set = getNormalizeSet(LOW_RUS_LETTERS, set);
                System.out.println("Множество " + NAMES_OF_SETS[i] + " было нормализовано: " + set);
                System.out.println();
            }
            sets.put(NAMES_OF_SETS[i], set);
        }

        return sets;
    }

    public static String getNormalizeSet(char[] universe, String set) {
        StringBuilder sb = new StringBuilder();
        String u = new String(universe);

        set.chars()
                .filter(s -> u.contains((String.valueOf((char) s))))
                .forEach(s -> sb.append((char) s));

        return sb.toString();
    }

    public static boolean isSetIncludedInUniverse(char[] universe, String set) {
        String u = new String(universe);
        return set.chars().allMatch(s -> u.contains((String.valueOf((char) s))));
    }

    /**
     * Метод заполнения множеств.
     * Множество представлено как массив бит заполненный рандомно
     *
     * @see java.util.Random
     * @see RandomBitSet
     */
    public static Map<Character, BitSet> getFilledRandomBitSets(int nSets) {

        if (nSets > NAMES_OF_SETS.length) {
            throw new IllegalArgumentException("Максимальное количество множеств " + NAMES_OF_SETS.length + ", было принято : " + nSets);
        }
        if (nSets <= 0)
            throw new IllegalArgumentException("Минимальное количество множеств 1, было принято : " + nSets);

        Map<Character, BitSet> sets = new HashMap<>();
        for (int i = 0; i < nSets; i++) {
            sets.put(NAMES_OF_SETS[i], new RandomBitSet());
        }

        return sets;
    }

    /**
     * Метод заполнения множеств.
     * Множество представлено как строка заполненная рандомно
     *
     * @see RandomStringGenerator
     */
    public static Map<Character, String> getFilledRandomStringSets(int nSets, int strLength) {

        if (nSets > NAMES_OF_SETS.length) {
            throw new IllegalArgumentException("Максимальное количество множеств " + NAMES_OF_SETS.length + ", было принято : " + nSets);
        }
        if (nSets <= 0)
            throw new IllegalArgumentException("Минимальное количество множеств 1, было принято : " + nSets);

        Map<Character, String> sets = new HashMap<>();
        for (int i = 0; i < nSets; i++) {
            sets.put(NAMES_OF_SETS[i], RandomStringGenerator.getNext(strLength, LOW_RUS_LETTERS));
        }

        return sets;
    }


    /**
     * Метод вычисляющий пересечение двух множеств
     */
    public static String intersection(String A, String B) {
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
    public static String difference(String A, String B) {

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


    public static char[] getSetOfLowRusLatterByBitSet(BitSet bitSet) {
        // изменяемая, но не потокобезопасная строка
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < LOW_RUS_LETTERS.length; i++) {
            if (bitSet.get(i))
                result.append(LOW_RUS_LETTERS[i]);
        }
        return result.toString().toCharArray();
    }

    /**
     * Конвертация множеств содержищие символы в множества содержащие массивы битов
     */
    public static Map<Character, BitSet> convertToBits(Map<Character, String> sets) {
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


    /**
     * Перевод строки в связанный список букв (char)
     *
     * @return связанный список (LinkedList) элементами которога являются символы, представленные типом char
     *  пустой связанный список, если строка пустая или null
     */
    public static LinkedList<Character> getLetterLinkedList(String string) {
        LinkedList<Character> letters = new LinkedList<>();

        if (string == null || string.isEmpty())
            return letters;
        else
            string.chars().forEach(s -> letters.add((char) s));

        return letters;
    }

}
