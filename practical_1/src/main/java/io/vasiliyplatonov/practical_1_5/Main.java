package io.vasiliyplatonov.practical_1_5;

import io.vasiliyplatonov.helpers.sets.BitSetWorker;
import io.vasiliyplatonov.helpers.sets.LinkedSetWorker;
import io.vasiliyplatonov.helpers.sets.SetWorker;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        LinkedSetWorker linkedSetWorker = new LinkedSetWorker();
        BitSetWorker bitSetWorker = new BitSetWorker();

        System.out.println("LinkedList sets: ");
        List<Map<Character, LinkedList<Character>>> linkedListSets = linkedSetWorker.readFile("test.csv");
        long linkedListAvTim = linkedListSets.stream()
                .mapToLong(m -> runAndGetTime(linkedSetWorker, m))
                .sum() / linkedListSets.size();

        System.out.println("Universe mapped sets: ");
        List<Map<Character, BitSet>> bitSetsList = bitSetWorker.readFile("test.csv");
        long bitSetAvTime = bitSetsList.stream()
                .mapToLong(m -> runAndGetTime(bitSetWorker, m))
                .sum() / bitSetsList.size();

        System.out.println("Linked list of sets average time = " + linkedListAvTim);
        System.out.println("List of bit sets average time = " + bitSetAvTime);
    }

    @SuppressWarnings("unchecked")
    private static <setType> long runAndGetTime(SetWorker setWorker, Map<Character, setType> set) {
        long before = System.nanoTime();
        setType resSet = calculate((SetWorker<setType>) setWorker, set);
        long resTime = System.nanoTime() - before;

        set.forEach((key, value) -> System.out.println(key + " = " + setWorker.setToString(value)));
        System.out.println("Result = " + setWorker.setToString(resSet));
        System.out.println("Time = " + resTime + "\n");

        return resTime;
    }

    private static <setType> setType calculate(SetWorker<setType> setWorker, Map<Character, setType> sets) {
        setType ab = setWorker.intersection(sets.get('a'), sets.get('b'));
        setType cd = setWorker.intersection(sets.get('c'), sets.get('d'));
        return setWorker.difference(ab, cd);
    }




//    private static <setType> long calcAndGetTime(SetWorker<setType> setWorker, Map<Character, setType> sets) {
//        long before = System.nanoTime();
//        calculate(setWorker, sets);
//        return System.nanoTime() - before;
//    }


    //    private static long runBitSet(BitSetWorker setWorker, Map<Character, BitSet> set) {
//        long before = System.nanoTime();
//        BitSet resSet = calculate(setWorker, set);
//        long resTime = System.nanoTime() - before;
//
//        set.forEach((k, v) -> {
//            System.out.println(k + " = " + String.valueOf(setWorker.getSetOfLowRusLatterByBitSet(v)));
//        });
//        System.out.println("Result = " + String.valueOf(setWorker.getSetOfLowRusLatterByBitSet(resSet)));
//        System.out.println("Time = " + resTime);
//        System.out.println();
//
//        return resTime;
//    }
}
