package com.wkk.medium.graph;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SnakesAndLadders {
    @Test
    public void test7(){
        int[][] board = {
            {-1, -1, -1, -1, 48, 5, -1},
            {12, 29, 13, 9, -1, 2, 32},
            {-1, -1, 21, 7, -1, 12, 49},
            {42, 37, 21, 40, -1, 22, 12},
            {42, -1, 2, -1, -1, -1, 6},
            {39, -1, 35, -1, -1, 39, -1},
            {-1, 36, -1, -1, -1, -1, 5}
        };
        snakesAndLadders(board);
    }
    private int minStep = Integer.MAX_VALUE;
    public int snakesAndLadders(int[][] board) {
        int[][] flagBoard = new int[board.length][board[0].length];
        stepMin(board, flagBoard,1, 0);
        return minStep;
    }

    public void stepMin(int[][] board, int[][] stepBoard, int position, int steps) {
        if (steps >= minStep) {
            return;
        }
        if (position == board.length * board.length) {
            minStep = steps;
            return;
        }
        for (int i = position + 1; i <= Math.min(position + 6, board.length * board.length); i++) {
            int[] location = getLocation(i, board.length);
            int currVal = board[location[0]][location[1]];
            int preSteps = stepBoard[location[0]][location[1]];
            if (preSteps > 0 && preSteps <= steps + 1) {
                continue;
            }
            stepBoard[location[0]][location[1]] = steps + 1;
            if (currVal == -1) {
                stepMin(board, stepBoard, i, steps + 1);
            }else {
                stepMin(board, stepBoard, currVal, steps + 1);
            }
        }
    }

    public int[] getLocation(int position, int length) {
        position--;
        int rOffset = position / length;
        boolean flag = rOffset % 2 == 0;
        int jOffset = position % length;
        int j;
        if (flag) {
            j = jOffset;
        }else {
            j = length - 1 - jOffset;
        }
        int i = length - 1 - rOffset;
        return new int[]{i, j};
    }

    public int getPosition(int[] location, int length) {
        int rowOffset = length - 1 - location[0];
        boolean flag = (rowOffset) % 2 == 0;
        int colOffset;
        if (flag) {
            colOffset = location[1] + 1;
        }else {
            colOffset = length - location[1];
        }
        return rowOffset * length + colOffset;
    }
}

class SnakesAndLaddersSolution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] vis = new boolean[n * n + 1];
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int i = 1; i <= 6; ++i) {
                int nxt = p[0] + i;
                if (nxt > n * n) { // 超出边界
                    break;
                }
                int[] rc = id2rc(nxt, n); // 得到下一步的行列
                if (board[rc[0]][rc[1]] > 0) { // 存在蛇或梯子
                    nxt = board[rc[0]][rc[1]];
                }
                if (nxt == n * n) { // 到达终点
                    return p[1] + 1;
                }
                if (!vis[nxt]) {
                    vis[nxt] = true;
                    queue.offer(new int[]{nxt, p[1] + 1}); // 扩展新状态
                }
            }
        }
        return -1;
    }

    public int[] id2rc(int id, int n) {
        int r = (id - 1) / n, c = (id - 1) % n;
        if (r % 2 == 1) {
            c = n - 1 - c;
        }
        return new int[]{n - 1 - r, c};
    }
}
