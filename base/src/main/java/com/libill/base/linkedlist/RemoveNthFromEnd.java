package com.libill.base.linkedlist;

/**
 * 删除链表的倒数第 n 个结点
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        ListNode dummy = new ListNode(-1, head);
        ListNode second = dummy;
        // 1.找到第 n 个节点
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        // 2.同时走下一步，找到倒数n的节点的前一个节点
        while (first != null){
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}
