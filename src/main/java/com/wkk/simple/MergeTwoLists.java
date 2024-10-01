package com.wkk.simple;

import com.wkk.util.CommonUtil;
import com.wkk.util.ListNode;
import org.junit.Test;

public class MergeTwoLists {
    @Test
    public void test7(){
        ListNode list1 = CommonUtil.arrayToList(new int[]{1, 2, 4});
        ListNode list2 = CommonUtil.arrayToList(new int[]{5});
        ListNode listNode = mergeTwoLists(list1, list2);
        CommonUtil.printList(listNode);
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode lo1 = list1, lo2 = list2, prev = new ListNode(), headPrev = prev;
        while (lo1 != null && lo2 != null) {
            if (lo1.val > lo2.val) {
                prev.next = lo2;
                lo2 = lo2.next;
            }else {
                prev.next = lo1;
                lo1 = lo1.next;
            }
            prev = prev.next;
        }
        if (lo1 != null) {
            prev.next = lo1;
        }
        if (lo2 != null) {
            prev.next = lo2;
        }
        return headPrev.next;
    }
}
