"""
LeetCode 2879: Select the First Three Rows

Problem:
Write a solution to select the first three rows of a pandas DataFrame.

Input: employees (pd.DataFrame)
Output: pd.DataFrame - A DataFrame containing the first 3 rows

Approach: Using pandas DataFrame.head() Method

Key Insight:
- pandas DataFrame has a `head()` method that returns the first n rows
- head(3) returns the first 3 rows
- If DataFrame has fewer than 3 rows, returns all available rows
- Returns a new DataFrame (does not modify original)

Algorithm:
1. Call head(3) on the DataFrame
2. Return the result (first 3 rows)

Formula:
  result = dataframe.head(3)

Time Complexity: O(1) to O(k)
  - Where k is the number of rows to return (3 in this case)
  - head() only needs to slice the first few rows
  - Very efficient operation

Space Complexity: O(k)
  - Where k is the number of rows returned (3)
  - Creates a new DataFrame with k rows
  - Copies the data for those rows

Example:

  Input DataFrame:
  +----+-------+--------+
  | id | name  | salary |
  +----+-------+--------+
  | 1  | Alice | 5000   |
  | 2  | Bob   | 6000   |
  | 3  | Charlie| 7000 |
  | 4  | David | 8000   |
  | 5  | Eve   | 9000   |
  +----+-------+--------+

  employees.head(3) returns:
  +----+-------+--------+
  | id | name  | salary |
  +----+-------+--------+
  | 1  | Alice | 5000   |
  | 2  | Bob   | 6000   |
  | 3  | Charlie| 7000 |
  +----+-------+--------+

Another Example:

  Input DataFrame (only 2 rows):
  +----+------+--------+
  | id | name | salary |
  +----+------+--------+
  | 1  | Alice| 5000   |
  | 2  | Bob  | 6000   |
  +----+------+--------+

  employees.head(3) returns:
  +----+------+--------+
  | id | name | salary |
  +----+------+--------+
  | 1  | Alice| 5000   |
  | 2  | Bob  | 6000   |
  +----+------+--------+
  (Returns all available rows if fewer than 3)

What is DataFrame.head()?

  - head() is a method of pandas DataFrame
  - Returns the first n rows (default n=5)
  - Syntax: dataframe.head(n) where n is number of rows
  - If n > number of rows, returns all rows
  - Returns a new DataFrame (does not modify original)

Why Use head() Instead of Slicing?

  - head(3) is more readable and explicit
  - Equivalent to employees.iloc[0:3] or employees[:3]
  - head() is the idiomatic pandas way
  - More semantic: clearly indicates "first n rows"

Alternative Approaches:

  1. Using iloc slicing:
     return employees.iloc[0:3]
     - Works the same way
     - More explicit about index-based selection
     - head(3) is more concise

  2. Using integer slicing:
     return employees[:3]
     - Python-style slicing
     - Works but less explicit
     - head(3) is more readable

  3. Using loc with range:
     return employees.loc[0:2]
     - Works if index is integer-based
     - May not work if index is not sequential
     - head() is more robust

Edge Cases:

  1. Empty DataFrame:
     - head(3) returns empty DataFrame
     - Result: Empty DataFrame with same columns

  2. DataFrame with 1 row:
     - head(3) returns that 1 row
     - Result: DataFrame with 1 row

  3. DataFrame with 2 rows:
     - head(3) returns both rows
     - Result: DataFrame with 2 rows

  4. DataFrame with exactly 3 rows:
     - head(3) returns all 3 rows
     - Result: DataFrame with 3 rows

  5. DataFrame with more than 3 rows:
     - head(3) returns first 3 rows
     - Result: DataFrame with 3 rows

  6. DataFrame with duplicate indices:
     - head(3) returns first 3 rows by position
     - Not affected by duplicate indices
     - Works correctly

Performance Considerations:

  - head() is very efficient
  - Only needs to access first few rows
  - No need to process entire DataFrame
  - O(1) to O(k) where k is number of rows returned
  - Much faster than operations that process all rows

DataFrame Index Preservation:

  - head() preserves the original index values
  - If original index is [0, 1, 2, 3, 4], head(3) returns index [0, 1, 2]
  - If original index is custom (e.g., ['a', 'b', 'c', 'd']), 
    head(3) returns index ['a', 'b', 'c']

Column Preservation:

  - head() preserves all columns
  - Returns DataFrame with same column names and types
  - No columns are dropped

Return Type:

  - head() always returns a DataFrame
  - Even if only one row, still returns DataFrame (not Series)
  - Maintains DataFrame structure

Why Return Type is pd.DataFrame?

  - head() always returns a DataFrame
  - Type hint helps with IDE support and type checking
  - Makes function signature clear
  - Ensures return value is always a DataFrame

Import Requirements:

  - import pandas as pd: For DataFrame type
  - Type hints use pd.DataFrame for clarity

Common Use Cases:

  - Quick preview of data
  - Testing with small subset
  - Displaying sample rows
  - Debugging data structure
"""

import pandas as pd


def selectFirstRows(employees: pd.DataFrame) -> pd.DataFrame:
    """
    Select the first three rows of a pandas DataFrame.
    
    Args:
        employees: A pandas DataFrame
        
    Returns:
        A DataFrame containing the first 3 rows (or fewer if DataFrame has less than 3 rows)
        
    Example:
        >>> df = pd.DataFrame({'id': [1, 2, 3, 4], 'name': ['Alice', 'Bob', 'Charlie', 'David']})
        >>> selectFirstRows(df)
           id     name
        0   1    Alice
        1   2      Bob
        2   3  Charlie
    """
    return employees.head(3)

