package io.vasiliyplatonov.practical_1_1_1;


import io.vasiliyplatonov.helpers.SetWorker;

import java.util.LinkedList;
import java.util.Map;


public class Main {
    private static final int COUNT_OF_SETS = 4;

    public static void main(String[] args) {
        Map<Character, String> sets = SetWorker.getFilledStringSets(COUNT_OF_SETS);


        //(A & B) / (C & D)
        String AB = SetWorker.intersection(sets.get('A'), sets.get('B'));
        String CD = SetWorker.intersection(sets.get('C'), sets.get('D'));
        String setE = SetWorker.difference(AB, CD);

        sets.put('E', setE);
        System.out.println("Набор множеств = " + sets);
    }
}
