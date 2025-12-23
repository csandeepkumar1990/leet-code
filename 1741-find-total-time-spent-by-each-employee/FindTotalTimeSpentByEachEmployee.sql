/*
 * LeetCode 1741: Find Total Time Spent by Each Employee
 *
 * Problem:
 * Write a solution to calculate the total time in minutes spent by each employee
 * on each day at the office. Note that within one day, an employee can enter and
 * leave more than once. The time spent in the office for a single entry is
 * out_time - in_time.
 *
 * Return the result table in any order.
 *
 * Table: Employees
 * +-------------+------+
 * | Column Name | Type |
 * +-------------+------+
 * | emp_id      | int  |
 * | event_day   | date |
 * | in_time     | int  |
 * | out_time    | int  |
 * +-------------+------+
 * (emp_id, event_day, in_time) is the primary key of this table.
 * The table shows the employees' entries and exits in an office.
 * event_day is the day at which this event happened, in_time is the minute at
 * which the employee entered the office, and out_time is the minute at which
 * they left the office.
 * in_time and out_time are between 1 and 1440.
 * It is guaranteed that no two events on the same day intersect in time, and
 * in_time < out_time.
 *
 * Approach: Group and Sum Time Differences
 *
 * Key Insight:
 * - For each entry/exit, time spent = out_time - in_time
 * - An employee can have multiple entries/exits in one day
 * - Need to sum all time differences for each employee per day
 * - Group by emp_id and event_day to aggregate
 *
 * Algorithm:
 * 1. Calculate time difference: out_time - in_time for each row
 * 2. GROUP BY emp_id, event_day to aggregate per employee per day
 * 3. SUM(out_time - in_time) to get total time spent
 * 4. Rename event_day to day in output
 *
 * Why SUM()?
 * - An employee can enter and leave multiple times in one day
 * - Each entry/exit contributes (out_time - in_time) minutes
 * - SUM() adds up all these durations for the total time per day
 *
 * Why GROUP BY emp_id, event_day?
 * - Need to aggregate time for each unique combination of employee and day
 * - Without grouping, we'd get one row per entry/exit
 * - With grouping, we get one row per employee per day with total time
 *
 * Example:
 *
 *   Employees:
 *   +--------+------------+---------+----------+
 *   | emp_id | event_day  | in_time | out_time |
 *   +--------+------------+---------+----------+
 *   | 1      | 2020-11-28 | 4       | 32       |
 *   | 1      | 2020-11-28 | 55      | 200      |
 *   | 1      | 2020-12-03 | 1       | 42       |
 *   | 2      | 2020-11-28 | 3       | 33       |
 *   | 2      | 2020-12-09 | 47      | 74       |
 *   +--------+------------+---------+----------+
 *
 *   Step 1: Calculate time differences:
 *   - Employee 1, 2020-11-28, entry 1: 32 - 4 = 28 minutes
 *   - Employee 1, 2020-11-28, entry 2: 200 - 55 = 145 minutes
 *   - Employee 1, 2020-12-03: 42 - 1 = 41 minutes
 *   - Employee 2, 2020-11-28: 33 - 3 = 30 minutes
 *   - Employee 2, 2020-12-09: 74 - 47 = 27 minutes
 *
 *   Step 2: Group and Sum:
 *   - Employee 1, 2020-11-28: 28 + 145 = 173 minutes
 *   - Employee 1, 2020-12-03: 41 minutes
 *   - Employee 2, 2020-11-28: 30 minutes
 *   - Employee 2, 2020-12-09: 27 minutes
 *
 *   Result:
 *   +------------+--------+------------+
 *   | day        | emp_id | total_time |
 *   +------------+--------+------------+
 *   | 2020-11-28 | 1      | 173        |
 *   | 2020-11-28 | 2      | 30         |
 *   | 2020-12-03 | 1      | 41         |
 *   | 2020-12-09 | 2      | 27         |
 *   +------------+--------+------------+
 *
 * Important Notes:
 * - Time is measured in minutes (integers)
 * - Multiple entries/exits per day are summed together
 * - out_time - in_time gives duration for each entry
 * - SUM() aggregates all durations for each employee-day combination
 * - GROUP BY ensures one row per employee per day
 */

# Write your MySQL query statement below

SELECT
    event_day AS day,
    emp_id,
    SUM(out_time - in_time) AS total_time
FROM Employees
GROUP BY emp_id, event_day;

