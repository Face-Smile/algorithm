package algorithm.binarySearch.findClosestElements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * 有序数组中查找k个离x最近的元素;相对距离越小的元素,距离越近;相对距离相同时,值越小的距离越近
 * 返回的结果为升序List
 */
public class Solution {
    public static void main(String[] args) {
        Random random = new Random();
        IntStream intStream = random.ints(1, 30);
        int[] arr = intStream.distinct().limit(20).sorted().toArray();
        // int[] arr = new int[]{1, 3, 4, 4, 6, 10};
        int k = 4;
        int x = 30;
        IntStream.of(arr).forEach(d -> System.out.printf("%-5d", d));
        System.out.println();
        System.out.println(new Solution().findClosestElements(arr, k, x));
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 查找元素x的位置, 不存在则找元素应该插入的位置(最后的元素除外)
        int i = 0, j = arr.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (arr[mid] >= x) j = mid;
            else i = mid + 1;
        }
        System.out.println(String.format("i:%4d j:%4d", i, j));
        // 已此为基准,双指针确定范围, (不包括两端)
        if (arr[i] == x) {
            k--;
            i--;
            j++;
        } else {
            i--;
        }
        while (i >= 0 && j < arr.length && k > 0) {
            if (x - arr[i] <= arr[j] - x) {
                i--;
            } else {
                j++;
            }
            k--;
        }
        if (k > 0) {
            if (i > 0) {
                i -= k;
            } else {
                j += k;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (i++; i < j; i++) {
            res.add(arr[i]);
        }
        return res;

    }
}
