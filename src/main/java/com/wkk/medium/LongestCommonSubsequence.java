package com.wkk.medium;

public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        return getResult(text1, text2, 0, 0);
    }

    public int getResult(String text1, String text2, int l, int r) {
        if (l == text1.length() || r == text2.length()) {
            return 0;
        }
        int res = 0;
        if (text1.charAt(l) == text2.charAt(r)){
            res = Math.max(res, getResult(text1, text2, l + 1, r + 1)) + 1;
        }else {
            res = Math.max(res, getResult(text1, text2, l + 1, r));
            res = Math.max(res, getResult(text1, text2, l, r + 1));
        }
        return res;
    }
}
