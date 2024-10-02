package com.wkk.medium.bitree;

import com.wkk.util.TreeNode;
import org.junit.Test;

public class Flatten {

    @Test
    public void test8(){
        Integer[] arr = {1,2,5,3,4,null,6};
        TreeNode root = TreeNode.arrayToTreeNode(arr);
        flatten(root);
        System.out.println(root);
    }

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        if (root.left != null) {
            TreeNode right = root.right;
            root.right = root.left;
            TreeNode loop = root;
            while (loop.right != null) {
                loop = loop.right;
            }
            loop.right = right;
            root.left = null;
        }
        flatten(root.right);
    }


}
