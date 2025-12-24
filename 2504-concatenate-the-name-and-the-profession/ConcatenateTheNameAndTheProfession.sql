/*
 * LeetCode 2504: Concatenate the Name and the Profession
 *
 * Problem:
 * Write a solution to show the unique ID of each person, If a person does not
 * have a unique ID replace just show null.
 *
 * Return the result table ordered by person_id in descending order.
 *
 * Table: Person
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | person_id   | int     |
 * | name        | varchar |
 * | profession  | varchar |
 * +-------------+---------+
 * person_id is the primary key for this table.
 * This table contains information about the ID of some persons and their names
 * and professions.
 *
 * Approach: String Concatenation with LEFT Function
 *
 * Key Insight:
 * - Need to concatenate name with the first letter of profession
 * - Format: "Name(P)" where P is the first letter of profession
 * - Use CONCAT and LEFT functions
 * - Order by person_id DESC
 *
 * Algorithm:
 * 1. SELECT person_id and formatted name
 * 2. Use CONCAT to combine:
 *    - name
 *    - '(' (opening parenthesis)
 *    - LEFT(profession, 1) (first character of profession)
 *    - ')' (closing parenthesis)
 * 3. ORDER BY person_id DESC
 *
 * Why LEFT(profession, 1)?
 * - LEFT(string, n) extracts the first n characters from a string
 * - LEFT(profession, 1) gets the first character of the profession
 * - Alternative: SUBSTRING(profession, 1, 1) or profession[0] (some DBMS)
 *
 * Example:
 *
 *   Person:
 *   +-----------+-------+------------+
 *   | person_id | name  | profession |
 *   +-----------+-------+------------+
 *   | 1         | Alex  | Singer     |
 *   | 3         | Alice | Actor      |
 *   | 2         | Bob   | Player     |
 *   | 4         | Messi | Doctor     |
 *   +-----------+-------+------------+
 *
 *   Processing:
 *   - Person 1: name='Alex', profession='Singer'
 *     → CONCAT('Alex', '(', LEFT('Singer', 1), ')')
 *     → CONCAT('Alex', '(', 'S', ')')
 *     → 'Alex(S)'
 *
 *   - Person 2: name='Bob', profession='Player'
 *     → CONCAT('Bob', '(', LEFT('Player', 1), ')')
 *     → CONCAT('Bob', '(', 'P', ')')
 *     → 'Bob(P)'
 *
 *   - Person 3: name='Alice', profession='Actor'
 *     → CONCAT('Alice', '(', LEFT('Actor', 1), ')')
 *     → CONCAT('Alice', '(', 'A', ')')
 *     → 'Alice(A)'
 *
 *   - Person 4: name='Messi', profession='Doctor'
 *     → CONCAT('Messi', '(', LEFT('Doctor', 1), ')')
 *     → CONCAT('Messi', '(', 'D', ')')
 *     → 'Messi(D)'
 *
 *   Result (ordered by person_id DESC):
 *   +-----------+----------+
 *   | person_id | name     |
 *   +-----------+----------+
 *   | 4         | Messi(D) |
 *   | 3         | Alice(A) |
 *   | 2         | Bob(P)   |
 *   | 1         | Alex(S)  |
 *   +-----------+----------+
 *
 * String Functions Used:
 * - CONCAT(str1, str2, ...): Concatenates multiple strings
 * - LEFT(str, n): Returns the leftmost n characters of a string
 *
 * Important Notes:
 * - CONCAT automatically handles NULL values (converts to empty string)
 * - LEFT(profession, 1) extracts the first character
 * - The format is: name + '(' + first_letter + ')'
 * - Results are ordered by person_id in descending order
 */

# Write your MySQL query statement below

SELECT person_id,
       CONCAT(name, '(', LEFT(profession, 1), ')') AS name
FROM Person
ORDER BY person_id DESC;

