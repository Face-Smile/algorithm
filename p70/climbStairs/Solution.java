//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
//
// 注意：给定 n 是一个正整数。
//
// 示例 1：
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶
//
// 示例 2：
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
//
// Related Topics 动态规划
// 👍 1474 👎 0
package algorithm.p70.climbStairs;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().climbStairs(44));
    }
    public int climbStairs(int n) {
        // 动态规划
        int[] np = new int[n+1];
        np[0] = 1;
        np[1] = 1;
        for (int i = 2; i <= n; i ++) {
            np[i] = np[i-1] + np[i-2];
        }
        return np[n];

        // 递归
        // if (n == 0) {
        //     return 1;
        // } else if (n > 0){
        //     return climbStairs(n-1) + climbStairs(n-2);
        // } else {
        //     return 0;
        // }
    }
}
