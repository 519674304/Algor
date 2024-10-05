package com.wkk.medium;

import com.wkk.util.CommonUtil;
import com.wkk.util.ListNode;
import org.junit.Test;

public class SortLinkList {

    @Test
    public void test8(){
        ListNode listNode = CommonUtil.arrayToList(new int[]{4,19,14,5,-3,1,8,5,11,15});
        ListNode listNode1 = sortList(listNode);
        System.out.println(listNode1);
    }

    public ListNode sortList(ListNode head) {
        ListNode prev = new ListNode(-1, head);
        sort(head, 1, prev);
        return prev.next;
    }

    public void sort(ListNode head, int group, ListNode prev) {
        if (head == null) {
            return;
        }
        ListNode currPrev = prev;
        ListNode currLastPrev = prev;
        ListNode nextNode = head;
        ListNode currNode = head;
        while (currNode != null) {
            for (int i = 0; i < group; i++) {
                if (nextNode == null) {
                    break;
                }
                nextNode = nextNode.next;
                currLastPrev = currLastPrev.next;
            }
            if (nextNode == null) {
                break;
            }
            ListNode nextLast = nextNode;
            ListNode nextLastPrev = currLastPrev;
            for (int i = 0; i < group; i++) {
                if (nextLast == null) {
                    break;
                }
                nextLast = nextLast.next;
                nextLastPrev = nextLastPrev.next;
            }
            ListNode prevLast = nextNode;
            while (true) {
                if (nextNode.val >= currNode.val) {
                    currPrev.next = currNode;
                    currNode = currNode.next;
                }else {
                    currPrev.next = nextNode;
                    nextNode = nextNode.next;
                }
                currPrev = currPrev.next;
                if (currNode == prevLast) {
                    currPrev.next = nextNode;
                    currPrev = nextLastPrev;
                    break;
                } else if (nextNode == nextLast) {
                    currPrev.next = currNode;
                    currLastPrev.next = nextLast;
                    currPrev = currLastPrev;
                    break;
                }
            }
            currNode = nextLast;
            nextNode = nextLast;
            currLastPrev = currPrev;
        }
        if (currNode == head) {
            return;
        }
        sort(prev.next, group * 2, prev);
    }
}
