package com.libill.base.linkedlist;

import java.util.HashSet;

/**
 * 是否环形链表
 * https://leetcode.cn/problems/linked-list-cycle/solutions/440042/huan-xing-lian-biao-by-leetcode-solution/
 */
public class HasCycle {
    public boolean hasCycle(ListNode head) {
        HashSet cache = new HashSet<ListNode>();
        while (head != null) {
            if (cache.contains(head)) {
                return true;
            }
            cache.add(head);
            head = head.next;
        }
        return false;
    }

    public boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
