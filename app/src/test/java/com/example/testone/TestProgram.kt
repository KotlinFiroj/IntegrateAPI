package com.example.testone

import org.junit.Test

class TestProgram {

    /*
     * 1. Write a program Reverse the string
     * 2. Write a program to Reverse the array
     * 3. Write a program sort the array
     * 4. Write a program find duplicate
     * 5. Write a program add two item count is 5.
     *
     *
     *
     *
     * */

    @Test
    fun `Write a program Reverse the string`() {
        val givenStr = "Sample"
        val reverseStr = givenStr.reverse()
        println("Given String is ::$givenStr and reverse string is :: $reverseStr")
    }

    fun String.reverse(): String {
        val stringBuilder = StringBuilder()
        val strLength = this.length
        for (i in strLength - 1 downTo 0) {
            stringBuilder.append(this[i])
        }
        return stringBuilder.toString()
    }

    @Test
    fun `Write a program to Reverse the array`() {
        val list = mutableListOf<Int>(1, 2, 3, 4, 5)
        val sortList = list.customList()
        println("Sorted Array is :: $sortList")
    }

    fun MutableList<Int>.customList(): MutableList<Int> {
        var start = 0
        var end = this.size - 1
        var temp: Int
        while (start < end) {
            temp = this[start]
            this[start] = this[end]
            this[end] = temp
            start++
            end--
        }
        return this
    }

    @Test
    fun `Write a program sort the array`() {
        val list = mutableListOf(1, 2, 7, 8, 9, 3, 4, 5)
        val sortList = list.sortList()
        println("Sorted list is :$sortList")
    }

    fun MutableList<Int>.sortList(): MutableList<Int> {
        // val sortList = mutableListOf<Int>()
        var temp = 0
        for (i in 0 until this.size - 1) {
            for (j in 0 until this.size - 1 - i) {
                if (this[j] > this[j + 1]) {
                    temp = this[j]
                    this[j] = this[j + 1]
                    this[j + 1] = temp
                }
            }
        }
        return this
    }

    @Test
    fun `Write a program find duplicate`() {
        val list = mutableListOf<Int>(1, 3, 5, 7, 9, 1, 2, 3, 4, 5)
        val duplicateList = list.duplicateList()
        println("duplicate List : $duplicateList")
    }

    fun MutableList<Int>.duplicateList(): MutableSet<Int> {
        val seenSet = mutableSetOf<Int>()
        val duplicateSet = mutableSetOf<Int>()

        for (item in this) {
            if (!seenSet.add(item)) {
                duplicateSet.add(item)
            }
        }
        return duplicateSet
    }

    @Test
    fun `Write a program add two item count is 5`() {
        val list = listOf<Int>(1, 2, 3, 4, 5, 6, 0)
        val mapCountFive = list.countFiveMap()
        println("Map Count Five$mapCountFive")
    }

    fun List<Int>.countFiveMap(): MutableMap<Int, Int> {
        val map = mutableMapOf<Int, Int>()

        for (i in 0 until this.size - 1) {
            for (j in i until this.size - 1) {
                if (this[i] + this[j] == 5) {
                    map.put(this[i], this[j])
                }
            }
        }
        return map
    }
}
