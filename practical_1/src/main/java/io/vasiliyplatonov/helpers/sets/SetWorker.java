package io.vasiliyplatonov.helpers.sets;

import io.vasiliyplatonov.helpers.RandomBitSet;
import io.vasiliyplatonov.helpers.RandomStringGenerator;

import java.util.*;

import static io.vasiliyplatonov.helpers.Universe.LOW_RUS_LETTERS;

public interface SetWorker<setType> {
    char[] NAMES_OF_SETS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    /**
     * Метод для заполнения множеств вручную
     */
    Map<Character, setType> getSetsFillManually(int nSets);

    /**
     * Метод для заполнения множеств рандомно
     *
     * @param nSets     количество множеств, которые будут заполнены и возвращены
     * @param cardinality  необходимая мощность множества
     */
    Map<Character, setType> getSetsFillRandom(int nSets, int cardinality );

    /**
     * Метод вычисляющий разность двух множеств
     */
    setType difference(setType A, setType B);

    /**
     * Метод вычисляющий пересечение двух множеств
     */
    setType intersection(setType A, setType B);


    /**
     * Метод заполнения множеств.
     * Множество представлено как массив бит заполненный рандомно
     *
     * @param nSets количество множеств, которые будут заполнены и возвращены
     * @see java.util.Random
     * @see RandomBitSet
     */
    static Map<Character, BitSet> getFilledRandomBitSets(int nSets) {

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
     * Множество представлено как строка.
     *
     * @param nSets количество множеств
     * @return множества в виде { ключ : значение },
     * где ключ имеет тип char, значение строка введенных пользователем букв
     * @throws IllegalArgumentException в случае, если параметр nSets больше длины NAMES_OF_SETS
     *                                  или меньше или равен нулю
     */
    static Map<Character, String> getFilledStringSets(int nSets) {

        if (nSets > NAMES_OF_SETS.length)
            throw new IllegalArgumentException("Максимальное количество множеств " + NAMES_OF_SETS.length + ", было принято : " + nSets);

        if (nSets <= 0)
            throw new IllegalArgumentException("Минимальное количество множеств 1, было принято : " + nSets);


        Map<Character, String> sets = new HashMap<>();
        Scanner in = new Scanner(System.in);

        System.out.println("Все вводимые множества должны быть включены в универсум: \n" + Arrays.toString(LOW_RUS_LETTERS) +"\n");

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

    /**
     * Метод заполнения множеств.
     * Множество представлено как строка заполненная рандомно
     *
     * @param nSets       количество множеств, которые будут заполнены и возвращены
     * @param cardinality необходимая мощность множества
     * @see RandomStringGenerator
     */
    static Map<Character, String> getFilledRandomStringSets(int nSets, int cardinality) {

        if (nSets > NAMES_OF_SETS.length) {
            throw new IllegalArgumentException("Максимальное количество множеств " + NAMES_OF_SETS.length + ", было принято : " + nSets);
        }
        if (nSets <= 0)
            throw new IllegalArgumentException("Минимальное количество множеств 1, было принято : " + nSets);

        Map<Character, String> sets = new HashMap<>();
        for (int i = 0; i < nSets; i++) {
            sets.put(NAMES_OF_SETS[i], RandomStringGenerator.getNext(cardinality, LOW_RUS_LETTERS));
        }

        return sets;
    }

    static String getNormalizeSet(char[] universe, String set) {
        StringBuilder sb = new StringBuilder();
        String u = new String(universe);

        set.chars()
                .filter(s -> u.contains((String.valueOf((char) s))))
                .forEach(s -> sb.append((char) s));

        return sb.toString();
    }

    static boolean isSetIncludedInUniverse(char[] universe, String set) {
        String u = new String(universe);
        return set.chars().allMatch(s -> u.contains((String.valueOf((char) s))));
    }

}

