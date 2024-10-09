package com.wkk.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class LengthOfLIS {
    @Test
    public void test9(){
        System.out.println(lengthOfLIS1(new int[]{4,10,4,3,8,9}));
    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 1;
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j], dp[i]);
                }
            }
            dp[i]++;
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public int lengthOfLIS1(int[] nums) {
        int[] d = new int[nums.length];
        int max = 0;
        d[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int index = getLeft(d, max, nums[i]);
            if (d[index] > nums[i]) {
                d[index] = nums[i];
            } else if (index == max && nums[i] > d[index]) {
                d[index + 1] = nums[i];
                max++;
            }
        }
        return max + 1;
    }

    public int getLeft(int[] d, int r, int num) {
        int l = 0;
        while (l < r - 1){
            int middle = (r + l) / 2;
            if (d[middle] < num) {
                l = middle;
            }else {
                r = middle;
            }
        }
        return d[l] >= num ? l : r;
    }
}
