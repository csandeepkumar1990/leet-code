/*
 * LeetCode 589: N-ary Tree Preorder Traversal
 * 
 * Problem:
 * Given the root of an n-ary tree, return the preorder traversal of its nodes' values.
 * 
 * N-ary tree: Each node can have any number of children (not just 2 like binary tree)
 * 
 * Preorder Traversal: Root → Children (left to right)
 * - Visit the root node first
 * - Then recursively visit all children from left to right
 * 
 * Approach: Recursive DFS (Depth-First Search)
 * 
 * Key Insight:
 * - Preorder: Process current node before its children
 * - Use a class-level list to accumulate results during traversal
 * - Recursively traverse all children in order
 * 
 * Algorithm:
 * 1. Base case: If node is null, return (stop recursion)
 * 2. Process current node: Add node.val to result list
 * 3. Recursively traverse all children from left to right
 * 4. Return the accumulated result list
 * 
 * Time Complexity: O(n), where n is the number of nodes.
 *   We visit each node exactly once.
 * 
 * Space Complexity: O(h), where h is the height of the tree.
 *   - Recursion stack: O(h) for the call stack
 *   - Result list: O(n) to store all node values
 *   - Overall: O(n) due to result storage
 *   - Worst case: O(n) for skewed tree (height = n)
 *   - Best case: O(log n) for balanced tree
 * 
 * Example:
 * 
 *           1
 *        /  |  \
 *       3   2   4
 *      / \
 *     5   6
 * 
 * Preorder Traversal: [1, 3, 5, 6, 2, 4]
 * 
 * Step-by-step:
 *   1. Visit 1 → result = [1]
 *   2. Visit 3 (first child of 1) → result = [1, 3]
 *   3. Visit 5 (first child of 3) → result = [1, 3, 5]
 *   4. Visit 6 (second child of 3) → result = [1, 3, 5, 6]
 *   5. Visit 2 (second child of 1) → result = [1, 3, 5, 6, 2]
 *   6. Visit 4 (third child of 1) → result = [1, 3, 5, 6, 2, 4]
 * 
 * Visual (Traversal Order):
 * 
 *   Step 1:        1           ← Visit root first
 *               /  |  \
 *   Step 2:    3   2   4
 *             / \
 *   Step 3:  5   6
 * 
 *   Order: 1 → 3 → 5 → 6 → 2 → 4
 * 
 * Another Example:
 * 
 *           1
 *        /  |  \
 *       2   3   4
 * 
 * Preorder: [1, 2, 3, 4]
 * 
 * Why Class-Level List?
 * - Need to accumulate results across recursive calls
 * - Instance variable persists across method calls
 * - Alternative: Pass list as parameter (also valid approach)
 * 
 * Why Process Root Before Children?
 * - Preorder definition: root → left → right (for binary)
 * - For N-ary: root → children (in order)
 * - Ensures parent appears before all its descendants
 * 
 * Alternative Approaches:
 * 1. Iterative with Stack: Use explicit stack instead of recursion
 * 2. Pass List as Parameter: Instead of class-level variable
 * 3. Return List from Helper: Build list recursively and return
 * 
 * Edge Cases:
 * - Empty tree (root = null): Return empty list []
 * - Single node: Return [root.val]
 * - Tree with one child: [root.val, child.val]
 * - Tree with many children: Process all in order
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

import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<Integer> result = new ArrayList<>();

    public List<Integer> preorder(Node root) {
        dfs(root);
        return result;
    }

    private void dfs(Node node) {
        // Base case: if node is null, return
        if (node == null) {
            return;
        }
      
        // Process current node (preorder: root first)
        result.add(node.val);
      
        // Recursively traverse all children from left to right
        for (Node child : node.children) {
            dfs(child);
        }
    }
}

