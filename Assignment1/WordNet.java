import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * WordNet
 */
public class WordNet {

    private String[] parseSynsets(String fileName) throws IOException {
        File file = new File("C:\\Users\\Manasa\\Documents\\New folder\\ADS2_2019501081\\Assignment1\\" + fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String[] words;
        StringBuilder text = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            text.append(line).append(" ");
            line = br.readLine();
        }
        br.close();
        words = text.toString().split(" ");
        return words;
    }

    private String[] parseHypernyms(String fileName) throws IOException {
        File file = new File("C:\\Users\\Manasa\\Documents\\New folder\\ADS2_2019501081\\Assignment1\\" + fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder text = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            text.append(line).append(" ");
            line = br.readLine();
        }
        String[] words = text.toString().split(" ");
        System.out.println(words[0]);
        return words;
    }

    public static void main(String[] args) throws IOException {
        WordNet w = new WordNet();
        w.parseSynsets("synsets11.txt");
        w.parseHypernyms("hypernyms100-subgraph.txt");
    }
}