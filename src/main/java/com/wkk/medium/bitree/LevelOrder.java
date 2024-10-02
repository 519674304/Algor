package com.wkk.medium.bitree;

import com.wkk.util.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> resList = new ArrayList<>();
        if (root != null) {
            deque.add(root);
        }
        while (!deque.isEmpty()) {
            List<Integer> currList = new ArrayList<>();
            int n = deque.size();
            for (int i = 0; i < n; i++) {
                TreeNode currNode = deque.pollLast();
                currList.add(currNode.val);
                if (currNode.left != null) {
                    deque.addFirst(currNode.left);
                }
                if (currNode.right != null) {
                    deque.addFirst(currNode.right);
                }
            }
            resList.add(currList);
        }
        return resList;
    }
}
