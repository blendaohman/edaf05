/*
 * An implementation of the Edmonds-Karp algorithm which is essentially Ford-Fulkerson with a BFS as
 * a method of finding augmenting paths. This Edmonds-Karp algorithm will allow you to find the max
 * flow through a directed graph and the min cut as a byproduct.
 *
 * <p>Time Complexity: O(VE^2)
 *
 */

import static java.lang.Math.min;

import java.util.*;

public class EF {

    /*
     * @param n - The number of nodes in the graph including source and sink nodes.
     * @param s - The index of the source node, 0 <= s < n
     * @param t - The index of the sink node, 0 <= t < n, t != s
     */
    private final int n, s, t;
    private long maxFlow;
    private List<List<Integer>> allRoutes = new LinkedList<>(); //last element is the weight
    private List<Edge>[] graph;
    // 'visited' and 'visitedToken' are variables used for graph sub-routines to
    // track whether a node has been visited or not. In particular, node 'i' was
    // recently visited if visited[i] == visitedToken is true. This is handy
    // because to mark all nodes as unvisited simply increment the visitedToken.
    private int visitedToken = 1;
    private int[] visited;
    // Indicates whether the network flow algorithm has ran. We should not need to
    // run the solver multiple times, because it always yields the same result.
    private boolean solved;


    /*
     * Creates an instance of a flow network solver. Use the {@link #addEdge} method to add edges to
     * the graph.
     *
     * @param n - The number of nodes in the graph including source and sink nodes.
     * @param s - The index of the source node, 0 <= s < n
     * @param t - The index of the sink node, 0 <= t < n, t != s
     */
    public EF(int n, int s, int t) {
        this.n = n;
        this.s = s;
        this.t = t;
        initializeGraph();
        visited = new int[n];
    }

    // Construct an empty graph with n nodes including the source and sink nodes.

    private void initializeGraph() {
        graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<Edge>();
    }

    /*
     * Adds a directed edge (and residual edge) to the flow graph.
     *
     * @param from - The index of the node the directed edge starts at.
     * @param to - The index of the node the directed edge ends at.
     * @param capacity - The capacity of the edge.
     */
    public void addEdge(int from, int to, long capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Capacity < 0");
        Edge e1 = new Edge(from, to, capacity);
        Edge e2 = new Edge(to, from, 0);
        e1.residual = e2;
        e2.residual = e1;
        graph[from].add(e1);
        graph[to].add(e2);
    }

    // Marks node 'i' as visited.
    public void visit(int i) {
        visited[i] = visitedToken;
    }

    // Returns whether or not node 'i' has been visited.
    public boolean visited(int i) {
        return visited[i] == visitedToken;
    }

    // Resets all nodes as unvisited. This is especially useful to do
    // between iterations of finding augmenting paths, O(1)
    public void markAllNodesAsUnvisited() {
        visitedToken++;
    }

    /*
     * Returns the graph after the solver has been executed. This allow you to inspect the Edge#flow compared to the {@link NetworkFlowSolverBase.Edge#capacity} in each edge. This is useful if you want to
     * figure out which edges were used during the max flow.
     */
    public List<Edge>[] getGraph() {
        execute();
        return graph;
    }

    // Returns the maximum flow from the source to the sink.
    public long getMaxFlow() {
        execute();
        return maxFlow;
    }

    // Wrapper method that ensures we only call solve() once
    private void execute() {
        if (solved) return;
        solved = true;
        solve();
    }
    // Run Edmonds-Karp and compute the max flow from the source to the sink node.

    public void solve() {
        long flow;
        do {
            markAllNodesAsUnvisited();
            flow = bfs();
            maxFlow += flow;

        } while (flow != 0);

        for(List<Integer> routes : allRoutes){
            System.out.println("Route: " + routes.toString());
        }
        //for (int i = 0; i < n; i++) if (visited(i)) minCut[i] = true;
    }

    private long bfs() {
        Edge[] prev = new Edge[n];

        // The queue can be optimized to use a faster queue
        Queue<Integer> q = new ArrayDeque<>(n);
        visit(s);
        q.offer(s);

        // Perform BFS from source to sink
        while (!q.isEmpty()) {
            int node = q.poll();
            if (node == t) break;

            for (Edge edge : graph[node]) {
                long cap = edge.remainingCapacity();
                if (cap > 0 && !visited(edge.to)) {
                    visit(edge.to);
                    prev[edge.to] = edge;
                    q.offer(edge.to);
                }
            }
        }

        // Sink not reachable!
        if (prev[t] == null) return 0;

        long bottleNeck = Long.MAX_VALUE;

        // Find augmented path and bottle neck
        for (Edge edge = prev[t]; edge != null; edge = prev[edge.from])
            bottleNeck = min(bottleNeck, edge.remainingCapacity());

        // Retrace augmented path and update flow values.
        for (Edge edge = prev[t]; edge != null; edge = prev[edge.from]) edge.augment(bottleNeck);

        // Return bottleneck flow
        return bottleNeck;
    }


    public static class Edge {
        public int from, to;
        public Edge residual;
        public long flow, cost;
        public final long capacity, originalCost;

        public Edge(int from, int to, long capacity) {
            this(from, to, capacity, 0 /* unused */);
        }

        public Edge(int from, int to, long capacity, long cost) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
            this.originalCost = this.cost = cost;
        }

        public boolean isResidual() {
            return capacity == 0;
        }

        public long remainingCapacity() {
            return capacity - flow;
        }

        public void augment(long bottleNeck) {
            flow += bottleNeck;
            residual.flow -= bottleNeck;
        }

        public String toString(int s, int t) {
            String u = (from == s) ? "s" : ((from == t) ? "t" : String.valueOf(from));
            String v = (to == s) ? "s" : ((to == t) ? "t" : String.valueOf(to));
            return String.format(
                    "Edge %s -> %s | flow = %d | capacity = %d | is residual: %s",
                    u, v, flow, capacity, isResidual());
        }
    }


}
