package com.wkk.medium;

public class UniquePathsWithObstacles {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length + 1][obstacleGrid[0].length + 1];
        int lastRow = obstacleGrid.length - 1;
        int lastCol = obstacleGrid[0].length - 1;
        if (obstacleGrid[lastRow][lastCol] == 1) {
            return 0;
        }
        dp[lastRow][lastCol] = 1;
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = dp[0].length - 2; j >= 0; j--) {
                if (i == lastRow && j == lastCol) {
                    dp[i][j] = 1;
                } else if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                }else {
                    dp[i][j] = dp[i][j + 1] + dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }

    public int getCount(int[][] obstacleGrid, int i, int j) {
        if (i == obstacleGrid.length - 1 && j == obstacleGrid[0].length - 1) {
            return 1;
        }
        if (i == obstacleGrid.length) {
            return 0;
        }
        if (j == obstacleGrid[0].length) {
            return 0;
        }
        if (obstacleGrid[i][j] == 1) {
            return 0;
        }
        return getCount(obstacleGrid, i + 1, j) + getCount(obstacleGrid, i, j + 1);
    }


}
