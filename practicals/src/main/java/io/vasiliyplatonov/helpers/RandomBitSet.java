package main.java.io.vasiliyplatonov.helpers;

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
        Random rand = new Random();
        for (int i = 0; i < this.size(); i++) {
            if ((rand.nextInt() % 2) == 0)
                this.set(i);
        }
    }

    public void showAsBinary() {
        StringBuilder builder = new StringBuilder("{ ");
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i)) builder.append("1, ");
            else builder.append("0, ");
        }
        builder.deleteCharAt(builder.length() - 2).append("}");
        System.out.println(builder);
    }


    public void showAsBinary(int fromIndex, int toIndex) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex < 0: " + fromIndex);
        if (toIndex < 0)
            throw new IndexOutOfBoundsException("toIndex < 0: " + toIndex);
        if (fromIndex > toIndex)
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex +
                    " > toIndex: " + toIndex);

        StringBuilder builder = new StringBuilder("{ ");
        for (int i = fromIndex; i < toIndex; i++) {
            if (this.get(i)) builder.append("1, ");
            else builder.append("0, ");
        }
        builder.deleteCharAt(builder.length() - 2).append("}");
        System.out.println(builder);
    }

    public void showAsBinary(int toIndex) {
        if (toIndex < 0)
            throw new IndexOutOfBoundsException("toIndex < 0: " + toIndex);
        if (toIndex == 0) {
            System.out.println(this.get(0));
        }

        StringBuilder builder = new StringBuilder("{ ");
        for (int i = 0; i < toIndex; i++) {
            if (this.get(i)) builder.append("1, ");
            else builder.append("0, ");
        }
        builder.deleteCharAt(builder.length() - 2).append("}");
        System.out.println(builder);
    }

}
