package com.wkk.simple;

import com.wkk.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class SortedArrayToBST {


    public TreeNode sortedArrayToBST(int[] nums) {
        List<TreeNode> treeNodeList = new ArrayList<>();
        for (int num : nums) {
            treeNodeList.add(new TreeNode(num));
        }
        return helper(treeNodeList, 0, nums.length - 1);
    }

    public TreeNode helper(List<TreeNode> treeNodeList, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return treeNodeList.get(start);
        }
        int middle = (start + end) / 2;
        TreeNode root = treeNodeList.get(middle);
        root.left = helper(treeNodeList, start, middle - 1);
        root.right = helper(treeNodeList, middle + 1, end);
        return root;
    }


}
