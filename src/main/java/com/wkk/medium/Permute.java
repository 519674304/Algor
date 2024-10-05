package com.wkk.medium;

import java.util.ArrayList;
import java.util.List;

public class Permute {
    private List<List<Integer>> collect = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        collect(nums, new ArrayList<>());
        return collect;
    }

    public void collect(int[] nums, List<Integer> partList) {
        if (partList.size() == nums.length) {
            collect.add(new ArrayList<>(partList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -20) {
                continue;
            }
            int oldVal = nums[i];
            partList.add(nums[i]);
            nums[i] = -20;
            collect(nums, partList);
            partList.remove(new Integer(oldVal));
            nums[i] = oldVal;
        }
    }
}
