import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;

public class SAP {
    Digraph graph;
    int ancestor = -1;
    // int tempLen;
    int tempLen = 0;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.graph = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) throws InterruptedException {
        int ancestor = ancestor(v, w);
        if (ancestor == -1) {
            return -1;
        } else {
            // System.out.println("len is " + len);
            return tempLen;
        }

    }

    // a common ancestor of v and w that participates in a shortest ancestral path;
    // -1 if no such path
    public int ancestor(int v, int w) throws InterruptedException {
        int tempLen = Integer.MAX_VALUE;
        BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(graph, w);
        // System.out.println(graph.V());
        int count = 0;
        for (int i = 0; i < graph.V(); i++) {
            if ((bfs1.hasPathTo(i) && bfs2.hasPathTo(i))) {
                count++;
                // System.out.println("True" + "," + bfs1.distTo(i) + "," + bfs2.distTo(i));
                int v_to_i = bfs1.distTo(i);
                int w_to_i = bfs2.distTo(i);
                if (count == 1) {
                    tempLen = (v_to_i + w_to_i);
                }
                System.out.println(tempLen + " " + v_to_i + " " + w_to_i);
                if (count > 1 && (v_to_i + w_to_i) < tempLen) {
                    tempLen = (v_to_i + w_to_i);
                    // System.out.println("here" + tempLen);
                    ancestor = i;
                }
            }
        }

        // System.out.println(ancestor + "," + tempLen + ",there");
        return ancestor;

    }

    // length of shortest ancestral path between any vertex in v and any vertex in
    // w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) throws InterruptedException {
        // System.out.println("Hello");
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i : v) {
            for (int j : w) {
                list.add(length(i, j));
            }
        }
        // System.out.println(list);
        return Collections.min(list);

    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such
    // path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) throws InterruptedException {
        HashMap<Integer, Integer> map = new HashMap<>();
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>();
        for (int i : v) {
            for (int j : w) {
                map.put(length(i, j), ancestor(i, j));

            }
        }
        sortedMap.putAll(map);
        int key = (int) sortedMap.keySet().toArray()[0];
        return sortedMap.get(key);

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
