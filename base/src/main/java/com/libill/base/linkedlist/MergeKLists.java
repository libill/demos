package com.libill.base.linkedlist;

public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = null;
        for (int i = 0; i < lists.length; i++) {
            dummy = mergeTwoLists(dummy, lists[i]);
        }
        return dummy;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                pre.next = list1;
                list1 = list1.next;
            } else {
                pre.next = list2;
                list2 = list2.next;
            }
            pre = pre.next;
        }
        pre.next = list1 != null ? list1 : list2;
        return dummy.next;
    }
}
