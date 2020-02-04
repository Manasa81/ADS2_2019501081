package Boggle;

import java.util.*;
import edu.princeton.cs.algs4.TST;

public class BoggleSolver {
    private static final String ArrayList = null;
    TST<Integer> t;
    int rows;
    int col;

    // Initializes the data structure using the given array of strings as the
    // dictionary.
    // (You can assume each word in the dictionary contains only the uppercase
    // letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        t = new TST<>();
        for (String i : dictionary) {
            t.put(i, scoreOf(i));
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        if (board == null) {
            throw new IllegalArgumentException();
        }
        rows = board.rows();
        col = board.cols();
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; i < col; i++) {
                if (i == 0) {
                    String word = "";
                    word += board.getLetter(i, j);
                    if (isAvailable(word)) {
                        list.add(word);
                    }
                }

            }
        }
    }

    private boolean getWordsInBoard(BoggleBoard board, int i, int j) {

    }



    private boolean isAvailable(String word) {
        Queue<String> q = (Queue) t.keysWithPrefix(word);
        for (String i : q) {
            if (i.equals(word)) {
                return true;
            }
        }
        return false;
    }

    // Returns the score of the given word if it is in the dictionary, zero
    // otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        int l = word.length();
        if (word.length() == 3 || word.length() == 4) {
            return 1;
        } else if (l > 4) {
            if (l == 5) {
                return 2;
            } else if (l == 6) {
                return 3;
            } else if (l == 7) {
                return 5;
            } else {
                return 11;
            }
        }
        return 0;
    }

}