package com.wkk.medium;

import java.util.ArrayList;
import java.util.List;

public class Combine {
    private List<List<Integer>> collect = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        collect(n, k, 1, new ArrayList<>());
        return collect;
    }

    public void collect(int n, int k, int start, List<Integer> list) {
        if (k == 0) {
            collect.add(new ArrayList<>(list));
            return;
        }
        if (start > n - k) {
            return;
        }
        for (int i = start; i <= n; i++) {
            list.add(i);
            collect(n, k - 1, i, list);
            list.remove(new Integer(i));
        }
    }
}
