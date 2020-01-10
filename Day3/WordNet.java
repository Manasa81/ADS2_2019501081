import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashSet;

public class WordNet {
    ArrayList<String[]> slist;
    ArrayList<String[]> hlist;
    HashMap<String, ArrayList<Integer>> hashWithNounKey;
    HashMap<Integer, String[]> hashWithIdKey;
    Digraph g;
    SAP sap;

    public WordNet(String synsets, String hypernyms) throws IOException {
        hashWithNounKey = new HashMap<>();
        hashWithIdKey = new HashMap<>();
        slist = parseSynsets(synsets);
        hlist = parseHypernyms(hypernyms);
        g = new Digraph(slist.size());
        sap = new SAP(g);

    }

    private HashMap<String, ArrayList<Integer>> keyNoun(ArrayList<String[]> slist) {
        for (int i = 0; i < slist.size(); i++) {
            String[] nouns = slist.get(i)[1].split(" ");
            int v = Integer.parseInt(slist.get(i)[0]);
            if (nouns.length == 1) {
                if (!hashWithNounKey.containsKey(nouns[0])) {
                    ArrayList<Integer> values = new ArrayList<>();
                    values.add(v);
                    hashWithNounKey.put(nouns[0], values);
                } else {
                    hashWithNounKey.get(nouns[0]).add(v);
                }

            } else {
                for (int j = 0; j < nouns.length; j++) {
                    if (!hashWithNounKey.containsKey(nouns[j])) {
                        ArrayList<Integer> values = new ArrayList<>();
                        values.add(v);
                        hashWithNounKey.put(nouns[j], values);
                    } else {
                        hashWithNounKey.get(nouns[j]).add(v);
                    }

                }
            }
        }
        System.out.println(hashWithNounKey.get("AB"));
        return hashWithNounKey;
    }

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
            int id = Integer.parseInt(words[0]);
            String[] nouns = words[1].split(" ");
            if (!hashWithIdKey.containsKey(id)) {
                hashWithIdKey.put(id, nouns);
            }
            line = br.readLine();
            list.add(words);
        }
        br.close();
        // System.out.println(Arrays.toString(list.get(10)));
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

    private void addConnectionsToHypernyms() throws IOException {
        int count = 0;
        System.out.println(Arrays.toString(hlist.get(5)));
        for (int i = 0; i < hlist.size(); i++) {
            // if (hlist.get(i).length == 1) {
            // g.addEdge(Integer.parseInt(hlist.get(i)[0]), null);
            // count++;
            // }
            if (hlist.get(i).length == 2) {
                g.addEdge(Integer.parseInt(hlist.get(i)[0]), Integer.parseInt(hlist.get(i)[1]));
                count++;
            } else if (hlist.get(i).length > 2) {
                for (int j = 1; j < hlist.get(i).length; j++) {
                    g.addEdge(Integer.parseInt(hlist.get(i)[0]), Integer.parseInt(hlist.get(i)[j]));
                    count++;
                }
            }
        }
        // g.printAdjacencyList(g.adj);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        ArrayList<String> nouns = new ArrayList<>();
        for (String str : hashWithNounKey.keySet()) {
            nouns.add(str);
        }
        return nouns;
    }

    // // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (hashWithNounKey.containsKey(word)) {
            return true;
        } else {
            return false;
        }

    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) throws InterruptedException {
        if (!hashWithNounKey.containsKey(nounA) || (!hashWithNounKey.containsKey(nounB))) {
            return -1;
        } else {
            return sap.length(hashWithNounKey.get(nounA), hashWithNounKey.get(nounB));
        }
    }

    // // a synset (second field of synsets.txt) that is the common ancestor of
    // // nounA
    // // and nounB
    // // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) throws InterruptedException {
        if (!hashWithNounKey.containsKey(nounA) || (!hashWithNounKey.containsKey(nounB))) {
            return null;
        } else {
            int ancestor = sap.ancestor(hashWithNounKey.get(nounA), hashWithNounKey.get(nounB));
            return hashWithIdKey.get(ancestor)[0];

        }

    }

    // do unit testing of this class
    public static void main(String[] args) throws IOException {
        WordNet w = new WordNet("synsets.txt", "hypernyms.txt");
        w.addConnectionsToHypernyms();
        w.keyNoun(w.slist);
        System.out.println(Arrays.toString(w.hashWithIdKey.get(34)));

        // System.out.println(w.distance("AIDS", "manasa"));
    }
}