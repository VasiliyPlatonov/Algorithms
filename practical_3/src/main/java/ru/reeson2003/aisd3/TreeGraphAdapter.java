package ru.reeson2003.aisd3;

import io.vasiliyplatonov.GraphVisualiser;
import io.vasiliyplatonov.graph.Graph;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.io.File.separator;

public class TreeGraphAdapter implements Graph {
    private final Tree<Character> origin;

    public TreeGraphAdapter(Tree<Character> origin) {
        this.origin = origin;
    }

    static void writeTree(Tree<Character> tree, String fileName) throws IOException {
        TreeGraphAdapter adapter = new TreeGraphAdapter(tree);
        GraphVisualiser.writePng(adapter,
                "practical_3" + separator +
                        "treescreens" + separator +
                        fileName + ".png");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TreeMap<String, Point> getPoints() {
        TreeMap<String, Point> result = new TreeMap<>();
        TreeProcessor.direct(origin, (row, pos) -> node -> {
            PointNodeAdapter adapter = new PointNodeAdapter(node);
            result.put(adapter.getName(), adapter);
        });
        return result;
    }

    @Override
    public Point getPoint(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPoints(TreeMap<String, Point> set) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Point addPoint(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addPoints(List<String> names) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addEdge(String from, String to) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addEdges(String from, List<String> to) {
        throw new UnsupportedOperationException();
    }

    private class PointNodeAdapter implements Graph.Point {

        private final Tree.Node<Character> originNode;

        private PointNodeAdapter(Tree.Node<Character> originNode) {
            this.originNode = originNode;
        }

        @Override
        public LinkedList<Point> getNeighbors() {
            return Tree.Position.direct()
                    .map(originNode::getNode)
                    .filter(Objects::nonNull)
                    .map(PointNodeAdapter::new)
                    .collect(Collectors.toCollection(LinkedList::new));
        }

        @Override
        public Point addNeighbor(String name) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Point popNeighbor() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Point getNeighborByName(String name) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isNeighbor(String name) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Point addEdgeTo(String name) {
            throw new UnsupportedOperationException();
        }

        @Override
        public String getName() {
            return String.valueOf(originNode.data);
        }

        @Override
        public boolean isIsolated() {
            return false;
        }
    }
}
