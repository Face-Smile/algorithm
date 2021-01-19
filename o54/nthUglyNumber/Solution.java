package algorithm.o54.nthUglyNumber;

//我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
//
//
//
// 示例:
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
//
// 说明:
//
//
// 1 是丑数。
// n 不超过1690。
//
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/
// Related Topics 数学
// 👍 85 👎 0

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().nthUglyNumber(1630));
    }

    /**
     * 动态规划
     */
    public int nthUglyNumber(int n) {
        int a, b, c;
        int n2, n3, n5;
        int[] dp = new int[n];
        a = b = c = 0;
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            n2 = dp[a] * 2;
            n3 = dp[b] * 3;
            n5 = dp[c] * 5;
            int min = Math.min(Math.min(n2, n3), n5);
            if (min == n2) a++;
            if (min == n3) b++;
            if (min == n5) c++;
            dp[i] = min;
        }
        // System.out.println(Arrays.toString(dp));
        return dp[n-1];
    }

    public int nthUglyNumber1(int n) {
        int s = 0;
        while (n > 0) {
            s++;
            if (isUglyNumber(s)) {
                n--;
            }
        }
        return s;
    }

    public boolean isUglyNumber(int num) {
        while (num > 0) {
            int r2 = num / 2;
            int r3 = num / 3;
            int r5 = num / 5;
            if (num == r2 * 2)
                num = r2;
            else if (num == r3 * 3)
                num = r3;
            else if (num == r5 * 5)
                num = r5;
            else return num == 1 || num == 2 || num == 3 || num == 5;
        }
        return false;
    }
}
