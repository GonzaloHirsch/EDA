package graphs;


import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;

public class TestGUI extends JApplet
{
    private static final long serialVersionUID = 2202072534703043194L;
    private static String[] urls = { "http://www.google.com", "http://www.wikipedia.org", "www.jgrapht.org"};

    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

    /**
     * An alternative starting point for this demo, to also allow running this applet as an
     * application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        TestGUI applet = new TestGUI();
        applet.init();

        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setTitle("JGraphT Adapter to JGraphX Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /*
    public class PrintableWeightedEdge extends DefaultWeightedEdge{

        public PrintableWeightedEdge(){
            super();
        }

        @Override
        public String toString(){
            return String.valueOf(this.getWeight());
        }
    }
    */

    @Override
    public void init()
    {
        // completar aca!!!!
        Graph<URI, DefaultEdge> g= null;
        try {
            g = createModel();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // completar aca!!!!
        Graph<String, PrintableWeightedEdge> alt= null;
        alt = createAlternativeModel();
        // create a visualization using JGraph, via an adapter
        JGraphXAdapter jgxAdapter = new JGraphXAdapter<>(alt);

        setPreferredSize(DEFAULT_SIZE);
        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);
        getContentPane().add(component);
        resize(DEFAULT_SIZE);

        // positioning via jgraphx layouts
        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);

        // center the circle
        int radius = 100;
        layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
        layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
        layout.setRadius(radius);
        layout.setMoveCircle(true);

        layout.execute(jgxAdapter.getDefaultParent());
        // that's all there is to it!...
    }

    private Graph<URI, DefaultEdge> createModel() throws URISyntaxException {
        Graph<URI, DefaultEdge> graph = GraphTypeBuilder.<URI, DefaultEdge> directed()
                    .allowingMultipleEdges(false)
                    .allowingSelfLoops(false)
                    .weighted(false)
                    .edgeClass(DefaultEdge.class)
                    .buildGraph();

        initGraph(graph);
        initEdges(graph);
        return graph;
    }

    private void initGraph(Graph<URI, DefaultEdge> g) throws URISyntaxException {
        for(String s : urls){
            g.addVertex(new URI(s));
        }
    }

    private void initEdges(Graph<URI, DefaultEdge> g) throws URISyntaxException {
        g.addEdge(new URI(urls[0]), new URI(urls[1]));
        g.addEdge(new URI(urls[0]), new URI(urls[2]));
        g.addEdge(new URI(urls[1]), new URI(urls[0]));
        g.addEdge(new URI(urls[2]), new URI(urls[1]));
    }

    public Graph<String, PrintableWeightedEdge> createAlternativeModel(){

        String[] nodes = {"HNL", "LAX", "SFO", "ORD", "PVD", "LGA", "MIA", "DFW"};

        Graph<String, PrintableWeightedEdge> flightDouble = GraphTypeBuilder.<String, PrintableWeightedEdge> directed()
                .allowingMultipleEdges(false)
                .allowingSelfLoops(false)
                .weighted(true)
                .edgeClass(PrintableWeightedEdge.class)
                .buildGraph();

        initNodes(flightDouble, nodes);
        initDoubleEdges(flightDouble);

        return flightDouble;
    }

    private static void initNodes(Graph<String, PrintableWeightedEdge> g, String[] nodes){
        for (String node : nodes) g.addVertex(node);
    }

    private static void initDoubleEdges(Graph<String, PrintableWeightedEdge> g){
        g.setEdgeWeight(g.addEdge("HNL", "LAX"), 2555);
        g.setEdgeWeight(g.addEdge("LAX", "HNL"), 2555);
        g.setEdgeWeight(g.addEdge("LAX", "SFO"), 337);
        g.setEdgeWeight(g.addEdge("SFO", "LAX"), 100);
        g.setEdgeWeight(g.addEdge("DFW", "LAX"), 1233);
        g.setEdgeWeight(g.addEdge("LAX", "ORD"), 1743);
        g.setEdgeWeight(g.addEdge("SFO", "ORD"), 1843);
        g.setEdgeWeight(g.addEdge("ORD", "SFO"), 100);
        g.setEdgeWeight(g.addEdge("ORD", "DFW"), 802);
        g.setEdgeWeight(g.addEdge("DFW", "LGA"), 1387);
        g.setEdgeWeight(g.addEdge("MIA", "DFW"), 1120);
        g.setEdgeWeight(g.addEdge("MIA", "LGA"), 1099);
        g.setEdgeWeight(g.addEdge("MIA", "PVD"), 1205);
        g.setEdgeWeight(g.addEdge("PVD", "MIA"), 1205);
        g.setEdgeWeight(g.addEdge("ORD", "PVD"), 849);
        g.setEdgeWeight(g.addEdge("LGA", "PVD"), 142);
        g.setEdgeWeight(g.addEdge("PVD", "LGA"), 999999);
    }

}