package algorithme.p493.reversePairs;

import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().reversePairs(new int[]{1, 3, 2, 3, 1}));
    }

    public int reversePairs(int[] nums) {
        int count = 0;
        Stack<Integer> stack = new Stack<>();


        return count;
    }

    public int reversePairs1(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j] * 2)
                    count++;
            }
        }
        return count;
    }
}
