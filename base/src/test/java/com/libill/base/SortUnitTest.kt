package com.libill.base

import com.libill.base.sort.BubbleSort
import com.libill.base.sort.InsertionSort
import com.libill.base.sort.SelectionSort
import com.libill.base.sort.SortUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class SortUnitTest {

    @Test
    fun testBubbleSort() {
        val b = intArrayOf(10,2,4,3,5,8,7,6).toTypedArray()
        BubbleSort().bubbleSort(b)
        assertEquals(true, SortUtils.isSorted(b))
    }

    @Test
    fun testSelection() {
        val b = intArrayOf(10,2,4,3,5,8,7,6).toTypedArray()
        SelectionSort().selectionSort(b)
        assertEquals(true, SortUtils.isSorted(b))
    }

    @Test
    fun testInsertionSort() {
        val b = intArrayOf(10,2,4,3,5,8,7,6).toTypedArray()
        InsertionSort().insertionSort(b)
        assertEquals(true, SortUtils.isSorted(b))
    }


}