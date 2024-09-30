package com.wkk.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalsInsert {
    @Test
    public void test5(){
        int[][] intervals = {{3, 5},{12, 15}};
        int[] newInterval = {0, 0};
        int[][] insert = insert(intervals, newInterval);
        for (int[] ints : insert) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            int[][] ints = new int[1][2];
            ints[0] = newInterval;
            return ints;
        }
        List<int[]> resList = new ArrayList<>();
        int i = 0;
        while (i < intervals.length) {
            if (intervals[i][0] > newInterval[1] || intervals[i][1] < newInterval[0]) {
                if (intervals[i][0] > newInterval[1]) {
                    if (i == 0 || intervals[i - 1][1] < newInterval[0]) {
                        resList.add(newInterval);
                    }
                }
                resList.add(intervals[i]);
                if (i == intervals.length - 1 && intervals[i][1] < newInterval[0]) {
                    resList.add(newInterval);
                }
                i++;
                continue;
            }
            int start = i;
            while (i < intervals.length) {
                if (intervals[i][0] > newInterval[1]) {
                    break;
                }
                if (intervals[i][1] > newInterval[1]) {
                    i++;
                    break;
                }
                i++;
            }
            resList.add(new int[]{Math.min(intervals[start][0], newInterval[0]), Math.max(intervals[i - 1][1], newInterval[1])});
        }
        int[][] res = new int[resList.size()][2];
        for (int j = 0; j < resList.size(); j++) {
            res[j] = resList.get(j);
        }
        return res;
    }
}

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }
}