import java.util.*;

public class Old {
    private HashMap<String,Boolean> visited = new HashMap<String, Boolean>();
    private HashMap<String , LinkedList<String>> tree = new HashMap<>();

    public static void main(String[] args) {
        InputReader inputReader = new InputReader();
        inputReader.parseInput();
        Map<String, LinkedList<String>> tree = inputReader.getTree(); //Vår representation av trädet

        //Gå igenom alla queries vi fått från inputen och kör algoritmen på dem
        for(String query : inputReader.getQueries()) {
            String[] parts = query.split(" ");
            bfs(tree, parts[1], parts[0]);
        }
    }

/*    static int algorithm(String startNode, String endNode){
        LinkedList<String> q = new LinkedList<>();
        q.addFirst(startNode);
        HashMap<String, String> pred = new HashMap<>();
        for(Entry<String, Boolean> set : visited.entrySet()){
            visited.replace(set.getKey(), false);
        }
        visited.replace(startNode, true);
        while(!(q.isEmpty())){
            String v = q.getFirst();
            for(String node: tree.get(v)){
                if(!(visited.get(node))){
                    visited.replace(node, true);
                    q.addLast(node);
                    pred.put(node, v);

                }
                if(node.equals(endNode)){
                    int dis = 0;
                    String test = node;
                    while(true){
                        if(test.equals(startNode)){
                            System.out.println(dis);
                            return 0;
                        }
                        dis++;
                        test = pred.get(test);
                    }
                }

            }
            q.removeFirst();

        }
        System.out.println("Impossible");
        return 1;
    }*/
    public static void bfs(Map<String, LinkedList<String>> tree, String start, String end){
//       System.out.println("start: " + start);
//       System.out.println("end: " + end);

        LinkedList<String> q = new LinkedList<>(); //This is a queue of the next node to visit
        q.addFirst(start);
        Map<String,Boolean> visited = new HashMap<String, Boolean>();
        Map<String,String> pred = new HashMap<String, String>();

        //Add all nodes but make them not visited
        for(Map.Entry<String, LinkedList<String>> node : tree.entrySet()) { //Hitta på vettigt sätt att lägga in alla noder
            visited.put(node.getKey(), false);
            pred.put(node.getKey(), null);

        }
        //set start node to visited
        visited.replace(start, true);



        while(!q.isEmpty()) {
            String node = q.get(0);
            LinkedList<String> neighbours = tree.get(node);

            for(String next: neighbours) {
                if(!visited.get(next)) {
                    q.add(next);
                    visited.put(next, true);
                    pred.put(next, node);

                    if (next.equals(end)) {
                        //System.out.println("Found path");

                        //Nu beräknar vi distansen
                        int dist = 0;
                        String temp = next;
                        while(true){
                            if(temp.equals(start)){
                                System.out.println(dist);
                                return;
                            }
                            dist++;
                            temp = pred.get(temp);
                        }

                        /*while(!temp.equals(start)){
                            dist++;
                            temp = pred.get(temp);
                            }

                        System.out.println(dist);
                        return;*/

                    }

                }
            }
            q.removeFirst();

        }
        System.out.println("Impossible");


    }


}
