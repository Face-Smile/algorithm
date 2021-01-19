package algorithm.p493.reversePairs;

import java.util.*;

//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
//
// 你需要返回给定数组中的重要翻转对的数量。
//
// 示例 1:
//
//
//输入: [1,3,2,3,1]
//输出: 2
//
//
// 示例 2:
//
//
//输入: [2,4,3,5,1]
//输出: 3
//
//
// 注意:
//
//
// 给定数组的长度不会超过50000。
// 输入数组中的所有数字都在32位整数的表示范围内。
//
// Related Topics 排序 树状数组 线段树 二分查找 分治算法
// 👍 171 👎 0

class TreeArray {
    private int n;
    private int[] arr;

    public TreeArray(int n) {
        this.n = n;
        this.arr = new int[n + 1];
    }

    private int lowbit(int x) {
        return x & -x;
    }

    public void update(int i, int k) {
        while (i <= n) {
            this.arr[i] += k;
            i += lowbit(i);
        }
    }

    public int query(int i) {
        int r = 0;
        while (i > 0) {
            r += this.arr[i];
            i -= lowbit(i);
        }
        return r;
    }

}


public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
        System.out.println(new Solution().reversePairs(nums));
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 翻转对：树状数组+哈希
     *
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        Set<Long> allNums = new TreeSet<>();
        for (int num : nums) {
            allNums.add((long) num);
            // 大坑，强制转换的优先级要大于算术运算
            // allNums.add((long) (num * 2)); 会出现数据溢出
            allNums.add((long) num * 2);
        }
        Map<Long, Integer> values = new HashMap<>();
        int idx = 1;
        for (Long num : allNums) {
            values.put(num, idx++);
        }
        TreeArray treeArray = new TreeArray(values.size());
        int res = 0;
        int right = values.size();
        for (int num : nums) {
            int left = values.get((long) num * 2);
            res += treeArray.query(right) - treeArray.query(left);
            treeArray.update(values.get((long) num), 1);
        }
        return res;
    }


    /**
     * 归并排序：对于nums[l...r]分为nums[l...m]和nums[m+1...r],可以发现nums[l...r]翻转对数等于nums[l...m]、nums[m+1...r]、介于nums[l...m]与nums[m+1...r]的翻转对的和
     *
     * @param nums
     * @return
     */
    public int reversePairs2(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    public int mergeSort(int[] nums, int i, int j) {
        if (i >= j) return 0;
        int m = (i + j) >> 1;
        int left = mergeSort(nums, i, m);
        int right = mergeSort(nums, m + 1, j);
        int p = i, q = m + 1;
        int count = 0, index = 0;
        while (p <= m) {
            // 如果nums[p] > 2 * nums[q] 则右子分区q之前的也满足 nums[left] > 2 * nums[right],因为子分区已经按升序排列。
            while (q <= j && nums[p] > 2 * (long) nums[q]) {
                q++;
            }
            count += q - m - 1;
            p++;
        }
        int[] t = new int[j - i + 1];
        p = i;
        q = m + 1;
        while (p <= m || q <= j) {
            if (p <= m && q <= j && nums[p] < nums[q] || q > j) {
                t[index++] = nums[p++];
            } else {
                t[index++] = nums[q++];
            }
        }
        System.arraycopy(t, 0, nums, i, j - i + 1);
        return left + count + right;
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
