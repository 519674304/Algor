package com.wkk.medium.bitree;

import com.wkk.util.Node;
import org.junit.Test;

import java.util.*;

public class BINodeConnect {

    @Test
    public void test12(){
        Integer[] arr = {1, 2, 3, 4, 5, null, 7};
        Node root = Node.arrayToTreeNode(arr);
        connect(root);
        System.out.println(root);
    }

    private List<Node> nodeList = new ArrayList<>();

    public Node connect(Node root) {
        linkSolution(root);
        return root;
    }

    public void linkSolution(Node root) {
        Node curr = root;
        Node dummy = new Node();
        while (curr != null) {
            Node next = dummy;
            while (curr != null) {
                if (curr.left != null) {
                    next.next = curr.left;
                    next = next.next;
                }
                if (curr.right != null) {
                    next.next = curr.right;
                    next = next.next;
                }
                curr = curr.next;
            }
            curr = dummy.next;
            dummy.next = null;
        }
    }

    public void iterSolution(Node iterNode, int depth) {
        if (iterNode == null) {
            return;
        }

        if (depth == nodeList.size()) {
            nodeList.add(iterNode);
        }else {
            nodeList.get(depth - 1).next = iterNode;
            nodeList.set(depth - 1, iterNode);
        }
        iterSolution(iterNode.left, depth + 1);
        iterSolution(iterNode.right, depth + 1);
    }



    private Node queSolution(Node root) {
        Deque<Node> deque = new LinkedList<>();
        deque.push(root);
        while (!deque.isEmpty()) {
            int n = deque.size();
            Node subsequent = null;
            for (int i = 0; i < n; i++) {
                Node last = deque.pollLast();
                addNotNull(last.right, deque);
                addNotNull(last.left, deque);
                last.next = subsequent;
                subsequent = last;
            }
        }
        return root;
    }

    public void addNotNull(Node node, Deque<Node> nodes) {
        if (Objects.isNull(node)) {
            return;
        }
        nodes.push(node);
    }

}

class Solution {
    public Node connect(Node root) {
        Node dummy = new Node();
        Node cur = root;
        while (cur != null) {
            dummy.next = null;
            Node nxt = dummy; // 下一层的链表
            while (cur != null) { // 遍历当前层的链表
                if (cur.left != null) {
                    nxt.next = cur.left; // 下一层的相邻节点连起来
                    nxt = cur.left;
                }
                if (cur.right != null) {
                    nxt.next = cur.right; // 下一层的相邻节点连起来
                    nxt = cur.right;
                }
                cur = cur.next; // 当前层链表的下一个节点
            }
            cur = dummy.next; // 下一层链表的头节点
        }
        return root;
    }
}
