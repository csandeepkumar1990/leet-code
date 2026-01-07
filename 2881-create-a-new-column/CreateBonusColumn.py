"""
LeetCode 2881: Create a New Column

Problem:
Write a solution to create a new column in a pandas DataFrame.
Create a column named 'bonus' that contains the value of each employee's salary multiplied by 2.

Input: employees (pd.DataFrame)
Output: pd.DataFrame - The same DataFrame with a new 'bonus' column added

Approach: Direct Column Assignment

Key Insight:
- Use direct assignment to create a new column
- employees["bonus"] = employees["salary"] * 2
- This modifies the DataFrame in-place and adds the new column
- Return the modified DataFrame

Algorithm:
1. Create new column "bonus" by multiplying "salary" column by 2
2. Return the modified DataFrame

Formula:
  employees["bonus"] = employees["salary"] * 2

Time Complexity: O(n)
  - Where n is the number of rows in the DataFrame
  - Need to multiply each salary value by 2
  - Column assignment is O(n) operation

Space Complexity: O(n)
  - Creates a new column with n values
  - Additional space for the bonus column
  - Original DataFrame is modified in-place

Example:

  Input DataFrame:
  +----+-------+--------+
  | id | name  | salary |
  +----+-------+--------+
  | 1  | Alice | 5000   |
  | 2  | Bob   | 6000   |
  | 3  | Charlie| 7000  |
  +----+-------+--------+

  After employees["bonus"] = employees["salary"] * 2:
  +----+-------+--------+-------+
  | id | name  | salary | bonus |
  +----+-------+--------+-------+
  | 1  | Alice | 5000   | 10000 |
  | 2  | Bob   | 6000   | 12000 |
  | 3  | Charlie| 7000  | 14000 |
  +----+-------+--------+-------+

  Result: DataFrame with new 'bonus' column

Another Example:

  Input DataFrame (with different salaries):
  +----+------+--------+
  | id | name | salary |
  +----+------+--------+
  | 1  | Alice| 3000   |
  | 2  | Bob  | 4500   |
  +----+------+--------+

  After operation:
  +----+------+--------+-------+
  | id | name | salary | bonus |
  +----+------+--------+-------+
  | 1  | Alice| 3000   | 6000  |
  | 2  | Bob  | 4500   | 9000  |
  +----+------+--------+-------+

What is Direct Column Assignment?

  - Direct assignment creates a new column or modifies existing column
  - Syntax: dataframe["column_name"] = values
  - values can be a Series, array, or expression
  - Modifies DataFrame in-place (unless using .copy())

Column Creation:

  - If "bonus" column doesn't exist, it's created
  - If "bonus" column exists, it's overwritten
  - New column is added to the right of existing columns
  - Column order: original columns, then new column

Vectorized Operations:

  - employees["salary"] * 2 is a vectorized operation
  - Applies multiplication to all values in the column
  - Much faster than iterating row by row
  - Leverages NumPy operations under the hood

Why Direct Assignment?

  1. vs assign() method:
     - Direct assignment is simpler and more readable
     - assign() returns new DataFrame: employees.assign(bonus=employees["salary"] * 2)
     - Direct assignment modifies in-place (more memory efficient)

  2. vs creating new DataFrame:
     - Direct assignment is more efficient
     - No need to copy entire DataFrame
     - Modifies existing DataFrame structure

  3. vs iterating:
     - Vectorized operation is much faster
     - No need for loops
     - Leverages optimized NumPy operations

Alternative Approaches:

  1. Using assign() method:
     return employees.assign(bonus=employees["salary"] * 2)
     - Returns new DataFrame (doesn't modify original)
     - More functional programming style
     - Less memory efficient (creates copy)

  2. Using copy() before assignment:
     result = employees.copy()
     result["bonus"] = result["salary"] * 2
     return result
     - Preserves original DataFrame
     - More memory usage
     - Useful if original should not be modified

  3. Using apply() with lambda:
     employees["bonus"] = employees["salary"].apply(lambda x: x * 2)
     - Works but less efficient than vectorized operation
     - apply() is slower for simple operations
     - Vectorized operations are preferred

Edge Cases:

  1. Empty DataFrame:
     - Creates empty "bonus" column
     - Result: DataFrame with "bonus" column (empty)

  2. Missing "salary" column:
     - Raises KeyError
     - Ensure "salary" column exists

  3. NULL/NaN values in salary:
     - NaN * 2 = NaN
     - NULL values are preserved as NaN
     - Result: bonus column has NaN where salary is NaN

  4. Zero salary:
     - 0 * 2 = 0
     - Works correctly
     - Result: bonus = 0

  5. Negative salary:
     - Negative * 2 = more negative
     - Works correctly (mathematically)
     - Result: bonus is 2x salary (can be negative)

  6. Very large salary values:
     - Works correctly
     - May cause overflow if exceeds data type limits
     - Result: bonus = 2x salary (if within limits)

Data Type Preservation:

  - If salary is int, bonus is int (if result fits)
  - If salary is float, bonus is float
  - If result exceeds int range, may convert to float
  - Data type is automatically determined

In-Place Modification:

  - Direct assignment modifies the original DataFrame
  - If original DataFrame should be preserved, use .copy()
  - In-place modification is more memory efficient
  - Problem statement may require modifying original

Column Order:

  - New column is added to the right
  - Original column order is preserved
  - New column appears after all original columns
  - Order: [original columns..., new_column]

Performance Considerations:

  - Vectorized operations are very fast
  - O(n) time complexity where n is number of rows
  - Much faster than row-by-row iteration
  - Leverages NumPy's optimized operations

Memory Considerations:

  - In-place modification doesn't create new DataFrame
  - Only adds one new column
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

Multiple Column Creation:

  - Can create multiple columns in one operation
  - Example: employees["bonus"] = employees["salary"] * 2
  - Can chain operations or create multiple columns separately
  - Each assignment is independent
"""

import pandas as pd


def createBonusColumn(employees: pd.DataFrame) -> pd.DataFrame:
    """
    Create a new 'bonus' column that is 2 times the 'salary' column.
    
    Args:
        employees: A pandas DataFrame with at least a 'salary' column
        
    Returns:
        The same DataFrame with a new 'bonus' column added
        
    Example:
        >>> df = pd.DataFrame({
        ...     'id': [1, 2],
        ...     'name': ['Alice', 'Bob'],
        ...     'salary': [5000, 6000]
        ... })
        >>> createBonusColumn(df)
           id   name  salary  bonus
        0   1  Alice    5000  10000
        1   2    Bob    6000  12000
    """
    employees["bonus"] = employees["salary"] * 2
    return employees

