package ru.reeson2003.aisd3;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Tree<T> {
    public Node<T> root;

    private Tree() {
    }

    public static <T> Tree<T> directTree(BiFunction<Integer, Position, Boolean> nodeCreateResolver, Supplier<T> dataSupply) {
        Tree<T> tree = new Tree<>();
        tree.root = fillDirect(nodeCreateResolver, dataSupply, null, 0, null);
        return tree;
    }

    public static <T> Tree<T> backwardTree(BiFunction<Integer, Position, Boolean> nodeCreateResolver, Supplier<T> dataSupply) {
        Tree<T> tree = new Tree<>();
        tree.root = fillBackward(nodeCreateResolver, dataSupply, null, 0, null);
        return tree;
    }

    private static <T> Node<T> fillDirect(BiFunction<Integer, Position, Boolean> nodeCreateResolver, Supplier<T> dataSupply, Node<T> parent, int depth, Position position) {
        if (nodeCreateResolver.apply(depth, position)) {
            Node<T> res = new Node<>();
            res.parent = parent;
            res.data = dataSupply.get();
            Position.direct().forEach(pos -> res.children.put(pos, fillDirect(nodeCreateResolver, dataSupply, res, depth + 1, pos)));
            return res;
        }
        return null;
    }

    private static <T> Node<T> fillBackward(BiFunction<Integer, Position, Boolean> nodeCreateResolver, Supplier<T> dataSupply, Node<T> parent, int depth, Position position) {
        if (nodeCreateResolver.apply(depth, position)) {
            Node<T> res = new Node<>();
            res.parent = parent;
            Position.backward().forEach(pos -> res.children.put(pos, fillBackward(nodeCreateResolver, dataSupply, res, depth + 1, pos)));
            res.data = dataSupply.get();
            return res;
        }
        return null;
    }

    enum Position {
        LEFT, MIDDLE, RIGHT;

        public static Stream<Position> direct() {
            return Stream.of(LEFT, MIDDLE, RIGHT);
        }

        public static Stream<Position> backward() {
            return Stream.of(RIGHT, MIDDLE, LEFT);
        }
    }

    public static final class Node<T> {
        public T data;
        private Node<T> parent;
        private Map<Position, Node<T>> children = new HashMap<>();

        public void setNode(Position position, Node<T> node) {
            children.put(position, node);
        }

        public Node<T> getNode(Position position) {
            return children.get(position);
        }

        public Node<T> getParent() {
            return parent;
        }
    }
}
