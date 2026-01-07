"""
LeetCode 2883: Drop Missing Data

Problem:
Write a solution to remove rows from a pandas DataFrame that have missing (NULL/NaN) values
in the 'name' column.

Input: students (pd.DataFrame)
Output: pd.DataFrame - A DataFrame with rows containing missing names removed

Approach: Using pandas DataFrame.dropna() Method

Key Insight:
- Use dropna() method to remove rows with missing values
- Specify subset=["name"] to check only the name column
- Rows with NULL/NaN in name column are removed
- Returns a new DataFrame (does not modify original by default)

Algorithm:
1. Call dropna() with subset=["name"]
2. Rows with missing name values are removed
3. Return the cleaned DataFrame

Formula:
  result = dataframe.dropna(subset=["name"])

Time Complexity: O(n)
  - Where n is the number of rows in the DataFrame
  - Need to check each row for missing values
  - Linear scan through the data

Space Complexity: O(k)
  - Where k is the number of rows without missing names
  - Creates a new DataFrame with k rows
  - Stores only rows with valid names

Example:

  Input DataFrame:
  +----+-------+-----+
  | id | name  | age |
  +----+-------+-----+
  | 1  | Alice | 20  |
  | 2  | NULL  | 21  |  ← missing name
  | 3  | Bob   | 22  |
  | 4  | NULL  | 23  |  ← missing name
  | 5  | Charlie| 24 |
  +----+-------+-----+

  After dropna(subset=["name"]):
  +----+-------+-----+
  | id | name  | age |
  +----+-------+-----+
  | 1  | Alice | 20  |
  | 3  | Bob   | 22  |
  | 5  | Charlie| 24 |
  +----+-------+-----+

  Result: Rows with missing names removed

Another Example:

  Input DataFrame (no missing names):
  +----+-------+-----+
  | id | name  | age |
  +----+-------+-----+
  | 1  | Alice | 20  |
  | 2  | Bob   | 21  |
  +----+-------+-----+

  Result: Same DataFrame (no missing values to remove)

What is dropna()?

  - dropna() is a method of pandas DataFrame
  - Removes rows or columns with missing values (NULL/NaN)
  - Returns a new DataFrame by default (doesn't modify original)
  - Can specify which columns to check for missing values
  - Can specify how to handle missing values (any vs all)

Parameters:

  1. subset: List of column names to check for missing values
     - subset=["name"]: Check only name column
     - subset=None: Check all columns (default)
     - Can specify multiple columns: subset=["name", "age"]

  2. how: How to determine if row should be dropped
     - how="any": Drop if ANY column has missing value (default)
     - how="all": Drop only if ALL columns have missing values

  3. thresh: Minimum number of non-NA values required
     - thresh=2: Keep row if at least 2 non-NA values
     - More flexible than how parameter

Why Use subset=["name"]?

  - Only checks name column for missing values
  - Other columns can have missing values (ignored)
  - More targeted cleaning
  - Example: Missing age is OK, but missing name is not

What are Missing Values?

  - NULL: SQL NULL value
  - NaN: Not a Number (pandas representation)
  - None: Python None value
  - All treated as missing by dropna()

Alternative Approaches:

  1. Using boolean indexing with notna():
     return students[students["name"].notna()]
     - notna() returns True for non-missing values
     - Boolean indexing filters rows
     - More explicit but less concise

  2. Using boolean indexing with notnull():
     return students[students["name"].notnull()]
     - notnull() is alias for notna()
     - Same result as notna()
     - Both work identically

  3. Using query():
     return students.query("name.notna()")
     - Query syntax for filtering
     - Less common for this use case
     - More verbose

  4. Using fillna() instead of dropna():
     return students.fillna({"name": "Unknown"})
     - Fills missing values instead of removing
     - Different approach (imputation vs removal)
     - May not be appropriate for this problem

Edge Cases:

  1. No missing values:
     - Returns original DataFrame unchanged
     - All rows have valid names
     - Result: Same as input

  2. All rows have missing names:
     - Returns empty DataFrame
     - All rows are removed
     - Result: Empty DataFrame with same columns

  3. Empty DataFrame:
     - Returns empty DataFrame
     - No rows to process
     - Result: Empty DataFrame

  4. Empty string vs NULL:
     - Empty string "" is NOT considered missing
     - Only NULL/NaN/None are missing
     - Empty string rows are kept
     - Use additional filter if empty strings should be removed

  5. Whitespace-only strings:
     - "   " (whitespace) is NOT considered missing
     - Treated as valid value
     - Use .str.strip() if whitespace should be treated as missing

  6. Mixed missing value types:
     - NULL, NaN, None all treated as missing
     - dropna() handles all types
     - Consistent behavior

Index Preservation:

  - dropna() preserves original index values
  - If original index is [0, 1, 2, 3, 4], result keeps matching indices
  - Example: If rows 0, 2, 4 are kept, result has index [0, 2, 4]
  - Can use reset_index() to create new sequential index

Return Type:

  - dropna() returns a new DataFrame
  - Does not modify original DataFrame (by default)
  - Type hint ensures return type is pd.DataFrame
  - Original DataFrame remains unchanged

In-Place Modification:

  - By default, dropna() returns new DataFrame
  - Can use inplace=True to modify original:
    students.dropna(subset=["name"], inplace=True)
  - In-place modification doesn't return DataFrame
  - Current solution uses default (returns new DataFrame)

Performance Considerations:

  - dropna() is optimized for this operation
  - Efficient scanning for missing values
  - More efficient than manual iteration
  - Linear time complexity

Memory Considerations:

  - Returns new DataFrame (default behavior)
  - Original DataFrame is not modified
  - Uses additional memory for result
  - Can use inplace=True to save memory (modifies original)

Multiple Column Checking:

  - Can specify multiple columns in subset
  - Example: subset=["name", "age"]
  - Rows are removed if ANY specified column has missing value
  - More strict filtering

Data Type Preservation:

  - Column data types are preserved
  - No type conversion occurs
  - Original column types maintained

Import Requirements:

  - import pandas as pd: For DataFrame type
  - Type hints use pd.DataFrame for clarity
  - No additional imports needed

Common Use Cases:

  - Data cleaning and preprocessing
  - Removing incomplete records
  - Ensuring required fields are present
  - Data quality assurance

Difference from drop_duplicates():

  - drop_duplicates(): Removes duplicate rows
  - dropna(): Removes rows with missing values
  - Different purposes but similar syntax
  - Both return new DataFrame by default
"""

import pandas as pd


def dropMissingData(students: pd.DataFrame) -> pd.DataFrame:
    """
    Remove rows from the DataFrame that have missing (NULL/NaN) values in the 'name' column.
    
    Args:
        students: A pandas DataFrame with at least a 'name' column
        
    Returns:
        A DataFrame with rows containing missing names removed
        
    Example:
        >>> df = pd.DataFrame({
        ...     'id': [1, 2, 3],
        ...     'name': ['Alice', None, 'Bob'],
        ...     'age': [20, 21, 22]
        ... })
        >>> dropMissingData(df)
           id   name  age
        0   1  Alice   20
        2   3    Bob   22
    """
    return students.dropna(subset=["name"])

