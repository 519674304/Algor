package com.wkk.medium;

import com.wkk.util.CommonUtil;
import com.wkk.util.ListNode;
import org.junit.Test;

public class RemoveNthFromEnd {
    @Test
    public void test7(){
        ListNode listNode = CommonUtil.arrayToList(new int[]{1});
        ListNode listNode1 = removeNthFromEnd(listNode, 1);
        CommonUtil.printList(listNode1);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prev = new ListNode(0, head), slow = prev, fast = prev;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return prev.next;
    }
}
