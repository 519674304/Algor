package com.wkk.simple;

import org.junit.Test;

public class MySqrt {
    @Test
    public void test5(){
        System.out.println(mySqrt(2147483647));
    }
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int l = 1, r = x;
        while (l < r - 1) {
            int middle = (r - l) / 2 + l;
            if ((long) middle * middle == x){
                return middle;
            }else if ((long) middle * middle > x){
                r = middle;
            }else {
                l = middle;
            }
        }
        return (long) r * r < x ? r : l;
    }

    public int mySqrt1(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;

    }
}
