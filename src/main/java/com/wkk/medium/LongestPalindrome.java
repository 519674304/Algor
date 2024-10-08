package com.wkk.medium;

import org.junit.Test;

public class LongestPalindrome {
    @Test
    public void test5(){
        System.out.println(longestPalindrome("aacabdkacaa"));
    }

    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        if (s.length() == 2) {
            return s.charAt(0) == s.charAt(1) ? s : s.substring(0, 1);
        }
        boolean[][] flagBoard = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            flagBoard[i][i] = true;
            if (i < s.length() - 1) {
                flagBoard[i + 1][i] = true;
            }
        }

        int maxLength = 0;
        String res = s.substring(0, 1);

        for (int i = 1; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                int row = j - i;
                flagBoard[row][j] = flagBoard[j][j - 1] && s.charAt(row) == s.charAt(j);
                if (flagBoard[row][j]) {
                    if (maxLength < (i + 1)) {
                        res = s.substring(row, j + 1);
                        maxLength = (i + 1);
                    }
                }
            }
        }
        return res;
    }
}
