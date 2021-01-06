//给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
//
// '?' 可以匹配任何单个字符。
//'*' 可以匹配任意字符串（包括空字符串）。
//
//
// 两个字符串完全匹配才算匹配成功。
//
// 说明:
//
//
// s 可能为空，且只包含从 a-z 的小写字母。
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
//
//
// 示例 1:
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。
//
// 示例 2:
//
// 输入:
//s = "aa"
//p = "*"
//输出: true
//解释: '*' 可以匹配任意字符串。
//
//
// 示例 3:
//
// 输入:
//s = "cb"
//p = "?a"
//输出: false
//解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
//
//
// 示例 4:
//
// 输入:
//s = "adceb"
//p = "*a*b"
//输出: true
//解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
//
//
// 示例 5:
//
// 输入:
//s = "acdcb"
//p = "a*c?b"
//输出: false
// Related Topics 贪心算法 字符串 动态规划 回溯算法
// 👍 577 👎 0
package algorithme.p44.isMatch;

import java.util.Arrays;

// "aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba"
// "a*******b"
// mississippi
// m??*ss*?i*pi
public class Solution {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(new Solution().isMatch("qq", "*"));
        System.out.println(new Solution().isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba", "*"));
        System.out.println(new Solution().isMatch("aa", "a"));
        System.out.println(System.currentTimeMillis() - start);
    }

    /**
     * 动态规划
     * 设置i，j 为指向s、p的指针, 代表指向字符串的第几个字符
     * 如果p[j]是字符串，则
     * dp[i][j] = dp[i-1][j-1]  && s[i] == p[j]
     * 如果p[j]是？，则
     * dp[i][j] = dp[i-1][j-1]
     * 如果p[j]是*，则
     * dp[i][j] = dp[i-1][j] (匹配了一个) || dp[i][j-1] (没有匹配);
     * 边界条件：
     * dp[0][0] = true
     * dp[i][0] = false (i > 0)
     * dp[0][j] = p[j] == '*' (j > 0)
     */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= s.length(); i++) {
            dp[i][0] = false;
        }
        for (int j = 1; j <= p.length(); j++) {
            dp[0][j] = dp[0][j-1] && p.charAt(j-1) == '*';
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                char b = p.charAt(j-1);
                if (b == '?')
                    dp[i][j] = dp[i-1][j-1];
                else if (b == '*')
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                else {
                    dp[i][j] = dp[i-1][j-1] && s.charAt(i-1) == b;
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /**
     * 递归解法，会超时
     */
    public boolean isMatch1(String s, String p) {
        if (s.isEmpty() && p.isEmpty())
            return true;
        else if (p.isEmpty())
            return false;
        else {
            char b = p.charAt(0);
            if (b == '?' && s.length() > 0) {
                return isMatch(s.substring(1), p.substring(1));
            } else if (b == '*') {
                for (int i = Math.max(0, s.length() - p.length() + 1); i >= 0; i--) {
                    if (isMatch(s.substring(i), p.substring(1)))
                        return true;
                }
            } else {
                if (s.length() > 0 && s.charAt(0) == b)
                    return isMatch(s.substring(1), p.substring(1));
                else
                    return false;
            }
        }
        return false;
    }
}
