package algorithme.p1379.sortString;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().sortString("huangjie"));
    }

    public String sortString(String s) {
        // 排序所有字符串，统计词频
        int[] statistics = new int[26];
        char[] chars = new char[s.length()];
        for (char aChar : s.toCharArray()) {
            statistics[aChar - 97] += 1;
        }
        // for (int i = 0; i < s.length(); i++) {
        //     statistics[s.charAt(i) - 'a'] += 1;
        // }
        int index = 0;
        while (index < s.length()){
            for (int i = 0; i < statistics.length; i++) {
                if (statistics[i] != 0) {
                    statistics[i] -= 1;
                    chars[index++] = (char) (i + 'a');
                }
            }
            for (int i = statistics.length - 1; i >= 0; i--) {
                if (statistics[i] != 0) {
                    statistics[i] -= 1;
                    chars[index++] = (char) (i + 'a');
                }
            }
        }
        return new String(chars);
    }
}
