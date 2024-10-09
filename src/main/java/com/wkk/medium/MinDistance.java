package com.wkk.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinDistance {

    @Test
    public void test10(){
        System.out.println(minDistance("horse", "ros"));
    }

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][word2.length()] = word1.length() - i;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[word1.length()][i] = word2.substring(i).length();
        }
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = dp[0].length - 2; j >= 0; j--) {
                boolean sameFlag = word1.charAt(i) == word2.charAt(j);
                int res = sameFlag ? dp[i + 1][j + 1] : dp[i + 1][j + 1] + 1;
                if (!sameFlag) {
                    res = Math.min(dp[i][j + 1] + 1, res);
                    res = Math.min(dp[i + 1][j] + 1, res);
                }
                dp[i][j] = res;
            }
        }
        return dp[0][0];
    }

    public int helper(String word1, String word2, int l, int r) {
        if (r == word2.length()) {
            return word1.length() - l;
        }
        if (l == word1.length()) {
            return word2.substring(r).length();
        }
        int res = helper(word1, word2, l + 1, r + 1);
        res = word1.charAt(l) == word2.charAt(r) ? res : res + 1;
        if (word1.charAt(l) != word2.charAt(r)) {
            res = Math.min(helper(word1, word2, l, r + 1) + 1, res);
            res = Math.min(helper(word1, word2, l + 1, r) + 1, res);
        }
        return res;
    }
}
