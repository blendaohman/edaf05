import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Vertex {

    private String label;
    private Set<Edge> edges; //collection of edges to neighbors

    public Vertex(String pageObject) {
        this.label = pageObject;
        edges = new HashSet<>();
    }

    String getLabel() {
        return label;
    }

    boolean addEdge(Edge edge){
        return edges.add(edge);
    }

    List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }

    @Override
    public String toString() {
        return label;
    }

    //todo override hashCode()
}