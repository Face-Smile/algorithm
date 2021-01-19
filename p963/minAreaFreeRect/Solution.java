package algorithm.p963.minAreaFreeRect;

import java.util.*;

/**
 * 规则：已知三点p1, p2, p3, 判断p1, p2, p3是否组成直角三角形
 * 如可以组成直角三角形，根据向量特性计算出第四个点p4的位置，
 * 最后判断第四个点是否在已知点的集合中，如在计算这个矩形的面积并保存
 * 最后取最小的面积值返回
 */
public class Solution {
    public static void main(String[] args) {
        int[][] points = new int[][]{{1, 2}, {2, 1}, {1, 0}, {0, 1}};
        System.out.println(new Solution().minAreaFreeRect(points));
    }

    public double minAreaFreeRect(int[][] points) {
        if (points.length < 4) return 0d;
        int[] pointsHash = new int[points.length];
        // 计算各个点的hash，方便比较（基本元素的hashCode没有被覆写，取得是对象的内存地址，使用此值会导致结果出错，所以此处使用Arrays.hashCode来计算数组的hashCode）
        for (int i = 0; i < points.length; i++) {
            pointsHash[i] = Arrays.hashCode(points[i]);
        }
        double minArea = 0d;
        // 组合：所有 n选3不重复点 的集合
        for (int i = 0; i < points.length - 2; i++) {
            for (int j = i + 1; j < points.length - 1; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    double area = calculateCombinedArea(points[i], points[j], points[k], pointsHash);
                    minArea = area != 0d && area < minArea || minArea == 0d ? area : minArea;
                }
            }
        }
        return minArea;
    }

    /**
     * 计算各个组合的面积
     *
     * @param p1
     * @param p2
     * @param p3
     * @param pointsHash
     * @return
     */
    public double calculateCombinedArea(int[] p1, int[] p2, int[] p3, int[] pointsHash) {
        int[][] points = hasRightAngle(p1, p2, p3);
        if (points == null)
            return 0d;
        else {
            int[] p4 = calculateFourthPoint(points[0], points[1], points[2]);
            if (hasContains(pointsHash, p4))
                return calculateArea(points[0], points[1], points[2]);
            else
                return 0d;
        }
    }

    /**
     * 判断某个点是否包含在已知点中
     *
     * @param pointsHash
     * @param point
     * @return
     */
    public boolean hasContains(int[] pointsHash, int[] point) {
        for (int inks : pointsHash) {
            if (inks == Arrays.hashCode(point))
                return true;
        }
        return false;
    }

    /**
     * 计算矩形的面积
     *
     * @param p1
     * @param p2
     * @param p3
     * @return
     */
    public double calculateArea(int[] p1, int[] p2, int[] p3) {
        double x = Math.sqrt(Math.pow(p2[0] - p1[0], 2) + Math.pow(p2[1] - p1[1], 2));
        double y = Math.sqrt(Math.pow(p3[0] - p1[0], 2) + Math.pow(p3[1] - p1[1], 2));
        return x * y;
    }

    /**
     * 判断三点是否组成直角三角形
     * 返回的数组，将直角所在的点排第一
     *
     * @param p1
     * @param p2
     * @param p3
     * @return
     */
    public int[][] hasRightAngle(int[] p1, int[] p2, int[] p3) {
        if (isRightAngle(p1, p2, p3)) {
            return new int[][]{p1, p2, p3};
        } else if (isRightAngle(p2, p1, p3)) {
            return new int[][]{p2, p1, p3};
        } else if (isRightAngle(p3, p1, p2)) {
            return new int[][]{p3, p1, p2};
        } else {
            return null;
        }
    }

    /**
     * 判断p1,p2,p3组成的三角形p1点位置是否为直角
     * 向量的点积为0 则垂直：判断(p2-p1)与(p3-p1)点积是否为0
     *
     * @param p1
     * @param p2
     * @param p3
     * @return
     */
    public boolean isRightAngle(int[] p1, int[] p2, int[] p3) {
        int x1 = p2[0] - p1[0];
        int x2 = p3[0] - p1[0];
        int y1 = p2[1] - p1[1];
        int y2 = p3[1] - p1[1];
        return x1 * x2 + y1 * y2 == 0;
    }


    /**
     * 计算第四个点的位置
     * 前提： p2, p3为对角线位置
     * p4 = p2 + p3 - p1
     *
     * @param p1
     * @param p2
     * @param p3
     * @return
     */
    public int[] calculateFourthPoint(int[] p1, int[] p2, int[] p3) {
        int x = p2[0] + p3[0] - p1[0];
        int y = p2[1] + p3[1] - p1[1];
        return new int[]{x, y};
    }
}
