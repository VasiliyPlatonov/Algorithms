package main.java.io.vasiliyplatonov.practical_1_3_5;

import main.java.io.vasiliyplatonov.helpers.RandomBitSet;
import main.java.io.vasiliyplatonov.helpers.SetWorker;

import java.util.Arrays;
import java.util.BitSet;

import static main.java.io.vasiliyplatonov.helpers.Universe.LOW_RUS_LETTERS;

public class Main {
    private static final int COUNT_OF_SETS = 4;

    public static void main(String[] args) {

        SetWorker setWorker = new SetWorker();


        // для  practical 1_2_1
        RandomBitSet randomBitSet = new RandomBitSet(LOW_RUS_LETTERS.length);
        randomBitSet.showAsBinary(LOW_RUS_LETTERS.length);
        System.out.println(Arrays.toString(LOW_RUS_LETTERS));
        System.out.println(Arrays.toString(setWorker.getSetOfLowRusLatterByBitSet(randomBitSet)));





    }


}
