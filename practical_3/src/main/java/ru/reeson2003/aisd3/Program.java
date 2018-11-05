package ru.reeson2003.aisd3;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.concurrent.atomic.AtomicInteger;

public class Program {

    public static final int MAX_WIDTH = 80;

    public static void main(String[] args) {
        StringCharacterIterator chars = new StringCharacterIterator("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        int maxRow = 4;
        Tree<Character> tree = Tree.directTree((row, pos) -> row < maxRow && chars.current() != CharacterIterator.DONE, () -> {
            char result = chars.current();
            chars.next();
            return result;
        });
        printTree(tree, MAX_WIDTH / 2, 4, MAX_WIDTH);
        AtomicInteger counter = new AtomicInteger(0);
        TreeProcessor.breadthFirstSearch(tree, (row, pos) -> {
            System.out.print("Row: " + row + ", ");
            System.out.print("Position: " + pos + ", ");
            return n -> {
                System.out.print("Data: " + n.data);
                if (n.getParent() != null) {
                    counter.incrementAndGet();
                    System.out.print(" <-- has parent");
                }
                System.out.println();
            };
        });
        System.out.println(counter.get() + " nodes has parent");
    }

    @SuppressWarnings("unchecked")
    static <T> void printTree(Tree<T> tTree, int offset, int maxrow, int maxWidth) {
        T[][] matrix = (T[][]) new Object[maxrow][maxWidth];
        outNodes(tTree.root, 1, offset, maxrow, maxWidth, offset, matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                T obj = matrix[i][j];
                if (obj == null)
                    System.out.print('.');
                else
                    System.out.print(obj);
            }
            System.out.println();
        }
    }

    static <T> void outNodes(Tree.Node<T> v, int r, int c, int maxrow, int maxWidth, int offset, T[][] matrix) {
        if (r > 0 && c > 0 && (c < maxWidth)) {
            matrix[r - 1][c - 1] = v.data; // вывод метки
        }
        if (r < maxrow) {
            if (v.getNode(Tree.Position.LEFT) != null)
                outNodes(v.getNode(Tree.Position.LEFT), r + 1, c - (offset >> r), maxrow, maxWidth, offset, matrix); //левый сын
            if (v.getNode(Tree.Position.MIDDLE) != null)
                outNodes(v.getNode(Tree.Position.MIDDLE), r + 1, c, maxrow, maxWidth, offset, matrix);    //средний сын (если нужно)
            if (v.getNode(Tree.Position.RIGHT) != null)
                outNodes(v.getNode(Tree.Position.RIGHT), r + 1, c + (offset >> r), maxrow, maxWidth, offset, matrix); //правый сын
        }
    }
}
