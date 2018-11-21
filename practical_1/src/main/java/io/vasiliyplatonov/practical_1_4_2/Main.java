package io.vasiliyplatonov.practical_1_4_2;

import io.vasiliyplatonov.helpers.Universe;
import io.vasiliyplatonov.helpers.sets.BitSetWorker;
import io.vasiliyplatonov.helpers.sets.LinkedSetWorker;

import java.util.Arrays;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.Map;

import static io.vasiliyplatonov.helpers.Universe.LOW_RUS_LETTERS;

public class Main {
    private static final int COUNT_OF_SETS = 4;

    public static void main(String[] args) {

        System.out.println("\n------------------------------  practical 1.1.1  ------------------------------\n");
        practical_1_1_1();

        System.out.println("\n------------------------------  practical 1.2.1  ------------------------------\n");
        practical_1_2_1();

    }

    private static void practical_1_1_1() {
        LinkedSetWorker setWorker = new LinkedSetWorker();
        Map<Character, LinkedList<Character>> sets = setWorker.getSetsFillRandom(COUNT_OF_SETS, Universe.LOW_RUS_LETTERS.length);

        long before = System.nanoTime();

        //(A & B) / (C & D)
        LinkedList<Character> AB = setWorker.intersection(sets.get('a'), sets.get('b'));
        LinkedList<Character> CD = setWorker.intersection(sets.get('c'), sets.get('d'));
        LinkedList<Character> E = setWorker.difference(AB, CD);

        long result = System.nanoTime() - before;

        for (Map.Entry<Character, LinkedList<Character>> entry : sets.entrySet()) {
            System.out.print(entry.getKey() + ":   ");
            System.out.println(entry.getValue());
        }
        System.out.print("Res: ");
        System.out.println(E);
        System.out.println("Time: " + result);
    }

    private static void practical_1_2_1() {
        BitSetWorker setWorker = new BitSetWorker();
        Map<Character, BitSet> bitSets = setWorker.getSetsFillRandom(COUNT_OF_SETS, Universe.LOW_RUS_LETTERS.length);

        long before = System.nanoTime();

//      Вычислить (A & B) / (C & D)
        BitSet resultSet = bitSets.get('a');
        resultSet.and(bitSets.get('b'));
        BitSet CD = bitSets.get('c');
        CD.and(bitSets.get('d'));
        resultSet.andNot(CD);
        long result = System.nanoTime() - before;

        System.out.println("Вывод как бинарный массив: ");
        for (Map.Entry<Character, BitSet> entry : bitSets.entrySet()) {
            System.out.print(entry.getKey() + ":   ");
            setWorker.showAsBinary(entry.getValue(), LOW_RUS_LETTERS.length);
        }
        System.out.print("Res: ");
        setWorker.showAsBinary(resultSet, LOW_RUS_LETTERS.length);

        System.out.println("\n\nВывод как массив букв: ");
        System.out.println("Универсум: " + Arrays.toString(LOW_RUS_LETTERS));
        for (Map.Entry<Character, BitSet> entry : bitSets.entrySet()) {
            System.out.print(entry.getKey() + ":   ");
            System.out.println(String.valueOf(setWorker.getSetOfLowRusLatterByBitSet(entry.getValue())));
        }
        // (A & B) / (C & D)
        System.out.print("Res: ");
        System.out.println(String.valueOf(setWorker.getSetOfLowRusLatterByBitSet(resultSet)));
        System.out.println("Time: " + result);
    }
}