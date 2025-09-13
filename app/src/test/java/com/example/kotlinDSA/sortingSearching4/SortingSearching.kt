package com.example.kotlinDSA.sortingSearching4

import org.junit.Assert.assertEquals
import org.junit.Test

class SortingSearchingTest {

    @Test
    fun testBinarySearch() {
        val nums = intArrayOf(1, 3, 5, 7, 9, 11)
        assertEquals(2, binarySearch(nums, 5))
        assertEquals(-1, binarySearch(nums, 6))
    }

    @Test
    fun testSearchRotatedArray() {
        val nums = intArrayOf(4, 5, 6, 7, 0, 1, 2)
        assertEquals(4, search(nums, 0))
        assertEquals(-1, search(nums, 3))
    }

    @Test
    fun testMergeSort() {
        val arr = intArrayOf(5, 2, 9, 1, 5, 6)
        val sorted = mergeSort(arr)
        assertEquals(listOf(1, 2, 5, 5, 6, 9), sorted.toList())
    }
}

/*
* 13. Binary Search */

fun binarySearch(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.lastIndex
    while (left <= right) {
        val mid = (left + right) / 2
        when {
            nums[mid] == target -> return mid
            nums[mid] < target -> left = mid + 1
            else -> right = mid - 1
        }
    }
    return -1
}

/*
* 14. Search in Rotated Sorted Array    */

fun search(nums: IntArray, target: Int): Int {
    var l = 0
    var r = nums.lastIndex
    while (l <= r) {
        val mid = (l + r) / 2
        if (nums[mid] == target) return mid
        if (nums[l] <= nums[mid]) {
            if (target >= nums[l] && target < nums[mid]) r = mid - 1 else l = mid + 1
        } else {
            if (target > nums[mid] && target <= nums[r]) l = mid + 1 else r = mid - 1
        }
    }
    return -1
}

/*
* 15. Merge Sort    */
fun mergeSort(arr: IntArray): IntArray {
    if (arr.size <= 1) return arr
    val mid = arr.size / 2
    val left = mergeSort(arr.sliceArray(0 until mid))
    val right = mergeSort(arr.sliceArray(mid until arr.size))
    return merge(left, right)
}

fun merge(left: IntArray, right: IntArray): IntArray {
    val result = mutableListOf<Int>()
    var i = 0; var j = 0
    while (i < left.size && j < right.size) {
        if (left[i] <= right[j]) result.add(left[i++]) else result.add(right[j++])
    }
    while (i < left.size) result.add(left[i++])
    while (j < right.size) result.add(right[j++])
    return result.toIntArray()
}
