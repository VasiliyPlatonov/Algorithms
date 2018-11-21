package io.vasiliyplatonov;

import io.vasiliyplatonov.graph.DiGraph;
import io.vasiliyplatonov.graph.Graph;

import java.io.IOException;

import static java.io.File.separator;

public class AppStart {
    private static final int N_VERTEX = 4;

    public static void main(String[] args) throws IOException {

        int i = 0;
        while (!(i == 5)) {
            DiGraph graph = (DiGraph) GraphFiller.randomFillingBigLatin(new DiGraph(), N_VERTEX);

            String path = graph.getEulerianPath();

            if (!(path == null) && !path.isEmpty()) {
                i++;
                System.err.println("\ngraph № " + i);
                System.err.println(graph);
                System.err.println(path);

                GraphVisualiser.writePng(graph,
                        "practical_4" + separator +
                                "graphscreens" + separator +
                                i + ".png");

                System.err.println("------------------------------");
            }
        }

    }
}
