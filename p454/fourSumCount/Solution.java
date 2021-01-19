package algorithm.p454.fourSumCount;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        // int[] A = new int[]{1, 2};
        // int[] B = new int[]{-2, -1};
        // int[] C = new int[]{-1, 2};
        // int[] D = new int[]{0, 2};
        int len = 3000;
        int range = Integer.MAX_VALUE;
        int[] A = randomArray(len, range);
        int[] B = randomArray(len, range);
        int[] C = randomArray(len, range);
        int[] D = randomArray(len, range);
        // System.out.println(Arrays.toString(A));
        // System.out.println(Arrays.toString(B));
        // System.out.println(Arrays.toString(C));
        // System.out.println(Arrays.toString(D));
        // System.out.println("---------------------------------------------------------------");
        // Arrays.sort(A);
        // Arrays.sort(B);
        // Arrays.sort(C);
        // Arrays.sort(D);
        // System.out.println(Arrays.toString(A));
        // System.out.println(Arrays.toString(B));
        // System.out.println(Arrays.toString(C));
        // System.out.println(Arrays.toString(D));
        long start = System.currentTimeMillis();
        System.out.println(new Solution().fourSumCount(A, B, C, D));
        long end = System.currentTimeMillis();
        System.out.println("cost: " + (end - start));
        System.out.println("***************************************************************");
        start = System.currentTimeMillis();
        System.out.println(new Solution().fourSumCount1(A, B, C, D));
        end = System.currentTimeMillis();
        System.out.println("cost: " + (end - start));

    }

    public static int[] randomArray(int len, int range) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * range * (Math.random() < 0.5 ? -1 : 1));
        }
        return arr;
    }

    /**
     * 分组+hash
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        Map<Integer, Integer> ha = new HashMap<>();
        for (int i : A) {
            for (int j : B) {
                ha.put(i + j, ha.getOrDefault(i+j, 0) + 1);
            }
        }
        for (int c : C) {
            for (int d : D) {
                if (ha.containsKey(-c - d)){
                    count += ha.get(-c - d);
                }
            }
        }
        return count;
    }

    public int fourSumCount1(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        for (int i : A) {
            for (int i1 : B) {
                for (int i2 : C) {
                    for (int i3 : D) {
                        if (i + i1 + i2 + i3 == 0) {
                            count++;
                            // System.out.printf("(%3d, %3d, %3d, %3d)\n", i, i1, i2, i3);
                        }
                    }
                }
            }
        }
        return count;
    }
}
