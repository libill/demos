package com.libill.base.linkedlist;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode random;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode buildListNode(int... val) {
        ListNode head = null;
        if (val == null || val.length == 0) {
            return head;
        }
        head = new ListNode(val[0]);

        ListNode cur = head;
        for (int i = 1; i < val.length; i++) {
            cur.next = new ListNode(val[i]);
            cur = cur.next;
        }
        return head;
    }

    public static boolean isSameListNode(ListNode head, ListNode targetHead) {
        printListNode(head);
        printListNode(targetHead);

        if (head == null) {
            return targetHead == null;
        }

        boolean isSame = true;
        ListNode cur = head;
        ListNode curTarget = targetHead;
        while(cur != null) {
            if (cur.val != curTarget.val) {
                isSame = false;
            }
            cur = cur.next;
            curTarget = curTarget.next;
        }
        if (cur == null && curTarget == null) {
            return isSame;
        }
        return false;
    }

    public static void printListNode(ListNode head) {
        System.out.print("LinkedList: ");
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val);
            cur = cur.next;
            if (cur != null) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public static ListNode buildCycleListNode(int... val) {
        ListNode head = null;
        if (val == null || val.length == 0) {
            return head;
        }
        head = new ListNode(val[0]);

        ListNode cur = head;
        for (int i = 1; i < val.length; i++) {
            cur.next = new ListNode(val[i]);
            cur = cur.next;
        }
        // set cycle
        cur.next = head;

        return head;
    }
}
