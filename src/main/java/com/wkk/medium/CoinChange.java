package com.wkk.medium;

public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin < 0 || dp[i - coin] == Integer.MAX_VALUE) {
                    continue;
                }
                dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
            }
        }
        return dp[dp.length - 1];
    }

    public int helper(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int minNum = Integer.MAX_VALUE;
        for (int coin : coins) {
            int resNum = helper(coins, amount - coin);
            if (resNum < 0) {
                continue;
            }
            minNum = Math.min(resNum + 1, minNum);
        }
        return minNum;
    }
}
