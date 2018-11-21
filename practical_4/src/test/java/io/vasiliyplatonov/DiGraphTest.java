package io.vasiliyplatonov;


import io.vasiliyplatonov.graph.DiGraph;
import io.vasiliyplatonov.graph.Graph;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class DiGraphTest {
    private static Graph graph;

    @Before
    public void setUp() {
        graph = new DiGraph();
    }

    @Test
    public void addPoints() {
        ArrayList<String> pointsName = new ArrayList<>(Arrays.asList("B", "C", "D"));
        graph.addPoints(pointsName);
        assertThat(graph.getPoints())
                .isNotNull()
                .isNotEmpty();

        graph.getPoints().values()
                .forEach(point -> assertThat(point.getName())
                        .isNotNull()
                        .isNotEmpty()
                        .isIn(pointsName));

    }

    @Test
    public void addPoint() {
        String name = "A";
        graph.addPoint(name);

        assertThat(graph.getPoints()).isNotNull().isNotEmpty();
        assertThat(graph.getPoints().values()).isNotNull();
        assertThat(graph.getPoints().get(name).getName()).isNotNull().isEqualTo(name);
        assertThat(graph.getPoints().get(name).getNeighbors()).isEmpty();

    }

    @Test
    public void addEdge() {
        String from = "A";
        String to = "B";

        graph.addEdge(from, to);
        assertThat(graph.getPoints().values()).hasSize(2);

        Graph.Point pointA = graph.getPoint(from);
        Graph.Point pointB = graph.getPoint(to);

        assertThat(pointA.getNeighbors()).hasSize(1).contains(pointB);

        graph.addEdge(from, from);
        assertThat(pointA.getNeighbors())
                .hasSize(2)
                .contains(pointA, pointB);

        graph.addEdge(from, from);
        assertThat(pointA.getNeighbors())
                .hasSize(3)
                .contains(pointA, pointB);

        graph.addEdge(to, to);

        graph.getPoints().values().forEach(p -> assertThat(p.getName()).isIn(from, to));
        graph.getPoints().values().forEach(p -> assertThat(p.getName()).isNotIn("C", "D"));
        System.out.println("graph = " + graph);
    }

    @Test
    public void addEdgeTo() {
        String from = "A";
        String to = "B";

        assertThat(graph.addPoint(from).addEdgeTo(to).getNeighbors())
                .hasSize(1)
                .contains(graph.getPoint(to));

        graph.getPoints().values().forEach(p -> assertThat(p.getName()).isIn(from, to));
        graph.getPoints().values().forEach(p -> assertThat(p.getName()).isNotIn("C", "D"));

    }

    @Test
    public void addEdges() {
        String from = "A";
        ArrayList<String> pointsName = new ArrayList<>(Arrays.asList("B", "C", "D"));
        graph.addEdges(from, pointsName);
        assertThat(graph.getPoints().values()).hasSize(4);
    }

    @Test
    public void getEulerianPath() {
        DiGraph graph = getEulerianGraph();

        assertThat(getEulerianGraph().getEulerianPath())
                .isNotNull()
                .isNotEmpty();

        assertThat(getNonEulerianGraph().getEulerianPath())
                .isNull();

        System.out.println(getAllNodeIsolatadGraph().getEulerianPath());
    }

    @Test
    public void inDegree() {
        Graph graph = getEulerianGraph();

        assertThat(((DiGraph.DiNode) graph.getPoint("A")).inDegree())
                .isNotNull()
                .isNotNegative()
                .isEqualTo(2);

        assertThat(((DiGraph.DiNode) graph.getPoint("D")).inDegree())
                .isNotNull()
                .isNotNegative()
                .isEqualTo(1);

        Graph graph2 = getNonEulerianGraph();
        System.out.println(graph2);
        assertThat(((DiGraph.DiNode) graph2.getPoint("C")).inDegree())
                .isNotNull()
                .isNotNegative()
                .isEqualTo(2);
    }

    @Test
    public void outDegree() {
        Graph graph = getEulerianGraph();

        assertThat(((DiGraph.DiNode) graph.getPoint("A")).outDegree())
                .isNotNull()
                .isNotNegative()
                .isEqualTo(2);

        assertThat(((DiGraph.DiNode) graph.getPoint("C")).outDegree())
                .isNotNull()
                .isNotNegative()
                .isEqualTo(1);
    }

    @Test
    public void isEulerianGraph() {
        DiGraph eulerianGraph = getEulerianGraph();
        DiGraph nonEulerianGraph = getNonEulerianGraph();
        DiGraph allNodeIsolated = getAllNodeIsolatadGraph();

        assertThat(eulerianGraph.isEulerianGraph()).isTrue();
        assertThat(nonEulerianGraph.isEulerianGraph()).isFalse();
        assertThat(allNodeIsolated.isEulerianGraph()).isFalse();
    }

    /**
     * @see " http://images.myshared.ru/5/472523/slide_22.jpg "
     */
    private DiGraph getEulerianGraph() {
        DiGraph graph = new DiGraph();

        graph.addEdge("A", "E");
        graph.addEdge("A", "D");
        graph.addEdge("E", "F");
        graph.addEdge("D", "F");
        graph.addEdge("F", "B");
        graph.addEdge("F", "C");
        graph.addEdge("C", "A");
        graph.addEdge("B", "A");

        return graph;
    }

    private DiGraph getNonEulerianGraph() {
        DiGraph graph = new DiGraph();

        graph.addEdge("A", "E");
        graph.addEdge("A", "D");
        graph.addEdge("E", "F");
        graph.addEdge("D", "F");
        graph.addEdge("F", "B");
        graph.addEdge("F", "C");
        graph.addEdge("C", "A");
        graph.addEdge("B", "C");
        graph.addPoint("X");

        return graph;
    }

    private DiGraph getAllNodeIsolatadGraph() {
        DiGraph graph = new DiGraph();

        graph.addPoint("A");
        graph.addPoint("B");
        graph.addPoint("C");
        graph.addPoint("D");
        graph.addPoint("E");
        graph.addPoint("F");
        graph.addPoint("X");

        return graph;
    }
}
