package com.wkk.medium;

import org.junit.Test;

public class Exist {

    @Test
    public void test6(){
        char[][] board = {{'a', 'b'}, {'c', 'd'}};
        System.out.println(exist(board, "cdba"));
    }

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean[][] flag = new boolean[board.length][board[0].length];
                if (helper(board, word, flag, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean helper(char[][] board, String word, boolean[][] flag, int r, int c, int depth) {
        if (depth == word.length()) {
            return true;
        }
        if (r < 0) {
            return false;
        }
        if (r >= board.length) {
            return false;
        }
        if (c < 0) {
            return false;
        }
        if (c >= board[0].length) {
            return false;
        }
        if (flag[r][c]) {
            return false;
        }
        if (board[r][c] != word.charAt(depth)) {
            return false;
        }
        flag[r][c] = true;
        boolean flag1 = helper(board, word, flag, r - 1, c, depth + 1);
        boolean flag2 = helper(board, word, flag, r + 1, c, depth + 1);
        boolean flag3 = helper(board, word, flag, r, c - 1, depth + 1);
        boolean flag4 = helper(board, word, flag, r, c + 1, depth + 1);
        flag[r][c] = false;
        return flag1 || flag2 || flag3 || flag4;
    }
}
