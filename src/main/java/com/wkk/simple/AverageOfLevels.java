package com.wkk.simple;

import com.wkk.util.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class AverageOfLevels {
    @Test
    public void test12(){
        TreeNode treeNode = TreeNode.arrayToTreeNode(new Integer[]{3, 9, 20, null, null, 15, 7});
        List<Double> doubles = averageOfLevels(treeNode);
        doubles.forEach(System.out::println);
    }
    public List<Double> averageOfLevels(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        deque.push(root);
        List<Double> res = new ArrayList<>();
        while (!deque.isEmpty()) {
            int n = deque.size();
            double sum = 0;
            for (int i = 0; i < n; i++) {
                TreeNode treeNode = deque.pollLast();
                sum += treeNode.val;
                if (treeNode.left != null) {
                    deque.addFirst(treeNode.left);
                }
                if (treeNode.right != null) {
                    deque.addFirst(treeNode.right);
                }
            }
            res.add(sum / n);
        }
        return res;
    }


}
