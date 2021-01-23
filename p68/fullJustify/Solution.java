//给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
//
// 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
//
// 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
//
// 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
//
// 说明:
//
//
// 单词是指由非空格字符组成的字符序列。
// 每个单词的长度大于 0，小于等于 maxWidth。
// 输入单词数组 words 至少包含一个单词。
//
//
// 示例:
//
// 输入:
//words = ["This", "is", "an", "example", "of", "text", "justification."]
//maxWidth = 16
//输出:
//[
//   "This    is    an",
//   "example  of text",
//   "justification.  "
//]
//
//
// 示例 2:
//
// 输入:
//words = ["What","must","be","acknowledgment","shall","be"]
//maxWidth = 16
//输出:
//[
//  "What   must   be",
//  "acknowledgment  ",
//  "shall be        "
//]
//解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
//     因为最后一行应为左对齐，而不是左右两端对齐。
//     第二行同样为左对齐，这是因为这行只包含一个单词。
//
//
// 示例 3:
//
// 输入:
//words = ["Science","is","what","we","understand","well","enough","to","explain
//",
//         "to","a","computer.","Art","is","everything","else","we","do"]
//maxWidth = 20
//输出:
//[
//  "Science  is  what we",
//  "understand      well",
//  "enough to explain to",
//  "a  computer.  Art is",
//  "everything  else  we",
//  "do                  "
//]
//
// Related Topics 字符串
// 👍 121 👎 0
package algorithm.p68.fullJustify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        int maxWidth = 16;
        String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        // String[] words = new String[]{"What", "must", "be", "acknowledgment", "shall", "be"};
        // int maxWidth = 20;
        // String[] words = new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        new Solution().fullJustify(words, maxWidth).forEach(System.out::println);
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<>();
        List<String> line = null;
        int len = 0, tmp;
        for (String word : words) {
            tmp = len + word.length() + 1;
            if (line == null || tmp > maxWidth) {
                if (line != null) lines.add(lineToString(len - line.size() + 1, maxWidth, line));
                len = word.length();
                line = new ArrayList<String>() {{
                    add(word);
                }};
            } else {
                len += word.length() + 1;
                line.add(word);
            }
        }
        if (line != null) {
            char[] chars = new char[maxWidth];
            char[] lineStr = String.join(" ", line).toCharArray();
            System.arraycopy(lineStr, 0, chars, 0, lineStr.length);
            Arrays.fill(chars, lineStr.length, maxWidth, ' ');
            lines.add(new String(chars));
        }
        return lines;
    }

    public String lineToString(int wordLen, int maxWidth, List<String> line) {
        char[] chars = new char[maxWidth];
        System.arraycopy(line.get(0).toCharArray(), 0, chars, 0, line.get(0).length());
        if (line.size() == 1) {
            Arrays.fill(chars, line.get(0).length(), maxWidth, ' ');
            return new String(chars);
        }
        int len = (maxWidth - wordLen) / (line.size() - 1);
        int left = (maxWidth - wordLen) % (line.size() - 1);
        int p = line.get(0).length(), t;
        for (int i = 1; i < line.size(); i++) {
            if (left-- > 0) t = p + len + 1;
            else t = p + len;
            Arrays.fill(chars, p, t, ' ');
            System.arraycopy(line.get(i).toCharArray(), 0, chars, t, line.get(i).length());
            p = t + line.get(i).length();
        }
        return new String(chars);
    }
}
