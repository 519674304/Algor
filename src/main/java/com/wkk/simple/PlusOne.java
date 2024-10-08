package com.wkk.simple;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        int prev = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] += prev + 1;
            if (digits[i] >= 10) {
                prev = 1;
            }else {
                prev = 0;
            }
            digits[i] %= 10;
        }
        if (prev == 0) {
            return digits;
        }
        int[] res = new int[digits.length + 1];
        res[0] = prev;
        System.arraycopy(digits, 0, res, 1, res.length - 1);
        return res;
    }
}
