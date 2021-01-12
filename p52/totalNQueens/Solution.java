//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
//
//
//
//
//
// 示例 1：
//
//
//输入：n = 4
//输出：2
//解释：如上图所示，4 皇后问题存在两个不同的解法。
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= n <= 9
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
//
//
//
// Related Topics 回溯算法
// 👍 222 👎 0
package algorithme.p52.totalNQueens;

public class Solution {
    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            System.out.printf("%d: ", i);
            System.out.println(new Solution().totalNQueens(i));
        }

    }
    public int totalNQueens(int n) {
        return resolve(0, n , 0, 0 ,0);
    }

    public int resolve(int i, int n, int columns, int d1, int d2) {
        if (i == n) {
            return 1;
        } else {
            int count = 0;
            // 比特位1表示可以放置皇后的位置
            int availablePositions = ((1 << n) -1) & ~(columns | d1 | d2);
            while (availablePositions != 0) {
                // low bit 获取最低位的1,当前第一个可以放置皇后的位置
                int position = availablePositions & -availablePositions;
                // 去除当前位置
                availablePositions = availablePositions & (availablePositions - 1);
                count += resolve(i+1, n, columns | position, (d1 | position) >> 1, (d2 | position) << 1);
            }
            return count;
        }
    }
}