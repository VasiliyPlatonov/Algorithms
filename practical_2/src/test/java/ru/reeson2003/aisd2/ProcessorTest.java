package ru.reeson2003.aisd2;

import org.junit.Test;
import ru.reeson2003.aisd2.set.ArraySet;
import ru.reeson2003.aisd2.set.MySet;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProcessorTest {

    @Test
    public void calculate() {
        Processor processor = new Processor();
        char[] a = "йцукенггшщзхъ".toCharArray();
        MySet mySetA = processor.create(ArraySet.class, a);
        char[] b = "кенгшщзхъфывапр".toCharArray();
        MySet mySetB = processor.create(ArraySet.class, b);
        char[] aAndB = "кенггшщзхъ".toCharArray();
        char[] c = "нгшщзхъфыва".toCharArray();
        MySet mySetC = processor.create(ArraySet.class, c);
        char[] d = "щзхъфывапрол".toCharArray();
        MySet mySetD = processor.create(ArraySet.class, d);
        char[] cAndD = "щзхъфыва".toCharArray();
        char[] actual = "кенгш".toCharArray();
        MySet result = processor.calculate(mySetA, mySetB, mySetC, mySetD);
        char[] resultChars = result.toString().toCharArray();
        assertThat(resultChars, is(actual));
    }
}