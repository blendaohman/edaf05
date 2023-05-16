import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        readInput();
    }

    private static void readInput(){
        List<Integer> remove = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        int nodes = scan.nextInt();
        int edges = scan.nextInt();
        int capacity = scan.nextInt();
        int routes = scan.nextInt();
        scan.nextLine();
        EF solver = new EF(nodes, 0, nodes-1);

        for(int i = 0; i < routes; i++){
            String s = scan.nextLine();
            String[] v = s.split(" ");
            solver.addEdge(Integer.parseInt(v[0]), Integer.parseInt(v[1]), Integer.parseInt(v[2]));
        }
        while(scan.hasNext()){
            int i = scan.nextInt();
            remove.add(i);
        }
        scan.close();
        System.out.println(solver.getMaxFlow());
    }
}




