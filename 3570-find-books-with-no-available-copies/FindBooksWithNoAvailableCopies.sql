/*
 * LeetCode 3570: Find Books with No Available Copies
 * 
 * Problem:
 * Write a solution to find books that have no available copies.
 * A book has no available copies if all its copies are currently borrowed
 * (return_date IS NULL).
 * Return the result table ordered by current_borrowers in descending order,
 * then by title in ascending order.
 * 
 * Table: library_books
 * +------------------+---------+
 * | Column Name      | Type    |
 * +------------------+---------+
 * | book_id          | int     |
 * | title            | varchar |
 * | author           | varchar |
 * | genre            | varchar |
 * | publication_year | int     |
 * | total_copies     | int     |
 * +------------------+---------+
 * book_id is the primary key for this table.
 * Each row contains information about a book in the library.
 * 
 * Table: borrowing_records
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | record_id   | int     |
 * | book_id     | int     |
 * | borrower_id | int     |
 * | borrow_date | date    |
 * | return_date | date    |
 * +-------------+---------+
 * record_id is the primary key for this table.
 * Each row contains information about a book borrowing record.
 * return_date IS NULL indicates the book is currently borrowed.
 * 
 * Approach: JOIN with GROUP BY and HAVING
 * 
 * Key Insight:
 * - Join books with borrowing records to get current borrowers
 * - Filter for records where return_date IS NULL (currently borrowed)
 * - Count current borrowers per book
 * - Check if count equals total_copies (all copies borrowed)
 * 
 * Formula:
 *   For each book:
 *   current_borrowers = COUNT(records where return_date IS NULL)
 *   Condition: current_borrowers = total_copies
 * 
 * Algorithm:
 * 1. JOIN library_books and borrowing_records on book_id
 * 2. Filter WHERE return_date IS NULL (currently borrowed)
 * 3. GROUP BY book details to aggregate per book
 * 4. COUNT(record_id) to count current borrowers
 * 5. HAVING COUNT(record_id) = total_copies (all copies borrowed)
 * 6. ORDER BY current_borrowers DESC, title ASC
 * 
 * Example:
 * 
 *   library_books:
 *   +---------+------------------+-------------+--------+------------------+-------------+
 *   | book_id | title            | author      | genre  | publication_year| total_copies|
 *   +---------+------------------+-------------+--------+------------------+-------------+
 *   | 1       | The Great Novel  | Author A    | Fiction| 2020            | 3           |
 *   | 2       | Mystery Book     | Author B    | Mystery| 2021            | 2           |
 *   | 3       | Sci-Fi Adventure | Author C    | Sci-Fi | 2022            | 1           |
 *   +---------+------------------+-------------+--------+------------------+-------------+
 * 
 *   borrowing_records:
 *   +-----------+---------+------------+------------+------------+
 *   | record_id | book_id | borrower_id| borrow_date| return_date|
 *   +-----------+---------+------------+------------+------------+
 *   | 1         | 1       | 101        | 2023-01-01 | NULL       |
 *   | 2         | 1       | 102        | 2023-01-02 | NULL       |
 *   | 3         | 1       | 103        | 2023-01-03 | NULL       |
 *   | 4         | 2       | 101        | 2023-01-01 | NULL       |
 *   | 5         | 2       | 102        | 2023-01-02 | 2023-01-10 |
 *   | 6         | 3       | 103        | 2023-01-01 | NULL       |
 *   +-----------+---------+------------+------------+------------+
 * 
 *   Step 1 - JOIN and filter return_date IS NULL:
 *   Book 1: 3 records (all currently borrowed)
 *   Book 2: 1 record (1 currently borrowed, 1 returned)
 *   Book 3: 1 record (currently borrowed)
 * 
 *   Step 2 - Count current borrowers:
 *   Book 1: COUNT = 3, total_copies = 3 → 3 = 3 ✓ (all copies borrowed)
 *   Book 2: COUNT = 1, total_copies = 2 → 1 ≠ 2 ✗ (1 copy available)
 *   Book 3: COUNT = 1, total_copies = 1 → 1 = 1 ✓ (all copies borrowed)
 * 
 *   Result:
 *   +---------+------------------+-------------+--------+------------------+------------------+
 *   | book_id | title            | author      | genre  | publication_year| current_borrowers|
 *   +---------+------------------+-------------+--------+------------------+------------------+
 *   | 1       | The Great Novel  | Author A    | Fiction| 2020            | 3                |
 *   | 3       | Sci-Fi Adventure | Author C    | Sci-Fi | 2022            | 1                |
 *   +---------+------------------+-------------+--------+------------------+------------------+
 * 
 * Why WHERE return_date IS NULL?
 *   - NULL return_date means the book hasn't been returned yet
 *   - This identifies currently borrowed books
 *   - Filters out records where books have been returned
 * 
 * Why JOIN?
 *   - Need book details from library_books
 *   - Need borrowing records to count current borrowers
 *   - JOIN on book_id links books with their borrowing records
 * 
 * Why GROUP BY all book columns?
 *   - Need to aggregate borrowing records per book
 *   - All non-aggregated columns in SELECT must be in GROUP BY
 *   - Includes total_copies (needed for HAVING comparison)
 * 
 * Why HAVING COUNT(record_id) = total_copies?
 *   - Checks if all copies are currently borrowed
 *   - If count equals total_copies, no copies are available
 *   - HAVING is used because it compares aggregate function with column
 * 
 * Why COUNT(record_id) instead of COUNT(*)?
 *   - Both work the same if record_id is never NULL
 *   - COUNT(record_id) is more explicit about what we're counting
 *   - COUNT(*) counts all rows regardless of NULL values
 * 
 * Available Copies Calculation:
 *   - Available = total_copies - current_borrowers
 *   - If current_borrowers = total_copies, then available = 0
 *   - This query finds books where available = 0
 * 
 * Edge Cases:
 *   - Book with no borrowing records: Not in result (no rows after JOIN)
 *   - Book with all copies returned: Not in result (no rows with return_date IS NULL)
 *   - Book with some copies returned: Only counted if all copies are borrowed
 *   - Book with total_copies = 0: Would match if count = 0 (edge case)
 */

# Write your MySQL query statement below

SELECT
    b.book_id,
    b.title,
    b.author,
    b.genre,
    b.publication_year,
    COUNT(r.record_id) AS current_borrowers
FROM library_books b
JOIN borrowing_records r
    ON b.book_id = r.book_id
WHERE r.return_date IS NULL
GROUP BY
    b.book_id,
    b.title,
    b.author,
    b.genre,
    b.publication_year,
    b.total_copies
HAVING COUNT(r.record_id) = b.total_copies
ORDER BY current_borrowers DESC, b.title ASC;

