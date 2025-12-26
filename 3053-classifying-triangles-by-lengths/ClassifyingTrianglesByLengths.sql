/*
 * LeetCode 3053: Classifying Triangles by Lengths
 * 
 * Problem:
 * Write a solution to classify each triangle based on its side lengths.
 * Return the result table with a column named triangle_type.
 * 
 * Table: Triangles
 * +-------------+------+
 * | Column Name | Type |
 * +-------------+------+
 * | A           | int  |
 * | B           | int  |
 * | C           | int  |
 * +-------------+------+
 * Each row contains the lengths of the three sides of a triangle.
 * 
 * Triangle Types:
 * - Equilateral: All three sides are equal (A = B = C)
 * - Isosceles: Exactly two sides are equal (A = B OR A = C OR B = C)
 * - Scalene: All three sides are different (A ≠ B ≠ C)
 * - Not A Triangle: Sides cannot form a valid triangle
 * 
 * Approach: CASE Statement with Triangle Inequality Check
 * 
 * Key Insight:
 * - First check if sides form a valid triangle using triangle inequality theorem
 * - Then classify based on side length relationships
 * - Order matters: check invalid triangle first, then equilateral, then isosceles
 * 
 * Triangle Inequality Theorem:
 *   For three sides to form a triangle, the sum of any two sides
 *   must be GREATER than the third side:
 *   - A + B > C
 *   - A + C > B
 *   - B + C > A
 * 
 *   If any of these fail (A + B <= C), it's not a valid triangle.
 * 
 * Formula:
 *   Invalid: A + B <= C OR A + C <= B OR B + C <= A
 *   Equilateral: A = B AND B = C
 *   Isosceles: A = B OR A = C OR B = C
 *   Scalene: All sides different (else case)
 * 
 * Algorithm:
 * 1. Check triangle validity using triangle inequality
 * 2. If invalid, return 'Not A Triangle'
 * 3. If all sides equal, return 'Equilateral'
 * 4. If any two sides equal, return 'Isosceles'
 * 5. Otherwise, return 'Scalene'
 * 
 * Example:
 * 
 *   Triangles:
 *   +---+---+---+
 *   | A | B | C |
 *   +---+---+---+
 *   | 3 | 3 | 3 |  → Equilateral (all equal)
 *   | 3 | 4 | 3 |  → Isosceles (A = C)
 *   | 3 | 4 | 5 |  → Scalene (all different)
 *   | 1 | 2 | 3 |  → Not A Triangle (1 + 2 = 3, not > 3)
 *   | 5 | 5 | 10|  → Not A Triangle (5 + 5 = 10, not > 10)
 *   +---+---+---+
 * 
 *   Step-by-step evaluation:
 * 
 *   Row 1: A=3, B=3, C=3
 *   - Check validity: 3+3 > 3 ✓, 3+3 > 3 ✓, 3+3 > 3 ✓ → Valid
 *   - A = B AND B = C? 3=3 AND 3=3 → TRUE → 'Equilateral'
 * 
 *   Row 2: A=3, B=4, C=3
 *   - Check validity: 3+4 > 3 ✓, 3+3 > 4 ✓, 4+3 > 3 ✓ → Valid
 *   - A = B AND B = C? 3=4 AND 4=3 → FALSE
 *   - A = B OR A = C OR B = C? 3=4 OR 3=3 OR 4=3 → TRUE → 'Isosceles'
 * 
 *   Row 3: A=3, B=4, C=5
 *   - Check validity: 3+4 > 5 ✓, 3+5 > 4 ✓, 4+5 > 3 ✓ → Valid
 *   - A = B AND B = C? 3=4 AND 4=5 → FALSE
 *   - A = B OR A = C OR B = C? 3=4 OR 3=5 OR 4=5 → FALSE → 'Scalene'
 * 
 *   Row 4: A=1, B=2, C=3
 *   - Check validity: 1+2 <= 3? 3 <= 3 → TRUE → 'Not A Triangle'
 * 
 *   Row 5: A=5, B=5, C=10
 *   - Check validity: 5+5 <= 10? 10 <= 10 → TRUE → 'Not A Triangle'
 * 
 *   Result:
 *   +----------------+
 *   | triangle_type  |
 *   +----------------+
 *   | Equilateral    |
 *   | Isosceles      |
 *   | Scalene        |
 *   | Not A Triangle |
 *   | Not A Triangle |
 *   +----------------+
 * 
 * Why check triangle validity first?
 *   - Triangle inequality must be satisfied for a valid triangle
 *   - If sides don't form a triangle, classification doesn't matter
 *   - Must check this BEFORE checking side length relationships
 * 
 * Why A + B <= C (not just <)?
 *   - If A + B = C, the three points are collinear (form a line, not a triangle)
 *   - For a triangle, we need A + B > C (strictly greater)
 *   - Using <= catches both invalid cases: < (too short) and = (collinear)
 * 
 * Why check all three inequalities?
 *   - Need to verify: A + B > C, A + C > B, B + C > A
 *   - If ANY one fails, it's not a valid triangle
 *   - Using OR means if any condition is true (invalid), return 'Not A Triangle'
 * 
 * CASE Statement Order:
 *   1. Invalid triangle check (must be first)
 *   2. Equilateral check (most specific - all equal)
 *   3. Isosceles check (two equal, but not all three)
 *   4. Scalene (else - all different)
 * 
 * Note on Isosceles Logic:
 *   - Isosceles means "exactly two sides equal"
 *   - But equilateral triangles also satisfy "two sides equal"
 *   - That's why we check equilateral FIRST, then isosceles
 *   - If A = B = C, equilateral condition catches it before isosceles
 */

# Write your MySQL query statement below

SELECT
    CASE
        WHEN A + B <= C OR A + C <= B OR B + C <= A THEN 'Not A Triangle'
        WHEN A = B AND B = C THEN 'Equilateral'
        WHEN A = B OR A = C OR B = C THEN 'Isosceles'
        ELSE 'Scalene'
    END AS triangle_type
FROM Triangles;

