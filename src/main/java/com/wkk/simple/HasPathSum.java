package com.wkk.simple;

import com.wkk.util.TreeNode;

public class HasPathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return targetSum == 0;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}
