/*
 * LeetCode 1173: Immediate Food Delivery I
 * 
 * Problem:
 * If the customer's preferred delivery date is the same as the order date,
 * then the order is called immediate; otherwise, it is called scheduled.
 * Write a solution to find the percentage of immediate orders, rounded to 2 decimals.
 * 
 * Table: Delivery
 * +-----------------------------+---------+
 * | Column Name                 | Type    |
 * +-----------------------------+---------+
 * | delivery_id                 | int     |
 * | customer_id                 | int     |
 * | order_date                  | date    |
 * | customer_pref_delivery_date | date    |
 * +-----------------------------+---------+
 * delivery_id is the primary key.
 * 
 * Definitions:
 * - IMMEDIATE: order_date = customer_pref_delivery_date (same-day delivery)
 * - SCHEDULED: order_date ≠ customer_pref_delivery_date (future delivery)
 * 
 * Formula:
 *                         # of immediate orders
 *   immediate_percentage = ───────────────────── × 100
 *                            total # of orders
 * 
 * Approach: Conditional COUNT with CASE WHEN
 * 
 * Key Insight:
 * - Use CASE WHEN to count orders where dates match
 * - Divide by total COUNT(*) and multiply by 100
 * - Use 100.0 to force decimal division
 * 
 * Example:
 * 
 *   Delivery:
 *   +-------------+-------------+------------+-----------------------------+
 *   | delivery_id | customer_id | order_date | customer_pref_delivery_date |
 *   +-------------+-------------+------------+-----------------------------+
 *   | 1           | 1           | 2019-08-01 | 2019-08-02                  | Scheduled
 *   | 2           | 5           | 2019-08-02 | 2019-08-02                  | Immediate ✓
 *   | 3           | 1           | 2019-08-11 | 2019-08-11                  | Immediate ✓
 *   | 4           | 3           | 2019-08-24 | 2019-08-26                  | Scheduled
 *   +-------------+-------------+------------+-----------------------------+
 * 
 *   Calculation:
 *   - Immediate orders: 2 (delivery_id 2 and 3)
 *   - Total orders: 4
 *   - Percentage: 2 / 4 × 100 = 50.00%
 * 
 *   Result:
 *   +----------------------+
 *   | immediate_percentage |
 *   +----------------------+
 *   | 50.00                |
 *   +----------------------+
 * 
 * How CASE WHEN Works:
 * 
 *   SUM(CASE WHEN order_date = customer_pref_delivery_date THEN 1 ELSE 0 END)
 *   
 *   Row 1: 2019-08-01 = 2019-08-02? No  → 0
 *   Row 2: 2019-08-02 = 2019-08-02? Yes → 1
 *   Row 3: 2019-08-11 = 2019-08-11? Yes → 1
 *   Row 4: 2019-08-24 = 2019-08-26? No  → 0
 *                                       ───
 *   SUM = 2 (immediate orders)
 * 
 * Visual:
 * 
 *   Order Date    Preferred Date    Match?
 *   ──────────    ──────────────    ──────
 *   Aug 01    →   Aug 02            ✗ Scheduled
 *   Aug 02    →   Aug 02            ✓ Immediate
 *   Aug 11    →   Aug 11            ✓ Immediate
 *   Aug 24    →   Aug 26            ✗ Scheduled
 */

SELECT 
    ROUND(
        100.0 * SUM(CASE WHEN order_date = customer_pref_delivery_date THEN 1 ELSE 0 END) / COUNT(*),
        2
    ) AS immediate_percentage
FROM Delivery;

