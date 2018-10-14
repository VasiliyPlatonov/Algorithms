package ru.reeson2003.aisd2.set;

import java.util.stream.IntStream;

public class LinkedListSet implements MySet<LinkedListSet> {

    private LinkedSet<Character> chars;

    LinkedListSet(char... chars) {
        Character[] boxed = IntStream.range(0, chars.length)
                .mapToObj(i -> chars[i])
                .toArray(Character[]::new);
        this.chars = LinkedSet.of(boxed);
    }

    public LinkedListSet(LinkedSet<Character> chars) {
        this.chars = chars;
    }

    @Override
    public LinkedListSet and(LinkedListSet mySet) {
        return new LinkedListSet(intersection(chars, mySet.chars));
    }

    @Override
    public LinkedListSet not(LinkedListSet mySet) {
        return new LinkedListSet(difference(chars, mySet.chars));
    }

    private char[] intersection(LinkedSet<Character> a, LinkedSet<Character> b) {
        if (a.head() == null || b.head() == null)
            return new char[0];
        // изменяемая, но не потокобезопасная строка
        StringBuilder result = new StringBuilder();
        boolean containsLetter;

        // проверить каждый элемент множества A, есть ли он в множестве B
        LinkedSet.Element<Character> currentA = a.head();
        while (currentA != null) {
            containsLetter = false;
            LinkedSet.Element<Character> currentB = b.head();
            while (currentB != null) {
                char aChar = currentA.value();
                char bChar = currentB.value();
                if (aChar == bChar) {

                    for (int k = 0; k < result.length(); k++) {
                        if (aChar == result.charAt(k)) {
                            containsLetter = true;
                        }
                    }
                    // если не содержит добавить
                    if (!containsLetter) {
                        result.append(aChar);
                    }
                }
                currentB = currentB.next();
            }
            currentA = currentA.next();
        }
        return result.toString().toCharArray();
    }

    private char[] difference(LinkedSet<Character> a, LinkedSet<Character> b) {
        if (a.head() == null || b.head() == null)
            return new char[0];
        // изменяемая, но не потокобезопасная строка
        StringBuilder result = new StringBuilder(0);
        boolean containsLetter;

        // проверить каждый элемент множества A, есть ли он в множестве B
        LinkedSet.Element<Character> currentA = a.head();
        while (currentA != null) {
            containsLetter = false;
            char aChar = currentA.value();
            LinkedSet.Element<Character> currentB = b.head();
            while (currentB != null) {
                char bChar = currentB.value();
                // если есть -> выйти из данного цикла
                if (aChar == bChar) {
                    containsLetter = true;
                    break;
                }
                currentB = currentB.next();
            }
            // если i-й буквы множества A нет во множестве B
            if (!containsLetter) {
                result.append(aChar);
            }
            currentA = currentA.next();
        }
        return result.toString().toCharArray();
    }

    @Override
    public String toString() {
        if (chars.head() == null)
            return "";
        StringBuilder sb = new StringBuilder();
        LinkedSet.Element<Character> head = chars.head();
        while (head != null) {
            sb.append(head.value());
            head = head.next();
        }
        return sb.toString();
    }
}
