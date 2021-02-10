package algorithm.binarySearch.findClosestElements;

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
        IntStream intStream = random.ints(1, 6);
        int[] arr = intStream.distinct().limit(5).sorted().toArray();
        int k = 4;
        int x = 3;
        IntStream.of(arr).forEach(d -> System.out.printf("%-5d", d));
        System.out.println();
        System.out.println(new Solution().findClosestElements(arr, k, x));
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        return null;
    }

    public List<Integer> findLeftClosestElements(int[] arr, int k, int x) {
        return null;
    }

    public List<Integer> findRightClosestElements(int[] arr, int k, int x) {
        return null;
    }
}
