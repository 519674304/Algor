package com.wkk.medium.bitree;

import com.wkk.util.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        boolean flag = true;
        Deque<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> resList = new LinkedList<>();
        if (root != null) {
            deque.push(root);
        }

        while (!deque.isEmpty()) {
            int n = deque.size();
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                TreeNode currNode = deque.pollLast();
                if (flag) {
                    list.add(currNode.val);
                }else {
                    list.add(0, currNode.val);
                }
                if (currNode.left != null) {
                    deque.addFirst(currNode.left);
                }
                if (currNode.right != null) {
                    deque.addFirst(currNode.right);
                }
            }
            resList.add(list);
        }
        return resList;
    }
}
