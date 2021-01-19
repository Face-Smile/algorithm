//实现 pow(x, n) ，即计算 x 的 n 次幂函数。
//
// 示例 1:
//
// 输入: 2.00000, 10
//输出: 1024.00000
//
//
// 示例 2:
//
// 输入: 2.10000, 3
//输出: 9.26100
//
//
// 示例 3:
//
// 输入: 2.00000, -2
//输出: 0.25000
//解释: 2-2 = 1/22 = 1/4 = 0.25
//
// 说明:
//
//
// -100.0 < x < 100.0
// n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
//
// Related Topics 数学 二分查找
// 👍 567 👎 0
package algorithm.p50.myPow;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().myPow1(2, 10));
        System.out.println(count);
    }

    static int count = 0;

    /**
     * 快速幂+递归
     */
    public double myPow(double x, long n) {
        count++;
        if (n == 0) return 1;
        else if (n > 0 && n % 2 == 0) return myPow(x * x, n / 2);
        else if (n > 0) return myPow(x, n - 1) * x;
        else return 1 / myPow(x, -n);
    }

    /**
     * 快速幂+迭代
     * x^77 = x * x^4 * x^8 * x^64, 刚好与 77 的二进制形式 (1001101)中每个1对应
     */
    public double myPow1(double x, long n) {
        if (n == 0) return 1;
        else if (n < 0) return 1 / myPow1(x, -n);
        else {
            double ans = 1.0;
            // 贡献的初始值为 x
            double x_contribute = x;
            // 在对 N 进行二进制拆分的同时计算答案
            while (n > 0) {
                if (n % 2 == 1) {
                    // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                    ans *= x_contribute;
                }
                // 将贡献不断地平方
                x_contribute *= x_contribute;
                // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
                n /= 2;
            }
            return ans;
        }
    }
}