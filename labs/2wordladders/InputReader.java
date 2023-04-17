import java.util.*;
import java.util.stream.Collectors;

public class InputReader {
    private int n;
    private int q;

    private Map<String, LinkedList<String>> tree = new HashMap<>();

    private Map<String, String> queries = new HashMap<>(); //these are just start and end nodes to be looked at
    private LinkedList<String> queriesList = new LinkedList<>();

    public void parseInput(){
        Scanner scan = new Scanner(System.in);
        int row = 0;
        while(scan.hasNext()) {
            String input = scan.nextLine();

            if(row == 0) { //Första raden, läs in n och connections
                String[] meta = input.split(" ");
                n = Integer.parseInt(meta[0]);
                q = Integer.parseInt(meta[1]);
            } else if(row <= n ) { //Alla noder ska läggas in i mappen

                tree.put(input, new LinkedList<>()); //Lägg till orden som en nod. Deras "neighbours" är en tom lista

            } else {
                //This is a query
                //String[] parts = input.split(" ");
                //queries.put(startAndEnd[0], startAndEnd[1]);
               // queries.put(startAndEnd[1], startAndEnd[0]);
                queriesList.add(input);

            }

            row++;

        }
        buildConnections();
        scan.close();
    }

    public Map<String, LinkedList<String>> getTree() {
        return tree;
    }

    public  List<String> getQueries(){
        return queriesList;
    }

    //Denna ska lägga till alla neighbours om de matchar
    public void buildConnections() {
        //for each node, create connection
        for(Map.Entry<String, LinkedList<String>> node: tree.entrySet()){
            for(String nodes: tree.keySet()){
                if(isNeighbor(node.getKey(), nodes)){
                    tree.get(node.getKey()).addLast(nodes);
                    //System.out.println("Adding " + node.getKey() + " to " + nodes);
                }

            }
        }
    }

    private boolean isEdge(String w1, String w2) {
        int count = 0;
        String sub = w1.substring(w1.length() - 4);

        for (char c : sub.toCharArray()) {
            if (w2.contains(Character.toString(c))) {
                count++;
            }
        }
        if(count < 4) {
            return false;
        } else {
            //System.out.println(w1 + " is linked to " + w2);
            return true;
        }

        //return count < 4 ? false : true;
    }

    static boolean isNeighbor(String v, String node){
        for(int index = 1; index < 5; index++){
            if(v.indexOf(node.charAt(index)) >= 0){
                int removeIndex = v.indexOf(node.charAt(index));
                v = v.substring(0, removeIndex) + " " + v.substring(removeIndex + 1);
            }
            else{
                return false;
            }
        }
        return true;
    }
}
