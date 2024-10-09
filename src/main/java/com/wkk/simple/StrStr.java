package com.wkk.simple;

import org.junit.Test;

public class StrStr {

    @Test
    public void test6(){
        System.out.println(strStr("aabaaabaaac", "aabaaac"));
    }

    public int strStr(String haystack, String needle) {
        int r = 0;
        int[] nextArray = getNextArray(needle);
        int i = 0;
        for (; i < haystack.length(); i++) {
            while (r != 0 && haystack.charAt(i) != needle.charAt(r)) {
                r = nextArray[r];
            }
            if (haystack.charAt(i) == needle.charAt(r)) {
                r++;
            }
            if (r == needle.length()) {
                break;
            }
        }
        return r == needle.length() ? (i - needle.length() + 1) : -1;
    }

    public int[] getNextArray(String needle) {
        if (needle.length() <= 2) {
            return new int[needle.length()];
        }
        int l = 0, r = 1;
        int[] nextArray = new int[needle.length()];
        while (r + 1 < needle.length()) {
            while (needle.charAt(r) != needle.charAt(l) && l != 0) {
                l = nextArray[l];
            }
            if (needle.charAt(l) == needle.charAt(r)) {
                nextArray[r + 1] = ++l;
            }
            r++;
        }
        return nextArray;
    }

}
