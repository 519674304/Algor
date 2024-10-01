package com.wkk.medium;

import com.wkk.util.ListNode;

import java.util.Objects;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        int upVal = 0;
        int reVal;
        ListNode lo1 = l1, lo2 = l2, lo3 = res;
        while (true) {
            int val1 = Objects.nonNull(lo1) ? lo1.val : 0;
            int val2 = Objects.nonNull(lo2) ? lo2.val : 0;
            reVal = (val1 + val2 + upVal) % 10;
            upVal = (val1 + val2 + upVal) / 10;
            lo3.val = reVal;
            if (Objects.nonNull(lo1)) {
                lo1 = lo1.next;
            }
            if (Objects.nonNull(lo2)) {
                lo2 = lo2.next;
            }
            if (lo1 == null && lo2 == null) {
                if (upVal > 0) {
                    lo3.next = new ListNode(upVal);
                }
                break;
            }
            lo3.next = new ListNode();
            lo3 = lo3.next;
        }
        return res;
    }
}
