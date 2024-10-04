package com.wkk.medium.bitree;

import com.wkk.util.TreeNode;
import org.junit.Test;

public class KthSmallest {

    @Test
    public void test8(){
        TreeNode root = TreeNode.arrayToTreeNode(new Integer[]{5, 3, 6, 2, 4, null, null, 1});
        int i = kthSmallest(root, 3);
        System.out.println(i);
    }

    private TreeNode kNode = null;
    private int num = 0;

    public int kthSmallest(TreeNode root, int k) {
        getKS(root, k);
        return kNode.val;
    }

    public void getKS(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        getKS(root.left, k);
        num++;
        if (num == k) {
            kNode = root;
        }
        getKS(root.right, k);
    }
}
