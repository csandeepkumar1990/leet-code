/*
 * LeetCode 2480: Form Chemical Bond
 * 
 * Problem:
 * Write a solution to find all possible pairs of elements that can form a chemical bond.
 * A chemical bond can be formed between a metal and a nonmetal.
 * Return the result table with metal and nonmetal columns.
 * 
 * Table: Elements
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | symbol      | varchar |
 * | type        | varchar |
 * +-------------+---------+
 * symbol is the primary key for this table.
 * Each row contains information about an element.
 * type can be 'Metal' or 'Nonmetal'.
 * 
 * Approach: Self-Join with Type Filtering
 * 
 * Key Insight:
 * - Need to generate all possible pairs of metals and nonmetals
 * - Use self-join to pair each metal with each nonmetal
 * - Filter by type in JOIN condition
 * - Each metal can bond with every nonmetal
 * 
 * Self-Join Concept:
 *   A self-join pairs rows from the same table
 *   - Creates two instances: m (metals) and n (nonmetals)
 *   - JOIN condition filters: m.type = 'Metal' AND n.type = 'Nonmetal'
 *   - Generates Cartesian product of all metals × all nonmetals
 * 
 * Formula:
 *   For m metals and n nonmetals:
 *   Number of bonds = m × n
 *   Each metal pairs with every nonmetal
 * 
 * Algorithm:
 * 1. Self-join Elements table (aliases m and n)
 * 2. JOIN condition filters by type:
 *    - m.type = 'Metal' (first instance is metal)
 *    - n.type = 'Nonmetal' (second instance is nonmetal)
 * 3. Select m.symbol as metal, n.symbol as nonmetal
 * 
 * Example:
 * 
 *   Elements:
 *   +--------+----------+
 *   | symbol | type     |
 *   +--------+----------+
 *   | Fe     | Metal    |
 *   | Cu     | Metal    |
 *   | O      | Nonmetal |
 *   | Cl     | Nonmetal |
 *   | H      | Nonmetal |
 *   +--------+----------+
 * 
 *   Step 1 - Self-join with type filtering:
 *   Metals (m): Fe, Cu
 *   Nonmetals (n): O, Cl, H
 * 
 *   Step 2 - Generate all pairs:
 *   Fe (Metal) × O (Nonmetal) → Fe-O
 *   Fe (Metal) × Cl (Nonmetal) → Fe-Cl
 *   Fe (Metal) × H (Nonmetal) → Fe-H
 *   Cu (Metal) × O (Nonmetal) → Cu-O
 *   Cu (Metal) × Cl (Nonmetal) → Cu-Cl
 *   Cu (Metal) × H (Nonmetal) → Cu-H
 * 
 *   Result:
 *   +-------+----------+
 *   | metal | nonmetal |
 *   +-------+----------+
 *   | Fe    | O        |
 *   | Fe    | Cl       |
 *   | Fe    | H        |
 *   | Cu    | O        |
 *   | Cu    | Cl       |
 *   | Cu    | H        |
 *   +-------+----------+
 * 
 *   Total: 2 metals × 3 nonmetals = 6 bonds
 * 
 * Why Self-Join?
 *   - Need to pair each metal with each nonmetal
 *   - Self-join creates Cartesian product of metals × nonmetals
 *   - JOIN condition filters to only valid pairs (metal + nonmetal)
 * 
 * Why Filter in JOIN Condition?
 *   - Filters rows during the join operation
 *   - More efficient than filtering in WHERE clause
 *   - Only creates pairs that match the type criteria
 *   - Alternative: Could use WHERE clause, but JOIN is cleaner
 * 
 * JOIN Condition vs WHERE Clause:
 *   - JOIN condition: ON m.type = 'Metal' AND n.type = 'Nonmetal'
 *     → Filters during join, only creates valid pairs
 *   - WHERE clause: WHERE m.type = 'Metal' AND n.type = 'Nonmetal'
 *     → Filters after join, creates all pairs then filters
 *   - Both work, but JOIN condition is more efficient
 * 
 * Chemical Bond Context:
 *   - Metals tend to lose electrons
 *   - Nonmetals tend to gain electrons
 *   - Ionic bonds form between metals and nonmetals
 *   - Example: NaCl (Sodium metal + Chlorine nonmetal)
 * 
 * Edge Cases:
 *   - No metals: Returns empty result
 *   - No nonmetals: Returns empty result
 *   - One metal, multiple nonmetals: One row per nonmetal
 *   - Multiple metals, one nonmetal: One row per metal
 * 
 * Alternative Approaches:
 *   - Using WHERE clause instead of JOIN condition
 *   - Using CROSS JOIN with WHERE filter
 *   - Both produce same result, JOIN condition is preferred
 */

# Write your MySQL query statement below

SELECT m.symbol AS metal,
       n.symbol AS nonmetal
FROM Elements m
JOIN Elements n
  ON m.type = 'Metal'
  AND n.type = 'Nonmetal';

