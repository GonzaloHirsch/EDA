package graphs;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;

public class main {

    public static void main(String[] args){

        String[] nodes = {"HNL", "LAX", "SFO", "ORD", "PVD", "LGA", "MIA", "DFW"};

        Graph<String, DefaultWeightedEdge> flight = GraphTypeBuilder.<String, DefaultWeightedEdge> undirected()
                .allowingMultipleEdges(false)
                .allowingSelfLoops(false)
                .weighted(true)
                .edgeClass(DefaultWeightedEdge.class)
                .buildGraph();

        Graph<String, DefaultWeightedEdge> flightDouble = GraphTypeBuilder.<String, DefaultWeightedEdge> directed()
                .allowingMultipleEdges(false)
                .allowingSelfLoops(false)
                .weighted(true)
                .edgeClass(DefaultWeightedEdge.class)
                .buildGraph();

        initNodes(flight, nodes);
        initNodes(flightDouble, nodes);
        initEdges(flight);
        initDoubleEdges(flightDouble);

        System.out.println(flight);
        System.out.println(flightDouble);
        minDistance(flightDouble, "PVD", nodes);
    }

    private static void initEdges(Graph<String, DefaultWeightedEdge> g){
        g.setEdgeWeight(g.addEdge("HNL", "LAX"), 2555);
        g.setEdgeWeight(g.addEdge("LAX", "SFO"), 337);
        g.setEdgeWeight(g.addEdge("LAX", "DFW"), 1233);
        g.setEdgeWeight(g.addEdge("LAX", "ORD"), 1743);
        g.setEdgeWeight(g.addEdge("SFO", "ORD"), 1843);
        g.setEdgeWeight(g.addEdge("DFW", "ORD"), 802);
        g.setEdgeWeight(g.addEdge("DFW", "LGA"), 1387);
        g.setEdgeWeight(g.addEdge("DFW", "MIA"), 1120);
        g.setEdgeWeight(g.addEdge("MIA", "LGA"), 1099);
        g.setEdgeWeight(g.addEdge("MIA", "PVD"), 1205);
        g.setEdgeWeight(g.addEdge("ORD", "PVD"), 849);
        g.setEdgeWeight(g.addEdge("LGA", "PVD"), 142);
    }

    private static void initNodes(Graph<String, DefaultWeightedEdge> g, String[] nodes){
        for (String node : nodes) g.addVertex(node);
    }

    private static void minDistance(Graph<String, DefaultWeightedEdge> g, String source, String[] targets){
        for (String target : targets) System.out.println("Distance " + source + " --> " + target + " : " + DijkstraShortestPath.findPathBetween(g, source, target).getWeight());
    }

    private static void initDoubleEdges(Graph<String, DefaultWeightedEdge> g){
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
