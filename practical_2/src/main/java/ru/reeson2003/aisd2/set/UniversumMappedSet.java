package ru.reeson2003.aisd2.set;

import java.util.BitSet;
import java.util.stream.Collectors;

public class UniversumMappedSet implements MySet<UniversumMappedSet> {

    private BitSet bitSet;

    UniversumMappedSet(char... chars) {
        this.bitSet = convert(chars);
    }

    private UniversumMappedSet(BitSet bitSet) {
        this.bitSet = bitSet;
    }

    @Override
    public UniversumMappedSet and(UniversumMappedSet mySet) {
        this.bitSet.and(mySet.bitSet);
        return this;
    }

    @Override
    public UniversumMappedSet not(UniversumMappedSet mySet) {
        this.bitSet.andNot(mySet.bitSet);
        return this;
    }

    private BitSet convert(char[] chars) {
        BitSet bitSet = new BitSet(64);
        for (char aChar : chars) {
            bitSet.set(LOW_RUS_LETTERS.indexOf(aChar));
        }
        return bitSet;
    }

    @Override
    public String toString() {
        return bitSet.stream()
                .mapToObj(LOW_RUS_LETTERS::charAt)
                .map(String::valueOf)
                .collect(Collectors.joining());

    }
}
