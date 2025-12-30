/*
 * LeetCode 590: N-ary Tree Postorder Traversal
 * 
 * Problem:
 * Given the root of an n-ary tree, return the postorder traversal of its nodes' values.
 * 
 * N-ary tree: Each node can have any number of children (not just 2 like binary tree)
 * 
 * Postorder Traversal: Children (left to right) → Root
 * - Recursively visit all children from left to right first
 * - Then process the root node
 * 
 * Approach: Recursive DFS (Depth-First Search)
 * 
 * Key Insight:
 * - Postorder: Process current node after all its children
 * - Use a class-level list to accumulate results during traversal
 * - Recursively traverse all children first, then add current node
 * 
 * Algorithm:
 * 1. Base case: If node is null, return (stop recursion)
 * 2. Recursively traverse all children from left to right first
 * 3. Process current node: Add node.val to result list (after children)
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
 * Postorder Traversal: [5, 6, 3, 2, 4, 1]
 * 
 * Step-by-step:
 *   1. Visit 5 (leaf, no children) → result = [5]
 *   2. Visit 6 (leaf, no children) → result = [5, 6]
 *   3. Visit 3 (after children 5, 6) → result = [5, 6, 3]
 *   4. Visit 2 (leaf, no children) → result = [5, 6, 3, 2]
 *   5. Visit 4 (leaf, no children) → result = [5, 6, 3, 2, 4]
 *   6. Visit 1 (after all children) → result = [5, 6, 3, 2, 4, 1]
 * 
 * Visual (Traversal Order):
 * 
 *   Step 1-2:      1
 *               /  |  \
 *              3   2   4
 *             / \
 *            5   6     ← Visit leaves first (5, 6)
 * 
 *   Step 3:        1
 *               /  |  \
 *              3   2   4     ← Visit 3 after its children
 *             / \
 *            5   6
 * 
 *   Step 4-5:      1
 *               /  |  \
 *              3   2   4     ← Visit 2, then 4
 *             / \
 *            5   6
 * 
 *   Step 6:        1           ← Visit root last
 *               /  |  \
 *              3   2   4
 *             / \
 *            5   6
 * 
 *   Order: 5 → 6 → 3 → 2 → 4 → 1
 * 
 * Another Example:
 * 
 *           1
 *        /  |  \
 *       2   3   4
 * 
 * Postorder: [2, 3, 4, 1]
 * 
 * Why Process Children Before Root?
 * - Postorder definition: left → right → root (for binary)
 * - For N-ary: children (in order) → root
 * - Ensures all descendants appear before their parent
 * - Useful for operations like deleting nodes (delete children first)
 * 
 * Why Class-Level List?
 * - Need to accumulate results across recursive calls
 * - Instance variable persists across method calls
 * - Alternative: Pass list as parameter (also valid approach)
 * 
 * Preorder vs Postorder:
 * 
 *   Preorder (589):  Root → Children
 *   - Process node before children
 *   - Example: [1, 3, 5, 6, 2, 4]
 * 
 *   Postorder (590): Children → Root
 *   - Process node after children
 *   - Example: [5, 6, 3, 2, 4, 1]
 * 
 * Alternative Approaches:
 * 1. Iterative with Stack: Use explicit stack instead of recursion
 * 2. Pass List as Parameter: Instead of class-level variable
 * 3. Return List from Helper: Build list recursively and return
 * 4. Reverse Preorder: Do preorder with reversed children, then reverse result
 * 
 * Edge Cases:
 * - Empty tree (root = null): Return empty list []
 * - Single node: Return [root.val]
 * - Tree with one child: [child.val, root.val]
 * - Tree with many children: Process all children first, then root
 * 
 * Use Cases for Postorder:
 * - Deleting a tree: Delete children before parent
 * - Evaluating expressions: Evaluate operands before operator
 * - Calculating directory sizes: Sum children sizes before parent
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
}
*/

import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<Integer> result = new ArrayList<>();

    public List<Integer> postorder(Node root) {
        performPostorderTraversal(root);
        return result;
    }

    private void performPostorderTraversal(Node node) {
        // Base case: if node is null, return
        if (node == null) {
            return;
        }
      
        // Recursively visit all children nodes first
        for (Node child : node.children) {
            performPostorderTraversal(child);
        }
      
        // After visiting all children, add current node's value to result
        result.add(node.val);
    }
}

