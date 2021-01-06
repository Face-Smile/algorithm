//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
//
// 示例:
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//]
//
// 说明：
//
//
// 所有输入均为小写字母。
// 不考虑答案输出的顺序。
//
// Related Topics 哈希表 字符串
// 👍 622 👎 0

package algorithme.p49.groupAnagrams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = new Solution().groupAnagrams(strs);
        lists.forEach(System.out::println);
        new Solution().groupAnagrams(strs, "计数哈希").forEach(System.out::println);
    }

    /**
     * 排序 哈希
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> stringListMap = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> stringList = stringListMap.getOrDefault(key, new ArrayList<>());
            stringList.add(str);
            stringListMap.put(key, stringList);
        }
        return new ArrayList<>(stringListMap.values());
    }

    /**
     * 计数 哈希
     */
    public List<List<String>> groupAnagrams(String[] strs, String method) {
        System.out.println("method: " + method);
        Map<String, List<String>> stringListMap = new HashMap<>();
        for (String str : strs) {
            int[] count = new int[26];
            for(char c: str.toCharArray()) {
                count[c - 'a'] ++;
            }
            // System.out.println(Arrays.toString(count));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count.length; i++) {
                for (int j = 0; j < count[i]; j++) {
                    sb.append('a' + i);
                    // sb.append((char)('a' + i));
                }
            }
            String key = sb.toString();
            List<String> stringList = stringListMap.getOrDefault(key, new ArrayList<>());
            stringList.add(str);
            stringListMap.put(key, stringList);
        }
        return new ArrayList<>(stringListMap.values());
    }
}
