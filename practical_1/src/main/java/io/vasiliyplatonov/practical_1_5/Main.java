package io.vasiliyplatonov.practical_1_5;

import io.vasiliyplatonov.helpers.sets.BitSetWorker;
import io.vasiliyplatonov.helpers.sets.LinkedSetWorker;
import io.vasiliyplatonov.helpers.sets.SetWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static java.util.Objects.isNull;

public class Main {
    private static final int SIZE_SET_LIST = 100;
    private static final int COUNT_SETS = 4;
    private static final int CARDINALITY_SETS = 4;
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws FileNotFoundException {

        LinkedSetWorker linkedSetWorker = new LinkedSetWorker();
        BitSetWorker bitSetWorker = new BitSetWorker();
        List<Map<Character, LinkedList<Character>>> linkedListSets = null;
        List<Map<Character, BitSet>> bitSetsList = null;

        if (isNull(args[0])) {
            throw new IllegalArgumentException("входящие аргументы не могут быть null");
        } else if (args[0].equals("rand")) {
            linkedListSets = linkedSetWorker.getRandomSetList(SIZE_SET_LIST, COUNT_SETS, CARDINALITY_SETS);
            bitSetsList = bitSetWorker.getRandomSetList(SIZE_SET_LIST, COUNT_SETS, CARDINALITY_SETS);
        } else {
            String filename = args[0];

            try {
                linkedListSets = linkedSetWorker.readFile(filename);
                bitSetsList = bitSetWorker.readFile(filename);
            } catch (FileNotFoundException e) {
               throw e;
            } catch (IOException e) {
                logger.error("Ошибка при чтении файла [ " + filename + " ]", e);
            }
        }


        logger.debug("LinkedList sets: ");
        long linkedListAvTim = linkedListSets.stream()
                .mapToLong(m -> runAndGetTime(linkedSetWorker, m))
                .sum() / linkedListSets.size();

        logger.debug("Universe mapped sets: ");
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
