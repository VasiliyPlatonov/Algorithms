package io.vasiliyplatonov.helpers;

import java.util.BitSet;
import java.util.Random;

public class RandomBitSet extends BitSet {

    public RandomBitSet() {
        this.fillRandom();
    }

    public RandomBitSet(int nbits) {
        super(nbits);
        fillRandom();
    }

    private void fillRandom() {
        // Random
        Random rand = new Random();
        for (int i = 0; i < this.size(); i++) {
            if ((rand.nextInt() % 2) == 0)
                this.set(i);
        }

        // Math.random
        /*
            for (int i = 0; i < this.size(); i++) {
            if ((((int)(Math.random() * 100)) % 2) == 0)
                this.set(i);
            }
        */
    }


}
