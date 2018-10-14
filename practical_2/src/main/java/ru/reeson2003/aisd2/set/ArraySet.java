package ru.reeson2003.aisd2.set;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArraySet implements MySet<ArraySet> {

    private char[] chars;

    ArraySet(char... chars) {
        this.chars = chars;
    }

    @Override
    public ArraySet and(ArraySet mySet) {
        return new ArraySet(intersection(chars, mySet.chars));
    }

    @Override
    public ArraySet not(ArraySet mySet) {
        return new ArraySet(difference(chars, mySet.chars));
    }

    private char[] intersection(char[] a, char[] b) {
        // изменяемая, но не потокобезопасная строка
        StringBuilder result = new StringBuilder();
        boolean containsLetter;

        // проверить каждый элемент множества A, есть ли он в множестве B
        for (int i = 0; i < a.length; i++) {
            containsLetter = false;
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {

                    for (int k = 0; k < result.length(); k++) {
                        if (a[i] == result.charAt(k)) {
                            containsLetter = true;
                        }
                    }
                    // если не содержит добавить
                    if (!containsLetter) {
                        result.append(a[i]);
                    }
                }

            }
        }
        return result.toString().toCharArray();
    }

    private char[] difference(char[] a, char[] b) {

        // изменяемая, но не потокобезопасная строка
        StringBuilder result = new StringBuilder(0);
        boolean containsLetter;

        // проверить каждый элемент множества A, есть ли он в множестве B
        for (int i = 0; i < a.length; i++) {
            containsLetter = false;
            for (int j = 0; j < b.length; j++) {
                // если есть -> выйти из данного цикла
                if (a[i] == b[j]) {
                    containsLetter = true;
                    break;
                }
            }

            // если i-й буквы множества A нет во множестве B
            if (!containsLetter) {
                result.append(a[i]);
            }

        }
        return result.toString().toCharArray();
    }

    char[] getChars() {
        return chars;
    }

    @Override
    public String toString() {
        return IntStream.range(0, chars.length)
                .mapToObj(i -> chars[i])
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
