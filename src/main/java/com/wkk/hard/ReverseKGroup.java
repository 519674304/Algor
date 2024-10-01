package com.wkk.hard;

import com.wkk.util.CommonUtil;
import com.wkk.util.ListNode;
import org.junit.Test;

public class ReverseKGroup {

    @Test
    public void test8(){
        ListNode head = CommonUtil.arrayToList(new int[]{1, 2});
        ListNode listNode = reverseKGroup(head, 2);
        CommonUtil.printList(listNode);

    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 0) {
            return head;
        }
        ListNode prevH = new ListNode(0, head), prev, lo = prevH;
        while (true) {
            prev = lo;
            for (int i = 0; i < k; i++) {
                if (lo == null) {
                    break;
                }
                lo = lo.next;
            }
            if (lo == null || prev.next.next == null) {
                break;
            }
            ListNode lastNext = lo.next;
            ListNode next = prev.next.next, last = prev.next;
            while (next != lastNext) {
                ListNode ol = prev.next;
                prev.next = next;
                next = next.next;
                prev.next.next = ol;
            }
            last.next = lastNext;
            lo = last;
        }
        return prevH.next;
    }
}
