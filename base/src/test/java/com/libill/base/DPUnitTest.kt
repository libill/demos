package com.libill.base

import com.libill.base.dynamicprogramming.MinPathSum
import org.junit.Assert.assertEquals
import org.junit.Test

class DPUnitTest {
    @Test
    fun testMinPathSum() {
        val a = arrayOf(intArrayOf(1,3,1), intArrayOf(1,3,1), intArrayOf(1,3,1))
        val result = MinPathSum().minPathSum(a)
        assertEquals(7, result)
    }

}