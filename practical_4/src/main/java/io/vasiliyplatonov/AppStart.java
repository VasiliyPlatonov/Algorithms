package io.vasiliyplatonov;

import io.vasiliyplatonov.graph.DiGraph;
import io.vasiliyplatonov.graph.Graph;

import java.io.IOException;

import static java.io.File.separator;

public class AppStart {
    private static final int N_VERTEX = 4;


    public static void main(String[] args) throws IOException {
        int countGraph = 0;
        int i = 0;

        long pathTime = 0L;
        long pathBefore;
        long pathAfter;
        long before;
        long time;

        before = System.nanoTime();
        while (!(i == 5)) {
            DiGraph graph = (DiGraph) GraphFiller.randomFillingBigLatin(new DiGraph(), N_VERTEX);
            countGraph++;

            pathBefore = System.nanoTime();
            String path = graph.getEulerianPath();
            pathAfter = System.nanoTime();

            if (!(path == null) && !path.isEmpty()) {
                i++;
                pathTime += pathAfter - pathBefore;

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
        time = System.nanoTime() - before;

        System.err.println("Всего создано графов: " + countGraph);
        System.err.println("Среднее время поиска эйлеровского цикла: " + pathTime / countGraph);
        System.err.println("Было потрачено времени: " + (time));
        System.err.flush();
    }
}
