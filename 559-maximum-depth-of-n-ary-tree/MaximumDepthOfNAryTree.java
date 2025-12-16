/*
 * LeetCode 559: Maximum Depth of N-ary Tree
 * 
 * Problem:
 * Given an n-ary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 * 
 * N-ary tree: Each node can have any number of children (not just 2 like binary tree)
 * 
 * Approach: Recursive DFS (Depth-First Search)
 * 
 * Key Insight:
 * - Maximum depth = 1 (current node) + maximum depth among all children
 * - Base case: null node has depth 0
 * - Recursively find max depth of each child subtree
 * 
 * Algorithm:
 * 1. If root is null, return 0 (empty tree)
 * 2. Initialize maxChildDepth = 0
 * 3. For each child, recursively compute depth and track maximum
 * 4. Return 1 + maxChildDepth
 * 
 * Time Complexity: O(n) - visit each node exactly once
 * Space Complexity: O(h) - recursion stack, where h = height of tree
 *                   Worst case O(n) for skewed tree, O(log n) for balanced tree
 * 
 * Example:
 * 
 *           1
 *        /  |  \
 *       3   2   4
 *      / \
 *     5   6
 * 
 * Traversal:
 *   maxDepth(1):
 *     maxDepth(3):
 *       maxDepth(5) → returns 1 (leaf)
 *       maxDepth(6) → returns 1 (leaf)
 *       maxChildDepth = 1
 *       returns 1 + 1 = 2
 *     maxDepth(2) → returns 1 (leaf)
 *     maxDepth(4) → returns 1 (leaf)
 *     maxChildDepth = 2 (from node 3)
 *     returns 1 + 2 = 3
 * 
 * Answer: 3
 * 
 * Visual (depth levels):
 * 
 *   Level 1:        1           ← depth 1
 *                /  |  \
 *   Level 2:    3   2   4       ← depth 2
 *              / \
 *   Level 3:  5   6             ← depth 3 (maximum)
 */

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {

    public int maxDepth(Node root) {

        // Base case: empty tree has depth 0
        if (root == null) {
            return 0;
        }

        // Track the maximum depth among all children
        int maxChildDepth = 0;

        // Traverse all children and find the maximum depth
        for (Node child : root.children) {
            maxChildDepth = Math.max(maxChildDepth, maxDepth(child));
        }

        // Depth of current node = 1 + max depth of children
        return 1 + maxChildDepth;

    }

}

