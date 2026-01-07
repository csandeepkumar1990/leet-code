/*
 * LeetCode 2853: Starting Salaries
 * 
 * Problem:
 * Write a solution to calculate the absolute difference between the maximum
 * salary in the Marketing department and the maximum salary in the Engineering
 * department.
 * 
 * Table: Salaries
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | emp_name    | varchar |
 * | department  | varchar |
 * | salary      | int     |
 * +-------------+---------+
 * (emp_name, department) is the primary key for this table.
 * Each row contains the employee name, their department, and their salary.
 * 
 * Approach: Conditional Aggregation with CASE and MAX
 * 
 * Key Insight:
 * - Need to find maximum salary for Marketing department
 * - Need to find maximum salary for Engineering department
 * - Calculate absolute difference between the two
 * - Use CASE WHEN to conditionally extract salaries by department
 * - Use MAX to aggregate the conditional values
 * 
 * Algorithm:
 * 1. Use CASE WHEN to extract salary when department = 'Marketing'
 * 2. Use CASE WHEN to extract salary when department = 'Engineering'
 * 3. Apply MAX to each CASE expression to get maximum salary per department
 * 4. Calculate the difference: MAX(Marketing) - MAX(Engineering)
 * 5. Apply ABS to get absolute difference
 * 
 * Formula:
 *   salary_difference = ABS(
 *       MAX(CASE WHEN department = 'Marketing' THEN salary END) -
 *       MAX(CASE WHEN department = 'Engineering' THEN salary END)
 *   )
 * 
 * How CASE WHEN Works:
 * 
 *   CASE WHEN department = 'Marketing' THEN salary END
 *   - Returns salary value when department matches 'Marketing'
 *   - Returns NULL when department doesn't match 'Marketing'
 *   - After processing all rows, we have: [salary1, NULL, salary2, NULL, ...]
 * 
 *   MAX(CASE WHEN department = 'Marketing' THEN salary END)
 *   - MAX ignores NULL values
 *   - Returns the maximum salary value from Marketing department
 *   - If no Marketing employees, returns NULL
 * 
 * Time Complexity: O(n), where n is the number of rows in Salaries table.
 *   - Single table scan: O(n)
 *   - CASE evaluation: O(n)
 *   - MAX aggregation: O(n)
 *   - ABS calculation: O(1)
 * 
 * Space Complexity: O(1)
 *   - Only storing two maximum values and the result
 * 
 * Example:
 * 
 *   Salaries:
 *   +-----------+-------------+--------+
 *   | emp_name  | department  | salary |
 *   +-----------+-------------+--------+
 *   | Alice     | Marketing   | 5000   |
 *   | Bob       | Engineering| 6000   |
 *   | Charlie   | Marketing   | 7000   |
 *   | David     | Engineering| 5500   |
 *   | Eve       | Marketing   | 6500   |
 *   | Frank     | Engineering| 8000   |
 *   +-----------+-------------+--------+
 * 
 *   Step 1 - CASE evaluation for Marketing:
 *   Row 1: department='Marketing' → CASE returns 5000
 *   Row 2: department='Engineering' → CASE returns NULL
 *   Row 3: department='Marketing' → CASE returns 7000
 *   Row 4: department='Engineering' → CASE returns NULL
 *   Row 5: department='Marketing' → CASE returns 6500
 *   Row 6: department='Engineering' → CASE returns NULL
 * 
 *   Values: [5000, NULL, 7000, NULL, 6500, NULL]
 *   MAX(Marketing) = MAX(5000, NULL, 7000, NULL, 6500, NULL) = 7000
 * 
 *   Step 2 - CASE evaluation for Engineering:
 *   Row 1: department='Marketing' → CASE returns NULL
 *   Row 2: department='Engineering' → CASE returns 6000
 *   Row 3: department='Marketing' → CASE returns NULL
 *   Row 4: department='Engineering' → CASE returns 5500
 *   Row 5: department='Marketing' → CASE returns NULL
 *   Row 6: department='Engineering' → CASE returns 8000
 * 
 *   Values: [NULL, 6000, NULL, 5500, NULL, 8000]
 *   MAX(Engineering) = MAX(NULL, 6000, NULL, 5500, NULL, 8000) = 8000
 * 
 *   Step 3 - Calculate difference:
 *   difference = 7000 - 8000 = -1000
 *   ABS(-1000) = 1000
 * 
 *   Result:
 *   +------------------+
 *   | salary_difference |
 *   +------------------+
 *   | 1000             |
 *   +------------------+
 * 
 * Why Use MAX with CASE?
 * 
 *   - CASE WHEN returns salary for matching department, NULL otherwise
 *   - MAX aggregates all non-NULL values to find the maximum
 *   - Since we're not using GROUP BY, MAX aggregates across entire table
 *   - MAX ignores NULL values, so it only considers salaries from target department
 * 
 * Why No GROUP BY?
 * 
 *   - We want a single result row (one salary difference value)
 *   - Not grouping by department (we want max from each department in one row)
 *   - Aggregating across the entire table without grouping
 *   - Result: one row with the calculated difference
 * 
 * Why ABS()?
 * 
 *   - Difference can be positive or negative depending on which is larger
 *   - ABS ensures we get the absolute (positive) difference
 *   - Example: If Marketing max = 5000, Engineering max = 8000
 *     - Without ABS: 5000 - 8000 = -3000
 *     - With ABS: ABS(5000 - 8000) = 3000
 *   - Problem asks for "difference" which typically means absolute difference
 * 
 * Alternative Approach: Using Subqueries
 * 
 *   SELECT
 *       ABS(
 *           (SELECT MAX(salary) FROM Salaries WHERE department = 'Marketing') -
 *           (SELECT MAX(salary) FROM Salaries WHERE department = 'Engineering')
 *       ) AS salary_difference;
 * 
 *   - More explicit and readable
 *   - Each subquery calculates max for one department
 *   - Slightly less efficient (two table scans vs one)
 *   - CASE approach is more efficient (single table scan)
 * 
 * Alternative Approach: Using CTE
 * 
 *   WITH dept_max AS (
 *       SELECT
 *           department,
 *           MAX(salary) AS max_salary
 *       FROM Salaries
 *       WHERE department IN ('Marketing', 'Engineering')
 *       GROUP BY department
 *   )
 *   SELECT
 *       ABS(
 *           MAX(CASE WHEN department = 'Marketing' THEN max_salary END) -
 *           MAX(CASE WHEN department = 'Engineering' THEN max_salary END)
 *       ) AS salary_difference
 *   FROM dept_max;
 * 
 *   - First calculates max per department
 *   - Then calculates difference
 *   - More verbose but clearer logic
 * 
 * Edge Cases:
 * 
 *   1. Only Marketing employees (no Engineering):
 *      - MAX(Marketing) = some value
 *      - MAX(Engineering) = NULL
 *      - NULL - value = NULL
 *      - ABS(NULL) = NULL
 *      - Result: NULL
 * 
 *   2. Only Engineering employees (no Marketing):
 *      - MAX(Marketing) = NULL
 *      - MAX(Engineering) = some value
 *      - NULL - value = NULL
 *      - ABS(NULL) = NULL
 *      - Result: NULL
 * 
 *   3. No employees in either department:
 *      - MAX(Marketing) = NULL
 *      - MAX(Engineering) = NULL
 *      - NULL - NULL = NULL
 *      - ABS(NULL) = NULL
 *      - Result: NULL
 * 
 *   4. Both departments have same maximum salary:
 *      - MAX(Marketing) = X
 *      - MAX(Engineering) = X
 *      - X - X = 0
 *      - ABS(0) = 0
 *      - Result: 0
 * 
 *   5. Single employee in each department:
 *      - MAX(Marketing) = that employee's salary
 *      - MAX(Engineering) = that employee's salary
 *      - Difference = |salary1 - salary2|
 * 
 * NULL Handling:
 * 
 *   - CASE WHEN returns NULL for non-matching departments
 *   - MAX ignores NULL values
 *   - If department doesn't exist, MAX returns NULL
 *   - NULL - number = NULL
 *   - NULL - NULL = NULL
 *   - ABS(NULL) = NULL
 *   - If either department is missing, result is NULL
 * 
 * Performance Considerations:
 * 
 *   - Single table scan (efficient)
 *   - CASE evaluation is fast (simple comparison)
 *   - MAX aggregation is efficient
 *   - Consider indexing on department if table is large
 *   - No JOINs needed (single table operation)
 * 
 * Why This Approach is Efficient:
 * 
 *   - Single pass through the table
 *   - No subqueries or multiple scans
 *   - Conditional aggregation in one query
 *   - Minimal memory usage (only tracking two max values)
 * 
 * Department Name Matching:
 * 
 *   - CASE uses exact string match: department = 'Marketing'
 *   - Case-sensitive in MySQL (unless using case-insensitive collation)
 *   - 'Marketing' ≠ 'marketing' ≠ 'MARKETING' (unless collation is case-insensitive)
 *   - Ensure department names match exactly as stored in database
 * 
 * Mathematical Properties:
 * 
 *   - ABS(a - b) = ABS(b - a) (commutative)
 *   - Order of subtraction doesn't matter
 *   - ABS(x) ≥ 0 (always non-negative)
 *   - ABS(0) = 0
 */

# Write your MySQL query statement below

SELECT
    ABS(
        MAX(CASE WHEN department = 'Marketing' THEN salary END) -
        MAX(CASE WHEN department = 'Engineering' THEN salary END)
    ) AS salary_difference
FROM Salaries;

