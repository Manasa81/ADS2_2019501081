import java.io.IOException;
import java.util.ArrayList;

public class Outcast {
    WordNet words;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        words = wordnet;
        // System.out.println(words.g);
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) throws InterruptedException {
        int max = 0;
        int count;
        String resNoun = "";
        for (String i : nouns) {
            count = 0;
            for (String j : nouns) {
                if (!i.equals(j)) {
                    count += words.distance(i, j);
                }
            }
            if (max < count) {
                max = count;
                resNoun = i;
            }
        }
        return resNoun;
    }

    // see test client below
    public static void main(String[] args) throws InterruptedException, IOException {
        Outcast o = new Outcast(new WordNet("synsets.txt", "hypernyms.txt"));
        String[] nouns = { "horse", "zebra", "bear", "table", "cat" };
        System.out.println(o.outcast(nouns));
    }
}