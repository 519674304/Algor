package com.wkk.medium.bitree;

import com.wkk.util.TreeNode;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class SumNumbers {
    private int sum = 0;

    @Test
    public void test9(){
        TreeNode treeNode = TreeNode.arrayToTreeNode(new Integer[]{1, 2, 3});
        System.out.println(sumNumbers(treeNode));
    }

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        sumHelper(root, 0);
        return sum ;
    }

    public void sumHelper(TreeNode root, int prev) {
        if(root == null){
            return;
        }
        if (root.left == null && root.right == null) {
            sum += prev * 10 + root.val;
            return;
        }
        sumHelper(root.left, prev * 10 + root.val);
        sumHelper(root.right, prev * 10 + root.val);
    }


}

class SumNumbersSolution {
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<Integer> numQueue = new LinkedList<Integer>();
        nodeQueue.offer(root);
        numQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int num = numQueue.poll();
            TreeNode left = node.left, right = node.right;
            if (left == null && right == null) {
                sum += num;
            } else {
                if (left != null) {
                    nodeQueue.offer(left);
                    numQueue.offer(num * 10 + left.val);
                }
                if (right != null) {
                    nodeQueue.offer(right);
                    numQueue.offer(num * 10 + right.val);
                }
            }
        }
        return sum;
    }
}
