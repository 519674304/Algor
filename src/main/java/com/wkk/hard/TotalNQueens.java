package com.wkk.hard;

import org.junit.Test;

public class TotalNQueens {
    @Test
    public void test5(){
        System.out.println(totalNQueens(4));
    }

    private int total = 0;
    public int totalNQueens(int n) {
        int[][] board = new int[n][n];
        helper(board, n - 1, new boolean[n]);
        return total;
    }

    public void helper(int[][] board, int rowPos, boolean[] colFlag) {
        if (rowPos < 0) {
            total++;
            return;
        }
        for (int i = 0; i < board[0].length; i++) {
            if (rowPos <= 0) {
                continue;
            }
            if (board[rowPos][i] == 1 || board[rowPos][i] == 3) {
                if (i > 0) {
                    if (board[rowPos - 1][i - 1] == 2 || board[rowPos - 1][i - 1] == 3) {
                        board[rowPos - 1][i - 1] = 3;
                    }else {
                        board[rowPos - 1][i - 1] = 1;
                    }
                }
            }
            if (board[rowPos][i] == 2 || board[rowPos][i] == 3) {
                if (i < board[0].length - 1) {
                    board[rowPos - 1][i + 1] = 2;
                }
            }
        }

        for (int i = 0; i < board[0].length; i++) {
            if (board[rowPos][i] > 0 || colFlag[i]) {
                continue;
            }
            int oldLeft = 0;
            int oldRight = 0;
            if (rowPos > 0) {
                if (i > 0) {
                    oldLeft = board[rowPos - 1][i - 1];
                    board[rowPos - 1][i - 1] = 1 + oldLeft;
                }
                if (i < board[0].length - 1) {
                    oldRight = board[rowPos - 1][i + 1];
                    board[rowPos - 1][i + 1] = 2 + oldRight;
                }
            }
            colFlag[i] = true;
            helper(board, rowPos - 1, colFlag);
            if (rowPos > 0) {
                if (i > 0) {
                    board[rowPos - 1][i - 1] = oldLeft;
                }
                if (i < board[0].length - 1) {
                    board[rowPos - 1][i + 1] = oldRight;
                }
            }
            colFlag[i] = false;
        }
        if (rowPos == 0) {
            return;
        }
        for (int i = 0; i < board[0].length; i++) {
            board[rowPos - 1][i] = 0;
        }
    }
}
