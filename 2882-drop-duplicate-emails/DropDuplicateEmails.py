"""
LeetCode 2882: Drop Duplicate Emails

Problem:
Write a solution to remove duplicate rows from a pandas DataFrame based on the 'email' column.
Keep only the first occurrence of each unique email address.

Input: customers (pd.DataFrame)
Output: pd.DataFrame - A DataFrame with duplicate emails removed

Approach: Using pandas DataFrame.drop_duplicates() Method

Key Insight:
- Use drop_duplicates() method to remove duplicate rows
- Specify subset=["email"] to check only the email column
- Use keep="first" to keep the first occurrence of each duplicate
- Returns a new DataFrame (does not modify original by default)

Algorithm:
1. Call drop_duplicates() with subset=["email"]
2. Use keep="first" to keep first occurrence
3. Return the deduplicated DataFrame

Formula:
  result = dataframe.drop_duplicates(subset=["email"], keep="first")

Time Complexity: O(n log n) to O(n)
  - Where n is the number of rows
  - Need to identify duplicates (hashing or sorting)
  - pandas optimizes this operation

Space Complexity: O(n)
  - In worst case, creates a new DataFrame
  - Stores unique rows
  - Additional space for duplicate detection

Example:

  Input DataFrame:
  +----+-------+------------------+
  | id | name  | email            |
  +----+-------+------------------+
  | 1  | Alice | alice@example.com|
  | 2  | Bob   | bob@example.com  |
  | 3  | Alice | alice@example.com|  ← duplicate email
  | 4  | David | david@example.com|
  | 5  | Bob   | bob@example.com  |  ← duplicate email
  +----+-------+------------------+

  After drop_duplicates(subset=["email"], keep="first"):
  +----+-------+------------------+
  | id | name  | email            |
  +----+-------+------------------+
  | 1  | Alice | alice@example.com|  ← first occurrence kept
  | 2  | Bob   | bob@example.com  |  ← first occurrence kept
  | 4  | David | david@example.com|
  +----+-------+------------------+

  Result: Duplicate emails removed, first occurrence kept

Another Example:

  Input DataFrame (no duplicates):
  +----+-------+------------------+
  | id | name  | email            |
  +----+-------+------------------+
  | 1  | Alice | alice@example.com|
  | 2  | Bob   | bob@example.com  |
  +----+-------+------------------+

  Result: Same DataFrame (no duplicates to remove)

What is drop_duplicates()?

  - drop_duplicates() is a method of pandas DataFrame
  - Removes duplicate rows based on specified columns
  - Returns a new DataFrame by default (doesn't modify original)
  - Can specify which columns to check for duplicates
  - Can specify which duplicate to keep (first, last, or none)

Parameters:

  1. subset: List of column names to check for duplicates
     - subset=["email"]: Check only email column
     - subset=None: Check all columns (default)
     - Can specify multiple columns: subset=["email", "name"]

  2. keep: Which duplicate to keep
     - keep="first": Keep first occurrence (default)
     - keep="last": Keep last occurrence
     - keep=False: Remove all duplicates (keep none)

Why Use subset=["email"]?

  - Only checks email column for duplicates
  - Other columns can have different values
  - Example: Same email but different names → treated as duplicate
  - More flexible than checking all columns

Why keep="first"?

  - Keeps the first occurrence of each duplicate
  - Preserves original order (first row encountered)
  - Common use case: keep earliest record
  - Alternative: keep="last" keeps most recent

Alternative Approaches:

  1. Using keep="last":
     return customers.drop_duplicates(subset=["email"], keep="last")
     - Keeps last occurrence instead of first
     - Useful if you want most recent record

  2. Using keep=False:
     return customers.drop_duplicates(subset=["email"], keep=False)
     - Removes ALL rows with duplicates
     - Only keeps rows with unique emails
     - More aggressive deduplication

  3. Using groupby().first():
     return customers.groupby("email").first().reset_index()
     - Groups by email and takes first row
     - More verbose but equivalent result
     - reset_index() to restore email as column

  4. Using boolean indexing with duplicated():
     mask = ~customers["email"].duplicated(keep="first")
     return customers[mask]
     - duplicated() returns boolean Series
     - ~ negates to get non-duplicates
     - More explicit but less concise

Edge Cases:

  1. No duplicates:
     - Returns original DataFrame unchanged
     - All rows are unique
     - Result: Same as input

  2. All rows are duplicates:
     - Returns DataFrame with single row
     - Only first row is kept
     - Result: One row

  3. Empty DataFrame:
     - Returns empty DataFrame
     - No rows to process
     - Result: Empty DataFrame

  4. NULL/NaN values in email:
     - NaN values are considered equal (duplicates)
     - Multiple NaN emails → only first kept
     - NaN != NaN normally, but drop_duplicates treats them as equal

  5. Case sensitivity:
     - "Alice@example.com" != "alice@example.com"
     - Treated as different emails
     - Use .str.lower() if case-insensitive matching needed

  6. Whitespace in emails:
     - "alice@example.com" != " alice@example.com "
     - Treated as different emails
     - Use .str.strip() if whitespace should be ignored

Index Preservation:

  - drop_duplicates() preserves original index values
  - If original index is [0, 1, 2, 3, 4], result keeps matching indices
  - Example: If rows 0, 2, 4 are kept, result has index [0, 2, 4]
  - Can use reset_index() to create new sequential index

Return Type:

  - drop_duplicates() returns a new DataFrame
  - Does not modify original DataFrame (by default)
  - Type hint ensures return type is pd.DataFrame
  - Original DataFrame remains unchanged

In-Place Modification:

  - By default, drop_duplicates() returns new DataFrame
  - Can use inplace=True to modify original:
    customers.drop_duplicates(subset=["email"], keep="first", inplace=True)
  - In-place modification doesn't return DataFrame
  - Current solution uses default (returns new DataFrame)

Performance Considerations:

  - drop_duplicates() is optimized for this operation
  - Uses hashing or sorting to identify duplicates
  - More efficient than manual iteration
  - Consider indexing on email column for large DataFrames

Memory Considerations:

  - Returns new DataFrame (default behavior)
  - Original DataFrame is not modified
  - Uses additional memory for result
  - Can use inplace=True to save memory (modifies original)

Multiple Column Deduplication:

  - Can specify multiple columns in subset
  - Example: subset=["email", "name"]
  - Rows are duplicates only if BOTH columns match
  - More strict deduplication criteria

Data Type Preservation:

  - Column data types are preserved
  - No type conversion occurs
  - Original column types maintained

Import Requirements:

  - import pandas as pd: For DataFrame type
  - Type hints use pd.DataFrame for clarity
  - No additional imports needed

Common Use Cases:

  - Removing duplicate customer records
  - Cleaning data with duplicate entries
  - Keeping first registration for each email
  - Data preprocessing and cleaning
"""

import pandas as pd


def dropDuplicateEmails(customers: pd.DataFrame) -> pd.DataFrame:
    """
    Remove duplicate rows based on the 'email' column, keeping the first occurrence.
    
    Args:
        customers: A pandas DataFrame with at least an 'email' column
        
    Returns:
        A DataFrame with duplicate emails removed (first occurrence kept)
        
    Example:
        >>> df = pd.DataFrame({
        ...     'id': [1, 2, 3],
        ...     'name': ['Alice', 'Bob', 'Alice'],
        ...     'email': ['alice@example.com', 'bob@example.com', 'alice@example.com']
        ... })
        >>> dropDuplicateEmails(df)
           id   name              email
        0   1  Alice  alice@example.com
        1   2    Bob    bob@example.com
    """
    return customers.drop_duplicates(subset=["email"], keep="first")

