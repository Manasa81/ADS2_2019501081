/**
 * . Outcast
 */
public class Outcast {
    /**
     * . words to pass
     */
    private WordNet words;

    /**
     *
     * @param wordnet wordnet object
     */
    public Outcast(WordNet wordnet) {
        words = wordnet;
        // System.out.println(words.g);
    }

    /**
     *
     * @param nouns input nouns
     * @return a noun
     */
    public String outcast(final String[] nouns) {
        int max = 0;
        int count;
        String resNoun = "m";
        for (String i : nouns) {
            count = 0;
            for (String j : nouns) {
                if (!i.equals(j)) {
                    count += words.distance(i, j);
                }
                // System.out.println(count);
            }
            // System.out.println("max is " + max + " count is " + count);
            if (max < count) {
                max = count;
                resNoun = i;
            }
        }
        // System.out.println(resNoun);
        return resNoun;
    }

    /**
     *
     * @param args args
     */
    public static void main(final String[] args) {
    }
}
