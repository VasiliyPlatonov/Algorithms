package main.java.io.vasiliyplatonov;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static final char[] NAMES_OF_SETS = {'A', 'B', 'C', 'D'};
    private static Map<Character, String> sets = new HashMap<>();

    public static void main(String[] args) {
        SetWorker setWorker = new SetWorker();

        sets = setWorker.getFilledSetsByNames(NAMES_OF_SETS);
        String setE = setWorker.calculateE(sets);

        sets.put('E', setE);

        System.out.println("Набор множеств = " + sets);
    }


}