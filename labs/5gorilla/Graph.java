import java.util.*;

public class Graph{

    private List<Vertex> vertices; //collection of all verices

    public Graph() {
        vertices = new LinkedList<>();
    }

    List<Vertex> getVertices() {
        return new ArrayList<>(vertices);
    }

    boolean addVertex(Vertex vertex){
        return vertices.add(vertex);
    }

    Vertex getVertex(int index){
        return vertices.get(index);
    }
}
