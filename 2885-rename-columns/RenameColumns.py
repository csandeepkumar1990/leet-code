"""
LeetCode 2885: Rename Columns

Problem:
Write a solution to rename specific columns in a pandas DataFrame.
Rename columns according to the mapping:
  - "id" → "student_id"
  - "first" → "first_name"
  - "last" → "last_name"
  - "age" → "age_in_years"

Input: students (pd.DataFrame)
Output: pd.DataFrame - A DataFrame with renamed columns

Approach: Using pandas DataFrame.rename() Method

Key Insight:
- Use rename() method with columns parameter to rename columns
- Pass a dictionary mapping old names to new names
- Returns a new DataFrame (does not modify original by default)
- Only specified columns are renamed, others remain unchanged

Algorithm:
1. Call rename() with columns parameter
2. Pass dictionary mapping old column names to new names
3. Return the DataFrame with renamed columns

Formula:
  result = dataframe.rename(columns={
      "old_name1": "new_name1",
      "old_name2": "new_name2",
      ...
  })

Time Complexity: O(1)
  - Renaming columns is a metadata operation
  - No data copying required
  - Constant time operation

Space Complexity: O(1) additional space
  - Only column names are changed
  - No data is copied
  - Minimal additional space for new column names

Example:

  Input DataFrame:
  +----+-------+------+-----+
  | id | first | last | age |
  +----+-------+------+-----+
  | 1  | Alice | Smith| 20  |
  | 2  | Bob   | Jones| 21  |
  +----+-------+------+-----+

  After rename(columns={...}):
  +------------+------------+-----------+-------------+
  | student_id | first_name | last_name | age_in_years|
  +------------+------------+-----------+-------------+
  | 1          | Alice      | Smith     | 20          |
  | 2          | Bob        | Jones     | 21          |
  +------------+------------+-----------+-------------+

  Result: Column names changed, data unchanged

Another Example:

  Input DataFrame (with additional columns):
  +----+-------+------+-----+--------+
  | id | first | last | age | grade  |
  +----+-------+------+-----+--------+
  | 1  | Alice | Smith| 20  | A      |
  +----+-------+------+-----+--------+

  After rename:
  +------------+------------+-----------+-------------+-------+
  | student_id | first_name | last_name | age_in_years| grade |
  +------------+------------+-----------+-------------+-------+
  | 1          | Alice      | Smith     | 20          | A     |
  +------------+------------+-----------+-------------+-------+

  Result: Specified columns renamed, "grade" unchanged

What is rename()?

  - rename() is a method of pandas DataFrame
  - Renames columns or index labels
  - Returns a new DataFrame by default (doesn't modify original)
  - Can rename columns, index, or both
  - Uses dictionary mapping for renaming

Parameters:

  1. columns: Dictionary mapping old column names to new names
     - {"old_name": "new_name"}
     - Only specified columns are renamed
     - Columns not in dictionary remain unchanged

  2. inplace: Whether to modify DataFrame in-place
     - inplace=False: Returns new DataFrame (default)
     - inplace=True: Modifies original DataFrame, returns None

  3. axis: Which axis to rename
     - axis="columns" or axis=1: Rename columns (default for columns parameter)
     - axis="index" or axis=0: Rename index labels

Why Use Dictionary Mapping?

  - Clear and explicit mapping
  - Easy to see old → new name relationships
  - Can rename multiple columns in one operation
  - More readable than other methods

Alternative Approaches:

  1. Using inplace=True:
     students.rename(columns={...}, inplace=True)
     return students
     - Modifies original DataFrame
     - More memory efficient
     - Returns same object

  2. Direct column assignment:
     students.columns = ["student_id", "first_name", "last_name", "age_in_years"]
     - Requires knowing exact order of all columns
     - Less flexible
     - Must rename all columns

  3. Using set_axis():
     return students.set_axis(["student_id", "first_name", "last_name", "age_in_years"], axis=1)
     - Similar to direct assignment
     - Requires all column names
     - Less flexible for partial renaming

  4. Using mapper parameter:
     return students.rename(mapper={"id": "student_id", ...}, axis="columns")
     - mapper is alias for columns when axis="columns"
     - Same functionality
     - columns parameter is more explicit

Edge Cases:

  1. Column doesn't exist:
     - If "id" doesn't exist, rename() ignores it
     - No error raised
     - Other columns are renamed normally
     - Result: Only existing columns are renamed

  2. Duplicate column names:
     - If multiple columns have same name, all are renamed
     - rename() handles duplicates
     - Result: All matching columns renamed

  3. Empty DataFrame:
     - Column names are still renamed
     - No data to affect
     - Result: DataFrame with renamed columns (empty)

  4. All columns renamed:
     - All specified columns exist and are renamed
     - Other columns unchanged
     - Result: Partial column rename

  5. No columns to rename:
     - If none of the specified columns exist
     - Returns original DataFrame unchanged
     - Result: Same as input

  6. New name conflicts with existing:
     - If new name already exists, column is overwritten
     - Original column with new name is lost
     - Be careful with name conflicts

Index Preservation:

  - rename() preserves original index
  - Only column names are changed
  - Index values remain unchanged
  - Row order preserved

Return Type:

  - rename() returns a new DataFrame (by default)
  - Does not modify original DataFrame
  - Type hint ensures return type is pd.DataFrame
  - Original DataFrame remains unchanged

In-Place Modification:

  - By default, rename() returns new DataFrame
  - Can use inplace=True to modify original:
    students.rename(columns={...}, inplace=True)
  - In-place modification doesn't return DataFrame
  - Current solution uses default (returns new DataFrame)

Performance Considerations:

  - rename() is very efficient
  - Only changes column name metadata
  - No data copying required
  - O(1) time complexity

Memory Considerations:

  - Returns new DataFrame (default behavior)
  - Original DataFrame is not modified
  - Column names are small, minimal memory overhead
  - Can use inplace=True to save memory (modifies original)

Partial Renaming:

  - Can rename only some columns
  - Columns not in dictionary remain unchanged
  - More flexible than renaming all columns
  - Useful for selective column renaming

Column Order:

  - Column order is preserved
  - Only names are changed
  - Position of columns remains the same

Data Preservation:

  - All data is preserved
  - Only column names change
  - No data transformation occurs
  - Values remain exactly the same

Import Requirements:

  - import pandas as pd: For DataFrame type
  - Type hints use pd.DataFrame for clarity
  - No additional imports needed

Common Use Cases:

  - Standardizing column names
  - Making column names more descriptive
  - Converting to naming conventions
  - Data cleaning and preprocessing
"""

import pandas as pd


def renameColumns(students: pd.DataFrame) -> pd.DataFrame:
    """
    Rename specific columns in the DataFrame according to the mapping:
    - "id" → "student_id"
    - "first" → "first_name"
    - "last" → "last_name"
    - "age" → "age_in_years"
    
    Args:
        students: A pandas DataFrame with columns to be renamed
        
    Returns:
        A DataFrame with renamed columns
        
    Example:
        >>> df = pd.DataFrame({
        ...     'id': [1, 2],
        ...     'first': ['Alice', 'Bob'],
        ...     'last': ['Smith', 'Jones'],
        ...     'age': [20, 21]
        ... })
        >>> renameColumns(df)
           student_id first_name last_name  age_in_years
        0           1      Alice     Smith           20
        1           2        Bob     Jones           21
    """
    return students.rename(columns={
        "id": "student_id",
        "first": "first_name",
        "last": "last_name",
        "age": "age_in_years"
    })

