import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Solution
 */
public class Solution {
    Digraph g;
    HashMap<Integer, String> emailMap = new HashMap<>();
    HashMap<Integer, ArrayList<Integer>> logsMap = new HashMap<>();

    Solution(String fileName, String fileName1) throws IOException {
        parseEmails(fileName);
        parseLogs(fileName1);
        // System.out.println(emailMap.size());
        g = new Digraph(emailMap.size());
        addConnections();

    }

    public void parseEmails(String fileName) throws IOException {
        File file = new File("C:\\Users\\Manasa\\Documents\\New folder\\ADS - 2 Exam - 1\\" + fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        while (line != null) {
            String[] words = line.split(";");
            int n = Integer.parseInt(words[0]);
            emailMap.put(n, words[1]);
            line = br.readLine();
        }
        // System.out.println(emailMap.get("237045@gmail.com"));
    }

    public void parseLogs(String fileName) throws IOException {
        File file = new File("C:\\Users\\Manasa\\Documents\\New folder\\ADS - 2 Exam - 1\\" + fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        while (line != null) {
            String[] words = line.split(",");
            String[] str1 = words[0].split(" ");
            String[] str2 = words[1].split(" ");
            int id = Integer.parseInt(str1[1]);
            int value = Integer.parseInt(str2[str2.length - 1]);
            if (!logsMap.containsKey(id)) {
                ArrayList<Integer> values = new ArrayList<>();
                values.add(value);
                logsMap.put(id, values);
            } else {
                logsMap.get(id).add(value);
            }

            line = br.readLine();

        }
    }

    public void addConnections() {
        for (int i : logsMap.keySet()) {
            for (int j : logsMap.get(i)) {
                g.addEdge(i, j);
            }
        }

    }

    public void inDegree() {
        HashMap<Integer, ArrayList<Integer>> in = new HashMap<>();
        TreeMap<Integer, ArrayList<Integer>> sortedIn = new TreeMap<>(Collections.reverseOrder());
        for (int i : emailMap.keySet()) {
            if (!in.containsKey(g.indegree(i))) {
                ArrayList<Integer> values = new ArrayList<>();
                values.add(i);
                in.put(g.indegree(i), values);
            } else {
                in.get(g.indegree(i)).add(i);
            }

        }

        sortedIn.putAll(in);
        for (int i = 0; i < 10; i++) {
            int key = (int) sortedIn.keySet().toArray()[i];
            ArrayList<Integer> value = sortedIn.get(key);
            System.out.println(emailMap.get(value.get(0)) + "," + key);
        }
        return;

    }

    public static void main(String[] args) throws IOException {
        Solution s = new Solution("emails.txt", "email-logs.txt");
        s.parseEmails("emails.txt");
        s.inDegree();
    }
}