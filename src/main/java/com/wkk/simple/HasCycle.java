package com.wkk.simple;

import com.wkk.util.ListNode;

import java.util.Objects;

public class HasCycle {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while(true){
            if (fast == null || slow == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next;
            if(fast == null){
                return false;
            }
            fast = fast.next;
            if (Objects.equals(slow, fast)){
                return true;
            }
        }
    }
}
