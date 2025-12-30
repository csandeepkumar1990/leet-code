/*
 * LeetCode 627: Swap Salary
 * 
 * Problem:
 * Write a solution to swap all 'f' and 'm' values (i.e., change all 'f' values to 'm'
 * and vice versa) with a single update statement and no intermediate temporary tables.
 * 
 * Note: You must write a single UPDATE statement, do not write any SELECT statement
 * for this problem.
 * 
 * Table: Salary
 * +-------------+----------+
 * | Column Name | Type     |
 * +-------------+----------+
 * | id          | int      |
 * | name        | varchar  |
 * | sex         | enum     |
 * | salary      | int      |
 * +-------------+----------+
 * id is the primary key for this table.
 * The sex column is of type ENUM value of ('m', 'f').
 * The table contains information about an employee.
 * 
 * Approach: UPDATE with CASE Expression
 * 
 * Key Insight:
 * - Swap 'm' to 'f' and 'f' to 'm' in a single UPDATE statement
 * - Use CASE expression to conditionally set the new value
 * - WHEN sex = 'm' THEN 'f' (swap m to f)
 * - ELSE 'm' (swap f to m, or handle any other values)
 * 
 * Algorithm:
 * 1. UPDATE Salary table
 * 2. SET sex = CASE expression:
 *    - WHEN sex = 'm' THEN 'f' (change m to f)
 *    - ELSE 'm' (change f to m, or any other value to m)
 * 3. All rows are updated in a single statement
 * 
 * Time Complexity: O(n), where n is the number of rows in Salary table.
 *   UPDATE statement scans and updates each row once.
 * 
 * Space Complexity: O(1), constant space.
 *   UPDATE modifies data in-place, no additional storage needed.
 * 
 * Example:
 * 
 *   Before UPDATE:
 *   +----+-------+-----+--------+
 *   | id | name  | sex | salary |
 *   +----+-------+-----+--------+
 *   | 1  | A     | m   | 2500   |
 *   | 2  | B     | f   | 1500   |
 *   | 3  | C     | m   | 5500   |
 *   | 4  | D     | f   | 500    |
 *   +----+-------+-----+--------+
 * 
 *   Step-by-step UPDATE:
 *   - Row 1: sex = 'm' → CASE matches WHEN 'm' → SET to 'f'
 *   - Row 2: sex = 'f' → CASE doesn't match WHEN → ELSE 'm' → SET to 'm'
 *   - Row 3: sex = 'm' → CASE matches WHEN 'm' → SET to 'f'
 *   - Row 4: sex = 'f' → CASE doesn't match WHEN → ELSE 'm' → SET to 'm'
 * 
 *   After UPDATE:
 *   +----+-------+-----+--------+
 *   | id | name  | sex | salary |
 *   +----+-------+-----+--------+
 *   | 1  | A     | f   | 2500   |
 *   | 2  | B     | m   | 1500   |
 *   | 3  | C     | f   | 5500   |
 *   | 4  | D     | m   | 500    |
 *   +----+-------+-----+--------+
 * 
 * CASE Expression Breakdown:
 * 
 *   CASE 
 *       WHEN sex = 'm' THEN 'f'   ← If sex is 'm', change to 'f'
 *       ELSE 'm'                  ← Otherwise (sex is 'f'), change to 'm'
 *   END
 * 
 * Truth Table:
 * 
 *   Current sex | Condition Match? | New sex
 *   ------------|------------------|--------
 *   'm'         | Yes (WHEN)       | 'f'
 *   'f'         | No (ELSE)       | 'm'
 * 
 * Why CASE Expression?
 * 
 *   - Allows conditional logic in UPDATE statement
 *   - Can handle multiple conditions in a single statement
 *   - More readable than nested IF statements
 *   - Standard SQL approach for conditional updates
 * 
 * Why ELSE 'm'?
 * 
 *   - Since sex is ENUM('m', 'f'), it can only be 'm' or 'f'
 *   - If sex is not 'm', it must be 'f'
 *   - ELSE 'm' handles the 'f' case (swaps f to m)
 *   - Also handles any unexpected NULL or other values (sets to 'm')
 * 
 * Alternative Approach: Two Separate WHEN Clauses
 * 
 *   UPDATE Salary
 *   SET sex = CASE 
 *       WHEN sex = 'm' THEN 'f'
 *       WHEN sex = 'f' THEN 'm'
 *   END;
 * 
 *   - More explicit, shows both swap cases
 *   - If sex is NULL or other value, it becomes NULL
 *   - Original approach with ELSE is safer (handles edge cases)
 * 
 * Alternative Approach: Using XOR-like Logic
 * 
 *   UPDATE Salary
 *   SET sex = IF(sex = 'm', 'f', 'm');
 * 
 *   - MySQL-specific IF function
 *   - More concise but less standard SQL
 *   - Works the same way: if 'm' then 'f', else 'm'
 * 
 * Why Single UPDATE Statement?
 * 
 *   - Problem requires single UPDATE (no intermediate tables)
 *   - CASE expression allows conditional logic in one statement
 *   - More efficient than multiple UPDATE statements
 *   - Atomic operation (all rows updated together)
 * 
 * Edge Cases:
 * 
 *   - All rows have 'm': All become 'f'
 *   - All rows have 'f': All become 'm'
 *   - Mixed 'm' and 'f': All are swapped correctly
 *   - NULL values: ELSE 'm' sets NULL to 'm' (may not be desired)
 *   - Empty table: No rows updated (no error)
 * 
 * NULL Handling:
 * 
 *   - If sex is NULL, sex = 'm' evaluates to NULL (not TRUE)
 *   - CASE falls through to ELSE 'm'
 *   - NULL values become 'm' (may or may not be desired)
 *   - If you want to preserve NULLs, use:
 *     CASE 
 *         WHEN sex = 'm' THEN 'f'
 *         WHEN sex = 'f' THEN 'm'
 *         ELSE sex  ← preserves NULL
 *     END
 * 
 * Performance Considerations:
 * 
 *   - Single UPDATE is efficient (one table scan)
 *   - No WHERE clause needed (updates all rows)
 *   - CASE expression is evaluated per row
 *   - Consider transaction if table is large
 * 
 * Important Notes:
 * 
 *   - This is an UPDATE statement, not a SELECT
 *   - Modifies data in-place (permanent change)
 *   - No WHERE clause means ALL rows are updated
 *   - Make sure to backup data before running UPDATE
 *   - In production, test on a copy first
 */

# Write your MySQL query statement below

UPDATE Salary
SET sex = CASE 
            WHEN sex = 'm' THEN 'f'
            ELSE 'm'
          END;

