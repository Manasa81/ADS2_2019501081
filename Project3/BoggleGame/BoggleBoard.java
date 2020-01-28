import java.util.Scanner;

public class BoggleSolver {
    // Initializes the data structure using the given array of strings as the
    // dictionary.
    // (You can assume each word in the dictionary contains only the uppercase
    // letters A through Z.)
    public BoggleSolver(String[] dictionary) {

    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {

    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        int l = word.length();
        if (word == null || l<3) {
            return 0;
        }
        if (l == 3 || l == 4) {
            return 1;
        } else if(l>4){
            if(l==5){
                return 2;
            } else if (l == 6) {
                return 3;
            } else if (l == 7) {
                return 5;
            } else {
                return 11;
            }
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner();

    }
}