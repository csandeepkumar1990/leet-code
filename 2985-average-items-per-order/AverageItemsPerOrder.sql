/*
 * LeetCode 2985: Calculate Compounded Orders
 * 
 * Problem:
 * Write a solution to calculate the average number of items per order.
 * The result should be rounded to 2 decimal places.
 * 
 * Table: Orders
 * +------------------+---------+
 * | Column Name      | Type    |
 * +------------------+---------+
 * | order_id         | int     |
 * | item_count       | int     |
 * | order_occurrences | int    |
 * +------------------+---------+
 * (order_id, item_count) is the primary key for this table.
 * Each row contains the item_count of an order and the number of times it occurred.
 * 
 * Approach: Weighted Average Calculation
 * 
 * Key Insight:
 * - Each row represents an order type with item_count and how many times it occurred
 * - We need to calculate a weighted average: total items / total orders
 * - Weighted average accounts for the frequency of each order type
 * 
 * Formula:
 *   average_items_per_order = SUM(item_count × order_occurrences) / SUM(order_occurrences)
 * 
 * Algorithm:
 * 1. Calculate total items: SUM(item_count * order_occurrences)
 * 2. Calculate total orders: SUM(order_occurrences)
 * 3. Divide total items by total orders
 * 4. Round to 2 decimal places
 * 
 * Example:
 * 
 *   Orders:
 *   +----------+------------+------------------+
 *   | order_id | item_count | order_occurrences|
 *   +----------+------------+------------------+
 *   | 10       | 1          | 500              |
 *   | 11       | 2          | 1000             |
 *   | 12       | 3          | 800              |
 *   | 13       | 4          | 1000             |
 *   +----------+------------+------------------+
 * 
 *   Calculation:
 *   Total items = (1×500) + (2×1000) + (3×800) + (4×1000)
 *                = 500 + 2000 + 2400 + 4000
 *                = 8900
 *   
 *   Total orders = 500 + 1000 + 800 + 1000 = 3300
 *   
 *   Average = 8900 / 3300 = 2.696969... ≈ 2.70
 * 
 *   Result:
 *   +---------------------------+
 *   | average_items_per_order   |
 *   +---------------------------+
 *   | 2.70                      |
 *   +---------------------------+
 * 
 * Why weighted average?
 *   - Simple average of item_count would be: (1+2+3+4)/4 = 2.5
 *   - But order with 1 item occurred 500 times, while order with 4 items occurred 1000 times
 *   - Weighted average correctly accounts for the frequency of each order type
 *   - This gives a more accurate representation of the actual average items per order
 * 
 * Why multiply by 1.0?
 *   - Forces floating-point division instead of integer division
 *   - In SQL, dividing two integers can result in integer division (truncates decimals)
 *   - Example: 8900 / 3300 = 2 (integer division) vs 2.696969... (decimal division)
 *   - Multiplying by 1.0 converts the expression to float, ensuring decimal precision
 *   - Alternative: CAST(... AS DECIMAL) or CAST(... AS FLOAT) would also work
 *   - In modern MySQL (5.7+), this is less critical but still a best practice
 */

# Write your MySQL query statement below
SELECT 
    ROUND(
        SUM(item_count * order_occurrences) * 1.0 / SUM(order_occurrences),
        2
    ) AS average_items_per_order
FROM Orders;

