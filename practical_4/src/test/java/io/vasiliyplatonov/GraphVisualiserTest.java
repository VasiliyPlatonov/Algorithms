package io.vasiliyplatonov;

import io.vasiliyplatonov.graph.DiGraph;
import io.vasiliyplatonov.graph.Graph;
import org.junit.Test;

public class GraphVisualiserTest {

    @Test
    public void show() {
        Graph testGraph = new DiGraph();
        GraphVisualiser.show(GraphFiller.randomFillingBigLatin(testGraph, 5));
        GraphVisualiser.show(GraphFiller.randomFillingBigLatin(testGraph, 5));
        GraphVisualiser.show(GraphFiller.randomFillingBigLatin(testGraph, 5));

        System.out.println(testGraph);
    }


}
