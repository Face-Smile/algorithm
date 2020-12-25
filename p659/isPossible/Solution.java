package algorithme.p659.isPossible;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

//给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
//
// 如果可以完成上述分割，则返回 true ；否则，返回 false 。
//
//
//
// 示例 1：
//
// 输入: [1,2,3,3,4,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 :
//1, 2, 3
//3, 4, 5
//
//
//
//
// 示例 2：
//
// 输入: [1,2,3,3,4,4,5,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 :
//1, 2, 3, 4, 5
//3, 4, 5
//
//
//
//
// 示例 3：
//
// 输入: [1,2,3,4,4,5]
//输出: False
//
//
//
//
// 提示：
//
//
// 输入的数组长度范围为 [1, 10000]
//
//
//
// Related Topics 堆 贪心算法
// 👍 153 👎 0

public class Solution {
    /**
     * 题目中提供的数组也是连续的，问题中没有说清楚
     */
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8};
        // int[] nums = new int[]{1, 2, 3, 4, 4, 5};
        long s = System.currentTimeMillis();
        System.out.println(new Solution().isPossible(nums));
        long e1 = System.currentTimeMillis();
        System.out.println(new Solution().isPossible1(nums));
        long e2 = System.currentTimeMillis();
        System.out.println(new Solution().isPossible2(nums));
        long e3 = System.currentTimeMillis();
        System.out.printf("e1: %14d\ne2: %14d\ne3: %14d\n", e1 - s, e2 - s, e3 - s);
    }

    /**
     * 哈希表+最小堆： 哈希表存储每个数字结尾的子序列的长度，哈希表的值的数据结构为最小堆，最小堆存储当前数字结尾的所有子序列的长度
     * 当x在数组中时，如果存在一个子序列以x-1结尾, 长度为k， 则可以将x加入该子序列中，得到长度为k+1的子序列。如果不存在x-1结尾的子序列，则新建一个以x结尾的子序列，长度为1
     * 如果存在多个以x-1结尾的子序列，则选择长度最小的子序列加入，因为题目要求子序列的长度至少为3，所以应该保证最小的子序列尽可能的长，以验证最低条件的子序列是否存在
     */
    public boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int x : nums) {
            if (!map.containsKey(x)) {
                map.put(x, new PriorityQueue<>());
            }
            if (map.containsKey(x - 1)) {
                int prevLength = map.get(x - 1).poll();
                if (map.get(x - 1).isEmpty()) {
                    map.remove(x - 1);
                }
                map.get(x).offer(prevLength + 1);
            } else {
                map.get(x).offer(1);
            }
        }
        System.out.println(map);
        Set<Map.Entry<Integer, PriorityQueue<Integer>>> entrySet = map.entrySet();
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : entrySet) {
            PriorityQueue<Integer> queue = entry.getValue();
            if (queue.peek() < 3) {
                return false;
            }
        }
        return true;
    }


    /**
     * 贪心算法
     *
     */
    public boolean isPossible1(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>(nums.length);
        Map<Integer, Integer> endMap = new HashMap<>(nums.length);
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        for (int num : nums) {
            int count = countMap.get(num);
            if (count > 0) {
                int preEndCount = endMap.getOrDefault(num - 1, 0);
                if (preEndCount > 0) {
                    countMap.put(num, count - 1);
                    endMap.put(num - 1, preEndCount - 1);
                    endMap.put(num, endMap.getOrDefault(num, 0) + 1);
                } else {
                    int count1 = countMap.getOrDefault(num + 1, 0);
                    int count2 = countMap.getOrDefault(num + 2, 0);
                    if (count1 > 0 && count2 > 0) {
                        countMap.put(num, count - 1);
                        countMap.put(num + 1, count1 - 1);
                        countMap.put(num + 2, count2 - 1);
                        endMap.put(num + 2, endMap.getOrDefault(num + 2, 0) + 1);
                    } else {
                        return false;
                    }

                }
            }

        }
        return true;
    }

    public boolean isPossible2(int[] nums) {
        int n = nums.length;
        int dp1 = 0;    // 长度为1的子序列数目
        int dp2 = 0;    // 长度为2的子序列数目
        int dp3 = 0;    // 长度>=3的子序列数目
        int idx = 0;
        int start = 0;  // 起始位置

        while(idx < n){
            start = idx;                        // 重新将起始位置赋值
            int x = nums[idx];
            while(idx < n && nums[idx] == x){
                idx++;
            }
            int cnt = idx - start;

            if(start > 0 && x != nums[start - 1] + 1){  // 对于nums[idx] != nums[idx - 1] + 1，说明当前整数无法加入到以nums[idx-1] 为结尾的序列中
                if(dp1 + dp2 > 0){                      // 如果 dp1+dp2>0，说明有些长度≤2的序列无法被满足，因此不存在相应的分割方案。
                    return false;
                }else{                                  // 否则，此前的序列全部作废
                    dp1 = cnt;
                    dp2 = dp3 = 0;
                }
            }else{                                      // 对于nums[idx] == nums[idx - 1] + 1，说明当前整数可以加入到所有以nums[idx-1]为结尾的序列中。假设数组中x的数目为cnt：
                if(dp1 + dp2 > cnt){                    // 首先，根据贪心的策略，我们要尽可能地先把 x 添加到长度≤2 的子序列中，从而尽可能地保证子序列的长度都≥3。如果x的数量不够，说明不存在相应的分割方案。
                    return false;
                }
                int left = cnt - dp1 - dp2;             // 此时 还剩下left = cnt -dp1 -dp2个 nums[idx-1](x)
                int keep = Math.min(dp3,left);          // 尽量将余下的left个整数添加到长度≥3的子序列中
                // 最后，更新 dp1,dp2,dp3的取值
                dp3 = keep + dp2;
                dp2 = dp1;
                dp1 = left - keep;                      // 如果还有剩余，才将开启对应数量的新序列。新序列的数目等于left−keep。
            }
        }

        return dp1 == 0 && dp2 == 0;
    }
}
