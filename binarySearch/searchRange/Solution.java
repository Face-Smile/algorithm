package algorithm.binarySearch.searchRange;

import java.util.Arrays;


public class Solution implements Runnable {
    public static void main(String[] args) {
        // int[] nums = new int[]{1, 2, 4, 4, 4, 5, 6, 8, 8, 20};
        // int[] nums = new int[]{1, 2, 2, 3, 3, 4};
        // int[] nums = new int[]{1};
        int[] nums = new int[]{2, 2};
        int target = 2;
        System.out.println(Arrays.toString(new Solution().searchRange(nums, target)));
        // System.out.println(Arrays.toString(new Solution().searchRange(nums, target)));
        // Runnable runnable = new Solution();
        // for (int i = 0; i < 5; i++) {
        //     new Thread(runnable).start();
        // }
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};
        return new int[]{searchLeft(nums, target), searchRight(nums, target)};
    }

    public int searchLeft(int[] nums, int target) {
        // int i = 0, j = nums.length - 1;
        // while (i < j) {
        //     int mid = i + (j - i) / 2;
        //     if (nums[mid] >= target) j = mid;
        //     else i = mid + 1;
        // }
        // if (i < nums.length && nums[i] == target) return i;
        // return -1;
        int i = 0, j = nums.length - 1;
        int ans = -1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] >= target) {
                ans = mid;
                j = mid - 1;
            } else i = mid + 1;
        }
        if (ans >= 0 && nums[ans] == target) return ans;
        return -1;
    }

    public int searchRight(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        int ans = -1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] <= target) {
                ans = mid;
                i = mid + 1;
            } else j = mid - 1;
        }
        if (ans >= 0 && nums[ans] == target) return ans;
        return -1;
    }

    @Override
    public void run() {
        int[] nums = new int[]{2, 2, 2};
        int target = 2;
        System.out.println(Arrays.toString(searchRange(nums, target)));
    }

    /*public int searchLeft(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i + 1 < j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] < target) i = mid;
            else j = mid;
        }
        if (nums[i] == target) return i;
        if (nums[j] == target) return j;
        return -1;
    }

    public int searchRight(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i + 1 < j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] <= target) i = mid;
            else j = mid;
        }
        if (nums[i] == target) return i;
        if (nums[j] == target) return j;
        return -1;
    }*/
}
