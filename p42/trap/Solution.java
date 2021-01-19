package algorithm.p42.trap;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().trap(new int[]{8, 7, 1, 3, 2, 5}));
        System.out.println(new Solution().trap2(new int[]{8, 7, 1, 3, 2, 5}));
    }

    /**
     * 双指针法
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int i = 0, j = height.length - 1;
        int sum = 0;
        while (i < j) {
            // ①哪边矮就移动哪边，并使用一个临时指针指向矮的那边
            // ②临时指针移动的过程中如果发现更矮的表示可以存水，计入存水量;循环此步骤，直达遇到更高的或i>=j退出循环
            // ③将临时指针赋值给矮的那边
            // ④循环①~③
            if (height[i] <= height[j]) {
                int k = i + 1;
                while (k < j && height[k] <= height[i]) {
                    sum += (height[i] - height[k]);
                    k++;
                }
                i = k;
            } else {
                int k = j - 1;
                while (k > i && height[k] <= height[j]) {
                    sum += (height[j] - height[k]);
                    k--;
                }
                j = k;
            }
        }
        return sum;
    }

    public int trap2(int[] height) {
        int ans = 0, current = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        while (current < height.length) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty())
                    break;
                int distance = current - stack.peek() - 1;
                int bounded_height = Math.min(height[current], height[stack.peek()]) - height[top];
                ans += distance * bounded_height;
            }
            stack.push(current++);
        }
        return ans;
    }
}
