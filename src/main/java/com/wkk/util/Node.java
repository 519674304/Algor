package com.wkk.util;

import java.util.LinkedList;
import java.util.Queue;

public class Node {
    public int val;
    public Node next;
    public Node left;
    public Node right;
    public Node random;

    public Node() {
    }

    public Node(int x) {
        val = x;
        next = null;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    public static Node arrayToTreeNode(Integer[] arr) {
        if (arr == null || arr.length == 0) return null;

        Node root = new Node(arr[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            Node current = queue.poll();

            // 处理左孩子
            if (arr[i] != null) {
                current.left = new Node(arr[i]);
                queue.add(current.left);
            }
            i++;

            // 处理右孩子
            if (i < arr.length && arr[i] != null) {
                current.right = new Node(arr[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }
}
