package ru.reeson2003.aisd2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.reeson2003.aisd2.set.ArraySet;
import ru.reeson2003.aisd2.set.LinkedListSet;
import ru.reeson2003.aisd2.set.MySet;
import ru.reeson2003.aisd2.set.UniversumMappedSet;

import java.io.IOException;
import java.util.List;

public class Processor {

    public static final int SIZE = 100;
    private static final Logger logger = LoggerFactory.getLogger(Processor.class);

    /**
     * @param args pass filename as argument to run with file data, or empty to run with random data
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Processor processor = new Processor();
        List<FourSet> fourSets = getFourSet(args);
        /*initial run fir jit compilation. doesn't measure*/
        runForList(processor, fourSets, ArraySet.class);
        runForList(processor, fourSets, LinkedListSet.class);
        runForList(processor, fourSets, UniversumMappedSet.class);
        /**/
        logger.debug("Array set:");
        long arraySetTime = runForList(processor, fourSets, ArraySet.class);
        logger.debug("linked set:");
        long linkedSetTime = runForList(processor, fourSets, LinkedListSet.class);
        logger.debug("Universum set:");
        long universumSetTime = runForList(processor, fourSets, UniversumMappedSet.class);
        logger.info("arraySetTime = " + arraySetTime);
        logger.info("linkedSetTime = " + linkedSetTime);
        logger.info("universumSetTime = " + universumSetTime);
    }

    @SuppressWarnings("unchecked")
    private static Long runForList(Processor processor, List<FourSet> fourSets, Class<? extends MySet> tClass) {
        return fourSets.stream()
                .mapToLong(f -> processor.run(tClass, f.a, f.b, f.c, f.d))
                .sum() / (long) fourSets.size();
    }

    private static List<FourSet> getFourSet(String[] args) throws IOException {
        if (args.length == 0)
            return DataSupply.getRandomFourSetList(SIZE);
        else {
            String filename = args[0];
            return FourSet.fromFile(filename);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends MySet<T>> long run(Class<T> tClass, char[] aArray, char[] bArray, char[] cArray, char[] dArray) {
        MySet a = create(tClass, aArray);
        MySet b = create(tClass, bArray);
        MySet c = create(tClass, cArray);
        MySet d = create(tClass, dArray);
        long before = System.nanoTime();
        MySet result = calculate(a, b, c, d);
        long after = System.nanoTime();
        long elapsed = after - before;
        logger.debug("{}, {}, {} ,{}, result: {}, time: {}", a, b, c, d, result, elapsed);
        return elapsed;
    }

    public <T extends MySet<T>> T calculate(T a, T b, T c, T d) {
        T aAndB = a.and(b);
        T cAndD = c.and(d);
        return aAndB.not(cAndD);
    }

    public <T extends MySet<T>> MySet create(Class<T> tClass, char[] array) {
        if (tClass == ArraySet.class)
            return MySet.arraySet(array);
        else if (tClass == LinkedListSet.class)
            return MySet.linkedListSet(array);
        else if (tClass == UniversumMappedSet.class)
            return MySet.universumMappedSet(array);
        else throw new IllegalArgumentException("Unknown type: " + tClass.getCanonicalName());
    }
}
