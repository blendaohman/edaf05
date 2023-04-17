
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.HashMap;
import java.util.LinkedList;

import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Solution {
    static int n;
    static int q;
    static HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
    static HashMap<String, LinkedList<String>> tree = new HashMap<>();

    public static void main(String[] args) throws IOException {
        run();
    }

    /* Parses input and runs algorithm on each query */
    public static void run() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        int row = 0;

        while (scan.hasNextLine()) {
            String input = scan.nextLine();

            if (row == 0) { //First row of input, carries metadata
                String[] meta = input.split(" ");
                n = Integer.parseInt(meta[0]);
                q = Integer.parseInt(meta[1]);

            } else if (row <= n) { //Node row

                visited.put(input, false);
                tree.put(input, new LinkedList<>());
            } else { //Query row

                //Build tree if it's the first query
                if (row == n + 1) {
                    buildConnections();
                }

                //Run this current query
                String[] inputRow = input.split(" ");
                bfs(inputRow[0], inputRow[1]);
            }
            row++;
        }
    }

    private static void buildConnections() {
        //for each node, create connection
        for (Map.Entry<String, LinkedList<String>> node : tree.entrySet()) { //for each node in tree
            for (String neighbour : tree.keySet()) { //Iterate though all nodes
                if (isMatch(node.getKey(), neighbour)) { //If the node and possible neighbour should be connected
                    tree.get(node.getKey()).add(neighbour); //Add the neighbour to the nodes list
                }

            }
        }
    }

    /* Checks if w1 should have a connection to w2 in the tree */
    private static boolean isMatch(String w1, String w2) {
        for (int index = 1; index < 5; index++) {

            if (w2.indexOf(w1.charAt(index)) >= 0) { //If the index of the character is present

                //We replace that character with " " to continue the comparison
                int removeIndex = w2.indexOf(w1.charAt(index));
                w2 = w2.substring(0, removeIndex) + " " + w2.substring(removeIndex + 1);
            } else { // Comparison failed, its not a match
                return false;
            }
        }
        return true; //Was a match
    }


/*    private static boolean isMatch(String w1, String w2) {
        for (int index = w1.length() - 1; index <  w1.length() - 5; index--) {
            if (w2.indexOf(w1.charAt(index)) >= 0) { //If the character is present in w2
                //We remove that character to continue the comparison
                int removeIndex = w2.indexOf(w1.charAt(index));
                w2 = w2.substring(0, removeIndex) + " " + w2.substring(removeIndex + 1);
            } else { // Comparison failed, its not a match
                return false;
            }
        }
        return true;
    }*/

    /* BFS algorithm */
    private static void bfs(String startNode, String endNode) {
        LinkedList<String> q = new LinkedList<>();

        q.add(startNode); //And startnode to queue
        HashMap<String, String> pred = new HashMap<>(); //To know the previous node

        for (Entry<String, Boolean> node : visited.entrySet()) {
            visited.replace(node.getKey(), false); //Setting all nodes to unvisited
        }
        visited.replace(startNode, true); //Set the startnode to visited

        while (!(q.isEmpty())) {
            String node = q.getFirst(); // Current node

            for (String next : tree.get(node)) { //Looking at all neighbours of current node
                if (!(visited.get(next))) { // If the neighbours is NOT visited
                    visited.replace(next, true); //Set it to visited
                    q.add(next); //Add it to the queue
                    pred.put(next, node); //Set node as the previous node of next

                }
                if (next.equals(endNode)) { //If the neighbour the endnode
                    int distance = 0;
                    String temp = endNode; //Setting temp to the endnode
                    while (true) {
                        if (temp.equals(startNode)) { //If we have reached the start again, print distance
                            System.out.println(distance);
                            return; //Break loop, path has been printed
                        }
                        distance++; //It was not the startnode, increase distance counter
                        temp = pred.get(temp); //Set temp to the node before this one in the path
                    }
                }

            }
            q.removeFirst();

        }
        System.out.println("Impossible");
    }

}