package algorithme.p767.reorganizeString;

import java.util.*;
//给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
//
// 若可行，输出任意可行的结果。若不可行，返回空字符串。
//
// 示例 1:
//
//
//输入: S = "aab"
//输出: "aba"
//
//
// 示例 2:
//
//
//输入: S = "aaab"
//输出: ""
//
//
// 注意:
//
//
// S 只包含小写字母并且长度在[1, 500]区间内。
//
// Related Topics 堆 贪心算法 排序 字符串
// 👍 173 👎 0

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().reorganizeString("ahdfahdjkfhdhofahdfahdfhadhfaldfh"));
        System.out.println(new Solution().reorganizeString1("ahdfahdjkfhdhofahdfahdfhadhfaldfh"));
    }

    public static class CharCount {
        char c;
        int count;
        public CharCount(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }
    public String reorganizeString1(String S) {
        if (S.length() < 2) {
            return S;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = S.length();
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }
        if (maxCount > (length + 1) / 2) {
            return "";
        }
        PriorityQueue<Character> queue = new PriorityQueue<Character>(new Comparator<Character>() {
            public int compare(Character letter1, Character letter2) {
                return counts[letter2 - 'a'] - counts[letter1 - 'a'];
            }
        });
        for (char c = 'a'; c <= 'z'; c++) {
            if (counts[c - 'a'] > 0) {
                queue.offer(c);
            }
        }
        StringBuffer sb = new StringBuffer();
        while (queue.size() > 1) {
            char letter1 = queue.poll();
            char letter2 = queue.poll();
            sb.append(letter1);
            sb.append(letter2);
            int index1 = letter1 - 'a', index2 = letter2 - 'a';
            counts[index1]--;
            counts[index2]--;
            if (counts[index1] > 0) {
                queue.offer(letter1);
            }
            if (counts[index2] > 0) {
                queue.offer(letter2);
            }
        }
        if (queue.size() > 0) {
            sb.append(queue.poll());
        }
        return sb.toString();
    }

    public String reorganizeString(String S) {
        if (S == null || S.length() == 0) return "";
        int[] counts = new int[26];
        int maxCount = 0;
        int len = S.length();
        for (char c : S.toCharArray()) {
            counts[c - 'a'] ++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }
        System.out.println(Arrays.toString(counts));
        if (maxCount > (len + 1) / 2) return "";
        PriorityQueue<Character> priorityQueue = new PriorityQueue<>((o1, o2) -> counts[o2 - 'a'] - counts[o1 - 'a']);
        StringBuffer sb = new StringBuffer();
        for (char c = 'a'; c <= 'z'; c ++) {
            if (counts[c - 'a'] > 0)
                priorityQueue.offer(c);
        }
        while (priorityQueue.size() > 1) {
            char letter1 = priorityQueue.poll();
            char letter2 = priorityQueue.poll();
            sb.append(letter1);
            sb.append(letter2);
            if (--counts[letter1 - 'a'] > 0) {
                priorityQueue.offer(letter1);
            }
            if (--counts[letter2 - 'a'] > 0) {
                priorityQueue.offer(letter2);
            }
        }
        if (priorityQueue.size() > 0) {
            sb.append(priorityQueue.poll());
        }
        return sb.toString();
    }

    public void build(CharCount[] charCounts) {
        for(int i = charCounts.length / 2 - 1; i > 0; i--) {
            upFloat(charCounts, i);
        }
    }

    public void upFloat(CharCount[] charCounts, int child) {
        CharCount current = charCounts[child];
        int parent;
        while (child > 0) {
            parent = (child - 1) / 2;
            if (current.count > charCounts[parent].count) {
                charCounts[child] = charCounts[parent];
            }
            child = parent;
        }
    }


}
