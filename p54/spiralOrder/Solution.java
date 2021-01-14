//给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
//
// 示例 1:
//
// 输入:
//[
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
//]
//输出: [1,2,3,6,9,8,7,4,5]
//
//
// 示例 2:
//
// 输入:
//[
//  [1, 2, 3, 4],
//  [5, 6, 7, 8],
//  [9,10,11,12]
//]
//输出: [1,2,3,4,8,12,11,10,9,5,6,7]
//
// Related Topics 数组
// 👍 581 👎 0
package algorithme.p54.spiralOrder;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        // int[][] matrix = new int[][]{
        //         {1, 2, 3, 4, 17},
        //         {5, 6, 7, 8, 18},
        //         {9, 10, 11, 12, 19},
        //         // {13, 14, 15, 16}
        // };
        // int[][] matrix = new int[][]{
        //         {1, 2, 3},
        //         {4, 5, 6},
        //         {7, 8, 9}
        // };
        int[][] matrix = new int[][] {
                {2}, {3}
        };
        System.out.println(new Solution().spiralOrder(matrix));
    }

    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> integerList = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) return integerList;
        int n = matrix[0].length;
        for (int i = 0; i < Math.min((m + 1) / 2, (n + 1) / 2); i++) {
            if (i == m / 2 && i == n / 2 && m == n) {
                integerList.add(matrix[i][i]);
            } else if (i == m / 2) {
                for (int j = i; j < n - i; j++) {
                    integerList.add(matrix[i][j]);
                    System.out.println(1);
                }
            } else if (i == n / 2) {
                for (int j = i; j < m - i; j++) {
                    integerList.add(matrix[j][n - 1 - i]);
                    System.out.println(2);
                }
            } else {
                for (int j = i; j < n - 1 - i; j++) {
                    integerList.add(matrix[i][j]);
                    System.out.println(1);
                }
                for (int j = i; j < m - 1 - i; j++) {
                    integerList.add(matrix[j][n - 1 - i]);
                    System.out.println(2);
                }
                for (int j = n - 1 - i; j > i; j--) {
                    integerList.add(matrix[m - 1 - i][j]);
                    System.out.println(3);
                }
                for (int j = m - 1 - i; j > i; j--) {
                    integerList.add(matrix[j][i]);
                    System.out.println(4);
                }
            }
        }
        return integerList;
    }

}

/**
 * 方法一：模拟
 * 可以模拟螺旋矩阵的路径。初始位置是矩阵的左上角，初始方向是向右，当路径超出界限或者进入之前访问过的位置时，则顺时针旋转，进入下一个方向。
 *
 * 判断路径是否进入之前访问过的位置需要使用一个与输入矩阵大小相同的辅助矩阵 \textit{visited}visited，其中的每个元素表示该位置是否被访问过。当一个元素被访问时，将 \textit{visited}visited 中的对应位置的元素设为已访问。
 *
 * 如何判断路径是否结束？由于矩阵中的每个元素都被访问一次，因此路径的长度即为矩阵中的元素数量，当路径的长度达到矩阵中的元素数量时即为完整路径，将该路径返回。
 *
 */
class Solution1 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][column]);
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }
}

/**
 * 可以将矩阵看成若干层，首先输出最外层的元素，其次输出次外层的元素，直到输出最内层的元素。
 *
 * 定义矩阵的第 kk 层是到最近边界距离为 kk 的所有顶点。例如，下图矩阵最外层元素都是第 11 层，次外层元素都是第 22 层，剩下的元素都是第 33 层。
 *
 */
class Solution2 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}




