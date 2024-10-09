package com.wkk.medium;

import org.junit.Test;

public class MinPathSum {

    @Test
    public void test6(){
        int[][] grid = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(minPathSum(grid));
    }
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[grid.length - 1][grid[0].length - 1] = grid[grid.length - 1][grid[0].length - 1];
        for (int i = dp.length - 2; i >= 0; i--) {
            dp[i][dp[0].length - 1] = dp[i + 1][dp[0].length - 1] + grid[i][dp[0].length - 1];
        }
        for (int i = dp[0].length - 2; i >= 0; i--) {
            dp[dp.length - 1][i] = dp[dp.length - 1][i + 1] + grid[dp.length - 1][i];
        }
        if (grid.length == 1 || grid[0].length == 1) {
            return dp[0][0];
        }
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = dp[0].length - 2; j >= 0; j--) {
                dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }

    public int getMinNum(int[][] grid, int r, int c) {
        if (r == grid.length - 1 && c == grid[0].length - 1) {
            return grid[r][c];
        }
        int currVal = grid[r][c];
        int restVal = Integer.MAX_VALUE;
        if (r + 1 < grid.length) {
            restVal = Math.min(getMinNum(grid, r + 1, c), restVal);
        }
        if (c + 1 < grid[0].length) {
            restVal = Math.min(getMinNum(grid, r, c + 1), restVal);
        }
        return restVal + currVal;
    }
}
