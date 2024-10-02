package com.wkk.medium.bitree;

import com.wkk.util.TreeNode;

import java.util.Stack;

public class BSTIterator {
    Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode pop = stack.pop();
        if (pop.right != null) {
            TreeNode loop = pop.right;
            while (loop != null) {
                stack.push(loop);
                loop = loop.left;
            }
        }
        return pop.val;
    }

    public boolean hasNext() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.arrayToTreeNode(new Integer[]{7, 3, 15, null, null, 9, 20});
        BSTIterator bstIterator = new BSTIterator(treeNode);

    }
}


