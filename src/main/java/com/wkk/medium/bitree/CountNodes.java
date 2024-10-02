package com.wkk.medium.bitree;

import com.wkk.util.TreeNode;

public class CountNodes {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(countNodes(root.left), countNodes(root.right)) + 1;
    }
}
