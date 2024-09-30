package com.wkk.medium;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class FindMinArrowShots {
    @Test
    public void test8(){
        int[][] points = {{10,16},{2,8},{1,6},{7,12}};
        System.out.println(findMinArrowShots(points));
    }
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparing(v -> v[0]));
        int i = 0;
        int count = 0;
        while (i < points.length) {
            int end = points[i][1];
            i++;
            while (i < points.length) {
                if (end < points[i][0]) {
                    break;
                }
                end = Math.min(end, points[i][1]);
                i++;
            }
            count++;
        }
        return count;
    }
}
