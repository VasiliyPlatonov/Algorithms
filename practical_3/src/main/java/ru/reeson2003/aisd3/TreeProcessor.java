package ru.reeson2003.aisd3;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class TreeProcessor<T> {

    private final Tree<T> tree;

    private final BiFunction<Integer, Tree.Position, Consumer<Tree.Node<T>>> dataProcessor;

    private TreeProcessor(Tree<T> tree, BiFunction<Integer, Tree.Position, Consumer<Tree.Node<T>>> dataProcessor) {
        this.dataProcessor = dataProcessor;
        this.tree = tree;
    }

    public static <T> void direct(Tree<T> tree, BiFunction<Integer, Tree.Position, Consumer<Tree.Node<T>>> dataProcessor) {
        new TreeProcessor<>(tree, dataProcessor).direct(tree.root, 0, null);
    }

    public static <T> void backward(Tree<T> tree, BiFunction<Integer, Tree.Position, Consumer<Tree.Node<T>>> dataProcessor) {
        new TreeProcessor<>(tree, dataProcessor).backward(tree.root, 0, null);
    }

    public static <T> void breadthFirstSearch(Tree<T> tree, BiFunction<Integer, Tree.Position, Consumer<Tree.Node<T>>> dataProcessor) {
        class Entry {
            final Tree.Node<T> node;
            final int row;
            final Tree.Position position;

            public Entry(Tree.Node<T> node, int row, Tree.Position position) {
                this.node = node;
                this.row = row;
                this.position = position;
            }
        }
        Queue<Entry> queue = new LinkedList<>();
        queue.offer(new Entry(tree.root, 0, null));
        while (!queue.isEmpty()) {
            Entry current = queue.poll();
            dataProcessor.apply(current.row, current.position).accept(current.node);
            Tree.Position.direct()
                    .forEach(pos -> {
                        Tree.Node<T> node = current.node.getNode(pos);
                        if (node != null)
                            queue.offer(new Entry(node, current.row + 1, pos));
                    });
        }
    }

    private void direct(Tree.Node<T> node, int row, Tree.Position position) {
        Optional.ofNullable(node).ifPresent(n -> {
            dataProcessor.apply(row, position).accept(n);
            Tree.Position.direct().forEach(pos -> processDirect(row, n, pos));
        });
    }

    private void processDirect(int row, Tree.Node<T> n, Tree.Position left) {
        direct(n.getNode(left), row + 1, left);
    }

    private void backward(Tree.Node<T> node, int row, Tree.Position position) {
        Optional.ofNullable(node).ifPresent(n -> {
            Tree.Position.backward().forEach(pos -> processBackward(row, n, pos));
            dataProcessor.apply(row, position).accept(n);
        });
    }

    private void processBackward(int row, Tree.Node<T> n, Tree.Position left) {
        backward(n.getNode(left), row + 1, left);
    }
}
