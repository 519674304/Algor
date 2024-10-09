package com.wkk.medium;

import org.junit.Test;

public class MaxProfit {

    @Test
    public void test6(){
        //System.out.println(helper(new int[]{7, 1, 5, 3, 6, 4}, 0, false));
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public int maxProfit(int[] prices) {
        int[][] dp = new int[2][prices.length + 1];
        for (int i = dp[0].length - 2; i >= 0; i--) {
            dp[0][i] = Math.max(dp[0][i + 1], dp[1][i + 1] - prices[i]);
            dp[1][i] = Math.max(dp[0][i + 1] + prices[i], dp[1][i + 1]);
        }
        return dp[0][0];
    }

    public int helper(int[] prices, int i, boolean has) {
        if (i == prices.length) {
            return 0;
        }
        int rest = helper(prices, i + 1, has);
        if (has) {
            rest = Math.max(helper(prices, i + 1, false) + prices[i], rest);
        }else {
            rest = Math.max(helper(prices, i + 1, true) - prices[i], rest);
        }
        return rest;
    }
}
