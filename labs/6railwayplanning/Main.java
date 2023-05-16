import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        readInput();
    }

    private static void readInput(){


        Scanner scan = new Scanner(System.in);
        int nodes = scan.nextInt();
        int edges = scan.nextInt();
        int capacity = scan.nextInt();
        int routes = scan.nextInt();
        scan.nextLine();
        EF solver = new EF(nodes, 0, nodes-1);
        //List<String[]> edgeInfo = new LinkedList<>();

        int[][] edgeInfo = new int[edges][3];
        //String[][] removedEdges = new String[edges][3];
        List<int[]> removedEdges = new LinkedList<>();

        List<Integer> remove = new ArrayList<>();

        for(int i = 0; i < routes; i++){
            String s = scan.nextLine();
            //node node cap
            String[] v = s.split(" ");

            int to = Integer.parseInt(v[0]);
            int from = Integer.parseInt(v[1]);
            int weight = Integer.parseInt(v[2]);

            edgeInfo[i][0] = to;
            edgeInfo[i][1] = from;
            edgeInfo[i][2] = weight;

            //solver.addEdge(Integer.parseInt(v[0]), Integer.parseInt(v[1]), Integer.parseInt(v[2]));
        }
        while(scan.hasNext()){
            remove.add(scan.nextInt()); // Vi sparar alla rutterna som man kan ta bort
        }

        scan.close();

        //Ta bort alla edges som var i slutet
        for(int index : remove){
           int to = edgeInfo[index][0];
           int from = edgeInfo[index][1];
           int weight = edgeInfo[index][2];

           int[] tmp = new int[]{to, from, weight};

            removedEdges.add(tmp);
            edgeInfo[index][2] = -1;

        }

        System.out.println("Removed edges: ");
        for(int[] s : removedEdges) {
            for(int k: s){
                System.out.print(k + " ");

            }
            System.out.println();

        }
        //Antalet index som tas bort
        int nbrRemoved = remove.size();

        //Adda alla edges
        for(int[] v : edgeInfo){
            if(v[2] != -1) {
                solver.addEdge(v[0], v[1], v[2]);
                solver.addEdge(v[1], v[0], v[2]);
                System.out.println("Added edge");
            }
        }


        // Kör algoritmen
        long maxFlow = solver.getMaxFlow();
        System.out.println("maxFlow: " + maxFlow);
        System.out.println("Capacity: " + capacity);

        while(maxFlow < capacity) {
            System.out.println("maxFlow < capacity: maxflow is " + maxFlow);
            int [] newEdge = removedEdges.remove(removedEdges.size()-1); //Lägg till den sista vi tog bort
            System.out.println("New edge: ");
            for(int s : newEdge) {
                System.out.print(s);
                System.out.println();
            }

            solver.addEdge(newEdge[0], newEdge[1], newEdge[2]);
            solver.addEdge(newEdge[1], newEdge[0], newEdge[2]);

            System.out.println("All edges in graph: ");
            for(List<EF.Edge> e : solver.getGraph()){
                for(EF.Edge o: e){
                    System.out.println(o.to + " " + o.from + " " + o.capacity);
                }
            }
            solver.getGraph();

            nbrRemoved--;
            maxFlow = solver.getMaxFlow();
            System.out.println("new maxflow: " + maxFlow);

            //System.out.println("maxFlow: " + maxFlow);
        }

        System.out.println(nbrRemoved + " " + maxFlow);
        // If solver.getMaxFlow >= capacity, print result och antal rader
        // Else, lägg till sista i listan igen


        //solver.solve();

        //System.out.println(solver.getMaxFlow());

    }
}




