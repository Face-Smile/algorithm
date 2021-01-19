//给出一个区间的集合，请合并所有重叠的区间。
//
//
//
// 示例 1:
//
// 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
//
//
// 示例 2:
//
// 输入: intervals = [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
//
// 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
//
//
//
// 提示：
//
//
// intervals[i][0] <= intervals[i][1]
//
// Related Topics 排序 数组
// 👍 761 👎 0
package algorithme.p56.merge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class Solution {
    public static void main(String[] args) {
        // String str = "[[1,3],[2,6],[8,10],[15,18]]".replaceAll("\\[", "{").replaceAll("]", "}");
        // System.out.println(str);
        int[][] intervals = new int[][] {{1,3},{2,6},{8,10},{15,18}};
        // int[][] intervals = new int[][]{{1, 4}, {5, 6}};
        System.out.println(Arrays.deepToString(new Solution().merge1(intervals)));
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length < 2) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int p = 0;
        for (int i = 1; i < intervals.length; i++) {
            int a = intervals[p][1];
            int b = intervals[i][0];
            int c = intervals[i][1];
            if (a < b) {
                p++;
                intervals[p] = intervals[i];
            } else if (a <= c) {
                intervals[p][1] = intervals[i][1];
            }
        }
        int[][] res = new int[p + 1][2];
        System.arraycopy(intervals, 0, res, 0, p + 1);
        return res;
    }

    public int[][] merge1(int[][] intervals) {
        if (intervals.length < 2) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] last = merged.get(merged.size()-1);
            if (intervals[i][0] > last[1])
                merged.add(intervals[i]);
            else {
                last[1] = Math.max(last[0], intervals[i][1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
