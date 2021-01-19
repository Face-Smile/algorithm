//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 8
// -10 <= nums[i] <= 10
//
// Related Topics 回溯算法
// 👍 556 👎 0
package algorithm.p47.permuteUnique;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1};
        List<List<Integer>> lists = new Solution().permuteUnique(nums);
        System.out.println("size: " + lists.size());
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    // 回溯算法
    public List<List<Integer>> permuteUnique(int[] nums) {
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

    public boolean hasRepeat(int[] nums, int i, int j) {
        while (i < j) {
            if (nums[i] == nums[j])
                return true;
            i ++;
        }
        return false;
    }

    public void backtrack(int[] nums, int s, List<List<Integer>> res) {
        if (s == nums.length) {
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        } else {
            for (int i = s; i < nums.length; i++) {
                if (hasRepeat(nums, s, i))
                    continue;
                swap(nums, s, i);
                backtrack(nums, s + 1, res);
                swap(nums, s, i);
            }
        }
    }

}
