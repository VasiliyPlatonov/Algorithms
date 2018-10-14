package io.vasiliyplatonov.helpers;

public class RandomStringGenerator {
    public static String getNext(int size, char[] universe) {
        char[] result = new char[size];
        for (int i = 0; i < size; i++) {
            int randomInt = (int) (Math.random() * universe.length);
            result[i] = universe[randomInt];
        }
        return String.valueOf(result);
    }
}
