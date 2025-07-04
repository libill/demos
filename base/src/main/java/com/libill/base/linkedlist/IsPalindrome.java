package com.libill.base.linkedlist;

/**
 * 回文链表
 * https://leetcode.cn/problems/palindrome-linked-list/
 */
public class IsPalindrome {

    public boolean isPalindrome(ListNode head) {
        // 1.快慢指针查找中间节点，拆分list1、list2
        ListNode middle = middleNode(head);
        ListNode list1 = head;
        ListNode list2 = middle.next;

        // 2.链表反转 list2
        list2 = reverseList(list2);

        // 3.检测list1、list2是否相等
        boolean isSame = checkSame(list1, list2);

        // 4.恢复链表
        reverseList(list2);

        return isSame;
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
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public boolean checkSame(ListNode list1, ListNode list2) {
        while (list2 != null) {
            if (list1.val != list2.val) {
                return false;
            }
            list1 = list1.next;
            list2 = list2.next;
        }
        return true;
    }
}
