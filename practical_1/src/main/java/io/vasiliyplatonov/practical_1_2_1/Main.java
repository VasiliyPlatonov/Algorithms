package io.vasiliyplatonov.practical_1_2_1;

import io.vasiliyplatonov.helpers.Universe;
import io.vasiliyplatonov.helpers.sets.BitSetWorker;

import java.util.BitSet;
import java.util.Map;

public class Main {
    private static final int COUNT_OF_SETS = 4;

    public static void main(String[] args) {
        BitSetWorker setWorker = new BitSetWorker();
        Map<Character, BitSet> sets = setWorker.getSetsFillRandom(COUNT_OF_SETS, Universe.LOW_RUS_LETTERS.length);

        for (Map.Entry<Character, BitSet> entry : sets.entrySet()) {
            System.out.println(setWorker.getSetOfLowRusLatterByBitSet(entry.getValue()));
        }

        //  Вычислить (A & B) / (C & D)
        BitSet resultSet = sets.get('A');
        resultSet.and(sets.get('B'));
        BitSet CD = sets.get('C');
        resultSet.andNot(CD);

        System.out.println("Резулитирующее множество: " + String.valueOf(setWorker.getSetOfLowRusLatterByBitSet(resultSet)));

    }
}
