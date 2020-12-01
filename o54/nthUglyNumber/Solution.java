package algorithme.o54.nthUglyNumber;

import java.util.Arrays;

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
