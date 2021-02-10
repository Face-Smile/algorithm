package algorithm.binarySearch.findMin;

import java.util.Random;
import java.util.stream.IntStream;

public class Solution {
    public static void main(String[] args) {
        Random random = new Random();
        IntStream intStream = random.ints(1, 100);
        int[] nums = intStream.distinct().limit(10).sorted().toArray();
        IntStream.of(nums).forEach(d -> System.out.printf("%-5d", d));
        System.out.println();
        int times = random.nextInt(nums.length);

        nums = rotate(nums, times);
        System.out.print("times: " + Math.max(times, nums.length - 1));
        System.out.println(", len: " + nums.length);
        IntStream.range(0, nums.length).forEach(d -> System.out.printf("%-5d", d));
        System.out.println();
        IntStream.of(nums).forEach(d -> System.out.printf("%-5d", d));
        System.out.println();
        System.out.println(new Solution().findMin(nums));
        System.out.println("Correct result: " + IntStream.of(nums).min().orElse(-1));
        // System.out.println(new Solution().findMin(nums));
    }

    public static int[] rotate(int[] nums, int times) {
        int[] arr = new int[nums.length];
        System.arraycopy(nums, 0, arr, times, nums.length - times);
        System.arraycopy(nums, nums.length - times, arr, 0, times);
        return arr;
    }

    public int findMin(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2; // 防止溢出的取中间值的方法
            if (nums[mid] >= nums[i]) { // 左边有序
                // 此时如果右边有序,则整个数组是有序的,所以返回最左边的值;否则缩小左边界
                if (nums[mid] > nums[j]) i = mid + 1;
                else return nums[i];
            } else // 右边有序,此处不再判断左边有序,是因为前面已经有这种情况了; 右边有序则最小值在右边的最左端及以前
                j = mid;
        }
        // 边界处理总结: 如果设置左边界 i = mid 的话 不能设置 i <= j, 因为取中间值的时候是左偏或不偏的
        return nums[i];
    }
}
