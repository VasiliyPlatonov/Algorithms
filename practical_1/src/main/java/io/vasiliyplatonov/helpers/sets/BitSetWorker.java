package io.vasiliyplatonov.helpers.sets;

import io.vasiliyplatonov.helpers.RandomBitSet;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import static io.vasiliyplatonov.helpers.Universe.LOW_RUS_LETTERS;

public class BitSetWorker extends SetWorker<BitSet> {

    @Override
    public Map<Character, BitSet> getSetsFillManually(int nSets) {
        throw new UnsupportedOperationException("Ручной ввод не поддерживается");
    }

    @Override
    public Map<Character, BitSet> getSetsFillRandom(int nSets, int cardinality) {
        if (nSets > NAMES_OF_SETS.length) {
            throw new IllegalArgumentException("Максимальное количество множеств " + NAMES_OF_SETS.length + ", было принято : " + nSets);
        }
        if (nSets <= 0)
            throw new IllegalArgumentException("Минимальное количество множеств 1, было принято : " + nSets);

        Map<Character, BitSet> sets = new HashMap<>();
        for (int i = 0; i < nSets; i++) {
            sets.put(NAMES_OF_SETS[i], new RandomBitSet(cardinality));
        }

        return sets;

    }

    @Override
    public BitSet difference(BitSet A, BitSet B) {
        BitSet result = new BitSet();
        result.or(A);
        result.and(B);
        return result;
    }

    @Override
    public BitSet intersection(BitSet A, BitSet B) {
        BitSet result = new BitSet();
        result.or(A);
        result.andNot(B);
        return result;
    }

    @Override
    public String setToString(BitSet s) {
        return String.valueOf(getSetOfLowRusLatterByBitSet(s));
    }

    @Override
    public BitSet setFromString(String s) {
        BitSet bitSet = new BitSet();
        fillBitSet(s, bitSet);
        return bitSet;
    }

    /**
     * Конвертация множеств содержищие символы в множества содержащие массивы битов
     */
    public Map<Character, BitSet> convertToBits(Map<Character, String> sets) {
        Map<Character, BitSet> result = new HashMap<>();

        for (Map.Entry<Character, String> entry : sets.entrySet()) {  // пройти по каждому вхождению в мапе чтобы
            BitSet bitSet = new BitSet(LOW_RUS_LETTERS.length);       // установить в результат имя множества и полученный массив бит
            fillBitSet(entry.getValue(), bitSet);                     //  { {A : 0100101 ... 001},
            result.put(entry.getKey(), bitSet);                       //     B : {1001001 ...01}
        }                                                             //     ... }

        return result;
    }

    /**
     * Заполнить массива бит в зависимости от того, есть ли в переданном множестве буква
     */
    private void fillBitSet(String set, BitSet bitSet) {                //  пройти по каждой букве в LOW_RUS_LETTERS (универсум)
        for (int i = 0; i < LOW_RUS_LETTERS.length; i++) {              //  если буква есть в множестве, то записать в результат 1
            for (int j = 0; j < set.length(); j++) {                    //  иначе 0
                if (LOW_RUS_LETTERS[i] == set.charAt(j)) {
                    bitSet.set(i); // bitSet[i] == 1
                }
            }
        }
    }


    public char[] getSetOfLowRusLatterByBitSet(BitSet bitSet) {
        char[] result = new char[LOW_RUS_LETTERS.length];

        for (int i = 0; i < LOW_RUS_LETTERS.length; i++) {
            if (bitSet.get(i))
                result[i] = (LOW_RUS_LETTERS[i]);
        }
        return result;
    }

    public void showAsBinary(BitSet bitSet) {
        StringBuilder builder = new StringBuilder("{ ");
        for (int i = 0; i < bitSet.size(); i++) {
            if (bitSet.get(i)) builder.append("1, ");
            else builder.append("0, ");
        }
        builder.deleteCharAt(builder.length() - 2).append("}");
        System.out.println(builder);
    }

    public void showAsBinary(BitSet bitSet, int fromIndex, int toIndex) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex < 0: " + fromIndex);
        if (toIndex < 0)
            throw new IndexOutOfBoundsException("toIndex < 0: " + toIndex);
        if (fromIndex > toIndex)
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex +
                    " > toIndex: " + toIndex);

        StringBuilder builder = new StringBuilder("{ ");
        for (int i = fromIndex; i < toIndex; i++) {
            if (bitSet.get(i)) builder.append("1, ");
            else builder.append("0, ");
        }
        builder.deleteCharAt(builder.length() - 2).append("}");
        System.out.println(builder);
    }

    public void showAsBinary(BitSet bitSet, int toIndex) {
        if (toIndex < 0)
            throw new IndexOutOfBoundsException("toIndex < 0: " + toIndex);
        if (toIndex == 0) {
            System.out.println(bitSet.get(0));
        }

        StringBuilder builder = new StringBuilder("{ ");
        for (int i = 0; i < toIndex; i++) {
            if (bitSet.get(i)) builder.append("1, ");
            else builder.append("0, ");
        }
        builder.deleteCharAt(builder.length() - 2).append("}");
        System.out.println(builder);
    }

}
