package com.wkk.medium.bitree;

import com.wkk.util.TreeNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BuildTreeII {

    @Test
    public void test12(){
        TreeNode treeNode = buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3});
        System.out.println(treeNode);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        stack.push(root);
        int j = inorder.length - 1;
        for (int i = postorder.length - 2; i >= 0; i--) {
            TreeNode prevNode = stack.peek();
            if (prevNode.val == inorder[j]) {
                while (j >= 0 && !stack.isEmpty() && stack.peek().val == inorder[j]) {
                    prevNode = stack.pop();
                    j--;
                }
                prevNode.left = new TreeNode(postorder[i]);
                stack.push(prevNode.left);
            }else {
                prevNode.right = new TreeNode(postorder[i]);
                stack.push(prevNode.right);
            }
        }
        return root;
    }

    private Map<Integer, Integer> indexMap = new HashMap<>();

    private TreeNode getTreeNode(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return build(inorder, postorder, postorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode build(int[] inorder, int[] postorder, int pre, int ple, int ire) {
        if (pre < ple) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[pre]);
        Integer rootIndex = indexMap.get(postorder[pre]);
        int rightSize = ire - rootIndex;
        root.right = build(inorder, postorder, pre - 1, pre - rightSize, ire);
        root.left = build(inorder, postorder, pre - rightSize - 1, ple, rootIndex - 1);
        return root;
    }


}
