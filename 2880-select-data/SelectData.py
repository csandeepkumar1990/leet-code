"""
LeetCode 2880: Select Data

Problem:
Write a solution to select specific rows and columns from a pandas DataFrame.
Select rows where student_id equals 101, and return only the 'name' and 'age' columns.

Input: students (pd.DataFrame)
Output: pd.DataFrame - A DataFrame containing filtered rows with selected columns

Approach: Using pandas DataFrame.loc[] for Conditional Selection

Key Insight:
- Use loc[] for label-based selection with boolean indexing
- Filter rows where student_id == 101
- Select only 'name' and 'age' columns
- Returns a new DataFrame with filtered rows and selected columns

Algorithm:
1. Use loc[] with boolean condition: students["student_id"] == 101
2. Select specific columns: ["name", "age"]
3. Return the filtered and selected DataFrame

Formula:
  result = dataframe.loc[condition, columns]
  where:
    condition = dataframe["student_id"] == 101
    columns = ["name", "age"]

Time Complexity: O(n)
  - Where n is the number of rows in the DataFrame
  - Need to check each row for the condition
  - Column selection is O(1) operation

Space Complexity: O(k)
  - Where k is the number of rows matching the condition
  - Creates a new DataFrame with k rows and 2 columns
  - Copies the data for matching rows

Example:

  Input DataFrame:
  +------------+-------+-----+
  | student_id | name  | age |
  +------------+-------+-----+
  | 101        | Alice | 20  |
  | 102        | Bob   | 21  |
  | 101        | Charlie| 22 |
  | 103        | David | 23  |
  +------------+-------+-----+

  Step 1 - Boolean condition: students["student_id"] == 101
  +------------+-------+
  | student_id | bool  |
  +------------+-------+
  | 101        | True  |
  | 102        | False |
  | 101        | True  |
  | 103        | False |
  +------------+-------+

  Step 2 - Filter and select: loc[condition, ["name", "age"]]
  Result:
  +-------+-----+
  | name  | age |
  +-------+-----+
  | Alice | 20  |
  | Charlie| 22 |
  +-------+-----+

Another Example:

  Input DataFrame (no matching rows):
  +------------+-------+-----+
  | student_id | name  | age |
  +------------+-------+-----+
  | 102        | Bob   | 21  |
  | 103        | David | 23  |
  +------------+-------+-----+

  Result:
  Empty DataFrame
  Columns: [name, age]
  Index: []

What is DataFrame.loc[]?

  - loc[] is a label-based data selection method
  - Syntax: dataframe.loc[row_condition, column_selection]
  - row_condition: Boolean array or condition to filter rows
  - column_selection: List of column names or single column name
  - Returns a new DataFrame (does not modify original)

Boolean Indexing:

  - students["student_id"] == 101 creates a boolean Series
  - True for rows where student_id equals 101
  - False for all other rows
  - Used to filter rows in loc[]

Column Selection:

  - ["name", "age"] selects only these two columns
  - Can select single column: ["name"]
  - Can select all columns: [:] or omit column parameter
  - Order matters: columns appear in specified order

Why Use loc[] Instead of Other Methods?

  1. vs query():
     - loc[] is more explicit and readable
     - query() uses string expressions: students.query("student_id == 101")
     - loc[] is more flexible for complex conditions

  2. vs boolean indexing with column selection:
     - students[students["student_id"] == 101][["name", "age"]]
     - Works but creates intermediate DataFrame
     - loc[] is more efficient (single operation)

  3. vs iloc[]:
     - iloc[] uses integer positions, loc[] uses labels/conditions
     - loc[] is better for conditional filtering
     - iloc[] is better for position-based selection

Alternative Approaches:

  1. Using query():
     return students.query("student_id == 101")[["name", "age"]]
     - More concise for simple conditions
     - String-based syntax
     - Less flexible for complex conditions

  2. Using boolean indexing:
     return students[students["student_id"] == 101][["name", "age"]]
     - Works but less efficient
     - Creates intermediate DataFrame
     - Two-step operation

  3. Using isin() for multiple values:
     return students.loc[students["student_id"].isin([101]), ["name", "age"]]
     - Useful if filtering for multiple student_id values
     - More flexible for multiple conditions

Edge Cases:

  1. No rows match condition:
     - Returns empty DataFrame with columns ["name", "age"]
     - Index is empty
     - Result: Empty DataFrame

  2. Multiple rows match condition:
     - Returns all matching rows
     - All rows with student_id == 101 are included
     - Result: DataFrame with multiple rows

  3. Single row matches condition:
     - Returns DataFrame with one row
     - Still a DataFrame (not a Series)
     - Result: DataFrame with 1 row

  4. Missing columns:
     - If "name" or "age" don't exist, raises KeyError
     - Ensure columns exist before selection

  5. student_id column doesn't exist:
     - students["student_id"] raises KeyError
     - Ensure column exists before filtering

  6. NULL/NaN values in student_id:
     - student_id == 101 returns False for NaN values
     - NaN != 101, so NaN rows are excluded
     - Use isna() or notna() if needed to handle NaN

Performance Considerations:

  - Boolean indexing requires scanning all rows: O(n)
  - Column selection is efficient: O(1)
  - Consider indexing on student_id for large DataFrames
  - loc[] is optimized for this type of operation

Index Preservation:

  - loc[] preserves original index values
  - If original index is [0, 1, 2, 3], filtered result keeps matching indices
  - Example: If rows 0 and 2 match, result has index [0, 2]

Return Type:

  - loc[] always returns a DataFrame
  - Even if only one row, still returns DataFrame
  - Maintains DataFrame structure
  - Type hint ensures return type is pd.DataFrame

Column Order:

  - Columns appear in the order specified
  - ["name", "age"] → columns in that order
  - ["age", "name"] → columns in reverse order
  - Order matters for output consistency

Multiple Conditions:

  - Can combine conditions with & (and), | (or), ~ (not)
  - Example: students.loc[(students["student_id"] == 101) & (students["age"] > 20), ["name", "age"]]
  - Use parentheses to group conditions correctly

Data Type Preservation:

  - Column data types are preserved
  - If "age" is int, result still has int type
  - If "name" is string, result still has string type
  - No type conversion occurs

Import Requirements:

  - import pandas as pd: For DataFrame type
  - Type hints use pd.DataFrame for clarity
"""

import pandas as pd


def selectData(students: pd.DataFrame) -> pd.DataFrame:
    """
    Select rows where student_id equals 101 and return only 'name' and 'age' columns.
    
    Args:
        students: A pandas DataFrame with columns including 'student_id', 'name', and 'age'
        
    Returns:
        A DataFrame containing rows where student_id == 101, with only 'name' and 'age' columns
        
    Example:
        >>> df = pd.DataFrame({
        ...     'student_id': [101, 102, 101],
        ...     'name': ['Alice', 'Bob', 'Charlie'],
        ...     'age': [20, 21, 22]
        ... })
        >>> selectData(df)
             name  age
        0   Alice   20
        2  Charlie   22
    """
    return students.loc[students["student_id"] == 101, ["name", "age"]]

