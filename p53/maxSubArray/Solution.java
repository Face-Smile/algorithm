package algorithme.p53.maxSubArray;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    public static class Status {
        public int lSum;
        public int rSum;
        public int mSum;
        public int iSum;
        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    // 分治法
    public int maxSubArray(int[] nums) {
        return getStatusInfo(nums, 0, nums.length - 1).mSum;
    }

    /**
     * 分治法：
     * lSum: 区间[l, r]之间的以l为左端点的最大字段和，分治法：max(子左区间.lSum, 子左区间的.iSum + 子右区间.lSum)
     * rSum: 区间[l, r]之间的以r为右端点的最大字段和，分治法：max(子右区间.rSum, 子右区间的.iSum + 子左区间.rSum)
     * mSum: 区间[l, r]之间的最大字段和，分治法：max(子左区间.mSum, 子右区间.lSum, 子左区间的.rSum + 子右区间.lSum)
     * iSum: 区间[l, r]之间的总和，分治法：子左区间.iSum + 子右区间.iSum
     */
    public Status getStatusInfo(int[] nums, int l, int r) {
        if (l == r) {
            return new Status(nums[l], nums[l], nums[l], nums[l]);
        }
        int m = (l + r) >> 1;
        Status lStatus, rStatus;
        lStatus = getStatusInfo(nums, l, m);
        rStatus = getStatusInfo(nums, m + 1, r);
        int lSum = Math.max(lStatus.lSum, lStatus.iSum + rStatus.lSum);
        int rSum = Math.max(rStatus.rSum, rStatus.iSum + lStatus.rSum);
        int mSum = Math.max(lStatus.rSum + rStatus.lSum, Math.max(lStatus.mSum, rStatus.mSum));
        int iSum = lStatus.iSum + rStatus.iSum;
        return new Status(lSum, rSum, mSum, iSum);
    }

    // 动态规划
    public int maxSubArray1(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else // 任何数加一个非正数都会变得更小
                sum = num;
            res = Math.max(res, sum);
        }
        return res;
    }
}
