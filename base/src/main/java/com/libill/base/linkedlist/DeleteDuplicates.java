package com.libill.base.linkedlist;

/**
 * 删除排序链表的重复元素
 * 1 -> 3 -> 5 -> 5 ->6
 * 1 -> 3 -> 5 -> 6
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list/description/
 */
public class DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
