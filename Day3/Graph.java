import java.util.ArrayList;

/**
 * Graph Took refernce from Geeks for Geeks.
 */
public class Graph<E> {

    int vertices;
    int edges;
    ArrayList<ArrayList<E>> adj;

    Graph(int n) {
        vertices = n;

        adj = new ArrayList<ArrayList<E>>(n);
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<E>());

    }

    void addEdge(int u, E v) {
        adj.get(u).add(v);
        edges++;
    }

    void printAdjacencyList(ArrayList<ArrayList<E>> adj) {
        for (int i = 0; i < adj.size(); i++) {
            System.out.println("Adjacency list of " + i);
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(adj.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public int V() {
        return vertices;
    }

    public int E() {
        return edges;
    }

    ArrayList<E> adj(int V) {
        return adj.get(V);
    }

    public static void main(String[] args) {
        Graph<Integer> g = new Graph(5);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.printAdjacencyList(g.adj);
    }

}