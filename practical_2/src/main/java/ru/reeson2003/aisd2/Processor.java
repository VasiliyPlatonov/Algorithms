package ru.reeson2003.aisd2;

import ru.reeson2003.aisd2.set.ArraySet;
import ru.reeson2003.aisd2.set.LinkedListSet;
import ru.reeson2003.aisd2.set.MySet;
import ru.reeson2003.aisd2.set.UniversumMappedSet;

public class Processor {

    public static void main(String[] args) {
        Processor processor = new Processor();
        char[] a = MySet.randomArray();
        char[] b = MySet.randomArray();
        char[] c = MySet.randomArray();
        char[] d = MySet.randomArray();
        long arraySetTime = processor.run(ArraySet.class, a, b, c, d);
        long linkedSetTime = processor.run(LinkedListSet.class, a, b, c, d);
        long universumSetTime = processor.run(UniversumMappedSet.class, a, b, c, d);
        System.out.println("arraySetTime = " + arraySetTime);
        System.out.println("linkedSetTime = " + linkedSetTime);
        System.out.println("universumSetTime = " + universumSetTime);
    }

    public <T extends MySet<T>> long run(Class<T> tClass, char[] aArray, char[] bArray, char[] cArray, char[] dArray) {
        MySet a = create(tClass, aArray);
        System.out.println("Set A: " + a);
        MySet b = create(tClass, bArray);
        System.out.println("Set B: " + b);
        MySet c = create(tClass, cArray);
        System.out.println("Set C: " + c);
        MySet d = create(tClass, dArray);
        System.out.println("Set D: " + d);
        long before = System.nanoTime();
        MySet result = calculate(a, b, c, d);
        long after = System.nanoTime();
        System.out.println("Result set: " + result);
        return after - before;
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
