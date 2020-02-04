package Boggle;

import java.util.Stack;

/**
 * Practice
 */
public class Practice {
    static void depthFirstSearch(int[][] matrix, int source) {
        boolean[] visited = new boolean[matrix.length];
        visited[source - 1] = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        int i, x;
        System.out.println("The depth first order is");
        System.out.println(source);
        while (!stack.isEmpty()) {
            x = stack.pop();
            for (i = 0; i < matrix.length; i++) {
                if (matrix[x - 1][i] == 1 && visited[i] == false) {
                    stack.push(x);
                    visited[i] = true;
                    System.out.println(i + 1);
                    x = i + 1;
                    i = -1;
                }
            }
        }
    }
    public static void main(String[] args) {
        int[][] arr = new int[3][3];
        arr[0][0] = 1;
        arr[0][1] = 2;
        arr[0][2] = 3;
        arr[1][0] = 4;
        arr[1][1] = 5;
        arr[1][2] = 6;
        arr[2][0] = 7;
        arr[2][1] = 8;
        arr[2][2] = 9;
        depthFirstSearch(arr, 2);

    }
}
