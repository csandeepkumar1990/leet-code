import java.util.*;

/**
 * LeetCode 1971: Find if Path Exists in Graph
 * 
 * Problem: There is a bi-directional graph with n vertices, labeled from 0 to n-1.
 *          The edges are given as a 2D array where edges[i] = [ui, vi] denotes 
 *          a bi-directional edge between vertex ui and vertex vi.
 *          Determine if there is a valid path from source to destination.
 * 
 * Key Insight: This is a graph connectivity problem. We can use BFS/DFS to traverse
 *              from source and check if we can reach destination.
 * 
 * Examples:
 *   Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
 *   Output: true
 *   Explanation: There are two paths from vertex 0 to vertex 2:
 *                - 0 → 1 → 2
 *                - 0 → 2
 * 
 *   Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
 *   Output: false
 *   Explanation: There is no path from vertex 0 to vertex 5.
 *                Vertices 0,1,2 are in one component, and 3,4,5 are in another.
 */
class Solution {

    /**
     * Determines if there is a valid path from source to destination in the graph.
     * 
     * @param n           The number of vertices in the graph (0 to n-1)
     * @param edges       2D array where edges[i] = [ui, vi] represents a bi-directional edge
     * @param source      The starting vertex
     * @param destination The target vertex
     * @return true if a path exists from source to destination, false otherwise
     * 
     * Approach: BFS (Breadth-First Search)
     *           1. Build an adjacency list representation of the graph
     *           2. Start BFS from source vertex
     *           3. Explore all reachable vertices level by level
     *           4. Return true if we reach destination, false if queue empties
     * 
     * Time Complexity: O(V + E) - visit each vertex and edge once
     *                  V = number of vertices, E = number of edges
     * Space Complexity: O(V + E) - adjacency list + visited array + queue
     */
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // Edge case: source and destination are the same vertex
        if (source == destination) {
            return true;
        }

        // Step 1: Build adjacency list representation of the graph
        // Each vertex maps to a list of its neighboring vertices
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges to the adjacency list (bi-directional)
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);  // Edge from u to v
            graph.get(v).add(u);  // Edge from v to u (bi-directional)
        }

        // Step 2: Initialize BFS data structures
        Queue<Integer> queue = new LinkedList<>();  // Queue for BFS traversal
        boolean[] visited = new boolean[n];         // Track visited vertices

        // Start BFS from the source vertex
        queue.offer(source);
        visited[source] = true;

        // Step 3: BFS traversal
        while (!queue.isEmpty()) {
            // Get the next vertex to process
            int node = queue.poll();

            // Check if we've reached the destination
            if (node == destination) {
                return true;
            }

            // Explore all neighbors of the current node
            for (int neighbor : graph.get(node)) {
                // Only visit unvisited neighbors to avoid cycles
                if (!visited[neighbor]) {
                    visited[neighbor] = true;   // Mark as visited
                    queue.offer(neighbor);      // Add to queue for processing
                }
            }
        }

        // Step 4: If we exhaust all reachable vertices without finding destination
        // then there is no path
        return false;
    }
}

