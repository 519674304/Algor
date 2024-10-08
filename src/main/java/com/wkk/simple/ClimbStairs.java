package com.wkk.simple;

import org.junit.Test;

public class ClimbStairs {

    @Test
    public void test6(){
        System.out.println(climbStairs(3));
    }
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[dp.length - 2] = 1;
        dp[dp.length - 3] = 2;
        for (int i = dp.length - 4; i >= 0; i--) {
            dp[i] = dp[i + 1] + dp[i + 2];
        }
        return dp[0];
    }

    public int climbHelper(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0) {
            return 1;
        }
        int num1 = climbHelper(n - 1);
        int num2 = climbHelper(n - 2);
        if (num1 == -1 && num2 == -1) {
            return -1;
        }
        if (num1 == -1 || num2 == -1) {
            return Math.max(num1, num2) + 1;
        }
        return num2 + num1 + 1;
    }

}
