package io.vasiliyplatonov;

import io.vasiliyplatonov.graph.DiGraph;
import io.vasiliyplatonov.graph.Graph;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GraphFillerTest {

    @Test
    public void randomFillGraph() {
        Graph graph1 = new DiGraph();
        int nVertex = 5;


        assertThat(GraphFiller.randomFillingBigLatin(graph1, nVertex).getPoints())
                .isNotNull()
                .hasSize(nVertex);

    }

}
