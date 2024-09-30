package com.wkk.simple;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    @Test
    public void test8(){
        List<String> strings = summaryRanges(new int[]{0, 1, 2, 4, 5, 7});
        strings.forEach(System.out::println);
    }
    public List<String> summaryRanges(int[] nums) {
        List<String> summary = new ArrayList<>();
        int s = 0, f = 1;
        while (f < nums.length) {
            if (f < s) {
                f++;
                continue;
            }
            if (nums[f] - nums[f - 1] == 1) {
                f++;
            }else {
                summary.add(getRangeStr(s, f, nums));
                s = f;
                f++;
            }
        }
        summary.add(getRangeStr(s, f, nums));
        return summary;
    }

    public String getRangeStr(int s, int f, int[] nums) {
        return s == f - 1 ? String.valueOf(nums[s]) : String.format("%d->%d", nums[s], nums[f - 1]);
    }
}

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ret = new ArrayList<String>();
        int i = 0;
        int n = nums.length;
        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            int high = i - 1;
            StringBuilder temp = new StringBuilder(Integer.toString(nums[low]));
            if (low < high) {
                temp.append("->");
                temp.append(nums[high]);
            }
            ret.add(temp.toString());
        }
        return ret;
    }
}
