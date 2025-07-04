package com.libill.base

import com.libill.base.linkedlist.AddTwoNumbers
import com.libill.base.linkedlist.DeleteDuplicates
import com.libill.base.linkedlist.DeleteDuplicates2
import com.libill.base.linkedlist.HasCycle
import com.libill.base.linkedlist.IsPalindrome
import com.libill.base.linkedlist.ListNode
import com.libill.base.linkedlist.MergeKLists
import com.libill.base.linkedlist.MergeTwoLists
import com.libill.base.linkedlist.MiddleNode
import com.libill.base.linkedlist.RemoveNthFromEnd
import com.libill.base.linkedlist.ReorderList
import com.libill.base.linkedlist.ReverseBetween
import com.libill.base.linkedlist.ReverseList
import org.junit.Test

import org.junit.Assert.*

/**
 * Linked list unit test
 *
 * See [testing linked_list](https://github.com/chienmy/algorithm-pattern-java/blob/master/data_structure/linked_list.md).
 */
class LinkedListUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testDeleteDuplicates() {
        val head = ListNode.buildListNode(1, 3, 5, 5, 6)
        ListNode.printListNode(head)
        val targetHead = ListNode.buildListNode(1, 3, 5, 6)
        DeleteDuplicates().deleteDuplicates(head)
        val actual = ListNode.isSameListNode(head, targetHead)

        assertEquals(true, actual)
    }

    @Test
    fun testDeleteDuplicates2() {
        val head = ListNode.buildListNode(1, 3, 5, 5, 6)
        ListNode.printListNode(head)
        val targetHead = ListNode.buildListNode(1, 3, 6)
        DeleteDuplicates2().deleteDuplicates(head)
        val actual = ListNode.isSameListNode(head, targetHead)

        assertEquals(true, actual)
    }

    @Test
    fun testReverseList() {
        var head = ListNode.buildListNode(1, 3, 5, 6, 9, 11)
        ListNode.printListNode(head)
        val targetHead = ListNode.buildListNode(11, 9, 6, 5, 3, 1)
        head = ReverseList().reverseList(head)
        val actual = ListNode.isSameListNode(head, targetHead)

        assertEquals(true, actual)
    }

    @Test
    fun testReverseBetween() {
        var head = ListNode.buildListNode(1, 3, 5, 6, 9, 11, 12, 13)
        ListNode.printListNode(head)
        val targetHead = ListNode.buildListNode(1, 3, 11, 9, 6, 5, 12, 13)
        head = ReverseBetween().reverseBetween(head, 3, 6)
        val actual = ListNode.isSameListNode(head, targetHead)

        assertEquals(true, actual)
    }

    @Test
    fun testMergeTwoList() {
        val head1 = ListNode.buildListNode(1, 3, 5, 6, 9, 11)
        val head2 = ListNode.buildListNode(1, 3, 5, 6, 9, 11)
        ListNode.printListNode(head1)
        ListNode.printListNode(head2)
        val targetHead = ListNode.buildListNode(1, 1, 3, 3, 5, 5, 6, 6, 9, 9, 11, 11)
        val head = MergeTwoLists().mergeTwoLists(head1, head2)
        val actual = ListNode.isSameListNode(head, targetHead)

        assertEquals(true, actual)
    }

    @Test
    fun testMergeKList() {
        val head1 = ListNode.buildListNode(1, 3, 5, 6, 9, 11)
        val head2 = ListNode.buildListNode(1, 3, 5, 6, 9, 11)
        val head3 = ListNode.buildListNode(1, 3, 5, 6, 9, 11)
        ListNode.printListNode(head1)
        ListNode.printListNode(head2)
        ListNode.printListNode(head3)
        val listNodes = arrayListOf<ListNode>()
        listNodes.add(head1)
        listNodes.add(head2)
        listNodes.add(head3)
        val targetHead =
            ListNode.buildListNode(1, 1, 1, 3, 3, 3, 5, 5, 5, 6, 6, 6, 9, 9, 9, 11, 11, 11)
        val head = MergeKLists().mergeKLists(listNodes.toTypedArray())
        val actual = ListNode.isSameListNode(head, targetHead)

        assertEquals(true, actual)
    }

    @Test
    fun testMiddleNode() {
        val head = ListNode.buildListNode(1, 3, 5, 6, 7, 8)
        ListNode.printListNode(head)
        val middle = MiddleNode().middleNode(head)
        val actual = middle.`val` == 6

        assertEquals(true, actual)
    }

    @Test
    fun testRecorderList() {
        var head = ListNode.buildListNode(1, 2, 3, 4, 5)
        ListNode.printListNode(head)
        head = ReorderList().reorderList(head)
        val targetHead = ListNode.buildListNode(1, 5, 2, 4, 3)
        val actual = ListNode.isSameListNode(head, targetHead)
        assertEquals(true, actual)
    }

    @Test
    fun testRecorderList1() {
        var head = ListNode.buildListNode(1, 2, 3, 4, 5, 6)
        ListNode.printListNode(head)
        head = ReorderList().reorderList(head)
        val targetHead = ListNode.buildListNode(1, 6, 2, 5, 3, 4)
        val actual = ListNode.isSameListNode(head, targetHead)
        assertEquals(true, actual)
    }

    @Test
    fun testIsPalindrome() {
        var head = ListNode.buildListNode(1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1)
        ListNode.printListNode(head)
        val actual = IsPalindrome().isPalindrome(head)
        assertEquals(true, actual)
    }

    @Test
    fun testHasCycle() {
        var head = ListNode.buildCycleListNode(1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1)
        // ListNode.printListNode(head)
        val actual = HasCycle().hasCycle(head)
        assertEquals(true, actual)
    }

    @Test
    fun testRemoveNthFromEnd() {
        var head = ListNode.buildListNode(1, 2, 3, 4, 5, 6)
        ListNode.printListNode(head)
        head = RemoveNthFromEnd().removeNthFromEnd(head, 2)
        val targetHead = ListNode.buildListNode(1, 2, 3, 4, 6)
        val actual = ListNode.isSameListNode(head, targetHead)
        assertEquals(true, actual)
    }

    @Test
    fun testAddTwoNumbers() {
        val head1 = ListNode.buildListNode(2,4,3)
        val head2 = ListNode.buildListNode(5,6,4)
        ListNode.printListNode(head1)
        ListNode.printListNode(head2)
        val targetHead = ListNode.buildListNode(7,0,8)
        val head = AddTwoNumbers().addTwoNumbers(head1, head2)
        val actual = ListNode.isSameListNode(head, targetHead)

        assertEquals(true, actual)
    }
}