package com.wkk.util;

public class Node {
    public int val;
    public Node next;
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
}
