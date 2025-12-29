/*
 * LeetCode 3358: Find Books With Null Rating
 * 
 * Problem:
 * Write a solution to find all books that have a NULL rating.
 * Return the result table ordered by book_id in ascending order.
 * 
 * Table: Books
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | book_id     | int     |
 * | title       | varchar |
 * | author      | varchar |
 * | rating      | float   |
 * +-------------+---------+
 * book_id is the primary key for this table.
 * Each row contains information about a book.
 * rating can be NULL.
 * 
 * Approach: Filter NULL Values with IS NULL
 * 
 * Key Insight:
 * - Need to find books where rating is NULL
 * - Use IS NULL (not = NULL) to check for NULL values
 * - Order results by book_id
 * 
 * NULL Handling in SQL:
 *   - NULL represents missing or unknown values
 *   - Cannot use = or != to compare with NULL
 *   - Must use IS NULL or IS NOT NULL
 *   - This is because NULL = NULL evaluates to NULL (unknown), not TRUE
 * 
 * Formula:
 *   Filter: rating IS NULL
 *   Sort: ORDER BY book_id ASC
 * 
 * Algorithm:
 * 1. Filter rows where rating IS NULL
 * 2. Order by book_id in ascending order
 * 
 * Example:
 * 
 *   Books:
 *   +---------+------------------+-------------+--------+
 *   | book_id | title            | author      | rating |
 *   +---------+------------------+-------------+--------+
 *   | 1       | The Great Gatsby | F. Scott    | 4.5    |
 *   | 2       | 1984             | George O.  | NULL   |
 *   | 3       | To Kill a Mocking| Harper Lee  | 4.8    |
 *   | 4       | The Catcher      | J.D. Salinger| NULL |
 *   | 5       | Pride and Prejudice| Jane A.  | 4.6    |
 *   +---------+------------------+-------------+--------+
 * 
 *   Step 1 - Filter rating IS NULL:
 *   Book 2: rating = NULL ✓
 *   Book 4: rating = NULL ✓
 * 
 *   Step 2 - Order by book_id:
 *   Book 2 (book_id = 2)
 *   Book 4 (book_id = 4)
 * 
 *   Result:
 *   +---------+-------------+-------------+
 *   | book_id | title       | author      |
 *   +---------+-------------+-------------+
 *   | 2       | 1984        | George O.   |
 *   | 4       | The Catcher | J.D. Salinger|
 *   +---------+-------------+-------------+
 * 
 * Why IS NULL (not = NULL)?
 *   - NULL = NULL evaluates to NULL (unknown), not TRUE
 *   - WHERE rating = NULL would return no rows (always false)
 *   - WHERE rating IS NULL correctly identifies NULL values
 *   - This is a fundamental SQL rule: use IS NULL for NULL comparisons
 * 
 * Common Errors:
 *   1. Using = NULL instead of IS NULL
 *      - WHERE rating = NULL  ❌ (wrong, returns nothing)
 *      - WHERE rating IS NULL ✓ (correct)
 * 
 *   2. Table name case sensitivity
 *      - FROM books  (might fail if table is Books)
 *      - FROM Books  (correct, matches table definition)
 * 
 *   3. Column name in ORDER BY
 *      - ORDER BY book_id  ✓ (correct)
 *      - Make sure column exists in table
 * 
 * NULL Comparison Rules:
 *   - NULL = NULL → NULL (unknown)
 *   - NULL != NULL → NULL (unknown)
 *   - NULL IS NULL → TRUE
 *   - NULL IS NOT NULL → FALSE
 *   - Any comparison with NULL using = or != returns NULL
 * 
 * Alternative NULL Checks:
 *   - IS NULL: finds NULL values
 *   - IS NOT NULL: finds non-NULL values
 *   - COALESCE(rating, 0) = 0: converts NULL to 0, then compares
 *   - IFNULL(rating, -1) = -1: converts NULL to -1, then compares
 *   - But IS NULL is the standard and most efficient way
 */

# Write your MySQL query statement below

SELECT book_id, title, author
FROM Books
WHERE rating IS NULL
ORDER BY book_id;

