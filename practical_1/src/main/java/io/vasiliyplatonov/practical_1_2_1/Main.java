package io.vasiliyplatonov.practical_1_2_1;


import io.vasiliyplatonov.helpers.SetWorker;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Map;

public class Main {
    private static final int COUNT_OF_SETS = 4;

    public static void main(String[] args) {

        Map<Character, String> sets = SetWorker.getFilledStringSets(COUNT_OF_SETS);

        // преобразовать множества из символов в массивы битов
        Map<Character, BitSet> setBit = SetWorker.convertToBits(sets);

        //  Вычислить (A & B) / (C & D)
        BitSet resultSet = setBit.get('A');
        resultSet.and(setBit.get('B'));
        BitSet CD = setBit.get('C');
        resultSet.andNot(CD);

        System.out.println("Резулитирующее множество: " + Arrays.toString(SetWorker.getSetOfLowRusLatterByBitSet(resultSet)));

    }
}
