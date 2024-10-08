package com.wkk.hard;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindMaximizedCapital {
    @Test
    public void test9(){
        int maximizedCapital = findMaximizedCapital(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1});
        System.out.println(maximizedCapital);
    }
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        if (capital.length == 0) {
            return 0;
        }
        Queue<Integer> profitMostQueue = new PriorityQueue<>((v1, v2) -> v2 - v1);
        Queue<int[]> capitalLessQueue = new PriorityQueue<>((v1, v2) -> v1[0] - v2[0]);
        for (int i = 0; i < capital.length; i++) {
            capitalLessQueue.add(new int[]{capital[i], i});
        }
        while (!capitalLessQueue.isEmpty() && capitalLessQueue.peek()[0] <= w) {
            int index = capitalLessQueue.poll()[1];
            int pureProfit = profits[index];
            if (pureProfit > 0) {
                profitMostQueue.add(pureProfit);
            }
        }
        int res = 0;
        while (k > 0 && !profitMostQueue.isEmpty()) {
            int pureProfit = profitMostQueue.poll();
            w += pureProfit;
            res += pureProfit;
            while (!capitalLessQueue.isEmpty() && capitalLessQueue.peek()[0] <= w) {
                int index = capitalLessQueue.poll()[1];
                pureProfit = profits[index];
                if (pureProfit > 0) {
                    profitMostQueue.add(pureProfit);
                }
            }
            k--;
        }
        return res;
    }
}
