package io.vasiliyplatonov.graph;


import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public interface Graph {
    int size();

    TreeMap<String, Point> getPoints();

    Point getPoint(String name);

    void setPoints(TreeMap<String, Point> set);

    Point addPoint(String name);

    void addPoints(List<String> names);

    void addEdge(String from, String to);

    void addEdges(String from, List<String> to);


    interface Point {
        LinkedList<Point> getNeighbors();

        Point addNeighbor(String name);

        Point popNeighbor();

        Point getNeighborByName(String name);

        boolean isNeighbor(String name);

        Point addEdgeTo(String name);

        String getName();

        boolean isIsolated();

    }
}
