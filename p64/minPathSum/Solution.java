//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
//
// 说明：每次只能向下或者向右移动一步。
//
//
//
// 示例 1：
//
//
//输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//输出：7
//解释：因为路径 1→3→1→1→1 的总和最小。
//
//
// 示例 2：
//
//
//输入：grid = [[1,2,3],[4,5,6]]
//输出：12
//
//
//
//
// 提示：
//
//
// m == m
// n == grid[i].length
// 1 <= m, n <= 200
// 0 <= grid[i][j] <= 100
//
// Related Topics 数组 动态规划
// 👍 761 👎 0
package algorithm.p64.minPathSum;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().minPathSum(new int[][]{
                {9, 1, 4, 8},
                {9, 1, 4, 8},
                {9, 1, 4, 8},
        }));
        System.out.println(new Solution().minPathSum(new int[][]{
                {9, 1, 4, 8}
        }));
        System.out.println(new Solution().minPathSum(new int[][]{
                {9},
                {1},
                {4},
                {8},
        }));
        System.out.println(new Solution().minPathSum(new int[][]{
                {9}
        }));
    }

    public int minPathSum(int[][] grid) {
        // System.out.println("correct: " + minPathSum1(grid));
        int n = grid[0].length;
        int[] dp = new int[n]; // 记录上一行的值
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int[] nums : grid) {
            dp[0] += nums[0];
            for (int j = 1; j < n; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + nums[j];
            }
        }
        return dp[n-1];
    }

    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        row[0] = col[0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            row[i] = row[i - 1] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            col[i] = col[i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                row[i] = col[j] = Math.min(row[i], col[j]) + grid[i][j];
            }
        }
        return n == 1 ? row[m - 1] : col[n - 1];
    }

    public int minPathSum1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
