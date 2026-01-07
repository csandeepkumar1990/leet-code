"""
LeetCode 2888: Reshape Data: Concatenate

Problem:
Write a solution to concatenate two pandas DataFrames vertically (row-wise).
Combine the rows of df1 and df2 into a single DataFrame.

Input: df1 (pd.DataFrame), df2 (pd.DataFrame)
Output: pd.DataFrame - A DataFrame containing all rows from both DataFrames

Approach: Using pandas pd.concat() Function

Key Insight:
- Use pd.concat() to combine DataFrames vertically
- Pass a list of DataFrames to concatenate
- Use ignore_index=True to create a new sequential index
- Returns a new DataFrame with all rows from both inputs

Algorithm:
1. Call pd.concat() with list of DataFrames [df1, df2]
2. Use ignore_index=True to reset index
3. Return the concatenated DataFrame

Formula:
  result = pd.concat([df1, df2], ignore_index=True)

Time Complexity: O(n + m)
  - Where n is number of rows in df1, m is number of rows in df2
  - Need to copy all rows from both DataFrames
  - Linear time complexity

Space Complexity: O(n + m)
  - Creates a new DataFrame with n + m rows
  - Stores all data from both DataFrames
  - Additional space for new index

Example:

  Input DataFrame 1:
  +----+-------+-----+
  | id | name  | age |
  +----+-------+-----+
  | 1  | Alice | 20  |
  | 2  | Bob   | 21  |
  +----+-------+-----+

  Input DataFrame 2:
  +----+-------+-----+
  | id | name  | age |
  +----+-------+-----+
  | 3  | Charlie| 22 |
  | 4  | David | 23  |
  +----+-------+-----+

  After pd.concat([df1, df2], ignore_index=True):
  +----+-------+-----+
  | id | name  | age |
  +----+-------+-----+
  | 1  | Alice | 20  |
  | 2  | Bob   | 21  |
  | 3  | Charlie| 22 |
  | 4  | David | 23  |
  +----+-------+-----+

  Result: All rows combined with new sequential index

Another Example:

  Input DataFrame 1:
  +----+------+
  | id | name |
  +----+------+
  | 1  | Alice|
  +----+------+

  Input DataFrame 2:
  +----+------+
  | id | name |
  +----+------+
  | 2  | Bob  |
  | 3  | Charlie|
  +----+------+

  Result:
  +----+-------+
  | id | name  |
  +----+-------+
  | 1  | Alice |
  | 2  | Bob   |
  | 3  | Charlie|
  +----+-------+

What is pd.concat()?

  - concat() is a pandas function for combining DataFrames
  - Can concatenate vertically (rows) or horizontally (columns)
  - Default axis=0 concatenates vertically (row-wise)
  - Returns a new DataFrame

Parameters:

  1. objs: List of DataFrames to concatenate
     - [df1, df2] - list of DataFrames
     - Can concatenate more than 2 DataFrames

  2. ignore_index: Whether to reset index
     - ignore_index=True: Creates new sequential index (0, 1, 2, ...)
     - ignore_index=False: Preserves original indices (may have duplicates)
     - Default is False

  3. axis: Direction of concatenation
     - axis=0 or "index": Concatenate vertically (row-wise) - default
     - axis=1 or "columns": Concatenate horizontally (column-wise)

Why Use ignore_index=True?

  - Creates clean sequential index (0, 1, 2, ...)
  - Avoids duplicate index values
  - Easier to work with after concatenation
  - More predictable index behavior

Alternative Approaches:

  1. Using ignore_index=False:
     return pd.concat([df1, df2], ignore_index=False)
     - Preserves original indices
     - May have duplicate index values
     - Less clean but preserves original structure

  2. Using append() method (deprecated):
     return df1.append(df2, ignore_index=True)
     - append() is deprecated in newer pandas versions
     - concat() is preferred
     - Same result but less recommended

  3. Using pd.concat() with multiple DataFrames:
     return pd.concat([df1, df2, df3, ...], ignore_index=True)
     - Can concatenate more than 2 DataFrames
     - Same syntax, just add more to list

  4. Manual concatenation with reset_index():
     result = pd.concat([df1, df2])
     return result.reset_index(drop=True)
     - Two-step process
     - Same result as ignore_index=True
     - More verbose

Edge Cases:

  1. Empty DataFrames:
     - concat([empty_df, df2]) returns df2
     - concat([df1, empty_df]) returns df1
     - concat([empty_df, empty_df]) returns empty DataFrame
     - Result: Non-empty DataFrame or empty if both empty

  2. Different column orders:
     - Columns are aligned by name
     - Order in result follows first DataFrame
     - Missing columns filled with NaN

  3. Different columns:
     - If df1 has columns [A, B] and df2 has [B, C]
     - Result has columns [A, B, C]
     - Missing values filled with NaN
     - Union of all columns

  4. Same index values:
     - With ignore_index=True: Creates new index (no issue)
     - With ignore_index=False: Duplicate indices possible
     - ignore_index=True avoids this problem

  5. Different number of columns:
     - All unique columns are included
     - Missing values filled with NaN
     - Result has union of all columns

  6. One DataFrame empty:
     - Returns the non-empty DataFrame
     - With ignore_index=True, index is reset
     - Result: Non-empty DataFrame with reset index

Index Handling:

  - ignore_index=True: Creates new sequential index [0, 1, 2, ...]
  - ignore_index=False: Preserves original indices
  - Original indices from df1 and df2 may overlap
  - ignore_index=True is cleaner for most use cases

Column Alignment:

  - Columns are aligned by name
  - If column exists in both: values are combined
  - If column exists in only one: filled with NaN
  - Result has union of all columns

Data Type Preservation:

  - Column data types are preserved when possible
  - If same column in both DataFrames: type is preserved
  - If column only in one: type is preserved
  - Mixed types may cause conversion

Performance Considerations:

  - concat() is optimized for this operation
  - More efficient than manual row-by-row concatenation
  - O(n + m) time complexity
  - Consider memory usage for large DataFrames

Memory Considerations:

  - Creates a new DataFrame
  - Copies all data from both DataFrames
  - Uses O(n + m) space
  - Original DataFrames are not modified

Return Type:

  - concat() returns a new DataFrame
  - Type hint ensures return type is pd.DataFrame
  - Original DataFrames remain unchanged

Import Requirements:

  - import pandas as pd: For DataFrame type and concat function
  - Type hints use pd.DataFrame for clarity
  - No additional imports needed

Common Use Cases:

  - Combining data from multiple sources
  - Appending new records to existing data
  - Merging datasets with same structure
  - Data preprocessing and cleaning
  - Combining results from multiple queries

Difference from pd.merge():

  - concat(): Combines DataFrames vertically (rows)
  - merge(): Combines DataFrames horizontally (joins)
  - concat(): Simple row stacking
  - merge(): SQL-like join operation
"""

import pandas as pd


def concatenateTables(df1: pd.DataFrame, df2: pd.DataFrame) -> pd.DataFrame:
    """
    Concatenate two DataFrames vertically (row-wise), combining all rows.
    
    Args:
        df1: First pandas DataFrame
        df2: Second pandas DataFrame
        
    Returns:
        A DataFrame containing all rows from both df1 and df2 with a new sequential index
        
    Example:
        >>> df1 = pd.DataFrame({'id': [1, 2], 'name': ['Alice', 'Bob']})
        >>> df2 = pd.DataFrame({'id': [3, 4], 'name': ['Charlie', 'David']})
        >>> concatenateTables(df1, df2)
           id     name
        0   1    Alice
        1   2      Bob
        2   3  Charlie
        3   4    David
    """
    return pd.concat([df1, df2], ignore_index=True)

