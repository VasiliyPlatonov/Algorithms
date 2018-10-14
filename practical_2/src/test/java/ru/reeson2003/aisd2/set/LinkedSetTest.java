package ru.reeson2003.aisd2.set;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LinkedSetTest {

    @Test
    public void test() {
        Integer[] elements = {1, 2, 3, 4, 5, 6, 7};
        LinkedSet<Integer> set = LinkedSet.of(elements);
        LinkedSet.Element<Integer> head = set.head();

        for (int i = elements.length - 1; i >= 0; i--) {
            assertThat(head.value(), is(elements[i]));
            head = head.next();
        }
    }
}