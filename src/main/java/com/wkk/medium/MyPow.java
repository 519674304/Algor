package com.wkk.medium;

import org.junit.Test;

public class MyPow {

    @Test
    public void test6(){
        System.out.println(myPow(2, -2147483648));
        System.out.println(Math.pow(2, -2147483648));
    }
    public double myPow(double x, int n) {
        double contribute = x, ans = 1.0;
        long pow = Math.abs((long) n);
        while (pow > 0) {
            if (pow % 2 != 0){
                ans *= contribute;
            }
            contribute *= contribute;
            pow >>= 1;
        }
        return n < 0 ? 1 / ans : ans;
    }
    class Solution {
        public double myPow(double x, int n) {
            long N = n;
            return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
        }

        public double quickMul(double x, long N) {
            if (N == 0) {
                return 1.0;
            }
            double y = quickMul(x, N / 2);
            return N % 2 == 0 ? y * y : y * y * x;
        }
    }
}
