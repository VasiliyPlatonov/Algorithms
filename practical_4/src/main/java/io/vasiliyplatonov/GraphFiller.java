package io.vasiliyplatonov;

import io.vasiliyplatonov.graph.Graph;

import java.util.Random;

public class GraphFiller {

    private static final String BIG_LATIN = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Random rand = new Random();

    public static Graph randomFillingBigLatin(Graph graph, int nVertex) {

        if (nVertex > 26) {
            throw new RuntimeException("Too more vertex");
        }

        for (int i = 0; i < nVertex; i++) {
            graph.addPoint(String.valueOf(BIG_LATIN.charAt(i)));
        }


        for (Graph.Point point : graph.getPoints().values()) {
            int r = rand.nextInt(nVertex);
            int r2 = rand.nextInt(nVertex);

            if (r < r2) {
                for (int i = r; i <= r2; i++) {
                    point.addEdgeTo(String.valueOf(BIG_LATIN.charAt(i)));
                }
            } else if (r > r2) {
                for (int i = r2; i <= r; i++) {
                    point.addEdgeTo(String.valueOf(BIG_LATIN.charAt(i)));
                }
            }
        }
        return graph;
    }

}
