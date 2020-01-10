import java.util.ArrayList;
import java.util.Collections;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;

public class SAP {
    Digraph graph;
    int ancestor = -1;
    int len = Integer.MAX_VALUE;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        graph = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) throws InterruptedException {
        int length = ancestor(v, w);
        if (length == -1) {
            return -1;
        } else {
            return len;
        }

    }

    // a common ancestor of v and w that participates in a shortest ancestral path;
    // -1 if no such path
    public int ancestor(int v, int w) throws InterruptedException {
        BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(graph, w);
        // System.out.println(graph.V());
        for (int i = 0; i < graph.V(); i++) {
            if (bfs1.hasPathTo(i) && bfs2.hasPathTo(i)) {
                // System.out.println(i + " i");
                int v_to_i = bfs1.distTo(i);
                int w_to_i = bfs2.distTo(i);
                // System.out.println((v_to_i) + " g " + (w_to_i));
                if ((v_to_i + w_to_i) < len) {
                    len = (v_to_i + w_to_i);
                    ancestor = i;
                }
            }
        }

        return ancestor;

    }

    // length of shortest ancestral path between any vertex in v and any vertex in
    // w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) throws InterruptedException {
        SAP s = new SAP(graph);
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i : v) {
            for (int j : w) {
                list.add(s.length(i, j));
            }
        }
        return Collections.min(list);

    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such
    // path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) throws InterruptedException {
        for (int i : v) {
            for (int j : w) {
                ancestor(i, j);
            }
        }
        return ancestor;

    }

    // do unit testing of this class
    public static void main(String[] args) throws InterruptedException {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
