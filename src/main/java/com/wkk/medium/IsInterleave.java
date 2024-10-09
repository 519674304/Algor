package com.wkk.medium;

public class IsInterleave {
    public boolean isInterleave(String s1, String s2, String s3) {
        boolean[][][] dp = new boolean[s1.length() + 1][s2.length() + 1][s3.length() + 1];
        int lastY = s1.length() - 1, lastX = s2.length() - 1, lastZ = s3.length() - 1;
        int edgeY = s1.length() + 1, edgeX = s2.length() + 1, edgeZ = s3.length() + 1;
        dp[s1.length()][s2.length()][s3.length()] = true;
        for (int z = lastZ; z >= 0; z--) {
            for (int y = lastY + 1; y >= 0; y--) {
                for (int x = lastX + 1; x >= 0; x--) {
                    dp[y][x][z] = false;
                    if (y + 1 < edgeY && s1.charAt(y) == s3.charAt(z)) {
                        dp[y][x][z] = dp[y][x][z] || dp[y + 1][x][z + 1];
                    }
                    if (x + 1 < edgeX && s2.charAt(x) == s3.charAt(z)) {
                        dp[y][x][z] = dp[y][x][z] || dp[y][x + 1][z + 1];
                    }
                }
            }
        }
        return dp[0][0][0];
    }

    public boolean helper(String s1, String s2, String s3, int i1, int i2) {
        if (i1 == s1.length() && i2 == s2.length()) {
            return true;
        }
        boolean result = false;
        if (i1 < s1.length() && s1.charAt(i1) == s3.charAt(i1 + i2)) {
            result = helper(s1, s2, s3, i1 + 1, i2);
        }
        if (i2 < s2.length() && s2.charAt(i2) == s3.charAt(i1 + i2)) {
            result = result || helper(s1, s2, s3, i1, i2 + 1);
        }
        return result;
    }


}
