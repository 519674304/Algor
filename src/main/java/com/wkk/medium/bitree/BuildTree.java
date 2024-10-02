package com.wkk.medium.bitree;

import com.wkk.util.TreeNode;
import org.junit.Test;

import java.util.*;

public class BuildTree {
    @Test
    public void test10(){
        TreeNode treeNode = buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(treeNode);
    }



    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode prev = new TreeNode(), rightPrev = null;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(prev);
        int l = 0, r = 0;
        while (l < preorder.length && r < inorder.length) {
            TreeNode currNode = new TreeNode(preorder[l]);
            if (rightPrev != null) {
                rightPrev.right = currNode;
                rightPrev = null;
            }else {
                stack.peek().left = currNode;
            }
            stack.push(currNode);
            if (preorder[l] == inorder[r]) {
                while (r < inorder.length && stack.peek().val == inorder[r] && stack.size() > 1) {
                    rightPrev = stack.pop();
                    r++;
                }
            }
            l++;
        }
        return prev.left;
    }

    private Map<Integer, Integer> indexMap = new HashMap<>();


    private TreeNode buildTreeMap(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return fillTreeNode(preorder, 0, preorder.length - 1, 0);
    }

    public TreeNode fillTreeNode(int[] preorder, int pl, int ple, int ir) {
        if (pl > ple) {
            return null;
        }
        TreeNode root = new TreeNode();
        root.val = preorder[pl];
        Integer rootIndex = indexMap.get(root.val);
        int leftSize = rootIndex - ir;
        root.left = fillTreeNode(preorder, pl + 1, pl + leftSize, ir);
        root.right = fillTreeNode(preorder, pl + leftSize + 1, ple, rootIndex + 1);
        return root;
    }

}

class OfficialSolution {
    private Map<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int preorder_left, int preorder_right, int inorder_left) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_left]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_left]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, 0, n - 1, 0);
    }
}

class SingleSolution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }
}
