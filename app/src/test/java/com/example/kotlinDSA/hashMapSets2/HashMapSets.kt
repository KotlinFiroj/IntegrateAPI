package com.example.kotlinDSA.hashMapSets2

/*
* 6. First Non-Repeating Character
* */

fun firstUniqueChar(s: String): Int {
    val count = IntArray(26)
    for (c in s) count[c - 'a']++
    for (i in s.indices) if (count[s[i] - 'a'] == 1) return i
    return -1
}

/*
*7. Find Duplicates in Array
* */

fun findDuplicates(nums: IntArray): Set<Int> {
    val seen = mutableSetOf<Int>()
    val duplicates = mutableSetOf<Int>()
    for (n in nums) if (!seen.add(n)) duplicates.add(n)
    return duplicates
}

/*
* 8. Group Anagrams
*/

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val map = mutableMapOf<String, MutableList<String>>()
    for (s in strs) {
        val key = s.toCharArray().sorted().joinToString("")
        map.computeIfAbsent(key) { mutableListOf() }.add(s)
    }
    return map.values.toList()
}
