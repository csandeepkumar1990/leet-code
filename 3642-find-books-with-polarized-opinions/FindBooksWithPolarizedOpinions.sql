/*
 * LeetCode 3642: Find Books with Polarized Opinions
 * 
 * Problem:
 * Write a solution to find books that have polarized opinions - books that receive
 * both very high ratings and very low ratings from different readers.
 * 
 * A book has polarized opinions if:
 *   - It has at least one rating ≥ 4 AND at least one rating ≤ 2
 *   - Has at least 5 reading sessions
 *   - Polarization score ≥ 0.6 (at least 60% extreme ratings)
 * 
 * Return the result table ordered by polarization score DESC, then title DESC.
 * 
 * Table: books
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | book_id     | int     |
 * | title       | varchar |
 * | author      | varchar |
 * | genre       | varchar |
 * | pages       | int     |
 * +-------------+---------+
 * book_id is the unique ID for this table.
 * 
 * Table: reading_sessions
 * +----------------+---------+
 * | Column Name    | Type    |
 * +----------------+---------+
 * | session_id     | int     |
 * | book_id        | int     |
 * | reader_name    | varchar |
 * | pages_read     | int     |
 * | session_rating | int     |
 * +----------------+---------+
 * session_id is the unique ID for this table.
 * session_rating is on a scale of 1-5.
 * 
 * Approach: JOIN with Aggregation and Conditional Counting
 * 
 * Key Insight:
 * - Join books and reading_sessions to get book details and ratings
 * - Group by book_id to aggregate per book
 * - Check for both high (≥4) and low (≤2) ratings
 * - Calculate polarization score as percentage of extreme ratings
 * - Filter by minimum sessions and polarization threshold
 * 
 * Formulas:
 *   rating_spread = MAX(session_rating) - MIN(session_rating)
 *   extreme_ratings = COUNT(CASE WHEN session_rating <= 2 OR session_rating >= 4 THEN 1 END)
 *   polarization_score = extreme_ratings / total_sessions
 * 
 * Conditions:
 *   - MAX(session_rating) >= 4 AND MIN(session_rating) <= 2 (has both extremes)
 *   - COUNT(*) >= 5 (at least 5 sessions)
 *   - polarization_score >= 0.6 (at least 60% extreme ratings)
 * 
 * Algorithm:
 * 1. JOIN books and reading_sessions on book_id
 * 2. GROUP BY book_id to aggregate per book
 * 3. Calculate rating spread: MAX - MIN
 * 4. Count extreme ratings: ratings <= 2 OR >= 4
 * 5. Calculate polarization score: extreme_count / total_count
 * 6. Filter with HAVING: both extremes, >= 5 sessions, score >= 0.6
 * 7. ORDER BY polarization_score DESC, title DESC
 * 
 * Example:
 * 
 *   Books:
 *   +---------+------------------+--------+-------+-------+
 *   | book_id | title            | author | genre | pages |
 *   +---------+------------------+--------+-------+-------+
 *   | 1       | The Great Novel  | Author1| Fiction| 300  |
 *   | 2       | Mystery Book     | Author2| Mystery| 250  |
 *   | 3       | Sci-Fi Adventure | Author3| Sci-Fi | 400  |
 *   +---------+------------------+--------+-------+-------+
 * 
 *   Reading Sessions:
 *   +------------+---------+-------------+------------+---------------+
 *   | session_id | book_id | reader_name | pages_read | session_rating|
 *   +------------+---------+-------------+------------+---------------+
 *   | 1          | 1       | Alice       | 50         | 5             |
 *   | 2          | 1       | Bob         | 100        | 1             |
 *   | 3          | 1       | Charlie     | 80         | 5             |
 *   | 4          | 1       | David       | 70         | 2             |
 *   | 5          | 1       | Eve         | 0          | 4             |
 *   | 6          | 2       | Alice       | 100        | 3             |
 *   | 7          | 2       | Bob         | 150        | 3             |
 *   | 8          | 2       | Charlie     | 0          | 3             |
 *   +------------+---------+-------------+------------+---------------+
 * 
 *   Book 1 Analysis:
 *   - Ratings: [5, 1, 5, 2, 4]
 *   - MAX = 5 >= 4 ✓, MIN = 1 <= 2 ✓ (has both extremes)
 *   - Total sessions = 5 >= 5 ✓
 *   - Extreme ratings: 5, 1, 5, 2, 4 = 5 (all are extreme)
 *   - Polarization score = 5/5 = 1.0 >= 0.6 ✓
 *   - Rating spread = 5 - 1 = 4
 *   → Included
 * 
 *   Book 2 Analysis:
 *   - Ratings: [3, 3, 3]
 *   - MAX = 3 < 4 ✗ (no high rating)
 *   - Total sessions = 3 < 5 ✗
 *   → Excluded
 * 
 *   Result:
 *   +---------+------------------+---------------+-------------------+
 *   | book_id | title            | rating_spread | polarization_score|
 *   +---------+------------------+---------------+-------------------+
 *   | 1       | The Great Novel  | 4             | 1.0               |
 *   +---------+------------------+---------------+-------------------+
 * 
 * Why JOIN?
 *   - Need book details (title) from books table
 *   - Need ratings from reading_sessions table
 *   - JOIN on book_id to combine the data
 * 
 * Why Conditional COUNT?
 *   - COUNT(CASE WHEN condition THEN 1 END) counts rows matching condition
 *   - Extreme ratings: session_rating <= 2 OR session_rating >= 4
 *   - This counts how many ratings are extreme (very high or very low)
 * 
 * Polarization Score Calculation:
 *   - extreme_count = COUNT(CASE WHEN session_rating <= 2 OR session_rating >= 4 THEN 1 END)
 *   - total_count = COUNT(*)
 *   - polarization_score = extreme_count * 1.0 / total_count
 *   - Multiply by 1.0 to ensure decimal division
 * 
 * Why HAVING for Multiple Conditions?
 *   - All conditions use aggregate functions (MAX, MIN, COUNT)
 *   - HAVING filters groups after aggregation
 *   - WHERE cannot be used with aggregate functions
 */

# Write your MySQL query statement below

SELECT 
    b.book_id,
    b.title,
    b.author,
    b.genre,
    b.pages,
    MAX(r.session_rating) - MIN(r.session_rating) AS rating_spread,
    ROUND(COUNT(CASE WHEN r.session_rating <= 2 OR r.session_rating >= 4 THEN 1 END) * 1.0 / COUNT(*), 2) AS polarization_score
FROM books b
JOIN reading_sessions r ON b.book_id = r.book_id
GROUP BY b.book_id, b.title, b.author, b.genre, b.pages
HAVING 
    MAX(r.session_rating) >= 4 
    AND MIN(r.session_rating) <= 2
    AND COUNT(*) >= 5
    AND COUNT(CASE WHEN r.session_rating <= 2 OR r.session_rating >= 4 THEN 1 END) * 1.0 / COUNT(*) >= 0.6
ORDER BY COUNT(CASE WHEN r.session_rating <= 2 OR r.session_rating >= 4 THEN 1 END) * 1.0 / COUNT(*) DESC, b.title DESC;
