import java.lang.reflect.Array;
import java.util.*;

public class Solution {


    public static void main(String[] args) {
        InputReader inputReader = new InputReader();
        inputReader.parseInput();
        Map<String, LinkedList<String>> tree = inputReader.getTree(); //Vår representation av trädet

        //Gå igenom alla queries vi fått från inputen och kör algoritmen på dem
        //I Varje queriy får vi en start- och slutnod
        for(Map.Entry<String, String> entry : inputReader.getQueries().entrySet()) {
            bfs(tree, entry.getKey(), entry.getValue());

            //inputReader.isMatch(entry.getKey(), entry.getValue()); //just for testing
            //Antingen printar vi här eller i bfs
        }
    }

    public static void bfs(Map<String, LinkedList<String>> tree, String start, String end){
        System.out.println("start: " + start);
        System.out.println("end: " + end);

        LinkedList<String> q = new LinkedList<>(); //This is a queue of the next node to visit
        Map<String,Boolean> visited = new HashMap<String, Boolean>();

        //Add all nodes but make them not visited
        for(String node: allNodes) { //Hitta på vettigt sätt att lägga in alla noder
            visited.put(node, false);

        }
        //set start node to visited
        visited.put(start, true);

        while(!q.isEmpty()) {
            String node = q.get(0);
            LinkedList<String> neighbours = tree.get(node);

            for(String n: neighbours) {
                if(visited.get(n) == false) {
                    q.add(n);
                    visited.put(n, true);
                }
            }

        }




    }


}
