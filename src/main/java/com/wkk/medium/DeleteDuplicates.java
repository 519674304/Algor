package com.wkk.medium;

import com.wkk.util.CommonUtil;
import com.wkk.util.ListNode;
import org.junit.Test;

public class DeleteDuplicates {
    @Test
    public void test7(){
        ListNode head = CommonUtil.arrayToList(new int[]{1, 2, 3, 3, 4, 4, 5});
        CommonUtil.printList(deleteDuplicates(head));

    }
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode prevH = new ListNode(0, head), prev = prevH, post = prev.next;
        while (true) {
            if (post.next == null) {
                if (prev.next != post) {
                    prev.next = null;
                }
                break;
            }
            if (prev.next.val == post.next.val) {
                post = post.next;
            }else if (prev.next != post){
                prev.next = post.next;
            }else {
                prev = prev.next;
            }
        }
        return prevH.next;
    }
}
