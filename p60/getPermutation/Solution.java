//给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
//
// 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
//
//
// "123"
// "132"
// "213"
// "231"
// "312"
// "321"
//
//
// 给定 n 和 k，返回第 k 个排列。
//
//
//
// 示例 1：
//
//
//输入：n = 3, k = 3
//输出："213"
//
//
// 示例 2：
//
//
//输入：n = 4, k = 9
//输出："2314"
//
//
// 示例 3：
//
//
//输入：n = 3, k = 1
//输出："123"
//
//
//
//
// 提示：
//
//
// 1 <= n <= 9
// 1 <= k <= n!
//
// Related Topics 数学 回溯算法
// 👍 456 👎 0
package algorithm.p60.getPermutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        // System.out.println(new Solution().getPermutation(3, 4));
        int n = 5;
        for (int i = 1; i <= jc(n); i++) {
            System.out.println(new Solution().getPermutation(n, i));
            System.out.println(new Solution1().getPermutation(n, i));
            System.out.println(new Solution().getPermutation1(n, i));
        }
    }

    public static int jc(int n) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    public String getPermutation(int n, int k) {
        List<Character> characters = new ArrayList<>();
        int count = 1, i = 1, t = 0;
        for (; i <= n; i++) {
            characters.add((char) ('0' + i));
            count *= i;
        }
        count /= --i;
        // System.out.println(characters);
        char[] chars = new char[n];
        while (t < n) {
            // System.out.printf("i:%d, k:%d, t:%d, count:%d\n", i, k, t, count);
            // System.out.println(Arrays.toString(chars));
            int index = (k - 1) / count;
            chars[t++] = characters.remove(index);
            k -= index * count;
            // 避免0的阶乘, (0! = 1)
            count /= (i != 1 ? --i : i);
        }
        return new String(chars);
    }

    public String getPermutation1(int n, int k) {
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; ++i) {
            factorial[i] = factorial[i - 1] * i;
        }
        --k;
        int[] valid = new int[n];
        Arrays.fill(valid, 0);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int index = k / (factorial[n - i]);
            int j = 0, t = 0;
            for (; j < n && t < index + 1; j++) {
                if (valid[j] == 0) {
                    t++;
                }
            }
            valid[--j] = 1;
            sb.append(j + 1);
            k = k % (factorial[n - i]);
        }
        return sb.toString();
    }
}


class Solution1 {
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; ++i) {
            factorial[i] = factorial[i - 1] * i;
        }
        --k;
        StringBuilder ans = new StringBuilder();
        int[] valid = new int[n + 1];
        Arrays.fill(valid, 1);
        for (int i = 1; i <= n; ++i) {
            int order = k / factorial[n - i] + 1;
            for (int j = 1; j <= n; ++j) {
                order -= valid[j];
                if (order == 0) {
                    ans.append(j);
                    valid[j] = 0;
                    break;
                }
            }
            k %= factorial[n - i];
        }
        return ans.toString();
    }
}