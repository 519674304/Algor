package com.wkk.medium;

import org.junit.Test;

public class FindPeakElement {

    @Test
    public void test6(){
        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        System.out.println(findPeakElement(nums));
    }

    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while(l < r){
            int middle = (l + r) /2;
            if(nums[middle] < nums[middle + 1]){
                l = middle + 1;
            }else{
                r = middle;
            }
        }
        return l;
    }
}
