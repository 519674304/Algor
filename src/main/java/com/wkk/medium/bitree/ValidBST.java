package com.wkk.medium.bitree;

import com.wkk.util.TreeNode;
import org.junit.Test;

import java.util.Stack;

public class ValidBST {
    @Test
    public void test9(){
        TreeNode treeNode = TreeNode.arrayToTreeNode(new Integer[]{2, 1, 3});
        System.out.println(isValidBST(treeNode));
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        int prevVal = Integer.MIN_VALUE;
        while (!nodeStack.isEmpty()) {
            while (nodeStack.peek().left != null) {
                nodeStack.push(nodeStack.peek().left);
            }
            while (!nodeStack.isEmpty()) {
                TreeNode node = nodeStack.pop();
                if (node.val < prevVal) {
                    return false;
                }
                prevVal = node.val;
                if (node.right != null) {
                    nodeStack.push(node.right);
                    break;
                }
            }
        }
        return true;
    }


}
