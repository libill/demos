package com.libill.base.linkedlist;

public class ReorderList {
    public ListNode reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 1.寻找中间节点，拆分list1、list2
        ListNode middle = middleNode(head);
        ListNode list1 = head;
        ListNode list2 = middle.next;
        middle.next = null;

        // 2.链表反转list2
        list2 = reverseList(list2);

        // 3.合并list1、list2
        ListNode newHead = mergeTwoList(list1, list2);
        return newHead;
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode mergeTwoList(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1, list1);
        ListNode list1Temp = null;
        ListNode list2Temp = null;
        while (list1 != null && list2 != null) {
            list1Temp = list1.next;
            list2Temp = list2.next;

            list1.next = list2;
            list1 = list1Temp;

            list2.next = list1;
            list2 = list2Temp;
        }
        return dummy.next;
    }
}
