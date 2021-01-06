//给定一个非负整数数组，你最初位于数组的第一个位置。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
//
// 示例:
//
// 输入: [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
//
//
// 说明:
//
// 假设你总是可以到达数组的最后一个位置。
// Related Topics 贪心算法 数组
// 👍 784 👎 0
package algorithme.p45.jump;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().jump(new int[]{2,1,2,1,4}));
    }

    // 贪心算法优化
    public int jump(int[] nums) {
        int steps = 0;
        int maxDistance = 0;
        int end = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // 计算下一步所能到达的最远距离
            maxDistance = Math.max(maxDistance, i + nums[i]);
            // 如果当前位置等于当前步的最远距离
            // 则更新当前步的最远距离为下一步能到达的最远距离，步数+1
            if (i == end) {
                end = maxDistance;
                steps ++;
            }
        }
        return steps;
    }

    // 贪心算法
    public int jump1(int[] nums) {
        if (nums.length <= 1) return 0;
        int count = 0, step, maxDistance;
        for (int i = 0; i < nums.length;) {
            if (i + nums[i] >= nums.length - 1)
                return ++count;
            maxDistance = i + nums[i];
            step = 0;
            for (int j = i+1; j <= Math.min(nums.length - 1, i + nums[i]); j++) {
                int temp = j + nums[j];
                if (temp > maxDistance) {
                    step = j - i;
                    maxDistance = temp;
                }
            }
            i += step;
            count ++;
        }
        return count;
    }
}
