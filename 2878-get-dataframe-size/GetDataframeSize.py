"""
LeetCode 2878: Get DataFrame Size

Problem:
Write a solution to get the size (number of rows and columns) of a pandas DataFrame.
Return a list containing [number of rows, number of columns].

Input: players (pd.DataFrame)
Output: List[int] - [number of rows, number of columns]

Approach: Using pandas DataFrame.shape Attribute

Key Insight:
- pandas DataFrame has a `shape` attribute that returns a tuple (rows, columns)
- shape[0] gives the number of rows
- shape[1] gives the number of columns
- Return as a list [rows, columns]

Algorithm:
1. Access the shape attribute of the DataFrame
2. Extract number of rows: shape[0]
3. Extract number of columns: shape[1]
4. Return as a list [rows, columns]

Formula:
  result = [dataframe.shape[0], dataframe.shape[1]]

Time Complexity: O(1)
  - Accessing shape attribute is a constant-time operation
  - pandas stores shape metadata, no need to count rows/columns

Space Complexity: O(1)
  - Only creating a list with 2 integers
  - Constant space regardless of DataFrame size

Example:

  Input DataFrame:
  +----+-------+------+
  | id | name  | age  |
  +----+-------+------+
  | 1  | Alice | 25   |
  | 2  | Bob   | 30   |
  | 3  | Charlie| 35  |
  +----+-------+------+

  players.shape = (3, 3)  # (rows, columns)
  players.shape[0] = 3    # number of rows
  players.shape[1] = 3    # number of columns

  Result: [3, 3]

Another Example:

  Input DataFrame (empty):
  Empty DataFrame
  Columns: []
  Index: []

  players.shape = (0, 0)
  players.shape[0] = 0
  players.shape[1] = 0

  Result: [0, 0]

Another Example:

  Input DataFrame (single row):
  +----+------+
  | id | name |
  +----+------+
  | 1  | Alice|
  +----+------+

  players.shape = (1, 2)
  Result: [1, 2]

What is DataFrame.shape?

  - shape is a property of pandas DataFrame
  - Returns a tuple (n_rows, n_columns)
  - n_rows: number of rows in the DataFrame
  - n_columns: number of columns in the DataFrame
  - Accessible via: dataframe.shape or dataframe.shape[0], dataframe.shape[1]

Why Use shape Instead of len()?

  - len(dataframe) only gives number of rows
  - Would need separate method to get columns: len(dataframe.columns)
  - shape provides both dimensions in one attribute
  - More efficient and cleaner code

Alternative Approaches:

  1. Using len() for rows and len(columns) for columns:
     return [len(players), len(players.columns)]
     - Works but less efficient (two operations)
     - len(players) counts rows, len(players.columns) counts columns

  2. Using shape tuple directly:
     return list(players.shape)
     - Converts tuple to list directly
     - More concise but less explicit
     - Same result: [rows, columns]

  3. Using shape with unpacking:
     rows, cols = players.shape
     return [rows, cols]
     - More verbose but clearer intent
     - Explicit variable names improve readability

Edge Cases:

  1. Empty DataFrame:
     - shape = (0, 0)
     - Result: [0, 0]

  2. DataFrame with no columns (only index):
     - shape = (n, 0) where n is number of rows
     - Result: [n, 0]

  3. DataFrame with no rows (only columns):
     - shape = (0, m) where m is number of columns
     - Result: [0, m]

  4. Single row, single column:
     - shape = (1, 1)
     - Result: [1, 1]

  5. Large DataFrame:
     - shape works efficiently regardless of size
     - No performance degradation for large DataFrames

Performance Considerations:

  - shape is a cached property in pandas
  - No need to iterate through DataFrame
  - O(1) time complexity
  - Very efficient for large DataFrames

pandas DataFrame Structure:

  DataFrame consists of:
  - Index: row labels (can be default integer index or custom)
  - Columns: column names
  - Data: actual data values

  shape returns (number of index entries, number of columns)

Type Hints:

  - Function signature uses type hints
  - players: pd.DataFrame - input parameter
  - -> List[int] - return type annotation
  - Helps with code clarity and IDE support

Import Requirements:

  - import pandas as pd: For DataFrame type
  - from typing import List: For List type hint (Python < 3.9)
  - In Python 3.9+, can use list[int] instead of List[int]

Why Return List Instead of Tuple?

  - Problem specification requires List[int]
  - List is mutable, tuple is immutable
  - Both work, but following problem requirements
  - Can easily convert: list(players.shape) or [players.shape[0], players.shape[1]]
"""

from typing import List
import pandas as pd


def getDataframeSize(players: pd.DataFrame) -> List[int]:
    """
    Get the size of a pandas DataFrame.
    
    Args:
        players: A pandas DataFrame
        
    Returns:
        A list containing [number of rows, number of columns]
        
    Example:
        >>> df = pd.DataFrame({'id': [1, 2], 'name': ['Alice', 'Bob']})
        >>> getDataframeSize(df)
        [2, 2]
    """
    return [players.shape[0], players.shape[1]]

