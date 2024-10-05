package com.wkk.medium;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    private List<String> collect = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        helper(n, 0, "");
        return collect;
    }

    public void helper(int n, int left, String sb) {
        if (n < 0) {
            return;
        }
        if (left < 0) {
            return;
        }
        if (n == 0 && left == 0) {
            collect.add(sb);
            return;
        }

        helper(n - 1, left + 1, sb + "(");
        helper(n, left - 1, sb + ")");
    }
}
