package com.wkk.medium;

public class Bag {

    public void bag(int[][] mats, int capacity) {

    }

    public int helper(int[][] mats, int i, int capacity) {
        if (capacity == 0) {
            return 0;
        }
        int rest = helper(mats, i + 1, capacity);
        if (capacity > mats[i][0]) {
            rest = Math.min(helper(mats, i + 1, capacity - mats[i][0]) + mats[i][1], rest);
        }
        return rest;
    }
}

