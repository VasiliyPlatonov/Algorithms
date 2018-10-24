package ru.reeson2003.aisd2;

import ru.reeson2003.aisd2.set.MySet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataSupply {

    public static void main(String[] args) throws IOException {
        List<FourSet> fourSets = getRandomFourSetList(100);
        FourSet.writeToFile(fourSets, "test.csv");
    }

    public static List<FourSet> getRandomFourSetList(int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> getRandomFourSet())
                .collect(Collectors.toList());
    }

    public static FourSet getRandomFourSet() {
        char[] a = MySet.randomArray();
        char[] b = MySet.randomArray();
        char[] c = MySet.randomArray();
        char[] d = MySet.randomArray();
        return new FourSet(a, b, c, d);
    }
}
