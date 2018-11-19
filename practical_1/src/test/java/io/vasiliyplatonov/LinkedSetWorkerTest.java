package io.vasiliyplatonov;

import io.vasiliyplatonov.helpers.sets.LinkedSetWorker;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LinkedSetWorkerTest {

    private final LinkedSetWorker linkedSetWorker = new LinkedSetWorker();

//
//    @Test
//    public void getSetsFillManually() {
//        System.out.println(linkedSetWorker.getSetsFillManually(4));
//    }

    @Test
    public void difference() {
        LinkedList<Character> A = new LinkedList<>(Arrays.asList('а', 'б', 'в', 'г'));
        LinkedList<Character> B = new LinkedList<>(Arrays.asList('г', 'б', 'н', 'к', 'у', 'з'));

        assertThat(linkedSetWorker.difference(A, B))
                .isNotNull()
                .isNotEmpty()
                .containsExactly('а', 'в');
    }

    @Test
    public void getSetsFillRandom() {

        assertThat(linkedSetWorker.getSetsFillRandom(4, 25))
                .isNotNull()
                .isNotEmpty()
                .containsKeys('a', 'b', 'c', 'd');

        assertThat(linkedSetWorker.getSetsFillRandom(4, 25).entrySet()).hasSize(4);

        linkedSetWorker.getSetsFillRandom(4, 25)
                .forEach((key, value) -> assertThat(value)
                        .hasSize(25)
                );

    }

    @Test
    public void setToString() {
        Character[] tData = {'ы', 'в', 'у', 'к', 'а', 'в', 'п', 'й'};
        LinkedList<Character> tSet = new LinkedList<>(Arrays.asList(tData));
        String expected = "ывукавпй";

        assertThat(linkedSetWorker.setToString(tSet))
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(expected);
    }

}
