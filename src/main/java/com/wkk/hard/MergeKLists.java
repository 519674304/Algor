package com.wkk.hard;

import com.wkk.util.ListNode;

import java.util.ArrayList;
import java.util.List;

public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeListHelper(lists, 0, lists.length - 1);
    }

    public ListNode mergeListHelper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        ListNode left = mergeListHelper(lists, start, (start + end) / 2);
        ListNode right = mergeListHelper(lists, (start + end) / 2 + 1, end);
        ListNode initPrev = new ListNode();
        ListNode loopPrev = initPrev;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                loopPrev.next = left;
                left = left.next;
            } else {
                loopPrev.next = right;
                right = right.next;
            }
            loopPrev = loopPrev.next;
        }
        if (left != null) {
            loopPrev.next = left;
        }
        if (right != null) {
            loopPrev.next = right;
        }
        return initPrev.next;
    }
}
