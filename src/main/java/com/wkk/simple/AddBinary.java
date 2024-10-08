package com.wkk.simple;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Queue;

public class AddBinary {
    @Test
    public void test5(){
        System.out.println("aa/bb/".lastIndexOf("/"));
        //System.out.println(addBinary("11", "1"));
    }

    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int i = 1;
        int prev = 0;
        while (a.length() - i >= 0 || b.length() - i >= 0) {
            int x = a.length() - i < 0 ? 0 : a.charAt(a.length() - i) - '0';
            int y = b.length() - i < 0 ? 0 : b.charAt(b.length() - i) - '0';
            int currVal = x + y + prev;
            prev = currVal / 2;
            res.insert(0, currVal % 2);
            i++;
        }
        if (prev > 0) {
            res.insert(0, prev);
        }

        return res.toString();
    }
}
