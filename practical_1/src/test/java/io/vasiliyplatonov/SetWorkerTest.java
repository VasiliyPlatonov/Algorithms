package io.vasiliyplatonov;


import io.vasiliyplatonov.helpers.sets.SetWorker;
import org.junit.Test;

import static io.vasiliyplatonov.helpers.Universe.LOW_RUS_LETTERS;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class SetWorkerTest {

//    @Test
//    public void isSetIncludedInUniverse() {
//        // positive tests
//        String set = "фбаыфщоффоы";
//        assertThat(SetWorker.isSetIncludedInUniverse(LOW_RUS_LETTERS, set)).isTrue();
//
//
//        // negative tests
//        String setWithNum = "фбаыфщо23ффоы";
//        assertThat(SetWorker.isSetIncludedInUniverse(LOW_RUS_LETTERS, setWithNum)).isFalse();
//
//        String setWithLatin = "фбаыфщоdafффоы";
//        assertThat(SetWorker.isSetIncludedInUniverse(LOW_RUS_LETTERS, setWithLatin)).isFalse();
//
//        setWithLatin = "fsaassa";
//        assertThat(SetWorker.isSetIncludedInUniverse(LOW_RUS_LETTERS, setWithLatin)).isFalse();
//
//        String setWithSpace = "фбаыфщ ффоы";
//        assertThat(SetWorker.isSetIncludedInUniverse(LOW_RUS_LETTERS, setWithSpace)).isFalse();
//    }
//
//    @Test
//    public void getNormalizeSet() {
//        String testStr = "adsьвыфол31дф";
//        String expected = "ьвыфолдф";
//
//        assertThat(SetWorker.getNormalizeSet(LOW_RUS_LETTERS, testStr)).isEqualTo(expected);
//    }
//


}
