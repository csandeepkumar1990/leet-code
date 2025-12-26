/*
 * LeetCode 3150: Invalid Tweets II
 * 
 * Problem:
 * Write a solution to find the IDs of invalid tweets.
 * A tweet is invalid if:
 *   1. The content length is greater than 140 characters, OR
 *   2. It contains more than 3 mentions (more than 3 '@' symbols), OR
 *   3. It contains more than 3 hashtags (more than 3 '#' symbols)
 * 
 * Return the result table ordered by tweet_id in ascending order.
 * 
 * Table: Tweets
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | tweet_id    | int     |
 * | content     | varchar |
 * +-------------+---------+
 * tweet_id is the primary key for this table.
 * Each row contains information about a tweet including its ID and content.
 * 
 * Approach: Character Counting with LENGTH and REPLACE
 * 
 * Key Insight:
 * - Check three conditions: length, '@' count, '#' count
 * - Use LENGTH() to get content length
 * - Use REPLACE() trick to count character occurrences
 * - Any condition being true makes the tweet invalid
 * 
 * Character Counting Technique:
 *   To count occurrences of a character in a string:
 *   count = LENGTH(original) - LENGTH(REPLACE(original, char, ''))
 * 
 *   How it works:
 *   - REPLACE(content, '@', '') removes all '@' characters
 *   - LENGTH(content) - LENGTH(REPLACE(content, '@', '')) = number of '@' removed
 *   - Each '@' removed reduces length by 1, so difference equals count
 * 
 * Formula:
 *   Invalid if:
 *   - LENGTH(content) > 140, OR
 *   - (LENGTH(content) - LENGTH(REPLACE(content, '@', ''))) > 3, OR
 *   - (LENGTH(content) - LENGTH(REPLACE(content, '#', ''))) > 3
 * 
 * Algorithm:
 * 1. Check if content length > 140 characters
 * 2. Count '@' symbols: LENGTH(content) - LENGTH(REPLACE(content, '@', ''))
 * 3. Count '#' symbols: LENGTH(content) - LENGTH(REPLACE(content, '#', ''))
 * 4. If any count > 3, tweet is invalid
 * 5. Return tweet_id for invalid tweets
 * 6. ORDER BY tweet_id
 * 
 * Example:
 * 
 *   Tweets:
 *   +----------+------------------------------------------------------------------+
 *   | tweet_id | content                                                         |
 *   +----------+------------------------------------------------------------------+
 *   | 1        | "This is a valid tweet"                                         |
 *   | 2        | "This tweet is way too long and exceeds the 140 character..."  |
 *   | 3        | "@user1 @user2 @user3 @user4 @user5 hello"                     |
 *   | 4        | "#tag1 #tag2 #tag3 #tag4 #tag5 content"                         |
 *   | 5        | "@user1 @user2 #tag1 #tag2 normal content"                      |
 *   +----------+------------------------------------------------------------------+
 * 
 *   Evaluation:
 * 
 *   Tweet 1: "This is a valid tweet"
 *   - Length: 24 <= 140 ✓
 *   - '@' count: 0 <= 3 ✓
 *   - '#' count: 0 <= 3 ✓
 *   → Valid (not in result)
 * 
 *   Tweet 2: "This tweet is way too long..."
 *   - Length: 145 > 140 ✗ → Invalid
 *   → Include in result
 * 
 *   Tweet 3: "@user1 @user2 @user3 @user4 @user5 hello"
 *   - Length: 45 <= 140 ✓
 *   - '@' count: 5 > 3 ✗ → Invalid
 *   → Include in result
 * 
 *   Tweet 4: "#tag1 #tag2 #tag3 #tag4 #tag5 content"
 *   - Length: 38 <= 140 ✓
 *   - '@' count: 0 <= 3 ✓
 *   - '#' count: 5 > 3 ✗ → Invalid
 *   → Include in result
 * 
 *   Tweet 5: "@user1 @user2 #tag1 #tag2 normal content"
 *   - Length: 42 <= 140 ✓
 *   - '@' count: 2 <= 3 ✓
 *   - '#' count: 2 <= 3 ✓
 *   → Valid (not in result)
 * 
 *   Result:
 *   +----------+
 *   | tweet_id |
 *   +----------+
 *   | 2        |
 *   | 3        |
 *   | 4        |
 *   +----------+
 * 
 * Character Counting Technique Explained:
 * 
 *   Example: Count '@' in "@user1 @user2 @user3"
 *   
 *   Step 1: Original content
 *   content = "@user1 @user2 @user3"
 *   LENGTH(content) = 20
 *   
 *   Step 2: Remove all '@' characters
 *   REPLACE(content, '@', '') = "user1 user2 user3"
 *   LENGTH(REPLACE(content, '@', '')) = 17
 *   
 *   Step 3: Calculate difference
 *   count = 20 - 17 = 3
 *   
 *   This works because:
 *   - Each '@' character is 1 character long
 *   - Removing '@' reduces length by exactly 1 per occurrence
 *   - Difference = number of characters removed = count of '@'
 * 
 * Why > 3 (not >= 3)?
 *   - Problem says "more than 3" mentions/hashtags
 *   - > 3 means 4 or more (invalid)
 *   - <= 3 means 3 or fewer (valid)
 *   - So we check if count > 3
 * 
 * Why use OR (not AND)?
 *   - A tweet is invalid if ANY condition is violated
 *   - If length > 140, it's invalid (regardless of mentions/hashtags)
 *   - If mentions > 3, it's invalid (regardless of length/hashtags)
 *   - If hashtags > 3, it's invalid (regardless of length/mentions)
 *   - OR means "at least one condition is true"
 * 
 * Alternative Approaches:
 *   - Using REGEXP to count: (LENGTH(content) - LENGTH(REGEXP_REPLACE(content, '@', '')))
 *   - Using recursive CTE to count characters (more complex)
 *   - Using SUBSTRING with LOCATE in a loop (inefficient)
 */

# Write your MySQL query statement below

SELECT tweet_id
FROM Tweets
WHERE
    LENGTH(content) > 140
    OR (LENGTH(content) - LENGTH(REPLACE(content, '@', ''))) > 3
    OR (LENGTH(content) - LENGTH(REPLACE(content, '#', ''))) > 3
ORDER BY tweet_id;

