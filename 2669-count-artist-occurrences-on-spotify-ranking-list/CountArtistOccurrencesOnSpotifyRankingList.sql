/*
 * LeetCode 2669: Count Artist Occurrences On Spotify Ranking List
 * 
 * Problem:
 * Write a solution to find the number of times each artist appeared on the
 * Spotify ranking list.
 * Return the result table ordered by occurrences in descending order, then
 * by artist in ascending order.
 * 
 * Table: Spotify
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | id          | int     |
 * | track_name  | varchar |
 * | artist      | varchar |
 * +-------------+---------+
 * id is the primary key for this table.
 * Each row contains information about a track and its artist.
 * 
 * Approach: GROUP BY with COUNT and Multi-Column ORDER BY
 * 
 * Key Insight:
 * - Count how many times each artist appears in the ranking list
 * - Group by artist to aggregate tracks per artist
 * - Order by count (descending), then artist name (ascending)
 * 
 * Formula:
 *   For each artist:
 *   occurrences = COUNT(*) (number of tracks)
 * 
 * Algorithm:
 * 1. GROUP BY artist to aggregate tracks per artist
 * 2. COUNT(*) to count number of tracks per artist
 * 3. ORDER BY occurrences DESC (most tracks first)
 * 4. ORDER BY artist ASC (alphabetical for ties)
 * 
 * Example:
 * 
 *   Spotify:
 *   +----+------------------+-------------+
 *   | id | track_name       | artist      |
 *   +----+------------------+-------------+
 *   | 1  | Blinding Lights  | The Weeknd  |
 *   | 2  | Watermelon Sugar | Harry Styles|
 *   | 3  | As It Was        | Harry Styles|
 *   | 4  | Save Your Tears  | The Weeknd  |
 *   | 5  | Levitating       | Dua Lipa    |
 *   | 6  | Good 4 U         | Olivia Rodrigo|
 *   | 7  | Stay             | The Kid LAROI|
 *   | 8  | Industry Baby    | Lil Nas X   |
 *   | 9  | Heat Waves       | Glass Animals|
 *   | 10 | Blinding Lights  | The Weeknd  |
 *   +----+------------------+-------------+
 * 
 *   Step 1 - Group by artist:
 *   The Weeknd: [id: 1, 4, 10] → 3 tracks
 *   Harry Styles: [id: 2, 3] → 2 tracks
 *   Dua Lipa: [id: 5] → 1 track
 *   Olivia Rodrigo: [id: 6] → 1 track
 *   The Kid LAROI: [id: 7] → 1 track
 *   Lil Nas X: [id: 8] → 1 track
 *   Glass Animals: [id: 9] → 1 track
 * 
 *   Step 2 - Count occurrences:
 *   The Weeknd: 3
 *   Harry Styles: 2
 *   Dua Lipa: 1
 *   Olivia Rodrigo: 1
 *   The Kid LAROI: 1
 *   Lil Nas X: 1
 *   Glass Animals: 1
 * 
 *   Step 3 - Order by occurrences DESC, artist ASC:
 *   The Weeknd: 3 (highest)
 *   Harry Styles: 2
 *   Dua Lipa: 1 (tied with others, sorted alphabetically)
 *   Glass Animals: 1
 *   Lil Nas X: 1
 *   Olivia Rodrigo: 1
 *   The Kid LAROI: 1
 * 
 *   Result:
 *   +-------------+-------------+
 *   | artist      | occurrences |
 *   +-------------+-------------+
 *   | The Weeknd  | 3           |
 *   | Harry Styles| 2           |
 *   | Dua Lipa    | 1           |
 *   | Glass Animals| 1          |
 *   | Lil Nas X   | 1           |
 *   | Olivia Rodrigo| 1         |
 *   | The Kid LAROI| 1          |
 *   +-------------+-------------+
 * 
 * Why COUNT(*) instead of COUNT(id)?
 *   - COUNT(*) counts all rows in the group
 *   - COUNT(id) counts non-NULL id values
 *   - Both work the same if id is never NULL
 *   - COUNT(*) is slightly more efficient
 *   - COUNT(*) is more idiomatic for counting rows
 * 
 * Why GROUP BY artist?
 *   - Aggregates tracks per artist
 *   - Without GROUP BY: Would get one row with total count
 *   - With GROUP BY: One row per artist with their count
 * 
 * Multi-Column ORDER BY:
 *   - Primary sort: occurrences DESC (most tracks first)
 *   - Secondary sort: artist ASC (alphabetical for ties)
 *   - Artists with same count are sorted alphabetically
 *   - Ensures consistent, predictable ordering
 * 
 * Why ORDER BY occurrences DESC?
 *   - Most popular artists (most tracks) appear first
 *   - DESC means highest count first
 *   - Standard ranking list format
 * 
 * Why ORDER BY artist ASC for ties?
 *   - When multiple artists have same count, sort alphabetically
 *   - ASC means A to Z
 *   - Provides consistent ordering for tied artists
 * 
 * Edge Cases:
 *   - Artist with single track: Count = 1
 *   - Artist with multiple tracks: Count = number of tracks
 *   - Artist name case sensitivity: Depends on collation
 *   - Duplicate tracks: Each row is counted separately
 */

# Write your MySQL query statement below

SELECT
    artist,
    COUNT(*) AS occurrences
FROM Spotify
GROUP BY artist
ORDER BY
    occurrences DESC,
    artist ASC;

