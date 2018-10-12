package main.java.io.vasiliyplatonov.practical_1_1_1;

import main.java.io.vasiliyplatonov.helpers.SetWorker;

import java.util.Map;


public class Main {
    private static final int COUNT_OF_SETS = 4;

    public static void main(String[] args) {
        SetWorker setWorker = new SetWorker();
        Map<Character, String> sets = setWorker.getFilledSets(COUNT_OF_SETS);

        //(A & B) / (C & D)
        String AB = setWorker.intersection(sets.get('A'), sets.get('B'));
        String CD = setWorker.intersection(sets.get('C'), sets.get('D'));
        String setE = setWorker.difference(AB, CD);

        sets.put('E', setE);
        System.out.println("Набор множеств = " + sets);
    }
}
