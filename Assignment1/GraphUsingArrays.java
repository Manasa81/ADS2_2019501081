import java.util.ArrayList;
import java.util.Arrays;

/**
 * GraphUsingArrays
 */
public class GraphUsingArrays {

    int n;
    ArrayList<Integer>[] adj;

    GraphUsingArrays(int n) {
        this.n = n;
        adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {

            adj[i] = new ArrayList<>();
        }
        // System.out.println(Arrays.toString(adj));
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    void print(ArrayList<Integer>[] adj) {
        // System.out.println(Arrays.toString(adj));
        for (int i = 0; i < adj.length; i++) {
            System.out.println(i + " connected to " + adj[i]);
        }
    }

    public static void main(String[] args) {
        GraphUsingArrays g = new GraphUsingArrays(3);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(1, 3);
        g.addEdge(2, 0);
        g.print(g.adj);
    }
}