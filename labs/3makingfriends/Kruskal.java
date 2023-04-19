import java.util.*;

public class Kruskal {

    //	Disjoint Sets Data Structure
    private static int[] friends; // tror denna ska vara n.


    private int find(int x){
        if(friends[x] == x){
            return x;
        }
        return find(friends[x]);
    }

    private void unite(int x, int y){
        int fx = find(x);
        int fy = find(y);
        friends[fx] = fy;
    }

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        Kruskal samp = new Kruskal();



        //	declaring the variables to load input
        int n,m;
        int a,b,w;
        ArrayList<Pair> edges = new ArrayList<>();
        //	loading the input
        n = scan.nextInt();
        m = scan.nextInt();

        /* detta är ju inte så snyggt så ska vara någon bra siffra, spelar dock inte roll hur stor den
        * än är så funkar det ändå inte */
        friends = new int[5000000]; //Funkar ej för 3 large
        //	initialize fathers for the disjoint sets
        for(int i=0;i<100;i++){
            samp.friends[i]=i;
        }

        for(int i=0;i<m;i++){
            a = scan.nextInt();
            b = scan.nextInt();
            w = scan.nextInt();
            edges.add(new Pair(w,a,b));
        }

        //	NOW THE KRUSKAL'S ALGORITHM BEGINS
        //	We firstly declare the variables for the MST
        int mst_weight = 0, mst_edges = 0;
        int	mst_ni = 0;
        //	STEP 1:	sort the list of edges
        //	comparator is interface that sort uses
        Collections.sort(edges, new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                return p1.w - p2.w;
            }
        });
        //	STEP 2-3:
        while( ( mst_edges < n-1) || (mst_ni < m) ){
            //	we brake the edge into the three integers they describe it
            a = edges.get(mst_ni).a; //DEtta ger out of bounce..???
            b = edges.get(mst_ni).b;
            w = edges.get(mst_ni).w;
            //	we check if the edge is ok to be included in the MST
            //	if a and b are in different trees (if they are on the same we will create a cycle)
            if( samp.find(a) != samp.find(b) ) {
                //	we unite the two trees the edge connects
                samp.unite(a,b);
                //	we add the weight of the edge
                mst_weight += w;
                //	we print the edge and count it
                //System.out.println(a + " " + b + " " + w);
                mst_edges++;
            }
            //	increase the index of the edge we will be chacking
            mst_ni++;
        }
        //	Presenting the WEIGHT
        System.out.println(mst_weight);
        //	THE END
    }

}