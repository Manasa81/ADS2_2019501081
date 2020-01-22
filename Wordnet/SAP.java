import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.In;

/**
 * SAP
 */
public class SAP {
    /**
     * Digraph object
     */
    private Digraph G;
    /**
     *
     * @param grapg grpa object
     */
    public SAP(final Digraph graph) {
        this.G = new Digraph(graph);
    }

    private boolean checkBounds(int v) {
        return 0 <= v && v < G.V();
    }

    public int length(int v, int w) {
        if (!checkBounds(v) || !checkBounds(w)) {
            throw new java.lang.IllegalArgumentException();
        }
        BreadthFirstDirectedPaths bfdpV = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfdpW = new BreadthFirstDirectedPaths(G, w);
        int minLength = Integer.MAX_VALUE;
        for (int u = 0; u < G.V(); ++u) {
            // System.out.println("Hell");
            if (!bfdpV.hasPathTo(u) || !bfdpW.hasPathTo(u)) {
                // System.out.println("hello");
                continue;
            }
            int dist = bfdpV.distTo(u) + bfdpW.distTo(u);
            if (dist < minLength) {
                minLength = dist;
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return -1;
        } else {
            return minLength;
        }
    }

    public int ancestor(int v, int w) {
        if (!checkBounds(v) || !checkBounds(w)) {
            throw new java.lang.IllegalArgumentException();
        }
        BreadthFirstDirectedPaths bfdpV = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfdpW = new BreadthFirstDirectedPaths(G, w);
        int minLength = Integer.MAX_VALUE;
        int anc = -1;
        for (int u = 0; u < G.V(); ++u) {
            if (!bfdpV.hasPathTo(u) || !bfdpW.hasPathTo(u)) {
                continue;
            }
            int dist = bfdpV.distTo(u) + bfdpW.distTo(u);
            if (dist < minLength) {
                minLength = dist;
                anc = u;
            }
        }
        return anc;
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        for (Integer i : v) {
            if (i == null) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        for (Integer j : w) {
            if (j == null) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        if (v.equals(null) || w.equals(null)) {
            throw new java.lang.IllegalArgumentException("Illegal argument");
        }
        for (int iv : v) {
            if (!checkBounds(iv)) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        for (int iw : w) {
            if (!checkBounds(iw)) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        int minLength = Integer.MAX_VALUE;
        for (int iv : v) {
            for (int iw : w) {
                int dist = length(iv, iw);
                if (dist == -1) {
                    continue;
                }
                if (dist < minLength) {
                    minLength = dist;
                }
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return -1;
        } else {
            return minLength;
        }
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        for (Integer i : v) {
            if (i == null) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        for (Integer j : w) {
            if (j == null) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        if (v.equals(null) || w.equals(null)) {
            throw new java.lang.IllegalArgumentException("Illegal argument");
        }
        for (int iv : v) {
            if (!checkBounds(iv)) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        for (int iw : w) {
            if (!checkBounds(iw)) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        int minLength = Integer.MAX_VALUE;
        int anc = -1;
        for (int iv : v) {
            for (int iw : w) {
                int dist = length(iv, iw);
                if (dist == -1) {
                    continue;
                }
                if (dist < minLength) {
                    minLength = dist;
                    anc = ancestor(iv, iw);
                }
            }
        }
        return anc;
    }
    public static void main(String[] args) {

    }
}
