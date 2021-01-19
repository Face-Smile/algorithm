package algorithm.p164.maximumGap;

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{124, 4567, 544, 562, 7, 2, 323436, 3636, 5,43543,5435454,21342,234,6, 7, 7};
        // System.out.println(Arrays.toString(nums));
        // new Solution().quickSort(nums, 0, nums.length - 1);
        // System.out.println(Arrays.toString(nums));
        System.out.println(new Solution().maximumGap(nums));
    }

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        // Arrays.sort(nums);
        quickSort(nums);
        int minGap = 0;
        for (int i = 1; i < nums.length; i++) {
            minGap = Math.max(minGap, nums[i] - nums[i-1]);
        }
        return minGap;
    }

    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int i, int j) {
        if (i >= j) return;
        int p = partition(nums, i, j);
        quickSort(nums, 0, p);
        quickSort(nums, p + 1, j);

    }

    public int partition(int[] nums, int i, int j) {
        int base = nums[i];
        while (i < j) {
            // 从右边找第一个小于基准的值
            while (nums[j] >= base && i < j)
                j--;
            nums[i] = nums[j];
            // 从左找到第一个大于基准的值
            while (nums[i] <= base && i < j)
                i++;
            nums[j] = nums[i];
        }
        nums[i] = base;
        // System.out.println("partition: " + i + ", arrays: " + Arrays.toString(nums));
        return i;
    }
}
