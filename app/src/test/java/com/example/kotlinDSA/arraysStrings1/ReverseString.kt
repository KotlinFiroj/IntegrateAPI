package com.example.kotlinDSA.arraysStrings1

/*
* 1. Reverse a String
* */
fun reverseString(s: String): String = s.reversed()

/*
* 2. Check Palindrome
* */
fun isPalindrome(s: String): Boolean = s == s.reversed()

/*
* 3. Two Sum (Return indices)
* */
fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    for ((i, num) in nums.withIndex()) {
        val diff = target - num
        if (map.containsKey(diff)) return intArrayOf(map[diff]!!, i)
        map[num] = i
    }
    return intArrayOf()
}

/*
* 4. Kadane’s Algorithm (Max Subarray Sum)
* */

fun maxSubArray(nums: IntArray): Int {
    var maxSoFar = nums[0]
    var current = nums[0]
    for (i in 1 until nums.size) {
        current = maxOf(nums[i], current + nums[i])
        maxSoFar = maxOf(maxSoFar, current)
    }
    return maxSoFar
}

/*
* 5. Longest Substring Without Repeating Characters
* */

fun lengthOfLongestSubstring(s: String): Int {
    val set = mutableSetOf<Char>()
    var left = 0
    var maxLen = 0
    for (right in s.indices) {
        while (set.contains(s[right])) {
            set.remove(s[left++])
        }
        set.add(s[right])
        maxLen = maxOf(maxLen, right - left + 1)
    }
    return maxLen
}

/*
* 3. First Non-Repeating Character*/

fun firstUniqueChar(s: String): Int {
    val count = IntArray(26)
    for (c in s) count[c - 'a']++
    for (i in s.indices) if (count[s[i] - 'a'] == 1) return i
    return -1
}

/*
* 5. Anagram Check*/
fun isAnagram(s: String, t: String): Boolean =
    s.toCharArray().sorted() == t.toCharArray().sorted()

/*
* 6. Group Anagrams*/

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val map = mutableMapOf<String, MutableList<String>>()
    for (s in strs) {
        val key = s.toCharArray().sorted().joinToString("")
        map.computeIfAbsent(key) { mutableListOf() }.add(s)
    }
    return map.values.toList()
}

/*
* 7. Longest Palindromic Substring*/

fun longestPalindrome(s: String): String {
    var start = 0; var end = 0
    for (i in s.indices) {
        val len1 = expandAroundCenter(s, i, i)
        val len2 = expandAroundCenter(s, i, i + 1)
        val len = maxOf(len1, len2)
        if (len > end - start) {
            start = i - (len - 1) / 2
            end = i + len / 2
        }
    }
    return s.substring(start, end + 1)
}

fun expandAroundCenter(s: String, left: Int, right: Int): Int {
    var l = left; var r = right
    while (l >= 0 && r < s.length && s[l] == s[r]) {
        l--; r++
    }
    return r - l - 1
}
