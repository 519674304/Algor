package com.wkk.medium;

public class Rob {
    public int rob(int[] nums) {
        int pv = 0, ppv = 0, res = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            res = Math.max(pv, ppv + nums[i]);
            ppv = pv;
            pv = res;
        }
        return res;
    }

    public int rob(int[] nums, int index) {
        if (index >= nums.length) {
            return 0;
        }
        int notRob = rob(nums, index + 1);
        int robNum = rob(nums, index + 2) + nums[index];
        return notRob + robNum;
    }
}
