//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
//
//
//
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
//
//
//
// 示例 1：
//
//
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：[["Q"]]
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
// 👍 715 👎 0
package algorithme.p51.solveNQueens;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        List<List<String>> res = new Solution().solveNQueens(10);
        res.forEach(System.out::println);
        System.out.println("size: " + res.size());
    }

    // 垂直
    int[] v;
    // 水平
    int[] h;
    // 左上-右下
    int[] lt_rb;
    // 右上-左下
    int[] rt_lb;
    // 位置图
    char[][] map;
    // 结果
    List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {
        v = new int[n];
        h = new int[n];
        lt_rb = new int[2 * n - 1];
        rt_lb = new int[2 * n - 1];
        map = new char[n][n];
        Arrays.fill(v, 0);
        Arrays.fill(h, 0);
        Arrays.fill(lt_rb, 0);
        Arrays.fill(rt_lb, 0);
        for (char[] chars : map) {
            Arrays.fill(chars, '.');
        }
        res = new ArrayList<>();
        return backtrace(0, n);
    }

    public List<List<String>> backtrace(int i, int n) {
        if (i == n) {
            List<String> list = new ArrayList<>();
            for (char[] chars : map) {
                list.add(new String(chars));
            }
            res.add(list);
        } else {
            int loc = -1;
            for (int j = 0; j < n; j++) {
                int flag = h[i] | v[j] | lt_rb[(n - 1) - (i - j)] | rt_lb[i + j];
                if (flag == 0) {
                    map[i][j] = 'Q';
                    loc = j;
                    h[i] = h[i] | (1 << j);
                    v[j] = v[j] | (1 << i);
                    lt_rb[(n - 1) - (i - j)] = lt_rb[(n - 1) - (i - j)] | (1 << i);
                    rt_lb[i + j] = rt_lb[i + j] | (1 << i);
                    backtrace(i + 1, n);
                }
                // 如果之前有放置过皇后,应该还原
                if (loc != -1) {
                    map[i][loc] = '.';
                    h[i] = h[i] & ~(1 << loc);
                    v[loc] = v[loc] & ~(1 << i);
                    lt_rb[(n - 1) - (i - loc)] = lt_rb[(n - 1) - (i - loc)] & ~(1 << i);
                    rt_lb[i + loc] = rt_lb[i + loc] & ~(1 << i);
                    loc = -1;

                    System.out.println("********************************");
                    print();
                    System.out.println("********************************");
                }
                print();
                System.out.println("----------------------------");
            }
        }
        return res;
    }

    private void print() {
        System.out.println(Arrays.toString(h));
        System.out.println(Arrays.toString(v));
        System.out.println(Arrays.toString(lt_rb));
        System.out.println(Arrays.toString(rt_lb));
        for (char[] chars : map) {
            System.out.println(Arrays.toString(chars));
        }
    }
}



class Solution2 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<List<String>>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> diagonals1 = new HashSet<Integer>();
        Set<Integer> diagonals2 = new HashSet<Integer>();
        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    public void backtrack(List<List<String>> solutions, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                backtrack(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);
                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}


class Solution1 {
    public List<List<String>> solveNQueens(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        List<List<String>> solutions = new ArrayList<List<String>>();
        solve(solutions, queens, n, 0, 0, 0, 0);
        return solutions;
    }

    public void solve(List<List<String>> solutions, int[] queens, int n, int row, int columns, int diagonals1, int diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            // 获取可以放置的皇后的位置
            // 从低位到高位上的0表示棋盘从左到右对应位置可以放置皇后
            int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2));
            while (availablePositions != 0) {
                // low bit获取最低位的1的位置
                int position = availablePositions & (-availablePositions);
                // 重置最低位的1为0
                availablePositions = availablePositions & (availablePositions - 1);
                // 计算二进制数中1的个数(计算可以放置皇后的列索引,从0开始)
                int column = Integer.bitCount(position - 1);
                // 该行放置皇后
                queens[row] = column;
                // columns | position: 将此次放置皇后位置的列加入禁止放入皇后位置的列集合中
                // (diagonals1 | position) << 1: 下一行的左上-右下对角线禁止集合
                // (diagonals2 | position) >> 1: 下一行的左下-右上对角线禁止集合
                solve(solutions, queens, n, row + 1, columns | position, (diagonals1 | position) << 1, (diagonals2 | position) >> 1);
                // 放置此位置的情况计算完,从新计算下个可用的位置,恢复之前的放置情况
                queens[row] = -1;
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}


