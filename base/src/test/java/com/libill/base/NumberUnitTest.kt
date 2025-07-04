package com.libill.base

import com.libill.base.number.Divide
import com.libill.base.number.FindMedianSortedArrays
import com.libill.base.number.IntToRoman
import com.libill.base.number.IntegerPalindrome
import com.libill.base.number.IntegerReverse
import com.libill.base.number.MergeTwoArray
import com.libill.base.number.MoveZeroes
import com.libill.base.number.Multiply
import com.libill.base.number.RomanToInt
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class NumberUnitTest {

    @Test
    fun testIntegerReverse() {
        val a = -123
        val result = IntegerReverse().reverse(a)
        assertEquals(-321, result)
    }

    @Test
    fun testIntegerPalindrome() {
        val a = 12321
        val result = IntegerPalindrome().isPalindrome(a)
        assertEquals(true, result)
    }

    @Test
    fun testFindMedianSortedArrays() {
        val a1 = arrayOf(1, 2, 3, 4, 5, 6, 7, 8).toIntArray()
        val a2 = arrayOf(1, 2, 3, 4, 9, 10, 11, 12).toIntArray()
        val result = FindMedianSortedArrays().findMedianSortedArrays(a1, a2)
        val s = result == 4.5
        assertEquals(true, s)
    }

    @Test
    fun testRomanToInt() {
        val result = RomanToInt().romanToInt("XIV")
        val s = result == 14
        assertEquals(true, s)
    }

    @Test
    fun testIntToRoman() {
        val result = IntToRoman().intToRoman(3749)
        assertEquals(result, "MMMDCCXLIX")
    }

    @Test
    fun testMultiply() {
        val result = Multiply().multiply("123", "456")
        assertEquals(result, "56088")
    }

    @Test
    fun testDivide() {
        val result = Divide().divide(-2147483648, 1)
        assertEquals(-2147483648, result)
    }

    @Test
    fun testMoveZeroes() {
        val a = intArrayOf(0, 1, 0, 3, 12);
        val target = intArrayOf(1, 3, 12, 0, 0);
        val result = MoveZeroes().moveZeroes(a)
        assertArrayEquals(target, a)
    }

    @Test
    fun testMergeTwoArray() {
        val a = intArrayOf(4,5,6,0,0,0);
        val b = intArrayOf(1,2,3);
        val target = intArrayOf(1, 2, 3, 4, 5, 6);
        val result = MergeTwoArray().merge(a, 6, b, 3)
        assertArrayEquals(target, a)
    }
}