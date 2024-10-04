package com.wkk.medium.graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Solve {
    
    @Test
    public void test9(){
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        solve(board);
        System.out.println(board);
    }
    public void solve(char[][] board) {
        boolean[][] flagBoard = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (flagBoard[i][j]) {
                    continue;
                }
                List<int[]> findList = new ArrayList<>();
                if (find(board, flagBoard, i, j, findList)) {
                    findList.forEach(v -> board[v[0]][v[1]] = 'X');
                }
            }
        }
    }

    public boolean find(char[][] board, boolean[][] flagBoard, int i, int j, List<int[]> findList) {
        if (i < 0) {
            return false;
        }
        if (j < 0) {
            return false;
        }
        if (i >= board.length) {
            return false;
        }
        if (j >= board[0].length) {
            return false;
        }
        if (flagBoard[i][j]) {
            return true;
        }
        flagBoard[i][j] = true;
        if (board[i][j] == 'X') {
            return true;
        }
        findList.add(new int[]{i, j});
        boolean flag1 = find(board, flagBoard, i + 1, j, findList);
        boolean flag2 = find(board, flagBoard, i, j + 1, findList);
        boolean flag3 = find(board, flagBoard, i - 1, j, findList);
        boolean flag4 = find(board, flagBoard, i, j - 1, findList);
        return flag1 && flag2 && flag3 && flag4;
    }
}

class SolveSolution {
    int n, m;

    public void solve(char[][] board) {
        n = board.length;
        if (n == 0) {
            return;
        }
        m = board[0].length;
        for (int i = 0; i < n; i++) {
            dfs(board, i, 0);
            dfs(board, i, m - 1);
        }
        for (int i = 1; i < m - 1; i++) {
            dfs(board, 0, i);
            dfs(board, n - 1, i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }
}
