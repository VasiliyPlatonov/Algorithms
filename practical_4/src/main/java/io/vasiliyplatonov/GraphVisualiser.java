package io.vasiliyplatonov;

import io.vasiliyplatonov.graph.DiGraph;
import io.vasiliyplatonov.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.stream.file.FileSinkImages;

import java.io.IOException;

public class GraphVisualiser {
    private static int countGraph = 0;

    public static void show(Graph myGraph) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        org.graphstream.graph.Graph graph = getGraphStream(myGraph);
        graph.display();
    }

    public static void writePng(Graph myGraph, String fileName) throws IOException {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        FileSinkImages pic = new FileSinkImages(FileSinkImages.OutputType.PNG, FileSinkImages.Resolutions.VGA);
        pic.setLayoutPolicy(FileSinkImages.LayoutPolicy.COMPUTED_FULLY_AT_NEW_IMAGE);
        pic.setRenderer(FileSinkImages.RendererType.SCALA);


        org.graphstream.graph.Graph graph = getGraphStream(myGraph);

        pic.setAutofit(true);
        pic.writeAll(graph, fileName);

    }

    private static org.graphstream.graph.Graph getGraphStream(io.vasiliyplatonov.graph.Graph myGraph) {
        org.graphstream.graph.Graph graph = new MultiGraph(String.valueOf(countGraph++));

        graph.setStrict(false);
        graph.setAutoCreate(true);
        graph.addAttribute("ui.stylesheet", "graph { fill-color: gray; }");
        graph.addAttribute("ui.stylesheet", "node{size: 35px, 35px; shape:circle;fill-color: yellow; text-alignment: center; }");

        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");

        for (Graph.Point p : myGraph.getPoints().values()) {
            if (p.isIsolated()) {
                graph.addNode(p.getName());
            } else {
                for (Graph.Point n : p.getNeighbors()) {
                    if (myGraph instanceof DiGraph)
                        graph.addEdge(p.getName() + n.getName(), p.getName(), n.getName(), true);
                    else
                        graph.addEdge(p.getName() + n.getName(), p.getName(), n.getName(), false);
                }
            }
        }

        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }
        return graph;
    }
}
