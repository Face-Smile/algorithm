//给定一个 没有重复 数字的序列，返回其所有可能的全排列。
//
// 示例:
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//]
// Related Topics 回溯算法
// 👍 1055 👎 0
package algorithme.p46.permute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        List<List<Integer>> lists = new Solution().permute(nums);
        System.out.println("size: " + lists.size());
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    // 回溯算法
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, res);
        return res;
    }

    public void swap(int[] nums, int i, int j) {
        if (i == j) return;
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void backtrack(int[] nums, int s, List<List<Integer>> res) {
        if (s == nums.length) {
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        } else {
            for (int i = s; i < nums.length; i++) {
                swap(nums, s, i);
                backtrack(nums, s + 1, res);
                swap(nums, s, i);
            }
        }
    }
}
