//实现 int sqrt(int x) 函数。
//
// 计算并返回 x 的平方根，其中 x 是非负整数。
//
// 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
//
// 示例 1:
//
// 输入: 4
//输出: 2
//
//
// 示例 2:
//
// 输入: 8
//输出: 2
//说明: 8 的平方根是 2.82842...,
//     由于返回类型是整数，小数部分将被舍去。
//
// Related Topics 数学 二分查找
// 👍 574 👎 0

package algorithm.p69.mySqrt;

public class Solution {
    public static void main(String[] args) {
        // System.out.println(new Solution().mySqrt(Integer.MAX_VALUE));
        for (int i = 0; i < 100; i++) {
            System.out.println(new Solution().mySqrt(i));
        }
    }

    public int mySqrt(int x) {
        int i = 0, j = x;
        int ans = 0;
        while (i <= j) {
            // 坑, 加法的优先级比位移高
            int m = i + ((j - i) >> 1) ;
            if ((long) m * m <= x) {
                ans = m;
                i = m + 1;
            } else j = m - 1;
        }
        return ans;

        // int i = 0;
        // while ((long)i*i <= x) i++;
        // return i-1;
    }


}

class Solution1 {
    public int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}
