package com.wkk.medium;

import com.wkk.util.CommonUtil;
import com.wkk.util.ListNode;
import org.junit.Test;

public class PartitionListNode {
    @Test
    public void test7(){
        ListNode head = CommonUtil.arrayToList(new int[]{1, 4, 3, 2, 5, 2});
        ListNode partition = partition(head, 3);
        CommonUtil.printList(partition);
    }
    public ListNode partition(ListNode head, int x) {
        ListNode prevH = new ListNode(0, head), lo = head, prev = prevH, loPrev = prevH;
        while (lo != null) {
            if (lo.val < x) {
                if (prev == loPrev) {
                    loPrev = lo;
                    lo = lo.next;
                    prev = prev.next;
                    continue;
                }
                ListNode old = prev.next;
                ListNode next = lo.next;
                prev.next = lo;
                lo.next = old;
                loPrev.next = next;
                lo = next;
                prev = prev.next;
                continue;
            }
            loPrev = lo;
            lo = lo.next;
        }
        return prevH.next;
    }
}
