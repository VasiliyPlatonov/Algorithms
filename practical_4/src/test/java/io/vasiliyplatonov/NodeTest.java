package io.vasiliyplatonov;

import io.vasiliyplatonov.graph.DiGraph;
import io.vasiliyplatonov.graph.Graph;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NodeTest {
    private static Graph graph = new DiGraph();

    @Test
    public void isIsolated() {
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
        graph.addPoint("Z");

        System.out.println(graph);

        assertThat(graph.getPoint("Z").isIsolated()).isTrue();
        assertThat(graph.getPoint("X").isIsolated()).isTrue();
        assertThat(graph.getPoint("A").isIsolated()).isFalse();
        assertThat(graph.getPoint("B").isIsolated()).isFalse();

    }


}
