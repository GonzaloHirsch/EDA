package graphs;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class GoogleGraph {

    private static String[] urls = { "http://www.google.com", "http://www.wikipedia.org", "www.jgrapht.org"};

    public static void main(String[] args) throws URISyntaxException {

        Graph<URI, DefaultEdge> graph = GraphTypeBuilder.<URI, DefaultEdge> directed()
                .allowingMultipleEdges(false)
                .allowingSelfLoops(false)
                .weighted(false)
                .edgeClass(DefaultEdge.class)
                .buildGraph();

        initGraph(graph);
        initEdges(graph);
        System.out.println(graph);
    }

    private static void initGraph(Graph<URI, DefaultEdge> g) throws URISyntaxException {
        for(String s : urls){
            g.addVertex(new URI(s));
        }
    }

    private static void initEdges(Graph<URI, DefaultEdge> g) throws URISyntaxException {
        g.addEdge(new URI(urls[0]), new URI(urls[1]));
        g.addEdge(new URI(urls[0]), new URI(urls[2]));
        g.addEdge(new URI(urls[1]), new URI(urls[0]));
        g.addEdge(new URI(urls[2]), new URI(urls[1]));
    }
}
