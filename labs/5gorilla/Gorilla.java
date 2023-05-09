import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Gorilla {
    private Map<String, LinkedList<String>> mapping = new HashMap<>();

    public static void main(String[] args) {
        Graph graph = new Graph();
        Scanner scan = new Scanner(System.in);
        int row = 0;
        int graphSize = 0;
        int columnIndex = 0;
        while(scan.hasNextLine()){
            String line = scan.nextLine();

            if(row == 0) { //Första raden. detta är alla noder
               String[] letters = line.split(" ");

               for(String l: letters){
                   Vertex v = new Vertex(l);
                   graph.addVertex(v); //adds vertex at the end of the list
               }

                //Vertex v = new Vertex("*");
                //graph.addVertex(v); //adds vertex at the end of the list
               graphSize = graph.getVertices().size();

            } else if(row > 0 && row <= graphSize) { //ska denna ha +1?? //Här lägger vi till alla weights
                String[] weights = line.split(" ");
                Vertex v1 = graph.getVertex(row - 1);

                for(int i = 0; i< weights.length; i++) { //Gå igenom och gör kopplingar
                    Vertex v2 = graph.getVertex(i);
                    Edge e = new Edge(v2, Integer.parseInt(weights[i]));
                    v1.addEdge(e);

                    //System.out.println("v1: " + v1.toString() + " v2: " + v2.toString() + " weight: " + e.getWeight());
                }

                columnIndex++;



            } else { //Query-rader
                int nbrOfQueries = Integer.parseInt(line); //Berättar hur många queries vi ska läsa in

                for(int q = 0; q < nbrOfQueries; q++) {
                    String request = scan.nextLine();

                    String[] query = request.split(" ");
                    // query[0] = from
                    // query[1] = to
                    System.out.println("from: " + query[0] + " to: " + query[1]);

                    // Kalla på algoritm
                }

            }


            row++;
        }
        scan.close();

    for(Vertex v : graph.getVertices()) {
        System.out.println("Vertex label: " + v.toString());
        for(Edge e : v.getEdges()) {
            //System.out.println("to:" + e.getTo() + " weight: " + e.getWeight());
        }
    }



    }
}
