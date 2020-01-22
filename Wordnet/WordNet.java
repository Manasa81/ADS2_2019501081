import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

/**
 * . Wordnet
 * Took reference from bob sedgewick SAP implementation class.
 */
public class WordNet {
    /**
     * . synstes list
     */
    private ArrayList<String[]> slist;
    /**
     * . synstes list
     */
    private ArrayList<String[]> hlist;
    /**
     * . synstes list
     */
    private HashMap<String, ArrayList<Integer>> hashWithNounKey
    = new HashMap<>();
    /**
     * . synstes list
     */
    private HashMap<Integer, String[]> hashWithIdKey = new HashMap<>();
    /**
     * . synstes list
     */
    private Digraph g;
    /**
     * . synstes list
     */
    private SAP sap;

    /**
     *
     * @param synsets   file name
     * @param hypernyms file name
     */
    public WordNet(final String synsets, final String hypernyms) {
        slist = parseSynsets(synsets);
        g = new Digraph(slist.size());
        // sap = new SAP(g);
        hlist = parseHypernyms(hypernyms);
        addConnectionsToHypernyms();
        sap = new SAP(g);

        hashWithNounKey = keyNoun(slist);


    }

    /**
     *
     * @param slist1 synsets lists
     * @return hashmap
     */
    private HashMap<String, ArrayList<Integer>>
    keyNoun(final ArrayList<String[]> slist1) {
        for (int i = 0; i < slist1.size(); i++) {
            String[] nouns = slist1.get(i)[1].split(" ");
            int v = Integer.parseInt(slist1.get(i)[0]);
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
        // System.out.println(hashWithNounKey.get("AB"));
        return hashWithNounKey;
    }

    /**
     *
     * @param fileName file
     * @return list
     */
    private ArrayList<String[]> parseSynsets(final String fileName) {
        In in = new In(fileName);
        int count = 0;
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] words = null;
        String line = "";
        while (!in.isEmpty()) {
            line = in.readLine();
            words = null;
            line = line.trim();
            words = line.split(",");
            if (words.length < 2) {
                throw new java.lang.IllegalArgumentException("less" + "number of lines");
            }
            int id = Integer.parseInt(words[0]);
            String[] nouns = words[1].split(" ");
            if (!hashWithIdKey.containsKey(id)) {
                hashWithIdKey.put(id, nouns);
            }
            list.add(words);
        }
        return list;
    }

    /**
     *
     * @param fileName file
     * @return list of hypernyms
     */
    private ArrayList<String[]> parseHypernyms(final String fileName) {
        In in = new In(fileName);
        String line = "";
        String[] words = null;
        ArrayList<String[]> list = new ArrayList<String[]>();
        while (!in.isEmpty()) {
            line = in.readLine();
            if (line.equals(null)) {
                throw new java.lang.IllegalArgumentException("no enough lines");
            }
            words = null;
            line = line.trim();
            words = line.split(",");
            list.add(words);
        }
        return list;
    }

    /**
     *
     */
    private void addConnectionsToHypernyms() {
        int count = 0;
        for (int i = 0; i < hlist.size(); i++) {
            if (hlist.get(i).length == 2) {
                g.addEdge(Integer.parseInt(hlist.get(i)[0]),
                Integer.parseInt(hlist.get(i)[1]));
                count++;
            } else if (hlist.get(i).length > 2) {
                for (int j = 1; j < hlist.get(i).length; j++) {
                    g.addEdge(Integer.parseInt(hlist.get(i)[0]),
                    Integer.parseInt(hlist.get(i)[j]));
                    count++;
                }
            }
        }
    }

    /**
     * . iterable
     *
     * @return nouns
     */
    public Iterable<String> nouns() {
        ArrayList<String> nouns = new ArrayList<>();
        for (String str : hashWithNounKey.keySet()) {
            nouns.add(str);
        }
        return nouns;
    }

    /**
     *
     * @param word noun
     * @return true/false
     */
    public boolean isNoun(final String word) {
        return hashWithNounKey.containsKey(word);

    }

    /**
     *
     * @param nounA noun
     * @param nounB moun
     * @return distance between nounA and nounB (defined below)
     */
    public int distance(final String nounA, final String nounB) {
        // System.out.println(nounA + " " + nounB);
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new java.lang.IllegalArgumentException("no noun");
        } else {
            ArrayList<Integer> v = hashWithNounKey.get(nounA);
            ArrayList<Integer> w = hashWithNounKey.get(nounB);
            if (v == null || w == null) {
                throw new java.lang.IllegalArgumentException(
                    "illegal argument");
            }
            return sap.length(v, w);
        }
    }

    // // a synset (second field of synsets.txt) that is the common ancestor of
    // // nounA
    // // and nounB
    // // in a shortest ancestral path (defined below)
    /**
     *
     * @param nounA word
     * @param nounB word
     * @return a synset (second field of synsets.txt) that is common ancestor
     *         of nounA and nounB
     */
    public String sap(final String nounA, final String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new java.lang.IllegalArgumentException("no noun");
        } else {
            int ancestor = sap.ancestor(hashWithNounKey.get(nounA),
             hashWithNounKey.get(nounB));
            return hashWithIdKey.get(ancestor)[0];

        }

    }

    /**
     *
     * @param args rges
     */
    public static void main(final String[] args) {
        // WordNet w = new WordNet("C:\\Users\\Manasa\\Documents\\New folder\\ADS2_2019501081\\Assignment1\\synsets.txt",
        //         "C:\\Users\\Manasa\\Documents\\New folder\\ADS2_2019501081\\Assignment1\\hypernyms.txt");
        // // System.out.println(Arrays.toString(w.hashWithIdKey.get(34)));
        // System.out.println(w.isNoun("zebra"));
        // System.out.println(w.distance("Cattell","panetela"));
    }
}
