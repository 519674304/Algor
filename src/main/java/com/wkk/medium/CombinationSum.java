package com.wkk.medium;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    private List<List<Integer>> collect = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        collectHelper(candidates,  0,0, new ArrayList<>(), target);
        return collect;
    }

    public void collectHelper(int[] candidates, int start, int prevVal, List<Integer> prevList, int target) {
        if (prevVal > target) {
            return;
        }
        if (prevVal == target) {
            collect.add(new ArrayList<>(prevList));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            prevList.add(candidates[i]);
            collectHelper(candidates, i, prevVal + candidates[i], prevList, target);
            prevList.remove(new Integer(candidates[i]));
        }
    }
}
