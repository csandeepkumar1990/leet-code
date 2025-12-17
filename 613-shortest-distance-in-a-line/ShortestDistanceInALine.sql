/*
 * LeetCode 613: Shortest Distance in a Line
 * 
 * Problem:
 * Find the shortest distance between any two points on a line.
 * 
 * Table: Point
 * +-------------+------+
 * | Column Name | Type |
 * +-------------+------+
 * | x           | int  |
 * +-------------+------+
 * x is the primary key.
 * Each row contains the position of a point on a line.
 * 
 * Example:
 * Input:
 * +----+
 * | x  |
 * +----+
 * | -1 |
 * | 0  |
 * | 2  |
 * +----+
 * 
 * Output:
 * +----------+
 * | shortest |
 * +----------+
 * | 1        |
 * +----------+
 * 
 * Explanation:
 * Distances between all pairs of different points:
 * - |(-1) - 0| = 1
 * - |(-1) - 2| = 3
 * - |0 - 2| = 2
 * 
 * The shortest distance is 1 (between -1 and 0).
 * 
 * Approach:
 * - Self-join Point table to compare every pair of points
 * - Join condition: p1.x <> p2.x ensures we don't compare a point with itself
 * - ABS(p1.x - p2.x) calculates the distance between two points
 * - MIN() finds the smallest distance among all pairs
 * 
 * Note: The join creates pairs (a,b) and (b,a) which have the same distance,
 * but MIN() handles this correctly since we only need the minimum.
 */

# Write your MySQL query statement below

SELECT MIN(ABS(p1.x - p2.x)) AS shortest
FROM Point p1
JOIN Point p2
  ON p1.x <> p2.x;

