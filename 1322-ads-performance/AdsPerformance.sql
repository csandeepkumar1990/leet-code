/*
 * LeetCode 1322: Ads Performance
 * 
 * Problem:
 * Calculate the Click-Through Rate (CTR) for each ad, rounded to 2 decimal places.
 * CTR = (Clicks / (Clicks + Views)) * 100
 * If no clicks and no views, CTR = 0.
 * Order by CTR descending, then by ad_id ascending.
 * 
 * Table: Ads
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | ad_id         | int     |
 * | user_id       | int     |
 * | action        | enum    |  ('Clicked', 'Viewed', 'Ignored')
 * +---------------+---------+
 * (ad_id, user_id) is the primary key.
 * 
 * Formula:
 *   CTR = Clicks / (Clicks + Views) × 100
 * 
 *   Note: 'Ignored' actions don't count in CTR calculation!
 * 
 * Approach: Conditional Aggregation with Division Safety
 * 
 * Key Insight:
 * - Use CASE WHEN to count Clicks and (Clicks + Views) separately
 * - Handle division by zero when no clicks AND no views
 * - 'Ignored' is excluded from both numerator and denominator
 * 
 * Algorithm:
 * 1. GROUP BY ad_id
 * 2. Count clicks: SUM(CASE WHEN action = 'Clicked')
 * 3. Count clicks+views: SUM(CASE WHEN action IN ('Clicked', 'Viewed'))
 * 4. If denominator = 0, return 0; else calculate CTR
 * 5. ORDER BY ctr DESC, ad_id ASC
 * 
 * Example:
 * 
 *   Ads:
 *   +-------+---------+---------+
 *   | ad_id | user_id | action  |
 *   +-------+---------+---------+
 *   | 1     | 1       | Clicked |
 *   | 2     | 2       | Clicked |
 *   | 3     | 3       | Viewed  |
 *   | 5     | 5       | Ignored |
 *   | 1     | 7       | Ignored |
 *   | 2     | 7       | Viewed  |
 *   | 3     | 5       | Clicked |
 *   | 1     | 4       | Viewed  |
 *   | 2     | 11      | Viewed  |
 *   | 1     | 2       | Clicked |
 *   +-------+---------+---------+
 * 
 *   Calculation per ad_id:
 * 
 *   Ad 1: Clicked=2, Viewed=1, Ignored=1
 *         CTR = 2 / (2+1) × 100 = 66.67%
 * 
 *   Ad 2: Clicked=1, Viewed=2, Ignored=0
 *         CTR = 1 / (1+2) × 100 = 33.33%
 * 
 *   Ad 3: Clicked=1, Viewed=1, Ignored=0
 *         CTR = 1 / (1+1) × 100 = 50.00%
 * 
 *   Ad 5: Clicked=0, Viewed=0, Ignored=1
 *         CTR = 0 (no clicks or views!)
 * 
 *   Result (ordered by CTR DESC, ad_id ASC):
 *   +-------+-------+
 *   | ad_id | ctr   |
 *   +-------+-------+
 *   | 1     | 66.67 |
 *   | 3     | 50.00 |
 *   | 2     | 33.33 |
 *   | 5     | 0.00  |
 *   +-------+-------+
 * 
 * Division by Zero Handling:
 * 
 *   CASE
 *     WHEN SUM(clicks + views) = 0 THEN 0  -- Avoid division by zero
 *     ELSE clicks * 100.0 / (clicks + views)
 *   END
 * 
 *   Ad 5 example:
 *   - Clicks = 0, Views = 0
 *   - Denominator = 0 + 0 = 0
 *   - Without CASE: 0/0 = ERROR!
 *   - With CASE: Return 0
 * 
 * Why 100.0 (not 100)?
 *   - Forces decimal division
 *   - 2/3 * 100 = 0 * 100 = 0 (integer math)
 *   - 2/3 * 100.0 = 66.67 (decimal math)
 * 
 * ============================================================
 * SUMMARY OF KEY CONCEPTS
 * ============================================================
 * 
 * | Concept              | Purpose                             |
 * |----------------------|-------------------------------------|
 * | CASE WHEN counting   | Count specific action types         |
 * | Division by zero     | CASE to return 0 when denom = 0     |
 * | 100.0 (not 100)      | Force decimal division              |
 * | IN ('Clicked','Viewed')| Exclude 'Ignored' from denominator|
 * | ORDER BY ctr DESC    | Highest CTR first                   |
 * | ORDER BY ad_id ASC   | Tie-breaker: smaller ad_id first    |
 */

SELECT
    ad_id,
    ROUND(
        CASE
            WHEN SUM(CASE WHEN action IN ('Clicked', 'Viewed') THEN 1 ELSE 0 END) = 0
            THEN 0
            ELSE
                SUM(CASE WHEN action = 'Clicked' THEN 1 ELSE 0 END) * 100.0
                /
                SUM(CASE WHEN action IN ('Clicked', 'Viewed') THEN 1 ELSE 0 END)
        END,
        2
    ) AS ctr
FROM Ads
GROUP BY ad_id
ORDER BY ctr DESC, ad_id ASC;

