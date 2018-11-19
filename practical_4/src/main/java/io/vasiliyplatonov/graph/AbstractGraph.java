package io.vasiliyplatonov.graph;


import java.util.*;

abstract class AbstractGraph implements Graph {

    TreeMap<String, Point> points = new TreeMap<>();

    public AbstractGraph() {
    }

    public AbstractGraph(TreeMap<String, Point> points) {
        this.points = points;
    }

    @Override
    public TreeMap<String, Point> getPoints() {
        return points;
    }

    @Override
    public void setPoints(TreeMap<String, Point> set) {
        points = set;
    }

    @Override
    public Point getPoint(String name) {
        return points.get(name);
    }


    @Override
    public Point addPoint(String name) {
        Node node = new Node(name);
        points.put(name, node);
        return node;
    }

    @Override
    public void addPoints(List<String> names) {
        names.forEach(n -> points.put(n, new Node(n)));
    }

    @Override
    public abstract void addEdge(String from, String to);

    @Override
    public void addEdges(String from, List<String> to) {

    }


    @Override
    public int size() {
        return points.size();
    }


    protected class Node implements Point {
        String name;
        LinkedList<Point> neighbors;

        public Node(String name) {
            this.name = name;
            neighbors = new LinkedList<>();
        }

        public Node(String name, LinkedList<Point> neighbors) {
            this.name = name;
            this.neighbors = neighbors;
        }

        @Override
        public String getName() {
            return name;
        }


        @Override
        public LinkedList<Point> getNeighbors() {
            return neighbors;
        }

        @Override
        public Point addNeighbor(String name) {
            neighbors.add(points.get(name));
            return this;
        }

        private void setNeighbors(LinkedList<Point> neighbors) {
            this.neighbors = neighbors;
        }

        @Override
        public Point getNeighborByName(String name) {
            for (Point point : neighbors) {
                if (point.getName().equals(name)) {
                    return point;
                }
            }
            return null;
            // todo: прочитать статью про Optional  https://megahub.me/post/16  https://sboychenko.ru/java-optional/
            //return pointSet.stream().findFirst().get();
        }

        @Override
        public Point popNeighbor() {
            return neighbors.pop();
        }

        @Override
        public boolean isNeighbor(String name) {
            return false;
        }

        @Override
        public Point addEdgeTo(String name) {
            addEdge(this.name, name);
            return this;
        }

        @Override
        public boolean isIsolated() {
            if (!neighbors.isEmpty()) return false;

            for (Point p : points.values()) {
                for (Point n : p.getNeighbors()) {
                    if (n == this)
                        return false;
                }
            }

            return true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            return name != null ? name.equals(node.name) : node.name == null;
        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("{ ");
            neighbors.forEach(n -> sb.append(n.getName()).append(" "));
            sb.append("}");
            return name + " -> " + sb;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Point point : points.values()) {
            sb.append(point).append("\n");
        }
        return sb.toString();
    }
}
