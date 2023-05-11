import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OPT {
    private final int delta = -4; // Value of a star
    private String star = "*";

    private Map<Character, Integer> map = new HashMap<>();
    private int m[][];
    private int [][] opt;

    public void readInput() {

        Scanner scan = new Scanner(System.in);
        String allNodes = scan.nextLine().replace(" ", "");

        int nbrNodes = allNodes.length(); // Total nbr of nodes

        for (int i = 0; i < nbrNodes; i++)
            map.put(allNodes.charAt(i), i); //Put all nodes in a map, connecting nodes to indexes

        m = new int[nbrNodes][nbrNodes]; //matrix with cost of match/mismatch

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                m[i][j] = scan.nextInt(); // Populating matrix with weight of the connection
            }
        }

        int q = scan.nextInt(); // Number of queries

        for (int i = 0; i < q; i++) {
            String s0 = scan.next();
            String s1 = scan.next();

            run(s0, s1); // For all queries, run our algorithm
        }
    }

    public void run(String s0, String s1) {
        int n = s0.length(); // Length of sequences
        int m = s1.length();

        opt = new int [n+1][m+1];

        // First row and first column is
       for (int i = 0; i < n+1; i++)
            opt[i][0] = delta*i;

        for (int j = 0; j < m+1; j++)
            opt[0][j] = delta*j;

        for(int ii= 0; ii<n+1; ii++) {
            for(int iii= 0;  iii<m+1; iii++) {
                System.out.print(opt[ii][iii]);
            }
            System.out.println();
        }



        //O(nm)
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {

                int a1 = opt[i-1][j] + delta;
                int a2 = opt[i][j-1] + delta;

                int cost = this.m[map.get(s0.charAt(i-1))][map.get(s1.charAt(j-1))];

                int a3 = opt[i-1][j-1] + cost;

                opt[i][j] = Math.max(a1, Math.max(a2, a3));
            }
        }

        //	print(opt, n, m);

        String ans = opt(s0, s1);
        System.out.println(ans);

    }

    public String opt(String s0, String s1) {

        int n = s0.length();
        int m = s1.length();

        while(true) {

            if(n == 0)
                return star.repeat(m) + s0 + " " + s1;

            if(m == 0)
                return s0 + " " + star.repeat(n) + s1;


            int cost = this.m[map.get(s0.charAt(n-1))][map.get(s1.charAt(m-1))];

            int a1 = opt[n-1][m] + delta;
            int a2 = opt[n][m-1] + delta;
            int a3 = opt[n-1][m-1] + cost;

            if(Math.max(a1, a2) > a3) {
                if(a1 < a2) {
                    s0 = s0.substring(0, n) + star + s0.substring(n);
                    n++;
                } else {
                    s1 = s1.substring(0, m) + star + s1.substring(m);
                    m++;
                }
            }
            n--;
            m--;
        }
    }


    public void print(int[][] opt, int n, int m) {

        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < m+1; j++) {

                System.out.print(opt[i][j]+",\t ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        OPT sa = new OPT();
        sa.readInput();
    }

}