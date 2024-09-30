package com.wkk.medium;

import org.junit.Test;

import java.util.*;

public class Merge {
    @Test
    public void test8(){
        int[][] intervals = {{2,3},{4,5},{6,7},{8,9},{1,10}};
        int[][] merge = merge(intervals);
        for (int[] ints : merge) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        int i = 0;
        List<int[]> resList = new ArrayList<>();
        while (i < intervals.length) {
            int start = i;
            i++;
            int max = intervals[start][1];
            while (i < intervals.length) {
                if (intervals[i][0] > max) {
                    break;
                }
                max = Math.max(intervals[i][1], max);
                i++;
            }
            resList.add(new int[]{intervals[start][0], max});
        }
        int[][] res = new int[resList.size()][2];
        for (int j = 0; j < resList.size(); j++) {
            res[j] = resList.get(j);
        }
        return res;
    }
}
