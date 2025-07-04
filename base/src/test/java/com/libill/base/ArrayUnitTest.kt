package com.libill.base

import com.libill.base.array.ThreeSum
import com.libill.base.array.ThreeSumClosest
import com.libill.base.array.TwoSum
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

class ArrayUnitTest {
    @Test
    fun testTwoSum() {
        val a = intArrayOf(2, 7, 11, 15)
        val target = arrayListOf<Int>(0, 1).toTypedArray()
        val result = TwoSum().twoSum(a, 9).toTypedArray()
        assertArrayEquals(target, result)
    }

    @Test
    fun testThreeSum() {
        val a = intArrayOf(-1, 0, 1, 2, -1, -4)
        val t1 = arrayListOf<Int>(-1, -1, 2)
        val t2 = arrayListOf<Int>(-1, 0, 1)
        val target = arrayListOf<List<Int>>(t1, t2)
        val result = ThreeSum().threeSum(a)
        assertEquals(target, result)
    }

    @Test
    fun testThreeSumClosest() {
        val a = intArrayOf(1,1,1,1)
        val result = ThreeSumClosest().threeSumClosest(a, 4)
        assertEquals(3, result)
    }
}
