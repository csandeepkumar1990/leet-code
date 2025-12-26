/*
 * LeetCode 2987: Find Cities With Average Price Greater Than Overall Average
 * 
 * Problem:
 * Write a solution to find all cities where the average price of listings
 * is greater than the overall average price of all listings.
 * Return the results ordered by city in ascending order.
 * 
 * Table: Listings
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | listing_id  | int     |
 * | city        | varchar |
 * | price       | int     |
 * +-------------+---------+
 * listing_id is the primary key for this table.
 * Each row contains information about a listing including its ID, city, and price.
 * 
 * Approach: GROUP BY with HAVING and Subquery
 * 
 * Key Insight:
 * - Need to calculate average price per city
 * - Compare each city's average to the overall average
 * - Use HAVING clause to filter groups (can't use WHERE with aggregate functions)
 * - Subquery calculates the overall average across all listings
 * 
 * Formula:
 *   city_avg = AVG(price) GROUP BY city
 *   overall_avg = AVG(price) FROM Listings
 *   Filter: city_avg > overall_avg
 * 
 * Algorithm:
 * 1. GROUP BY city to aggregate listings by city
 * 2. Calculate AVG(price) for each city
 * 3. Use HAVING to filter cities where city_avg > overall_avg
 * 4. Subquery calculates overall average: SELECT AVG(price) FROM Listings
 * 5. ORDER BY city for alphabetical sorting
 * 
 * Example:
 * 
 *   Listings:
 *   +------------+--------------+---------+
 *   | listing_id | city         | price   |
 *   +------------+--------------+---------+
 *   | 113        | LosAngeles   | 7560386 |
 *   | 136        | SanFrancisco | 2380268 |
 *   | 92         | Chicago      | 9833209 |
 *   | 60         | Chicago      | 5147582 |
 *   | 8          | Chicago      | 5274441 |
 *   | 79         | SanFrancisco | 8372065 |
 *   | 37         | Chicago      | 7939595 |
 *   | 53         | LosAngeles   | 4965123 |
 *   | 178        | SanFrancisco | 999207  |
 *   | 51         | NewYork      | 5951718 |
 *   | 121        | NewYork      | 2893760 |
 *   +------------+--------------+---------+
 * 
 *   Step 1 - Calculate overall average:
 *   Overall AVG = (7560386 + 2380268 + 9833209 + 5147582 + 5274441 + 
 *                  8372065 + 7939595 + 4965123 + 999207 + 5951718 + 2893760) / 11
 *                = 67,342,654 / 11
 *                = 6,122,059.45
 * 
 *   Step 2 - Calculate average per city:
 *   Chicago:      (9833209 + 5147582 + 5274441 + 7939595) / 4 = 7,043,706.75  ✓ > overall
 *   LosAngeles:   (7560386 + 4965123) / 2 = 6,277,754.50      ✓ > overall
 *   SanFrancisco: (2380268 + 8372065 + 999207) / 3 = 3,900,513.33  ✗ < overall
 *   NewYork:      (5951718 + 2893760) / 2 = 4,422,739.00      ✗ < overall
 * 
 *   Result:
 *   +------------+
 *   | city       |
 *   +------------+
 *   | Chicago    |
 *   | LosAngeles |
 *   +------------+
 * 
 * Why HAVING instead of WHERE?
 *   - WHERE filters rows BEFORE grouping
 *   - HAVING filters groups AFTER aggregation
 *   - Since we're comparing AVG(price) (aggregate function), we need HAVING
 *   - WHERE AVG(price) > ... would cause an error
 * 
 * Why subquery in HAVING?
 *   - The overall average needs to be calculated once for all cities
 *   - Subquery executes first, then each city's average is compared to it
 *   - Alternative: Could use a CTE (WITH clause) for better readability
 * 
 * Performance Note:
 *   - The subquery executes once and its result is reused for each city comparison
 *   - For large datasets, consider using a CTE to make the query more readable
 */

# Write your MySQL query statement below

SELECT city
FROM Listings
GROUP BY city
HAVING AVG(price) > (SELECT AVG(price) FROM Listings)
ORDER BY city;

