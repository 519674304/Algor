package com.wkk.simple;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReverseBits {
    @Test
    public void test10(){
        System.out.println(isPalindrome(1001));
    }

    // you need treat n as an unsigned value
    public boolean isPalindrome(int x) {
        if(x < 0 || ((x % 10) == 0 && x != 0) ){
            return false;
        }
        int reverseNum = 0;
        while(reverseNum < x){
            int num = x % 10;
            reverseNum = (reverseNum * 10 + num);
            x /= 10;
        }
        return reverseNum == x || x == reverseNum / 10;
    }
}
