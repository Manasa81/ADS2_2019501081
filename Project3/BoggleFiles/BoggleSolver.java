import java.util.Set;
import java.util.TreeSet;

public class BoggleSolver {
    // Initializes the data structure using the given array of strings as the
    // dictionary.
    // (You can assume each word in the dictionary contains only the uppercase
    // letters A through Z.)
    private TrieSET listOfWords;

    public BoggleSolver(String[] dictionary)
     {
        listOfWords = new TrieSET();
        for (String word : dictionary)
         {
            listOfWords.add(word);
        }
    }

    // Returns the map of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board)
     {
        if (board == null)
         {
            throw new NullPointerException("board is empty");
        }
        Set<String> words = new TreeSet<String>();

        int rows = board.rows();
        int cols = board.cols();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean[][] visited = new boolean[rows][cols];
                dfs(board, i, j, visited, "", words);
            }
        }
        return words;
    }

    private void dfs(BoggleBoard board, int row, int col, boolean[][] marked, String prefix,
            Set<String> map)
        {
        if (marked[row][col])
         {
            return;
        }

        char letter = board.getLetter(row, col);
        String word = prefix;

        if (letter == 'Q')
        {
            word += "QU";
        } else
         {
            word += letter;
        }

        if (!listOfWords.hasPrefix(word))
         {
            return;
        }

        if (word.length() > 2 && listOfWords.contains(word))
        {
            map.add(word);
        }

        marked[row][col] = true;

        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
             {
                if (i == 0 && j == 0)
                {
                    continue;
                }

                if ((row + i >= 0) && (row + i < board.rows()) && (col + j >= 0) && (col + j < board.cols()))
                {
                    dfs(board, row + i, col + j, marked, word, map);
                }
            }
        }

        marked[row][col] = false;

    }

    // Returns the score of the given word if it is in the dictionary, zero
    // otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word)
     {
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
