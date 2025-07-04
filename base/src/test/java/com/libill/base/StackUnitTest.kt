package com.libill.base

import com.libill.base.stack.EvalRPN
import com.libill.base.stack.MinStack
import com.libill.base.stack.SortedStack
import com.libill.base.stack.ValidBracket
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Stack unit test
 * 「力扣」第 42、739、496、316、901、402、581 题。
 * See [testing Stack](https://github.com/chienmy/algorithm-pattern-java/blob/master/data_structure/stack_queue.md).
 */
class StackUnitTest {

    @Test
    fun testEvalRPN() {
        val tokens = arrayOf("10","6","9","3","+","-11","*","/","*","17","+","5","+")
        assertEquals(22, EvalRPN().evalRPN(tokens))
    }

    @Test
    fun testValidBracket() {
        val tokens = "()[]{}"
        assertEquals(true, ValidBracket().isValid(tokens))
    }

    @Test
    fun testMinStack() {
        val minStack = MinStack()
        minStack.push(-2)
        minStack.push(2)
        minStack.push(4)
        minStack.push(-1)
        assertEquals(-2, minStack.min)
    }

    @Test
    fun testSortedStack() {
        val sortedStack = SortedStack()
        sortedStack.peek()
        sortedStack.peek()
        sortedStack.pop()
        sortedStack.isEmpty()
        sortedStack.peek()
        sortedStack.push(40)
        sortedStack.pop()
        sortedStack.push(19)
        sortedStack.pop()
        sortedStack.push(44)
        sortedStack.peek()
        sortedStack.push(8)
        sortedStack.pop()
        sortedStack.push(7)
        sortedStack.push(1)
        sortedStack.pop()
        sortedStack.push(63)
        assertEquals(7, sortedStack.peek())
    }
}