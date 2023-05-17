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

        int[][] edgeInfo = new int[edges][3];
        List<int[]> removedEdges = new LinkedList<>();

        for(int i = 0; i < edges; i++){
            String s = scan.nextLine();
            String[] v = s.split(" ");

            int to = Integer.parseInt(v[0]);
            int from = Integer.parseInt(v[1]);
            int weight = Integer.parseInt(v[2]);

            edgeInfo[i] = new int[]{to, from, weight};

        }
        while(scan.hasNext()){
            int removeIndex = scan.nextInt(); // Vi sparar alla rutterna som man kan ta bort

            int to = edgeInfo[removeIndex][0];
            int from = edgeInfo[removeIndex][1];
            int weight = edgeInfo[removeIndex][2];

            int[] tmp = new int[]{to, from, weight}; // Kopia av edge infon

            removedEdges.add(tmp); //Sparar så vi kan lägga tillbaka den
            edgeInfo[removeIndex][2] = -1;

        }
        scan.close();

        //Antalet index som tas bort
        int nbrRemoved = routes; //remove.size();

        //Adda alla edges (inte de som tas bort från början)
        for(int[] v : edgeInfo){
            if(v[2] != -1) {
                solver.addEdge(v[0], v[1], v[2]);
                solver.addEdge(v[1], v[0], v[2]);
            }
        }

        // Kör algoritmen
        long maxFlow = solver.getMaxFlow();

        /* Om maxFlow var mindre än capaciteten, ska vi lägga till en edge vi tagit bort*/
        while(maxFlow < capacity) {
            int [] newEdge = removedEdges.remove(removedEdges.size()-1); //Lägg till den sista vi tog bort

            solver.addEdge(newEdge[0], newEdge[1], newEdge[2]);
            solver.addEdge(newEdge[1], newEdge[0], newEdge[2]);

            nbrRemoved--; //Minskar antalet edges vi "tagit bort"
            maxFlow = solver.getMaxFlow(); // +=, då funkar inte  1small.in
        }

        System.out.println(nbrRemoved + " " + maxFlow);

    }
}




