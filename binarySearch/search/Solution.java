package algorithm.binarySearch.search;

public class Solution {
    public static void main(String[] args) {
        // int[] nums = new int[]{4};
        // int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int[] nums = new int[]{5, 1, 3};
        int target = 5;
        System.out.println(new Solution().search(nums, target));
    }

    public int search(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] >= nums[i]) { // 左侧有序
                if (target >= nums[i] && target < nums[mid]) j = mid - 1;
                else i = mid + 1;
            } else { // 右侧有序
                if (target > nums[mid] && target <= nums[j]) i = mid + 1;
                else j = mid - 1;
            }
        }
        return -1;
    }
}
