package graphs;

import org.jgrapht.graph.DefaultWeightedEdge;

public class PrintableWeightedEdge extends DefaultWeightedEdge {
    @Override
    public String toString(){
        return String.valueOf(this.getWeight());
    }
}
