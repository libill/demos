package com.libill.base.linkedlist;

import java.util.HashMap;

/**
 * 随机链表的复制
 * https://leetcode.cn/problems/copy-list-with-random-pointer/description/
 */
public class CopyRandomList {

    // 1、回溯 + 哈希表
    HashMap<ListNode, ListNode> cache = new HashMap<>();

    public ListNode copyRandomList(ListNode head) {
        if (head == null) {
            return head;
        }
        if (!cache.containsKey(head)) {
            ListNode node = new ListNode(head.val);
            cache.put(head, node);
            node.next = copyRandomList(head);
            node.random = copyRandomList(head);
        }
        return cache.get(head);
    }

    // 2、复制拆分法
    public ListNode copyRandomList1(ListNode head) {
        if (head == null) {
            return head;
        }
        // 1、在下一个位置复制 A->A1->B->B1
        ListNode cur = head;
        while (cur != null) {
            ListNode newNode = new ListNode(cur.val);
            newNode.next = cur.next;
            cur.next = newNode;
            if (cur.next != null) {
                cur = cur.next.next;
            } else {
                cur = null;
            }
        }
        // 2.复制的点复制random
        cur = head;
        while (cur != null) {
            ListNode newNode = cur.next;
            if (cur.random != null) {
                newNode.random = cur.random.next;
            } else {
                newNode.random = null;
            }
            ListNode next = cur.next.next;
            cur = next;
        }
        // 3.拆分两条链表
        ListNode newHead = head.next;
        cur = head;
        while (cur != null) {
            ListNode newNode = cur.next;
            cur.next = cur.next.next;
            if (newNode.next != null) {
                newNode.next = newNode.next.next;
            } else {
                newNode.next = null;
            }
            cur = cur.next;
        }
        return newHead;
    }

}
