package io.vasiliyplatonov.practical_1_3_5;


import io.vasiliyplatonov.helpers.RandomBitSet;
import io.vasiliyplatonov.helpers.RandomStringGenerator;
import io.vasiliyplatonov.helpers.SetWorker;
import io.vasiliyplatonov.helpers.Universe;

import java.util.Arrays;
import java.util.BitSet;
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
        Map<Character, String> strSets = SetWorker.getFilledRandomStringSets(COUNT_OF_SETS, 20);


        //(A & B) / (C & D)
        String AB = SetWorker.intersection(strSets.get('A'), strSets.get('B'));
        String CD = SetWorker.intersection(strSets.get('C'), strSets.get('D'));
        String setE = SetWorker.difference(AB, CD);

        for (Map.Entry<Character, String> entry : strSets.entrySet()) {
            System.out.print(entry.getKey() + ":   ");
            System.out.println(entry.getValue());
        }
        System.out.print("Res: ");
        System.out.println(setE);
    }

    private static void practical_1_2_1() {
        Map<Character, BitSet> bitSets = SetWorker.getFilledRandomBitSets(COUNT_OF_SETS);
//
//          Вычислить (A & B) / (C & D)
        BitSet resultSet = bitSets.get('A');
        resultSet.and(bitSets.get('B'));
        BitSet CD = bitSets.get('C');
        resultSet.andNot(CD);

        System.out.println("Вывод как бинарный массив: ");
        for (Map.Entry<Character, BitSet> entry : bitSets.entrySet()) {
            System.out.print(entry.getKey() + ":   ");
            RandomBitSet.showAsBinary(entry.getValue(), LOW_RUS_LETTERS.length);
        }
        System.out.print("Res: ");
        RandomBitSet.showAsBinary(resultSet, LOW_RUS_LETTERS.length);

        System.out.println("\n\nВывод как массив букв: ");
        System.out.println("Универсум: " + Arrays.toString(LOW_RUS_LETTERS));
        for (Map.Entry<Character, BitSet> entry : bitSets.entrySet()) {
            System.out.print(entry.getKey() + ":   ");
            System.out.println(Arrays.toString(SetWorker.getSetOfLowRusLatterByBitSet(entry.getValue())));
        }
//        (A & B) / (C & D)
        System.out.print("Res: ");
        System.out.println(Arrays.toString(SetWorker.getSetOfLowRusLatterByBitSet(resultSet)));
    }


    /*
    * Метод для проверки
    * */
//    private static void checkEqualsSet(int countOfChecks) {
//        SetWorker setWorker = new SetWorker();
//        for (int i = 0; i < countOfChecks; i++) {
//            Map<Character, BitSet> bitSets = setWorker.getFilledRandomBitSets(COUNT_OF_SETS);
//
//            BitSet resultSet = bitSets.get('A');
//            resultSet.and(bitSets.get('B'));
//            BitSet CD = bitSets.get('C');
//            resultSet.andNot(CD);
//
//            String setA = String.valueOf(setWorker.getSetOfLowRusLatterByBitSet(bitSets.get('A')));
//            String result = String.valueOf(setWorker.getSetOfLowRusLatterByBitSet(resultSet));
//
//            if (!(setA.equals(result))) {
//                System.out.println("Set A: " + setA);
//                System.out.println("Result: " + result);
//            }
//        }
//    }


}
