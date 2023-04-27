import java.util.*;

public class Kruskal {

    //	Data Structure for disjoint sets of people
    private int[] parents; //

    public Kruskal(int n){
        this.parents = new int[n]; // Number of people at the event
    }


    /* TBA  */
    private int find(int x){
        if(parents[x] == x){ // If the person x has no connection yet
            return x; //We can return index x
        }
        return find(parents[x]); //Else keep looking because we dont want a cykle
    }

    /* TBA */
    private void unite(int x, int y){
        int fx = find(x); //Node that will not create cycle
        int fy = find(y); //Node that will not create cycle
        parents[fx] = fy;  //set parent
    }

    /* Main method reads input, and finds weight of MST */
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);

        //	declaring the variables to load input
        int n,m;
        int a,b,w;

        //List of all our nodes in our tree, consisting of Pairs (name a, name b, and a weight w)
        ArrayList<Pair> nodes = new ArrayList<>();
        //	First row of input is some metadata
        n = scan.nextInt();
        m = scan.nextInt();

        // An instance of Kruskal class containing an arraylist of friends
        Kruskal kru = new Kruskal(n+1); //detta kan ju man ändra. Men får error i find() om den är mindre

        //Vad gör detta idk
        //	initialize friends for the disjoint sets
        // Everyone is just connected to themselves?

        for(int i=0;i<n;i++){ //Testat med i<n och i<n+1 för att få att funka
            kru.parents[i]=i;
        }

        // Continue reading rest of input
        for(int i=0;i<m;i++){
            a = scan.nextInt();
            b = scan.nextInt();
            w = scan.nextInt();
            nodes.add(new Pair(w,a,b)); // Adding nodes and their connection to noode list
        }

        //	Kruskals algorithm
        //	Variables for the Minimum Spanning Tree (MST)
        int treeWeight = 0;
        int treeEdges = 0;
        int	currentNode = 0;

        //	Sorting the nodes by weight using Comparator
        Collections.sort(nodes, new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                return p1.w - p2.w; //Giving us the smallest weighted pair
            }
        });

        //	Now we start comparing nodes to find the mimimum spanning tree
        while( ( treeEdges < n-1) || (currentNode < m) ){

            //	Getting the three propeties of this pair/connection of the current node
            a = nodes.get(currentNode).a;
            b = nodes.get(currentNode).b;
            w = nodes.get(currentNode).w;

            //	We check if nodes are ok to include and if they are in the same MST or not,
            // We do NOT want to create a cycle
            if( kru.find(a) != kru.find(b) ) {

                //	Uniting the trees at connection a-b when we know there is no cycle
                kru.unite(a,b);
                //	Adding total weight
                treeWeight += w;

                // Incrementing nbr of nodes in tree
                treeEdges++;
            }
            //	Going to the next node
            currentNode++;
        }
        //	Printing result
        System.out.println(treeWeight);
    }



}