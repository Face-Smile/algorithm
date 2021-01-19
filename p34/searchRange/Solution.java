package algorithm.p34.searchRange;

import java.util.Arrays;

//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。
//
// 进阶：
//
//
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
//
//
//
//
// 示例 1：
//
//
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4]
//
// 示例 2：
//
//
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1]
//
// 示例 3：
//
//
//输入：nums = [], target = 0
//输出：[-1,-1]
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 105
// -109 <= nums[i] <= 109
// nums 是一个非递减数组
// -109 <= target <= 109
//
// Related Topics 数组 二分查找
// 👍 691 👎 0

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(new Solution().searchRange(nums, 10)));
    }

    public int[] searchRange1(int[] nums, int target) {
        int index = binarySearch(nums, target, 0, nums.length - 1);
        if (index == -1) return new int[]{-1, -1};
        int left, right;
        left = right = index;
        while (left >= 0 && nums[left] == nums[index])
            left--;
        while (right <= nums.length - 1 && nums[right] == nums[index])
            right++;
        return new int[]{++left, --right};
    }

    public int[] searchRange(int[] nums, int target) {
        int lIndex = binarySearch(nums, target, true);
        int rIndex = binarySearch(nums, target, false) - 1;
        if (lIndex <= rIndex && rIndex < nums.length && nums[lIndex] == target && nums[rIndex] == target)
            return new int[]{lIndex, rIndex};
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, m, ans = nums.length;
        while (left <= right) {
            m = (left + right) >> 1;
            // System.out.printf("left: %d, m: %d, right: %d\n", left, m, right);
            if (nums[m] > target || (lower && nums[m] >= target)) {
                right = m - 1;
                ans = m;
            } else {
                left = m + 1;
            }
        }
        return ans;
    }

    public int binarySearch(int[] nums, int target, int i, int j) {
        if (i > j) return -1;
        int m = (i + j) >> 1;
        if (nums[m] == target)
            return m;
        else if (nums[m] > target)
            return binarySearch(nums, target, i, m - 1);
        else
            return binarySearch(nums, target, m + 1, j);
    }
}
