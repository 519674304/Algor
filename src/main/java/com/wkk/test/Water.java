package com.wkk.test;

import org.junit.Test;

public class Water {
    @Test
    public void test5(){
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height));
    }

    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int max = 0;
        while (l < r){
            int h = Math.min(height[l], height[r]);
            int area = (r - l) * h;
            max = Math.max(max, area);
            if (height[l] > height[r]) {
                r--;
            }else {
                l++;
            }
        }
        return max;
    }
}


