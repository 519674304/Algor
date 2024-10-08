package com.wkk.medium;

import java.util.List;

public class MinimumTotal {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size() + 1][triangle.size() + 1];
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                Integer cv = triangle.get(i).get(j);
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + cv;
            }
        }
        return dp[0][0];
    }
}
