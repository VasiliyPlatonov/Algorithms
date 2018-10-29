package io.vasiliyplatonov.practical_1_1_1;


import io.vasiliyplatonov.helpers.sets.LinkedSetWorker;
import io.vasiliyplatonov.helpers.sets.SetWorker;

import java.util.LinkedList;
import java.util.Map;


public class Main {
    private static final int COUNT_OF_SETS = 4;

    public static void main(String[] args) {
        SetWorker<LinkedList<Character>> setWorker = new LinkedSetWorker();
        Map<Character, LinkedList<Character>> sets = setWorker.getSetsFillManually(COUNT_OF_SETS);

        LinkedList<Character> AB = setWorker.intersection(sets.get('A'), sets.get('B'));
        LinkedList<Character> CD = setWorker.intersection(sets.get('C'), sets.get('D'));
        LinkedList<Character> E = setWorker.difference(AB, CD);

        sets.put('E', E);
        System.out.println("Набор множеств = " + sets);
    }
}
