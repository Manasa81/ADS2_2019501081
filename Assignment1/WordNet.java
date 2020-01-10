import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * WordNet
 */
public class WordNet {

    private ArrayList<String[]> parseSynsets(String fileName) throws IOException {
        File file = new File("C:\\Users\\Manasa\\Documents\\New folder\\ADS2_2019501081\\Assignment1\\" + fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] words = null;
        String line = br.readLine();
        while (line != null) {
            words = null;
            line = line.trim();
            words = line.split(",");
            line = br.readLine();
            list.add(words);
        }
        br.close();
        System.out.println(Arrays.toString(list.get(34)));
        return list;
    }

    private ArrayList<String[]> parseHypernyms(String fileName) throws IOException {
        File file = new File("C:\\Users\\Manasa\\Documents\\New folder\\ADS2_2019501081\\Assignment1\\" + fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder text = new StringBuilder();
        String line = br.readLine();
        String[] words = null;
        ArrayList<String[]> list = new ArrayList<String[]>();
        while (line != null) {
            words = null;
            line = line.trim();
            words = line.split(",");
            line = br.readLine();
            list.add(words);
        }
        br.close();
        // System.out.println(Arrays.toString(list.get(34)));
        return list;
    }

    /**
     * This method is only used to connect hypernyms Since hypernms are basically
     * integers in nature we are passing generic of Integer type into graph.
     */
    public void addConnectionsToHypernyms(String fileName) throws IOException {
        ArrayList<String[]> list = parseHypernyms(fileName);
        Graph<Integer> g = new Graph(list.size());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length == 1) {
                g.addEdge(Integer.parseInt(list.get(i)[0]), null);
            }
            if (list.get(i).length == 2) {
                g.addEdge(Integer.parseInt(list.get(i)[0]), Integer.parseInt(list.get(i)[1]));
            } else {
                for (int j = 1; j < list.get(i).length; j++) {
                    g.addEdge(Integer.parseInt(list.get(i)[0]), Integer.parseInt(list.get(i)[j]));
                }
            }
        }
        g.printAdjacencyList(g.adj);
    }

    /**
     * This method is only used to connect synsets.As synsets contain both integers
     * and strings, we are passing generic of String type into graph.
     */
    public void addConnectionsToSynsets(String fileName) throws IOException {
        ArrayList<String[]> list = parseHypernyms(fileName);
        Graph<String> g = new Graph(list.size());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length == 1) {
                g.addEdge(Integer.parseInt(list.get(i)[0]), null);
            }
            if (list.get(i).length == 2) {
                g.addEdge(Integer.parseInt(list.get(i)[0]), list.get(i)[1]);
            } else {
                for (int j = 1; j < list.get(i).length; j++) {
                    g.addEdge(Integer.parseInt(list.get(i)[0]), j + ")" + list.get(i)[j]);
                }
            }
        }
        g.printAdjacencyList(g.adj);
    }

    public static void main(String[] args) throws IOException {
        WordNet w = new WordNet();
        w.parseSynsets("synsets.txt");
        // w.addConnectionsToSynsets("synsets100-subgraph.txt");

    }
}