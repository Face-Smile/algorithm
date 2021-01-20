//给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
//
// 示例:
//
// 输入: 3
//输出:
//[
// [ 1, 2, 3 ],
// [ 8, 9, 4 ],
// [ 7, 6, 5 ]
//]
// Related Topics 数组
// 👍 286 👎 0
package algorithm.p59.generateMatrix;


public class Solution {
    public static void main(String[] args) {
        for (int[] ints : new Solution().generateMatrix(20)) {
            for (int anInt : ints) {
                System.out.printf("%5d", anInt);
            }
            System.out.println();
        }
        System.out.println("------------------------------------------------------");
        for (int[] ints : new Solution().generateMatrix1(20)) {
            for (int anInt : ints) {
                System.out.printf("%5d", anInt);
            }
            System.out.println();
        }
    }

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int count = 0;
        int i = 0, j = 0;
        while (count < n * n) {
            while (j < n && matrix[i][j] == 0) {
                matrix[i][j++] = ++count;
            }
            i++;
            j--;
            while (i < n && matrix[i][j] == 0) {
                matrix[i++][j] = ++count;
            }
            i--;
            j--;
            while (j >= 0 && matrix[i][j] == 0) {
                matrix[i][j--] = ++count;
            }
            i--;
            j++;
            while (i >= 0 && matrix[i][j] == 0) {
                matrix[i--][j] = ++count;
            }
            i++;
            j++;
        }
        return matrix;
    }

    public int[][] generateMatrix1(int n) {
        int[][] matrix = new int[n][n];
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int all = n * n;
        int count = 0;
        while (count < all) {
            for (int i = l; i <= r; i++) matrix[t][i] = ++count;
            t++;
            for (int i = t; i <= b; i++) matrix[i][r] = ++count;
            r--;
            for (int i = r; i >= l; i--) matrix[b][i] = ++count;
            b--;
            for (int i = b; i >= t; i--) matrix[i][l] = ++count;
            l++;
        }
        return matrix;
    }
}
