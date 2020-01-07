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
        System.out.println(Arrays.toString(list.get(33)));
        return list;
    }

    private String[] parseHypernyms(String fileName) throws IOException {
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
        System.out.println(Arrays.toString(list.get(34)));
        return words;
    }

    public static void main(String[] args) throws IOException {
        WordNet w = new WordNet();
        w.parseSynsets("synsets.txt");
        w.parseHypernyms("hypernyms1000-subgraph.txt");
    }
}