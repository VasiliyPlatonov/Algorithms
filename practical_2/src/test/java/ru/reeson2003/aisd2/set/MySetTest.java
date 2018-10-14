package ru.reeson2003.aisd2.set;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MySetTest {

    @Test
    public void and() {
        char[] a = "йцукенгшщзфывапро".toCharArray();
        char[] b = "нгшщзфывапролдджя".toCharArray();
        char[] actual = "нгшщзфывапро".toCharArray();

        ArraySet arraySetA = MySet.arraySet(a);
        ArraySet arraySetB = MySet.arraySet(b);
        check(arraySetA.and(arraySetB), actual);

        LinkedListSet listSetA = MySet.linkedListSet(a);
        LinkedListSet listSetB = MySet.linkedListSet(b);
        check(listSetA.and(listSetB), actual);

        UniversumMappedSet uniSetA = MySet.universumMappedSet(a);
        UniversumMappedSet uniSetB = MySet.universumMappedSet(b);
        check(uniSetA.and(uniSetB), actual);
    }

    private <T extends MySet<T>> void check(T result, char[] actual) {
        char[] resultSorted = result.toString().toCharArray();
        Arrays.sort(resultSorted);
        Arrays.sort(actual);
        assertThat(resultSorted, is(actual));
    }

    @Test
    public void not() {
        char[] a = "йцукенгшщзфывапро".toCharArray();
        char[] b = "нгшщзфывапролдджя".toCharArray();
        char[] actual = "йцуке".toCharArray();

        ArraySet arraySetA = MySet.arraySet(a);
        ArraySet arraySetB = MySet.arraySet(b);
        check(arraySetA.not(arraySetB), actual);

        LinkedListSet listSetA = MySet.linkedListSet(a);
        LinkedListSet listSetB = MySet.linkedListSet(b);
        check(listSetA.not(listSetB), actual);

        UniversumMappedSet uniSetA = MySet.universumMappedSet(a);
        UniversumMappedSet uniSetB = MySet.universumMappedSet(b);
        check(uniSetA.not(uniSetB), actual);
    }

    @Test
    public void uniqueOnly() {
        char[] set = "йцууукккенгшщз".toCharArray();
        char[] actual = "йцукенгшщз".toCharArray();
        char[] result = MySet.uniqueOnly(set);
        assertThat(result, is(actual));
    }
}