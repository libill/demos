package com.libill.base

import com.libill.base.String.AddBinary
import com.libill.base.String.BreakPalindrome
import com.libill.base.String.CountSubstrings
import com.libill.base.String.ExcelRowName
import com.libill.base.String.LengthOfLongestSubstring
import com.libill.base.String.LongestCommonPrefix
import com.libill.base.String.LongestPalindrome
import com.libill.base.String.StringIsPalindrome
import org.junit.Assert.assertEquals
import org.junit.Test

class StringUnitTest {

    @Test
    fun testLongestPalindrome() {
        val a = "babad"
        val target = "bab"
        val result = LongestPalindrome().longestPalindrome(a)
        assertEquals(target, result)
    }

    @Test
    fun testLengthOfLongestSubstring() {
        val a = "pwwkew"
        val target = 3
        val result = LengthOfLongestSubstring().lengthOfLongestSubstring(a)
        assertEquals(target, result)
    }

    @Test
    fun testLongestCommonPrefix() {
        val a = arrayOf("flower","flow","flight")
        val target = "fl"
        val result = LongestCommonPrefix().longestCommonPrefix(a)
        assertEquals(target, result)
    }

    @Test
    fun testAddBinary() {
        val target = "101"
        val result = AddBinary().addBinary("11", "1")
        assertEquals(target, result)
    }

    @Test
    fun testStringIsPalindrome() {
        val target = false
        val result = StringIsPalindrome().isPalindrome("0P")
        assertEquals(target, result)
    }

    @Test
    fun testExcelRowName() {
        val target = "FXSHRXW"
        val result = ExcelRowName().convertToTitle(2147483647)
        assertEquals(target, result)
    }

    @Test
    fun testCountSubstrings() {
        val target = 6
        val result = CountSubstrings().countSubstrings("aaa")
        assertEquals(target, result)
    }

    @Test
    fun testBreakPalindrome() {
        val target = "aaccba"
        val result = BreakPalindrome().breakPalindrome("abccba")
        assertEquals(target, result)
    }
}