package com.wkk.medium;

import com.wkk.util.ListNode;

import java.util.Objects;

public class ReverseBetween {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode prevHead = new ListNode(0, head);
        ListNode first = prevHead, lo = prevHead, last = prevHead;
        int i = 0;
        while (i <= right) {
            if (i == left - 1) {
                first = lo;
            }
            if (i == right) {
                last = lo.next;
            }
            lo = lo.next;
            i++;
        }
        ListNode prev = first, next = first.next.next, post = first.next;
        while (next != last) {
            ListNode ol = prev.next;
            prev.next = next;
            next = next.next;
            prev.next.next = ol;
        }
        post.next = last;
        return prevHead.next;
    }
}
