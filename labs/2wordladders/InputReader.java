import java.util.*;
import java.util.stream.Collectors;

public class InputReader {
    private int n;
    private List<String> allNodes = new ArrayList<>();
    private Map<String, LinkedList<String>> tree = new HashMap<>();

    private Map<String, String> queries = new HashMap<>(); //these are just start and end nodes to be looked at

    public void parseInput(){
        Scanner scan = new Scanner(System.in);
        int row = 0;
        while(scan.hasNext()) {
            String input = scan.nextLine();

            if(row == 0) { //Första raden, läs in n och connections
                String[] meta = input.split(" ");
                n = Integer.parseInt(meta[0]);
                //connections = Integer.parseInt(meta[1]);
            } else if(row <= n ) { //Alla noder ska läggas in i mappen

                tree.put(input, new LinkedList<>()); //Lägg till orden som en nod. Deras "neighbours" är en tom lista
                allNodes.add(input);
            } else {
                //This is a query
                String[] startAndEnd = input.split(" ");
                queries.put(startAndEnd[0], startAndEnd[1]);

            }
            row++;
        }


        scan.close();
    }

    public Map<String, LinkedList<String>> getTree() {
        return tree;
    }

    public  int getN() {
        return n;
    }

    public  Map<String, String> getQueries(){
        return queries;
    }

    public List<String> getAllNodes() {
        return allNodes;
    }

    //Denna ska lägga till alla neighbours om de matchar
   /* public void buildConnections() {
        //for each node, create connection
        for(Map.Entry<String, LinkedList<String>> node: tree.entrySet()){
            for(Map.Entry<String, LinkedList<String>> next: tree.entrySet()){
                if(isMatch(node.getKey(), next.getKey())){
                    tree.get(node.getKey())next.getValue());
                }

            }
        }

    }*/

    //If the last characters of are included in b then its a match
    public boolean isMatch(String a, String b) {
        //Get the last 4 characters of both words
        String lastA = a.substring(a.length()-4);
        String lastB = b.substring(b.length()-4);

        //Make the last 4 characters into lists of characters
        List<Character> charA = new ArrayList<>();
        List<Character> charB = new ArrayList<>();

        for(int i= 0; i<4; i++) {
            charA.add(lastA.charAt(i));
            charB.add(lastB.charAt(i));
        }

        //For each character in list of a, check if its in list b. If yes, then remove from both until
        // list of A isempty

        for(char c: lastA.toCharArray()) {
            if(charB.contains(c)) {
                charA.remove( (Character) c);
                charB.remove( (Character) c);

            }
        }

        //Just for testing!
        if (charA.isEmpty()) {
            System.out.println(a + " is linked to " + b);
        }

        return charA.isEmpty();

        //return a.substring(a.length() - 4, a.length()).equals(b.substring(b.length() -4, b.length()));
    }
}
