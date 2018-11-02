package io.vasiliyplatonov.helpers.sets;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public class LinkedSetWorker implements SetWorker<LinkedList<Character>> {

    /**
     * Перевод строки в связанный список букв (char)
     *
     * @return связанный список (LinkedList) элементами которого являются символы, представленные типом char
     * пустой связанный список, если строка пустая или null
     */
    public LinkedList<Character> setFromString(String string) {
        LinkedList<Character> letters = new LinkedList<>();

        if (string == null || string.isEmpty())
            return letters;
        else
            string.chars().forEach(s -> letters.add((char) s));

        return letters;
    }

    @Override
    public Map<Character, LinkedList<Character>> getSetsFillManually(int nSets) {
        Map<Character, String> strings = SetWorker.getFilledStringSets(nSets);
        Map<Character, LinkedList<Character>> linkedLetters = new HashMap<>();

        strings.forEach((key, value) -> linkedLetters.put(key, setFromString(value)));

        return linkedLetters;
    }

    @Override
    public Map<Character, LinkedList<Character>> getSetsFillRandom(int nSets, int cardinality) {
        Map<Character, String> strings = SetWorker.getFilledRandomStringSets(nSets, cardinality);
        Map<Character, LinkedList<Character>> linkedLetters = new HashMap<>();

        strings.forEach((key, value) -> linkedLetters.put(key, setFromString(value)));

        return linkedLetters;
    }

    @Override
    public LinkedList<Character> difference(LinkedList<Character> A, LinkedList<Character> B) {
        LinkedList<Character> result = new LinkedList<>();
        boolean containsLetter;

        // проверить каждый элемент множества A, есть ли он в множестве B
        for (int i = 0; i < A.size(); i++) {
            containsLetter = false;
            for (int j = 0; j < B.size(); j++) {
                // если есть -> выйти из данного цикла
                if (A.get(i).equals(B.get(j))) {
                    containsLetter = true;
                    break;
                }
            }

            // если i-й буквы множества A нет во множестве B
            if (!containsLetter) result.add(A.get(i));
        }
        return result;
    }

    @Override
    public LinkedList<Character> intersection(LinkedList<Character> A, LinkedList<Character> B) {
        LinkedList<Character> result = new LinkedList<>();
        boolean containsLetter;

        // проверить каждый элемент множества A, есть ли он в множестве B
        for (int i = 0; i < A.size(); i++) {
            containsLetter = false;
            for (int j = 0; j < B.size(); j++) {
                if (A.get(i).equals(B.get(j))) {

                    // если в множество result пустое, то просто добавить элемент
                    if (result.size() == 0) result.add(A.get(i));

                        // иначе проверить есть ли уже такой элемент в множестве result
                    else {
                        for (int k = 0; k < result.size(); k++) {
                            if (A.get(i).equals(result.get(k))) {
                                containsLetter = true;
                            }
                        }
                        // если не содержит добавить
                        if (!containsLetter) {
                            result.add(A.get(i));
                        }
                    }
                }

            }
        }

        return result;
    }

    @Override
    public String setToString(LinkedList<Character> set) {
        return set.stream().map(Object::toString).collect(Collectors.joining());
    }
}
