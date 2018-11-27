package ru.reeson2003.aisd3;

import java.io.IOException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Program {
    public static final int MAX_WIDTH = 80;

    public static void main(String[] args) {
        StringCharacterIterator chars = new StringCharacterIterator("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        int maxRow = 4;
        Tree<Character> tree = measure(() -> createTree(chars, maxRow),
                time -> System.out.println("Tree created in [" + time + "] nanoseconds"));
        measure(() -> printTree(tree, MAX_WIDTH / 2, 4, MAX_WIDTH),
                time -> System.out.println("Tree printed in [" + time + "] nanoseconds"));
        writeTreeImage(tree);
        Integer counter = measure(() -> processTree(tree),
                time -> System.out.println("Tree processed in [" + time + "] nanoseconds"));
        System.out.println(counter + " nodes has parent");
    }

    private static <T> T measure(Supplier<T> command, Consumer<Long> timeConsumer) {
        long start = System.nanoTime();
        T t = command.get();
        long duration = System.nanoTime() - start;
        timeConsumer.accept(duration);
        return t;
    }

    private static <T> void measure(Runnable command, Consumer<Long> timeConsumer) {
        measure(() -> {
            command.run();
            return null;
        }, timeConsumer);
    }

    private static Integer processTree(Tree<Character> tree) {
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
        return counter.get();
    }

    private static Tree<Character> createTree(StringCharacterIterator chars, int maxRow) {
        return Tree.directTree((row, pos) -> row < maxRow && chars.current() != CharacterIterator.DONE, () -> {
            char result = chars.current();
            chars.next();
            return result;
        });
    }

    private static void writeTreeImage(Tree<Character> tree) {
        try {
            TreeGraphAdapter.writeTree(tree, "tree");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
