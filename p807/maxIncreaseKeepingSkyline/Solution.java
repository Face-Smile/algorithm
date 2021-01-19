package algorithm.p807.maxIncreaseKeepingSkyline;

public class Solution {
    public static void main(String[] args) {
        int[][] grid = new int[][]{{3,0,8,4},{2,4,5,7},{9,2,6,3},{0,3,1,0}};
        System.out.println(new Solution().maxIncreaseKeepingSkyline(grid));
    }

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        // 数组竖直方向视图
        int[] hor = new int[grid[0].length];
        // 数组侧视图
        int[] ver = new int[grid.length];
        
        int add = 0;

        for (int i = 0; i < grid[0].length; i++) {
            for (int[] ints : grid) {
                hor[i] = Math.max(hor[i], ints[i]);
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                ver[i] = Math.max(ver[i], grid[i][j]);
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int all = Math.min(ver[i], hor[j]);
                add += (all - grid[i][j]);
            }
        }
        return add;
    }
}
