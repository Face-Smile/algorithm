//给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
//
// 如果不存在最后一个单词，请返回 0 。
//
// 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
//
//
//
// 示例:
//
// 输入: "Hello World"
//输出: 5
//
// Related Topics 字符串
// 👍 266 👎 0
package algorithm.p58.lengthOfLastWord;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLastWord("hrtg  retretet "));
        System.out.println(new Solution().lengthOfLastWord(""));
    }

    public int lengthOfLastWord(String s) {
        int c = 0;
        int t = s.length();
        while (t > 0) {
            t--;
            if (c != 0 && s.charAt(t) == ' ') {
                break;
            } else if (s.charAt(t) != ' ') {
                c++;
            }
        }
        return c;
    }

    public int lengthOfLastWord1(String s) {
        String[] t = s.trim().split(" +");
        // System.out.println(Arrays.toString(t));
        // System.out.println(t.length);
        return t[t.length - 1].length();
    }
}
