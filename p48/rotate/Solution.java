package algorithme.p48.rotate;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        // int[][] matrix = new int[][]{
        //         {5, 1, 9, 11},
        //         {2, 4, 8, 10},
        //         {13, 3, 6, 7},
        //         {15, 14, 12, 16}
        // };
        int[][] matrix = new int[][]{
                {1, 2},
                {3, 4},
        };
        for (int[] m : matrix) {
            System.out.println(Arrays.toString(m));
        }
        new Solution().rotate(matrix);
        System.out.println("----------------------------------------");
        for (int[] m : matrix) {
            System.out.println(Arrays.toString(m));
        }
    }

    /**
     * 方式一
     * 用公式解释就是 matrix[row][col]=matrix[n−col−1][row]
     * 方式二
     * 先上下翻转,再沿着 左上-右下 对角线翻转
     * 方式三: 如下,对应位置置换
     * 三种方式的原理相同
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length - 1;
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = i; j < matrix.length - 1 - i; j++) {
                System.out.println("**********************");
                System.out.printf("%4d,%4d\n", i, j);
                System.out.printf("%4d,%4d\n", n - j, i);
                System.out.printf("%4d,%4d\n", n - i, n - j);
                System.out.printf("%4d,%4d\n", j, n - i);
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j][i];
                matrix[n - j][i] = matrix[n - i][n - j];
                matrix[n - i][n - j] = matrix[j][n - i];
                matrix[j][n - i] = temp;
            }
        }
    }
}
