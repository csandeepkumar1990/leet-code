/*
 * LeetCode 1623: All Valid Triplets That Can Represent a Country
 * 
 * Problem:
 * There is a country with three schools, where each student is in one school.
 * The country is joining a competition and wants to select one student from each
 * school to form a team.
 * 
 * Write a solution to return all possible triplets representing the country, where
 * the students are from different schools and have different student IDs and names.
 * 
 * Return the result table in any order.
 * 
 * Table: SchoolA
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | student_id    | int     |
 * | student_name  | varchar |
 * +---------------+---------+
 * student_id is the primary key for this table.
 * Each row contains the ID and the name of one student in school A.
 * 
 * Table: SchoolB
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | student_id    | int     |
 * | student_name  | varchar |
 * +---------------+---------+
 * student_id is the primary key for this table.
 * Each row contains the ID and the name of one student in school B.
 * 
 * Table: SchoolC
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | student_id    | int     |
 * | student_name  | varchar |
 * +---------------+---------+
 * student_id is the primary key for this table.
 * Each row contains the ID and the name of one student in school C.
 * 
 * Approach: Multiple CROSS JOINs with WHERE Filters
 * 
 * Key Insight:
 * - Need all combinations of students from three schools (Cartesian product)
 * - Use CROSS JOIN to create all triplets
 * - Filter to ensure all students have different IDs and names
 * - Each student must be unique in both ID and name
 * 
 * Algorithm:
 * 1. CROSS JOIN SchoolA with SchoolB to create all pairs
 * 2. CROSS JOIN result with SchoolC to create all triplets
 * 3. WHERE clause filters to ensure:
 *    - All student IDs are different (a.id <> b.id, a.id <> c.id, b.id <> c.id)
 *    - All student names are different (a.name <> b.name, a.name <> c.name, b.name <> c.name)
 * 4. Return valid triplets
 * 
 * Time Complexity: O(a × b × c), where:
 *   - a = number of students in SchoolA
 *   - b = number of students in SchoolB
 *   - c = number of students in SchoolC
 *   CROSS JOIN creates all combinations, then WHERE filters them.
 * 
 * Space Complexity: O(k), where k is the number of valid triplets.
 *   Result set contains only triplets that pass all filters.
 * 
 * Example:
 * 
 *   SchoolA:
 *   +------------+--------------+
 *   | student_id | student_name |
 *   +------------+--------------+
 *   | 1          | Alice        |
 *   | 2          | Bob          |
 *   +------------+--------------+
 * 
 *   SchoolB:
 *   +------------+--------------+
 *   | student_id | student_name |
 *   +------------+--------------+
 *   | 3          | Tom          |
 *   | 2          | Jerry        |  ← Same ID as SchoolA student 2
 *   | 10         | Alice        |  ← Same name as SchoolA student 1
 *   +------------+--------------+
 * 
 *   SchoolC:
 *   +------------+--------------+
 *   | student_id | student_name |
 *   +------------+--------------+
 *   | 2          | Tom          |  ← Same ID as SchoolA student 2, same name as SchoolB student 3
 *   | 2          | Jerry        |  ← Same ID as SchoolA student 2, same name as SchoolB student 2
 *   | 10         | Alice        |  ← Same name as SchoolA student 1
 *   | 5          | Bob          |  ← Same name as SchoolA student 2
 *   | 1          | Meir         |
 *   +------------+--------------+
 * 
 *   Step 1 - CROSS JOIN SchoolA × SchoolB:
 *   Creates 2 × 3 = 6 pairs:
 *   (1, Alice, 3, Tom), (1, Alice, 2, Jerry), (1, Alice, 10, Alice),
 *   (2, Bob, 3, Tom), (2, Bob, 2, Jerry), (2, Bob, 10, Alice)
 * 
 *   Step 2 - CROSS JOIN with SchoolC:
 *   Creates 6 × 5 = 30 triplets (before filtering)
 * 
 *   Step 3 - Apply WHERE filters:
 *   Check each triplet for:
 *   - Different IDs: a.id <> b.id AND a.id <> c.id AND b.id <> c.id
 *   - Different names: a.name <> b.name AND a.name <> c.name AND b.name <> c.name
 * 
 *   Valid triplets:
 *   - (1, Alice, 3, Tom, 1, Meir): 
 *     IDs: 1 ≠ 3, 1 ≠ 1 ✗ (a.id = c.id) → Invalid
 *   - (1, Alice, 3, Tom, 5, Bob):
 *     IDs: 1 ≠ 3, 1 ≠ 5, 3 ≠ 5 ✓
 *     Names: Alice ≠ Tom, Alice ≠ Bob, Tom ≠ Bob ✓
 *     → Valid ✓
 *   - (2, Bob, 3, Tom, 1, Meir):
 *     IDs: 2 ≠ 3, 2 ≠ 1, 3 ≠ 1 ✓
 *     Names: Bob ≠ Tom, Bob ≠ Meir, Tom ≠ Meir ✓
 *     → Valid ✓
 * 
 *   Result:
 *   +---------+---------+---------+
 *   | member_A| member_B| member_C|
 *   +---------+---------+---------+
 *   | Alice   | Tom     | Bob     |
 *   | Bob     | Tom     | Meir    |
 *   | ...     | ...     | ...     |
 *   +---------+---------+---------+
 * 
 * Why Multiple CROSS JOINs?
 * 
 *   - Need all combinations of students from three schools
 *   - First CROSS JOIN: SchoolA × SchoolB (all pairs)
 *   - Second CROSS JOIN: (SchoolA × SchoolB) × SchoolC (all triplets)
 *   - Creates Cartesian product: a × b × c combinations
 * 
 * Why Filter on Both ID and Name?
 * 
 *   - Problem requires students to be different in both ID and name
 *   - Two students could have same ID but different names (should be excluded)
 *   - Two students could have same name but different IDs (should be excluded)
 *   - Both conditions must be satisfied for a valid triplet
 * 
 * Filter Conditions Explained:
 * 
 *   ID filters:
 *   - a.student_id <> b.student_id: A and B have different IDs
 *   - a.student_id <> c.student_id: A and C have different IDs
 *   - b.student_id <> c.student_id: B and C have different IDs
 * 
 *   Name filters:
 *   - a.student_name <> b.student_name: A and B have different names
 *   - a.student_name <> c.student_name: A and C have different names
 *   - b.student_name <> c.student_name: B and C have different names
 * 
 * Why All Six Conditions?
 * 
 *   - Need to ensure all three students are pairwise different
 *   - For three students A, B, C:
 *     - A ≠ B (both ID and name)
 *     - A ≠ C (both ID and name)
 *     - B ≠ C (both ID and name)
 *   - This ensures no duplicates in the triplet
 * 
 * Visual Example:
 * 
 *   SchoolA: [1:Alice, 2:Bob]
 *   SchoolB: [3:Tom, 4:Jerry]
 *   SchoolC: [5:Charlie, 6:Diana]
 * 
 *   All combinations (before filtering):
 *   - (1:Alice, 3:Tom, 5:Charlie) ✓ All different
 *   - (1:Alice, 3:Tom, 6:Diana) ✓ All different
 *   - (1:Alice, 4:Jerry, 5:Charlie) ✓ All different
 *   - (1:Alice, 4:Jerry, 6:Diana) ✓ All different
 *   - (2:Bob, 3:Tom, 5:Charlie) ✓ All different
 *   - (2:Bob, 3:Tom, 6:Diana) ✓ All different
 *   - (2:Bob, 4:Jerry, 5:Charlie) ✓ All different
 *   - (2:Bob, 4:Jerry, 6:Diana) ✓ All different
 * 
 *   All 8 triplets are valid (no duplicates in IDs or names)
 * 
 * Edge Cases:
 * 
 *   - All students have unique IDs and names: All combinations are valid
 *   - Some students share IDs: Those combinations are filtered out
 *   - Some students share names: Those combinations are filtered out
 *   - Empty school: No triplets possible
 *   - Single student per school: One triplet if all are different
 * 
 * Performance Considerations:
 * 
 *   - CROSS JOIN creates large intermediate result sets
 *   - For a=10, b=10, c=10: 1,000 combinations before filtering
 *   - WHERE clause filters reduce the result set
 *   - Consider if indexes can help (though CROSS JOIN doesn't use indexes)
 *   - For very large datasets, this approach may be slow
 * 
 * Alternative: Using WHERE with Multiple Conditions
 * 
 *   The current approach is already optimal for this problem.
 *   All conditions are necessary to ensure uniqueness.
 * 
 * Why Not Use DISTINCT?
 * 
 *   - DISTINCT removes duplicate rows from result
 *   - But we need to filter combinations, not remove duplicates
 *   - WHERE clause filters during join, more efficient
 *   - DISTINCT would be applied after creating all combinations (less efficient)
 */

# Write your MySQL query statement below

SELECT 
    a.student_name AS member_A,
    b.student_name AS member_B,
    c.student_name AS member_C
FROM SchoolA a
CROSS JOIN SchoolB b
CROSS JOIN SchoolC c
WHERE a.student_id <> b.student_id
  AND a.student_id <> c.student_id
  AND b.student_id <> c.student_id
  AND a.student_name <> b.student_name
  AND a.student_name <> c.student_name
  AND b.student_name <> c.student_name;

