"""
LeetCode 2884: Modify Salary Column

Problem:
Write a solution to modify the 'salary' column in a pandas DataFrame.
Double the value of each employee's salary.

Input: employees (pd.DataFrame)
Output: pd.DataFrame - The same DataFrame with the 'salary' column modified

Approach: Direct Column Assignment with Vectorized Operation

Key Insight:
- Use direct assignment to modify an existing column
- employees["salary"] = employees["salary"] * 2
- This modifies the column in-place
- Return the modified DataFrame

Algorithm:
1. Multiply the "salary" column by 2
2. Assign the result back to the "salary" column
3. Return the modified DataFrame

Formula:
  employees["salary"] = employees["salary"] * 2

Time Complexity: O(n)
  - Where n is the number of rows in the DataFrame
  - Need to multiply each salary value by 2
  - Column assignment is O(n) operation

Space Complexity: O(1) additional space
  - Modifies existing column in-place
  - No new columns created
  - Only temporary space for calculation

Example:

  Input DataFrame:
  +----+-------+--------+
  | id | name  | salary |
  +----+-------+--------+
  | 1  | Alice | 5000   |
  | 2  | Bob   | 6000   |
  | 3  | Charlie| 7000  |
  +----+-------+--------+

  After employees["salary"] = employees["salary"] * 2:
  +----+-------+--------+
  | id | name  | salary |
  +----+-------+--------+
  | 1  | Alice | 10000  |  ← doubled
  | 2  | Bob   | 12000  |  ← doubled
  | 3  | Charlie| 14000|  ← doubled
  +----+-------+--------+

  Result: Salary column values are doubled

Another Example:

  Input DataFrame (with different salaries):
  +----+------+--------+
  | id | name | salary |
  +----+------+--------+
  | 1  | Alice| 3000   |
  | 2  | Bob  | 4500   |
  +----+------+--------+

  After operation:
  +----+------+--------+
  | id | name | salary |
  +----+------+--------+
  | 1  | Alice| 6000   |  ← doubled
  | 2  | Bob  | 9000   |  ← doubled
  +----+------+--------+

What is Direct Column Assignment?

  - Direct assignment modifies an existing column
  - Syntax: dataframe["column_name"] = new_values
  - new_values can be a Series, array, or expression
  - Modifies DataFrame in-place

Column Modification:

  - Modifies existing "salary" column
  - Does not create a new column
  - Original values are replaced
  - Column name remains the same

Vectorized Operations:

  - employees["salary"] * 2 is a vectorized operation
  - Applies multiplication to all values in the column
  - Much faster than iterating row by row
  - Leverages NumPy operations under the hood

Why Direct Assignment?

  1. vs creating new column:
     - Modifies existing column (more memory efficient)
     - No new column created
     - Original column structure maintained

  2. vs using assign() method:
     - Direct assignment is simpler and more readable
     - assign() returns new DataFrame: employees.assign(salary=employees["salary"] * 2)
     - Direct assignment modifies in-place (more memory efficient)

  3. vs iterating:
     - Vectorized operation is much faster
     - No need for loops
     - Leverages optimized NumPy operations

Alternative Approaches:

  1. Using assign() method:
     return employees.assign(salary=employees["salary"] * 2)
     - Returns new DataFrame (doesn't modify original)
     - More functional programming style
     - Less memory efficient (creates copy)

  2. Using copy() before assignment:
     result = employees.copy()
     result["salary"] = result["salary"] * 2
     return result
     - Preserves original DataFrame
     - More memory usage
     - Useful if original should not be modified

  3. Using apply() with lambda:
     employees["salary"] = employees["salary"].apply(lambda x: x * 2)
     - Works but less efficient than vectorized operation
     - apply() is slower for simple operations
     - Vectorized operations are preferred

  4. Using mul() method:
     employees["salary"] = employees["salary"].mul(2)
     - More explicit method call
     - Same result as multiplication operator
     - Slightly more verbose

Edge Cases:

  1. Empty DataFrame:
     - No rows to modify
     - Result: Empty DataFrame with salary column (unchanged)

  2. Missing "salary" column:
     - Raises KeyError
     - Ensure "salary" column exists

  3. NULL/NaN values in salary:
     - NaN * 2 = NaN
     - NULL values are preserved as NaN
     - Result: salary column has NaN where original was NaN

  4. Zero salary:
     - 0 * 2 = 0
     - Works correctly
     - Result: salary = 0 (unchanged)

  5. Negative salary:
     - Negative * 2 = more negative
     - Works correctly (mathematically)
     - Result: salary is doubled (can be more negative)

  6. Very large salary values:
     - Works correctly
     - May cause overflow if exceeds data type limits
     - Result: salary = 2x original (if within limits)

Data Type Preservation:

  - If salary is int, result is int (if result fits)
  - If salary is float, result is float
  - If result exceeds int range, may convert to float
  - Data type is automatically determined

In-Place Modification:

  - Direct assignment modifies the original DataFrame
  - If original DataFrame should be preserved, use .copy()
  - In-place modification is more memory efficient
  - Problem statement may require modifying original

Column Order:

  - Column order is preserved
  - Only values are modified
  - Column position remains the same

Performance Considerations:

  - Vectorized operations are very fast
  - O(n) time complexity where n is number of rows
  - Much faster than row-by-row iteration
  - Leverages NumPy's optimized operations

Memory Considerations:

  - In-place modification doesn't create new DataFrame
  - Only modifies existing column values
  - More memory efficient than creating copy
  - Original DataFrame structure is reused

Return Value:

  - Returns the modified DataFrame
  - Same object reference (not a copy)
  - Type hint ensures return type is pd.DataFrame
  - Function modifies and returns the same DataFrame

Import Requirements:

  - import pandas as pd: For DataFrame type
  - Type hints use pd.DataFrame for clarity
  - No additional imports needed

Difference from Creating New Column:

  - Creating new column: employees["bonus"] = employees["salary"] * 2
  - Modifying existing: employees["salary"] = employees["salary"] * 2
  - One adds column, other modifies existing
  - Both use same syntax pattern
"""

import pandas as pd


def modifySalaryColumn(employees: pd.DataFrame) -> pd.DataFrame:
    """
    Modify the 'salary' column by doubling each value.
    
    Args:
        employees: A pandas DataFrame with at least a 'salary' column
        
    Returns:
        The same DataFrame with the 'salary' column values doubled
        
    Example:
        >>> df = pd.DataFrame({
        ...     'id': [1, 2],
        ...     'name': ['Alice', 'Bob'],
        ...     'salary': [5000, 6000]
        ... })
        >>> modifySalaryColumn(df)
           id   name  salary
        0   1  Alice   10000
        1   2    Bob   12000
    """
    employees["salary"] = employees["salary"] * 2
    return employees

