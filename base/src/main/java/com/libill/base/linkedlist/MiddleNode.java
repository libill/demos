package com.libill.base.linkedlist;

public class MiddleNode {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = slow.next.next;
        }
        return slow;
    }
}
