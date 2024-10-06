package com.wkk.medium;

import org.junit.Test;

public class MaxSubarraySumCircular {

    @Test
    public void test6(){
        int[] nums = {-3, -2, -3};
        System.out.println(maxSubarraySumCircular(nums));

    }
    public int maxSubarraySumCircular(int[] nums) {
        int preMax = 0, preMin = 0, sum = 0, maxAns = Integer.MIN_VALUE;
        int[] preMins = new int[nums.length];
        int[] preMaxs = new int[nums.length];

        for(int i = 0; i < nums.length; i++){
            preMax = Math.max(preMax + nums[i], nums[i]);
            preMin = Math.min(preMin + nums[i], nums[i]);
            preMaxs[i] = preMax;
            preMins[i] = preMin;
            sum += nums[i];
        }

        for(int i = 0; i < nums.length; i++){
            maxAns = Math.max(Math.max(preMaxs[i], sum - preMins[i] == 0 ? Integer.MIN_VALUE : sum - preMins[i]), maxAns);
        }
        return maxAns;
    }


    public int getIndex(int i, int length){
        return (i + length) % length;
    }
}
