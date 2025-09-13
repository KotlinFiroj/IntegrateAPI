package com.example.kotlinDSA.treesGraphs3

import junit.framework.TestCase.assertEquals
import org.junit.Test

class TreesGraphs3() {

    @Test
    fun testInorder() {
        val root = TreeNode(
            1,
            left = TreeNode(2, TreeNode(4), TreeNode(5)),
            right = TreeNode(3),
        )
        assertEquals(listOf(4, 2, 5, 1, 3), inorder(root))
    }

    @Test
    fun testLevelOrder() {
        val root = TreeNode(
            1,
            left = TreeNode(2, TreeNode(4), TreeNode(5)),
            right = TreeNode(3),
        )
        assertEquals(listOf(listOf(1), listOf(2, 3), listOf(4, 5)), levelOrder(root))
    }

    @Test
    fun testLowestCommonAncestor() {
        val root = TreeNode(
            3,
            left = TreeNode(
                5,
                left = TreeNode(6),
                right = TreeNode(2, TreeNode(7), TreeNode(4)),
            ),
            right = TreeNode(1, TreeNode(0), TreeNode(8)),
        )
        val node5 = root.left
        val node1 = root.right
        assertEquals(root, lowestCommonAncestor(root, node5, node1))
    }

    @Test
    fun testGraphBFS() {
        val graph = mapOf(
            0 to listOf(1, 2),
            1 to listOf(2),
            2 to listOf(0, 3),
            3 to listOf(3),
        )
        assertEquals(listOf(2, 0, 3, 1), bfs(graph, 2))
    }
}
data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

/*
* 9. Binary Tree Inorder Traversal  */

fun inorder(root: TreeNode?): List<Int> {
    if (root == null) return emptyList()
    return inorder(root.left) + listOf(root.`val`) + inorder(root.right)
}

/*
* 10. BFS Level Order Traversal */

fun levelOrder(root: TreeNode?): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    if (root == null) return result
    val queue = ArrayDeque<TreeNode>()
    queue.add(root)
    while (queue.isNotEmpty()) {
        val size = queue.size
        val level = mutableListOf<Int>()
        repeat(size) {
            val node = queue.removeFirst()
            level.add(node.`val`)
            node.left?.let { queue.add(it) }
            node.right?.let { queue.add(it) }
        }
        result.add(level)
    }
    return result
}

/*
* 11. Lowest Common Ancestor    */

fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if (root == null || root == p || root == q) return root
    val left = lowestCommonAncestor(root.left, p, q)
    val right = lowestCommonAncestor(root.right, p, q)
    return if (left != null && right != null) root else left ?: right
}

/*
* 12. Graph BFS */

fun bfs(graph: Map<Int, List<Int>>, start: Int): List<Int> {
    val visited = mutableSetOf<Int>()
    val queue = ArrayDeque<Int>()
    val result = mutableListOf<Int>()
    queue.add(start)
    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()
        if (node !in visited) {
            visited.add(node)
            result.add(node)
            graph[node]?.forEach { queue.add(it) }
        }
    }
    return result
}
