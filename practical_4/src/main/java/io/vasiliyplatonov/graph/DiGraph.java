package io.vasiliyplatonov.graph;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class DiGraph extends AbstractGraph {

    @Override
    public Point addPoint(String name) {
        DiNode node = new DiNode(name);
        points.put(name, node);
        return node;
    }

    @Override
    public void addEdge(String from, String to) {
        if (isNull(points.get(from))) addPoint(from);
        if (isNull(points.get(to))) addPoint(to);
        points.get(from).addNeighbor(to);
    }

    @Override
    public void addEdges(String from, List<String> to) {
        if (isNull(points.get(from))) addPoint(from);
        // todo: переписать более оптимально!
        to.forEach(t -> {
            addEdge(from, t);
        });
    }

    /**
     * В ориентированном графе существует эйлеров цикл тогда и только тогда, когда:
     * Входная степень любой вершины равна ее выходной степени.
     */
    public boolean isEulerianGraph() {
        int countIsolatedNode = 0;

        for (Point point : points.values()) {
            if (!point.isIsolated()) {
                if (((DiNode) point).outDegree() != ((DiNode) point).inDegree())
                    return false;
            } else countIsolatedNode++;
        }

        if (countIsolatedNode >= (points.size() - 2)) {
            return false;
        }

        return true;
    }


    /*
    * стек S ⇐ любая вершина из V;
    *  пока S != ∅:
    *    посмотреть вершину стека: v := Top(S);
    *    если ∃u ∈ A(v):
    *        втолкнуть u в S;
    *        E := E \ (u, v);
    *    иначе:
    *        удалить вершину из S;
    *        v — следующая вершина цикла;
    * */
    public String getEulerianPath() {
        StringBuilder sb = new StringBuilder();
        if (!this.isEulerianGraph()) return null;

        DiGraph copyG = new DiGraph().copy(this);
        final LinkedList<Point> copyPoints = new LinkedList<>(copyG.points.values());

        LinkedList<Point> stack = new LinkedList<>();

        // excluding isolated node
        while (stack.isEmpty()) {
            Point p = copyPoints.pop();
            if (!p.isIsolated()) {
                stack.add(p);
            }
        }

        while (!stack.isEmpty()) {
            Point top = stack.peek();
            if (top.getNeighbors().isEmpty()) {
                sb.append(top.getName());
                stack.pop();
            } else {
                Point n = top.popNeighbor();
                stack.push(n);
            }
        }
        return sb.reverse().toString();
    }


    public class DiNode extends Node {

        public DiNode(String name) {
            super(name);
        }

        public DiNode(String name, LinkedList<Point> neighbors) {
            super(name, neighbors);
        }

        public int inDegree() {
            int res = 0;

            for (Point p : points.values()) {
                for (Point n : p.getNeighbors()) {
                    if (n == this)
                        res++;
                }
            }
            return res;
        }

        public int outDegree() {
            return neighbors.size();
        }
    }

    private DiGraph copy(DiGraph diGraph) {

        for (Map.Entry<String, Point> entry : diGraph.points.entrySet()) {
            this.addPoint(entry.getValue().getName());
        }

        for (Point point : diGraph.points.values()) {
            this.addEdges(point.getName(), point.getNeighbors().stream().map(Point::getName).collect(Collectors.toList()));
        }
        return this;
    }
}
