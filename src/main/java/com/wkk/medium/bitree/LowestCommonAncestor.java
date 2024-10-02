package com.wkk.medium.bitree;

import com.wkk.util.TreeNode;
import org.junit.Test;

public class LowestCommonAncestor {
    @Test
    public void test7(){
        TreeNode root = TreeNode.arrayToTreeNode(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        TreeNode treeNode = lowestCommonAncestor(root, root.left, root.right);
        System.out.println(treeNode);
    }

    private TreeNode firstNode = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        find(root, p, q);
        return firstNode;
    }

    public int find(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return 0;
        }

        int left = find(root.left, p, q);
        int right = find(root.right, p, q);
        int currVal = (root == p || root == q) ? 1 : 0;
        int totalVal = left + right + currVal;
        if (totalVal == 2 && firstNode == null) {
            firstNode = root;
        }
        return totalVal;
    }
}

class LowestCommonAncestorSolution {

    private TreeNode ans;

    public LowestCommonAncestorSolution() {
        this.ans = null;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }
}
