package com.example.kotlinDSA.kotlinInterviewPrograms6

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.junit.Test

class KotlinInterviewProgramsTest {

    @Test
    fun testFactorial() {
        assertEquals(120, factorial(5))
        assertEquals(1, factorial(0))
    }

    @Test
    fun testFibonacci() {
        assertEquals(listOf(0, 1, 1, 2, 3), fibonacci(5))
    }

    @Test
    fun testIsPrime() {
        assertEquals(true, isPrime(7))
        assertEquals(false, isPrime(10))
    }

    @Test
    fun testArmstrong() {
        assertEquals(true, isArmstrong(153))
        assertEquals(false, isArmstrong(123))
    }

    @Test
    fun testPalindromeNumber() {
        assertEquals(true, isPalindromeNumber(121))
        assertEquals(false, isPalindromeNumber(123))
    }

    @Test
    fun testFindMissingNumber() {
        assertEquals(4, findMissingNumber(intArrayOf(1, 2, 3, 5)))
    }

    @Test
    fun testSecondLargest() {
        assertEquals(5, secondLargest(intArrayOf(1, 5, 2, 9, 5, 6)))
    }

    @Test
    fun testCountWords() {
        assertEquals(3, countWords("Hello Kotlin World"))
    }

    @Test
    fun testCharFrequency() {
        val result = charFrequency("hello")
        assertEquals(mapOf('h' to 1, 'e' to 1, 'l' to 2, 'o' to 1), result)
    }

    @Test
    fun testReverseWords() {
        assertEquals("World Kotlin Hello", reverseWords("Hello Kotlin World"))
    }
}

/*
* 1. FizzBuzz (Classic Warmup)*/

fun fizzBuzz(n: Int) {
    for (i in 1..n) {
        when {
            i % 15 == 0 -> println("FizzBuzz")
            i % 3 == 0 -> println("Fizz")
            i % 5 == 0 -> println("Buzz")
            else -> println(i)
        }
    }
}

/*
* 2. Factorial (Recursion & Iterative)*/

fun factorial(n: Int): Int =
    if (n <= 1) 1 else n * factorial(n - 1)

fun factorialIterative(n: Int): Int {
    var result = 1
    for (i in 2..n) result *= i
    return result
}

/*
* 3. Fibonacci (Sequence with Sequence)*/

fun fibonacci(n: Int): List<Int> {
    return generateSequence(Pair(0, 1)) { Pair(it.second, it.first + it.second) }
        .map { it.first }
        .take(n)
        .toList()
}

/*
* 4. Check Prime Number*/

fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return false
    }
    return true
}

/*
* 5. Armstrong Number (153 → 1³+5³+3³)*/

fun isArmstrong(num: Int): Boolean {
    val digits = num.toString().map { it.toString().toInt() }
    val sum = digits.sumOf { Math.pow(it.toDouble(), digits.size.toDouble()).toInt() }
    return sum == num
}

/*
* 6. Palindrome Number*/

fun isPalindromeNumber(num: Int): Boolean {
    val s = num.toString()
    return s == s.reversed()
}

/*
* 7. Find Missing Number in Array*/

fun findMissingNumber(nums: IntArray): Int {
    val n = nums.size + 1
    val total = n * (n + 1) / 2
    return total - nums.sum()
}

/*
* 8. Second Largest Number*/

fun secondLargest(nums: IntArray): Int {
    var first = Int.MIN_VALUE
    var second = Int.MIN_VALUE
    for (n in nums) {
        if (n > first) {
            second = first
            first = n
        } else if (n > second && n != first) {
            second = n
        }
    }
    return second
}

/*
* 9. Count Words in a String*/

fun countWords(str: String): Int = str.trim().split("\\s+".toRegex()).size

/*
* 10. Count Character Occurrences*/

fun charFrequency(str: String): Map<Char, Int> =
    str.groupingBy { it }.eachCount()

/*
* 11. Reverse Words in a Sentence*/

fun reverseWords(sentence: String): String =
    sentence.split(" ").reversed().joinToString(" ")

/*
* 12. Kotlin Coroutines – Async Example
* */

suspend fun main() = coroutineScope {
    val task1 = async { delay(1000); "Task1 Done" }
    val task2 = async { delay(500); "Task2 Done" }
    println(task1.await())
    println(task2.await())
}

/*
* 13. Singleton in Kotlin*/

object DatabaseConnection {
    init {
        println("Database initialized")
    }
    fun query(sql: String) = println("Executing: $sql")
}

/*
* 14. Data Class Example*/

data class User(val id: Int, val name: String)

fun main2() {
    val u1 = User(1, "Firoj")
    val u2 = u1.copy(name = "Mohammad")
    println(u1)
    println(u2)
}

/*
* 15. Sealed Class Example*/

sealed class ApiResponse
data class Success(val data: String) : ApiResponse()
data class Error(val message: String) : ApiResponse()
object Loading : ApiResponse()
