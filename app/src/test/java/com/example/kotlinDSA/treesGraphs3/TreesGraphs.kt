package com.example.kotlinDSA.treesGraphs3

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
