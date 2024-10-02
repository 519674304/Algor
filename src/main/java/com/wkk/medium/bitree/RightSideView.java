package com.wkk.medium.bitree;

import com.wkk.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class RightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        collect(root, 0, resList);
        return resList;
    }

    public void collect(TreeNode loop, int depth, List<Integer> list) {
        if (loop == null) {
            return;
        }

        if (list.size() <= depth) {
            list.add(loop.val);
        }
        collect(loop.right, depth + 1, list);
        collect(loop.left, depth + 1, list);
    }
}
