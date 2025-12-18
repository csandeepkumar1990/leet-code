/*
 * LeetCode 1241: Number of Comments per Post
 * 
 * Problem:
 * Write a solution to find the number of comments per post. The result should
 * be sorted by post_id in ascending order.
 * 
 * Table: Submissions
 * +---------------+----------+
 * | Column Name   | Type     |
 * +---------------+----------+
 * | sub_id        | int      |
 * | parent_id     | int      |
 * +---------------+----------+
 * No primary key. May contain duplicates.
 * 
 * Data Model:
 * - Both POSTS and COMMENTS are in the same table
 * - parent_id = NULL → It's a POST (top-level, no parent)
 * - parent_id = X    → It's a COMMENT on post with sub_id = X
 * 
 * Visual:
 *   POST (sub_id=1, parent_id=NULL)
 *   ├── COMMENT (sub_id=3, parent_id=1)
 *   └── COMMENT (sub_id=4, parent_id=1)
 *   
 *   POST (sub_id=2, parent_id=NULL)
 *   └── COMMENT (sub_id=5, parent_id=2)
 * 
 * Approach: CTEs to Separate Posts and Comments, then LEFT JOIN
 * 
 * Key Insight:
 * - Use parent_id IS NULL to identify posts
 * - Use parent_id IS NOT NULL to identify comments
 * - LEFT JOIN ensures posts with 0 comments still appear
 * - Use DISTINCT to handle duplicate submissions
 * 
 * Algorithm:
 * 1. CTE 'posts': Get distinct post IDs (parent_id IS NULL)
 * 2. CTE 'comments': Get distinct comments (parent_id IS NOT NULL)
 * 3. LEFT JOIN posts with comments on post_id = parent_id
 * 4. COUNT comments per post (COALESCE for posts with no comments)
 * 5. ORDER BY post_id
 * 
 * Example:
 * 
 *   Submissions:
 *   +--------+-----------+
 *   | sub_id | parent_id |
 *   +--------+-----------+
 *   | 1      | NULL      |  ← POST
 *   | 2      | NULL      |  ← POST
 *   | 1      | NULL      |  ← POST (duplicate)
 *   | 12     | NULL      |  ← POST
 *   | 3      | 1         |  ← COMMENT on post 1
 *   | 5      | 2         |  ← COMMENT on post 2
 *   | 3      | 1         |  ← COMMENT (duplicate)
 *   | 4      | 1         |  ← COMMENT on post 1
 *   | 9      | 1         |  ← COMMENT on post 1
 *   | 10     | 2         |  ← COMMENT on post 2
 *   | 6      | 7         |  ← COMMENT on post 7 (post 7 doesn't exist!)
 *   +--------+-----------+
 * 
 *   Posts (DISTINCT, parent_id IS NULL):
 *   +--------+
 *   | post_id|
 *   +--------+
 *   | 1      |
 *   | 2      |
 *   | 12     |
 *   +--------+
 * 
 *   Result:
 *   +---------+--------------------+
 *   | post_id | number_of_comments |
 *   +---------+--------------------+
 *   | 1       | 3                  |  (comments: 3, 4, 9)
 *   | 2       | 2                  |  (comments: 5, 10)
 *   | 12      | 0                  |  (no comments)
 *   +---------+--------------------+
 * 
 * Note: Comment on post 7 is ignored because post 7 doesn't exist
 *       (we only count comments for posts that exist in our posts CTE)
 */

WITH posts AS (
    SELECT DISTINCT sub_id AS post_id
    FROM Submissions
    WHERE parent_id IS NULL
),
comments AS (
    SELECT DISTINCT sub_id, parent_id
    FROM Submissions
    WHERE parent_id IS NOT NULL
)
SELECT
    p.post_id,
    COALESCE(COUNT(c.sub_id), 0) AS number_of_comments
FROM posts p
LEFT JOIN comments c
    ON p.post_id = c.parent_id
GROUP BY p.post_id
ORDER BY p.post_id;

