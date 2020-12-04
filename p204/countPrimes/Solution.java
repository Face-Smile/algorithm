package algorithme.p204.countPrimes;

//统计所有小于非负整数 n 的质数的数量。
//
//
//
// 示例 1：
//
// 输入：n = 10
//输出：4
//解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
//
//
// 示例 2：
//
// 输入：n = 0
//输出：0
//
//
// 示例 3：
//
// 输入：n = 1
//输出：0
//
//
//
//
// 提示：
//
//
// 0 <= n <= 5 * 106
//
// Related Topics 哈希表 数学
// 👍 543 👎 0

public class Solution {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(new Solution().countPrimes(15000000));
        long end = System.currentTimeMillis();
        System.out.println("cost: " + (end - start));
    }

    public int countPrimes(int n) {
        int[] flags = new int[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (flags[i] == 0) {
                count++;
                if ((long) i * i < n) {
                    for (int j = i; i * j < n; j++) {
                        flags[i * j] = 1;
                    }
                }
            }
        }
        return count;
    }

    public int countPrimes1(int n) {
        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrime(i)) count++;
        return count;
    }

    public boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
