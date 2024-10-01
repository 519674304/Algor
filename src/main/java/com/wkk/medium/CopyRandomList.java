package com.wkk.medium;

import com.wkk.util.ListNode;
import com.wkk.util.Node;

import java.util.HashMap;
import java.util.Map;

public class CopyRandomList {

    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        Node lo = head, coHead = new Node(head.val), coLo = coHead;
        Map<Node, Node> onMap = new HashMap<>();
        while (lo != null) {
            onMap.put(lo, coLo);
            lo = lo.next;
            if (lo != null) {
                coLo.next = new Node(lo.val);
                coLo = coLo.next;
            }
        }
        lo = head;
        coLo = coHead;
        while (lo != null) {
            coLo.random = onMap.get(lo.random);
            lo = lo.next;
            coLo = coLo.next;
        }
        return coHead;
    }
}

class RandomListSolution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        for (Node node = head; node != null; node = node.next.next) {
            Node nodeNew = new Node(node.val);
            nodeNew.next = node.next;
            node.next = nodeNew;
        }
        for (Node node = head; node != null; node = node.next.next) {
            Node nodeNew = node.next;
            nodeNew.random = (node.random != null) ? node.random.next : null;
        }
        Node headNew = head.next;
        for (Node node = head; node != null; node = node.next) {
            Node nodeNew = node.next;
            node.next = node.next.next;
            nodeNew.next = (nodeNew.next != null) ? nodeNew.next.next : null;
        }
        return headNew;
    }
}
