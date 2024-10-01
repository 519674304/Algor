package com.wkk.medium;

import com.wkk.util.ListNode;
import org.junit.Test;

public class RotateRight {
    @Test
    public void test5(){

    }

    public ListNode rotateRight(ListNode head, int k) {
        ListNode prevH = new ListNode(0, head), slow = prevH, fast = prevH;
        ListNode loopN = prevH;
        int count = 0;
        while (loopN.next != null) {
            count++;
            loopN = loopN.next;
        }
        k %= count;
        if (k == 0) {
            return head;
        }
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        fast.next = prevH.next;
        prevH.next = slow.next;
        slow.next = null;
        return prevH.next;
    }
}
