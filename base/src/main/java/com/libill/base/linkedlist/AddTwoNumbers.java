package com.libill.base.linkedlist;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry > 0) {
            int a = l1 != null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;
            int sum = a + b + carry;
            carry = sum >= 10 ? 1 : 0;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return dummy.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0, null);
        ListNode dummy = head;
        int plus = 0;
        while (l1 != null || l2 != null) {
            if (l1 != null && l1.next != null || l2 != null && l2.next != null) {
                dummy.next = new ListNode(0, null);
            }
            if (l1 != null && l2 != null) {
                int temp = l1.val + l2.val + plus;
                plus = temp / 10;
                temp = temp % 10;
                dummy.val = temp;
                l1 = l1.next;
                l2 = l2.next;
            } else if (l1 != null) {
                int temp = l1.val + plus;
                plus = temp / 10;
                temp = temp % 10;
                dummy.val = temp;
                l1 = l1.next;
            } else if (l2 != null) {
                int temp = l2.val + plus;
                plus = temp / 10;
                temp = temp % 10;
                dummy.val = temp;
                l2 = l2.next;
            }
            if (dummy.next != null) {
                dummy = dummy.next;
            } else if (plus > 0) {
                dummy.next = new ListNode(plus);
            }
        }

        return head;
    }
}
