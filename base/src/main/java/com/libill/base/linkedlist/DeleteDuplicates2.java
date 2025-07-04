package com.libill.base.linkedlist;

public class DeleteDuplicates2 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while(cur.next != null && cur.next.next != null){
            if (cur.next.val == cur.next.next.val){
                // 先找到重复值 x
                int x = cur.next.val;
                // 开循环删
                while(cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                // 不重复直接移到下一个元素继续检测
                cur = cur.next;
            }
        }
        return head;
    }
}
