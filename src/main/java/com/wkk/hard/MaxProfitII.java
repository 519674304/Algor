package com.wkk.hard;

public class MaxProfitII {
    public int maxProfit(int k, int[] prices) {
        int[][][] dp = new int[prices.length + 1][k + 1][2];
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j][0] = Math.max(dp[i + 1][j][0], dp[i + 1][j][1] - prices[i]);
                dp[i][j][1] = Math.max(dp[i + 1][j - 1][0] + prices[i], dp[i + 1][j][1]);
            }
        }
        return dp[0][k][0];
    }

    public int helper(int[] prices, int i, boolean has, int k) {
        if (i == prices.length || k == 0) {
            return 0;
        }
        int rest = helper(prices, i + 1, has, k);
        if (has) {
            rest = Math.max(helper(prices, i + 1, false, k - 1) + prices[i], rest);
        }else {
            rest = Math.max(helper(prices, i + 1, true, k) - prices[i], rest);
        }
        return rest;
    }
}
