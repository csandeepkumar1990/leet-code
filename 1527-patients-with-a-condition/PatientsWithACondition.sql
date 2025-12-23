/*
 * LeetCode 1527: Patients With a Condition
 *
 * Problem:
 * Write a solution to find the patient_id, patient_name, and conditions 
 * of patients who have been diagnosed with diabetes (code 'DIAB1').
 *
 * A patient's conditions column is a space-separated list of codes, for example:
 *   'DIAB1 HYP2'
 *   'HYP2 DIAB1'
 *   'ASTH1'
 *
 * We need to match rows where 'DIAB1' appears:
 * - As the first condition in the string, or
 * - As a later condition preceded by a space.
 *
 * Table: Patients
 * +--------------+---------+
 * | Column Name  | Type    |
 * +--------------+---------+
 * | patient_id   | int     |
 * | patient_name | varchar |
 * | conditions   | varchar |
 * +--------------+---------+
 * patient_id is the primary key for this table.
 * conditions contains 0 or more space-separated condition codes.
 *
 * Approach: Pattern Match on Conditions
 *
 * Key Insight:
 * - 'conditions' is a single string with space-separated codes.
 * - We must avoid matching partial codes (e.g., 'XDIAB1', 'DIAB12').
 * - A safe pattern is:
 *     - starts with 'DIAB1'  → 'DIAB1%'
 *     - or has ' DIAB1'     → '% DIAB1%'
 *
 * Algorithm:
 * 1. Select all columns from Patients.
 * 2. Filter with WHERE:
 *      conditions LIKE 'DIAB1%'
 *      OR conditions LIKE '% DIAB1%'.
 *
 * This ensures 'DIAB1' appears as a whole token at the start or after a space.
 */

# Write your MySQL query statement below
SELECT *
FROM Patients
WHERE conditions LIKE 'DIAB1%'
   OR conditions LIKE '% DIAB1%';


