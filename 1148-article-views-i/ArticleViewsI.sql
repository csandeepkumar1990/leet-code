/*
 * LeetCode 1148: Article Views I
 * 
 * Problem:
 * Write a solution to find all the authors that viewed at least one of their
 * own articles. Return the result sorted by id in ascending order.
 * 
 * Table: Views
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | article_id    | int     |
 * | author_id     | int     |
 * | viewer_id     | int     |
 * | view_date     | date    |
 * +---------------+---------+
 * No primary key (may have duplicate rows).
 * Each row indicates that viewer_id viewed article_id by author_id on view_date.
 * 
 * Approach: Simple Filter with DISTINCT
 * 
 * Key Insight:
 * - An author views their own article when author_id = viewer_id
 * - Use DISTINCT because same author may view multiple articles (duplicates)
 * - Order by id as required
 * 
 * Example:
 * 
 *   Views:
 *   +------------+-----------+-----------+------------+
 *   | article_id | author_id | viewer_id | view_date  |
 *   +------------+-----------+-----------+------------+
 *   | 1          | 3         | 5         | 2019-08-01 |  author 3, viewer 5 → different
 *   | 1          | 3         | 6         | 2019-08-02 |  author 3, viewer 6 → different
 *   | 2          | 7         | 7         | 2019-08-01 |  author 7, viewer 7 → SAME ✓
 *   | 2          | 7         | 6         | 2019-08-02 |  author 7, viewer 6 → different
 *   | 4          | 7         | 1         | 2019-07-22 |  author 7, viewer 1 → different
 *   | 3          | 4         | 4         | 2019-07-21 |  author 4, viewer 4 → SAME ✓
 *   | 3          | 4         | 4         | 2019-07-21 |  author 4, viewer 4 → SAME ✓ (duplicate)
 *   +------------+-----------+-----------+------------+
 * 
 *   Filter: author_id = viewer_id
 *   - Row 3: author 7 = viewer 7 ✓
 *   - Row 6: author 4 = viewer 4 ✓
 *   - Row 7: author 4 = viewer 4 ✓ (duplicate of row 6)
 * 
 *   DISTINCT removes duplicate author 4
 * 
 *   Result (sorted by id):
 *   +------+
 *   | id   |
 *   +------+
 *   | 4    |
 *   | 7    |
 *   +------+
 * 
 * Why DISTINCT?
 * 
 *   Without DISTINCT:         With DISTINCT:
 *   +------+                  +------+
 *   | id   |                  | id   |
 *   +------+                  +------+
 *   | 4    |                  | 4    |
 *   | 4    |  ← duplicate     | 7    |
 *   | 7    |                  +------+
 *   +------+
 * 
 * Visual:
 * 
 *   Author 3 wrote Article 1
 *     └─ Viewed by: 5, 6 (not by author 3)
 * 
 *   Author 7 wrote Articles 2, 4
 *     └─ Article 2 viewed by: 7 ✓ (self), 6
 *     └─ Article 4 viewed by: 1
 * 
 *   Author 4 wrote Article 3
 *     └─ Viewed by: 4 ✓ (self, twice)
 * 
 *   Authors who viewed own articles: 4, 7
 */

SELECT DISTINCT author_id AS id
FROM Views
WHERE author_id = viewer_id
ORDER BY author_id;

