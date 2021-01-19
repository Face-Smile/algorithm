//给出一个无重叠的 ，按照区间起始端点排序的区间列表。
//
// 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
//
//
//
// 示例 1：
//
// 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
//输出：[[1,5],[6,9]]
//
//
// 示例 2：
//
// 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//输出：[[1,2],[3,10],[12,16]]
//解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
//
//
//
//
// 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。
// Related Topics 排序 数组
// 👍 355 👎 0
package algorithm.p57.insert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        System.out.println("[[1,2],[3,5],[6,7],[8,10],[12,16]]".replaceAll("\\[", "{").replaceAll("]", "}"));
        // int[][] intervals = new int[][]{{1, 3}, {6, 9}};
        // int[][] intervals = new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[][] intervals = new int[][]{};
        // int[] newInterval = new int[]{2, 5};
        int[] newInterval = new int[]{4, 8};
        System.out.println(Arrays.deepToString(new Solution().insert(intervals, newInterval)));
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) return new int[][]{newInterval};
        List<int[]> merged = new ArrayList<>();
        merged.add(newInterval);
        for (int[] tmp : intervals) {
            int[] last = merged.get(merged.size() - 1);
            // 后面插
            if (tmp[0] > last[1]) {
                merged.add(tmp);
            }
            // 前面插
            else if (tmp[1] < last[0]) {
                merged.add(merged.size() - 1, tmp);
            }
            // 合并
            else {
                last[0] = Math.min(tmp[0], last[0]);
                last[1] = Math.max(tmp[1], last[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
