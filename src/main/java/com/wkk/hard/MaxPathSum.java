package com.wkk.hard;

import com.wkk.util.TreeNode;
import org.junit.Test;

public class MaxPathSum {
    @Test
    public void test7(){
        TreeNode root = TreeNode.arrayToTreeNode(new Integer[]{-1,-2,10,-6,null,-3,-6});
        System.out.println(maxPathSum(root));
    }

    private int sum = 0;

    public int maxPathSum(TreeNode root) {
        return maxSumHelper(root);
    }

    public int maxSumHelper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftGain = Math.max(maxSumHelper(root.left), 0);
        int rightGain = Math.max(maxSumHelper(root.right), 0);
        sum = Math.max(Math.max(leftGain, rightGain), leftGain + rightGain + root.val);
        return leftGain + rightGain + root.val;
    }


    public int helper(TreeNode iterNode) {
        if (iterNode == null) {
            return 0;
        }
        int currVal = iterNode.val;
        int leftVal = helper(iterNode.left);
        int rightVal = helper(iterNode.right);
        if (iterNode.left == null && iterNode.right == null) {
            return currVal;
        }
        if (iterNode.left == null) {
            iterNode.val = Math.max(iterNode.right.val + currVal, currVal);
            return maxHelper(rightVal, iterNode.right.val + currVal, currVal);
        }
        if (iterNode.right == null) {
            iterNode.val = Math.max(iterNode.left.val + currVal, currVal);
            return maxHelper(leftVal, iterNode.left.val + currVal, currVal);
        }
        int leftHVal = iterNode.left.val;
        int rightHVal = iterNode.right.val;
        iterNode.val = maxHelper(leftHVal + currVal, rightHVal + currVal, currVal);
        return maxHelper(leftVal, rightVal, leftHVal + currVal, rightHVal + currVal, leftHVal + rightHVal + currVal, currVal);
    }

    public int maxHelper(int... vals) {
        if (vals.length == 1) {
            return vals[0];
        }
        int res = vals[0];
        for (int i = 1; i < vals.length; i++) {
            res = Math.max(res, vals[i]);
        }
        return res;
    }


}

class MaxPathSumSolution {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = node.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, priceNewpath);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }
}