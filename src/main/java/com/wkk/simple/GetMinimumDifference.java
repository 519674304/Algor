package com.wkk.simple;

import com.wkk.util.TreeNode;

public class GetMinimumDifference {
    private int min = Integer.MAX_VALUE;
    private int pre = -1;
    public int getMinimumDifference(TreeNode root) {
        helper(root);
        return min;
    }

    public void helper(TreeNode root){
        helper(root.left);
        if (pre == -1) {
            pre = root.val;
        }else {
            min = Math.min(Math.abs(root.val - pre), min);
        }
        helper(root.right);
    }
}
