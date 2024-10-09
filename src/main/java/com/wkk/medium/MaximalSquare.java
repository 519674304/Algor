package com.wkk.medium;

import org.junit.Test;

public class MaximalSquare {
    @Test
    public void test5(){
        char[][] matrix1 = {
            {'1','0','1','1','0','1'},
            {'1','1','1','1','1','1'},
            {'0','1','1','0','1','1'},
            {'1','1','1','0','1','0'},
            {'0','1','1','1','1','1'},
            {'1','1','0','1','1','1'}
        };
        char[][] matrix = {{'0', '1'}, {'1', '0'}};
        System.out.println(maximalSquare(matrix1));
    }

    public int maximalSquare(char[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            dp[i][0] = matrix[i][0] - '0';
            max = Math.max(dp[i][0], max);
        }
        for (int i = 0; i < matrix[0].length; i++) {
            dp[0][i] = matrix[0][i] - '0';
            max = Math.max(max, dp[0][i]);
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                int val = Math.min(dp[i - 1][j], dp[i][j - 1]);
                val = Math.min(val, dp[i - 1][j - 1]);
                dp[i][j] = matrix[i][j] - '0' == 1 ? 1 + val : 0;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }
}
