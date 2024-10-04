package com.wkk.medium.graph;

public class NumIslands {
    private int land = 0;
    public int numIslands(char[][] grid) {
        boolean[][] flagGrid = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (flagGrid[i][j]) {
                    continue;
                }
                if (grid[i][j] == '0') {
                    continue;
                }
                land++;
                findLand(grid, flagGrid, i, j);
            }
        }
        return land;
    }

    public void findLand(char[][] grid, boolean[][] flagGrid, int i, int j) {
        if (i >= grid.length) {
            return;
        }
        if (j >= grid[0].length) {
            return;
        }
        if (i < 0) {
            return;
        }
        if (j < 0) {
            return;
        }
        if (flagGrid[i][j]) {
            return;
        }
        flagGrid[i][j] = true;
        if (grid[i][j] == '0') {
            return;
        }
        findLand(grid, flagGrid, i + 1, j);
        findLand(grid, flagGrid, i, j + 1);
        findLand(grid, flagGrid, i - 1, j);
        findLand(grid, flagGrid, i, j - 1);
    }
}
