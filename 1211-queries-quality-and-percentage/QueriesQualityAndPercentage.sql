/*
 * LeetCode 1211: Queries Quality and Percentage
 * 
 * Problem:
 * Write a solution to find each query_name, its quality, and poor_query_percentage.
 * - quality: average of (rating / position), rounded to 2 decimal places
 * - poor_query_percentage: percentage of queries with rating < 3, rounded to 2 decimal places
 * 
 * Table: Queries
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | query_name  | varchar |
 * | result      | varchar |
 * | position    | int     |  (1 to 500)
 * | rating      | int     |  (1 to 5)
 * +-------------+---------+
 * 
 * Approach: GROUP BY with Aggregations
 * 
 * Formulas:
 * 
 *   quality = AVG(rating / position)
 *           = SUM(rating / position) / COUNT(*)
 * 
 *   poor_query_percentage = (# of queries with rating < 3) / (total queries) × 100
 * 
 * Key Insight:
 * - Use AVG for quality calculation
 * - Use CASE WHEN for conditional counting (rating < 3)
 * - Multiply by 1.0 or 100.0 to force decimal division
 * 
 * Example:
 * 
 *   Queries:
 *   +------------+--------+----------+--------+
 *   | query_name | result | position | rating |
 *   +------------+--------+----------+--------+
 *   | Dog        | A      | 1        | 5      |
 *   | Dog        | B      | 2        | 5      |
 *   | Dog        | C      | 1        | 3      |
 *   | Dog        | D      | 4        | 4      |
 *   | Cat        | E      | 3        | 1      |  ← poor (rating < 3)
 *   | Cat        | F      | 2        | 2      |  ← poor (rating < 3)
 *   +------------+--------+----------+--------+
 * 
 *   Calculation for "Dog":
 *   quality = AVG(5/1, 5/2, 3/1, 4/4)
 *           = AVG(5, 2.5, 3, 1)
 *           = 11.5 / 4 = 2.88
 *   
 *   poor_query_percentage = 0 / 4 × 100 = 0.00%
 *   (no ratings < 3 for Dog)
 * 
 *   Calculation for "Cat":
 *   quality = AVG(1/3, 2/2)
 *           = AVG(0.33, 1)
 *           = 1.33 / 2 = 0.67
 *   
 *   poor_query_percentage = 2 / 2 × 100 = 100.00%
 *   (both ratings < 3 for Cat)
 * 
 *   Result:
 *   +------------+---------+-----------------------+
 *   | query_name | quality | poor_query_percentage |
 *   +------------+---------+-----------------------+
 *   | Dog        | 2.88    | 0.00                  |
 *   | Cat        | 0.67    | 100.00                |
 *   +------------+---------+-----------------------+
 * 
 * CASE WHEN Breakdown:
 * 
 *   SUM(CASE WHEN rating < 3 THEN 1 ELSE 0 END)
 *       └─────────────────────────────────────┘
 *       Counts how many rows have rating < 3
 * 
 *   For Cat: rating=1 → 1, rating=2 → 1  → SUM = 2
 *   For Dog: all ratings ≥ 3            → SUM = 0
 * 
 * Why multiply by 100.0 (not 100)?
 *   - 100.0 forces decimal division
 *   - 2 / 2 × 100 = 1 × 100 = 100 (integer math, but works here)
 *   - 1 / 2 × 100 = 0 × 100 = 0   ← WRONG!
 *   - 100.0 × 1 / 2 = 50.0        ← CORRECT!
 */

SELECT
    query_name,
    ROUND(AVG(rating * 1.0 / position), 2) AS quality,
    ROUND(100.0 * SUM(CASE WHEN rating < 3 THEN 1 ELSE 0 END) / COUNT(*), 2) AS poor_query_percentage
FROM Queries
WHERE query_name IS NOT NULL
GROUP BY query_name;

