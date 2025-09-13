package com.example.kotlinDSA.dynamicProgramming5

/*
* 16. Fibonacci (Memoization)   */

fun fib(n: Int, memo: MutableMap<Int, Int> = mutableMapOf()): Int {
    if (n <= 1) return n
    if (n in memo) return memo[n]!!
    memo[n] = fib(n - 1, memo) + fib(n - 2, memo)
    return memo[n]!!
}

/*
* 17. Coin Change (Min Coins)*/

fun coinChange(coins: IntArray, amount: Int): Int {
    val dp = IntArray(amount + 1) { amount + 1 }
    dp[0] = 0
    for (i in 1..amount) {
        for (c in coins) {
            if (i - c >= 0) dp[i] = minOf(dp[i], dp[i - c] + 1)
        }
    }
    return if (dp[amount] > amount) -1 else dp[amount]
}

/*
* 18. Longest Common Subsequence*/

fun longestCommonSubsequence(text1: String, text2: String): Int {
    val dp = Array(text1.length + 1) { IntArray(text2.length + 1) }
    for (i in text1.indices.reversed()) {
        for (j in text2.indices.reversed()) {
            dp[i][j] = if (text1[i] == text2[j]) {
                1 + dp[i + 1][j + 1]
            } else {
                maxOf(dp[i + 1][j], dp[i][j + 1])
            }
        }
    }
    return dp[0][0]
}

/*
* 19. 0/1 Knapsack*/

fun knapsack(weights: IntArray, values: IntArray, W: Int): Int {
    val n = weights.size
    val dp = Array(n + 1) { IntArray(W + 1) }
    for (i in 1..n) {
        for (w in 0..W) {
            dp[i][w] = if (weights[i - 1] <= w) {
                maxOf(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1])
            } else {
                dp[i - 1][w]
            }
        }
    }
    return dp[n][W]
}

/*
* 20. Climbing Stairs*/

fun climbStairs(n: Int): Int {
    if (n <= 2) return n
    var a = 1; var b = 2
    for (i in 3..n) {
        val temp = a + b
        a = b
        b = temp
    }
    return b
}
