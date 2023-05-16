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
        List<String[]> edgeInfo = new LinkedList<>();

        List<Integer> remove = new ArrayList<>();

        List<String[]> removedEdges = new LinkedList<>();

        for(int i = 0; i < routes; i++){
            String s = scan.nextLine();
            String[] v = s.split(" ");
            //edgeInfo.add(i, new LinkedList<>());
            edgeInfo.add(v);

            //solver.addEdge(Integer.parseInt(v[0]), Integer.parseInt(v[1]), Integer.parseInt(v[2]));
        }
        while(scan.hasNext()){
            remove.add(scan.nextInt()); // Vi sparar alla rutterna som man kan ta bort
        }

        scan.close();

        //Ta bort alla edges som var i slutet
        for(int index : remove){
            removedEdges.add(edgeInfo.remove(index));
        }

        //Antalet index som tas bort
        int nbrRemoved = remove.size();

        //Adda alla edges
        for(String[] v : edgeInfo){
            solver.addEdge(Integer.parseInt(v[0]), Integer.parseInt(v[1]), Integer.parseInt(v[2]));
        }

        // Kör algoritmen
        long maxFlow = solver.getMaxFlow();


        while(maxFlow< capacity) {
            edgeInfo.add(removedEdges.get(remove.size()-1)); // Lägg till edgen som ligger sist i removedEdges

            // Kör algoritmen igen
            // maxFlow = solver.getMaxFlow();
            //
        }

        System.out.println(nbrRemoved + " " + maxFlow);
        // If solver.getMaxFlow >= capacity, print result och antal rader
        // Else, lägg till sista i listan igen


        //solver.solve();

        //System.out.println(solver.getMaxFlow());

    }
}




