package ru.reeson2003.aisd2.set;

import java.util.Random;

public interface MySet<T extends MySet> {

    String LOW_RUS_LETTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    static ArraySet arraySet(char... chars) {
        return new ArraySet(uniqueOnly(chars));
    }

    static LinkedListSet linkedListSet(char... chars) {
        return new LinkedListSet(uniqueOnly(chars));
    }

    static UniversumMappedSet universumMappedSet(char... chars) {
        return new UniversumMappedSet(uniqueOnly(chars));
    }

    static char[] uniqueOnly(char[] chars) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            boolean contains = false;
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    contains = true;
                    break;
                }
            }
            if (!contains)
                sb.append(chars[i]);
        }
        return sb.toString().toCharArray();
    }

    static char[] randomArray() {
        char[] chars = new char[LOW_RUS_LETTERS.length()];
        Random random = new Random(System.nanoTime());
        for (int i = 0; i < chars.length; i++) {
            int r = random.nextInt(chars.length);
            chars[i] = LOW_RUS_LETTERS.charAt(r);
        }
        return uniqueOnly(chars);
    }

    T and(T mySet);

    T not(T mySet);
}
