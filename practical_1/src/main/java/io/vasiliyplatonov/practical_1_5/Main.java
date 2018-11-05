package io.vasiliyplatonov.practical_1_5;

import io.vasiliyplatonov.helpers.sets.BitSetWorker;
import io.vasiliyplatonov.helpers.sets.LinkedSetWorker;
import io.vasiliyplatonov.helpers.sets.SetWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.processing.Processor;
import java.io.IOException;
import java.util.*;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        LinkedSetWorker linkedSetWorker = new LinkedSetWorker();
        BitSetWorker bitSetWorker = new BitSetWorker();

        logger.debug("LinkedList sets: ");
        List<Map<Character, LinkedList<Character>>> linkedListSets = linkedSetWorker.readFile("test.csv");
        long linkedListAvTim = linkedListSets.stream()
                .mapToLong(m -> runAndGetTime(linkedSetWorker, m))
                .sum() / linkedListSets.size();

        logger.debug("Universe mapped sets: ");
        List<Map<Character, BitSet>> bitSetsList = bitSetWorker.readFile("test.csv");
        long bitSetAvTime = bitSetsList.stream()
                .mapToLong(m -> runAndGetTime(bitSetWorker, m))
                .sum() / bitSetsList.size();

        logger.info("Linked list of sets average time = " + linkedListAvTim);
        logger.info("List of bit sets average time = " + bitSetAvTime);
    }

    @SuppressWarnings("unchecked")
    private static <setType> long runAndGetTime(SetWorker setWorker, Map<Character, setType> set) {
        long before = System.nanoTime();
        setType resSet = calculate((SetWorker<setType>) setWorker, set);
        long resTime = System.nanoTime() - before;

        set.forEach((key, value) -> logger.debug(key + " = " + setWorker.setToString(value)));
        logger.debug("Result = " + setWorker.setToString(resSet));
        logger.debug("Time = " + resTime + "\n");

        return resTime;
    }

    private static <setType> setType calculate(SetWorker<setType> setWorker, Map<Character, setType> sets) {
        setType ab = setWorker.intersection(sets.get('a'), sets.get('b'));
        setType cd = setWorker.intersection(sets.get('c'), sets.get('d'));
        return setWorker.difference(ab, cd);
    }

}
