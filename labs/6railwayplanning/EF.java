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

    private final int n, s, t;
    private long maxFlow;
    private List<Edge>[] graph;
    private int visitedToken = 1;
    private int[] visited;

    /*
     * Creates an instance of a flow network solver.
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
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
    }

    /*
     * Adds a directed edge (and residual edge) to the flow graph.
     */
    public void addEdge(int from, int to, long capacity) {
        //if (capacity < 0) throw new IllegalArgumentException("Capacity < 0");
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

    // Returns whether the node 'i' has been visited or not.
    public boolean visited(int i) {
        return visited[i] == visitedToken;
    }

    // Resets all nodes as unvisited. This is especially useful to do
    // between iterations of finding augmenting paths, O(1)
    public void markAllNodesAsUnvisited() {
        visitedToken++;
    }

    // Returns the maximum flow from the source to the sink.
    public long getMaxFlow() {
        solve();
        return maxFlow;
    }

    public void solve() {
        long flow;
        do {
            markAllNodesAsUnvisited();
            flow = bfs();
            maxFlow += flow;

        } while (flow != 0);
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
        public long flow;
        public final long capacity;

        public Edge(int from, int to, long capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
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
    }


}
