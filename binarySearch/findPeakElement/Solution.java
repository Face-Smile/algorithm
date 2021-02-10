package algorithm.binarySearch.findPeakElement;

import java.util.Random;
import java.util.stream.IntStream;


/**
 * 找任意峰值索引
 */
public class Solution {

    public static void main(String[] args) {
        Random random = new Random();
        IntStream intStream = random.ints(1, 100);
        int[] nums = intStream.distinct().limit(20).toArray();
        System.out.println(nums.length);
        IntStream.range(0, nums.length).forEach(d -> System.out.printf("%5d", d));
        System.out.println();
        IntStream.of(nums).forEach(d -> System.out.printf("%5d", d));
        System.out.println();
        System.out.println(new Solution().findPeakElement1(nums));
        System.out.println(new Solution().findPeakElement(nums));
    }

    public int findPeakElement1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i+1]) return i;
        }
        return nums.length - 1;
    }

    public int findPeakElement(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] > nums[mid+1]) j = mid;
            else i = mid+1;
        }
        return i;
    }
}
