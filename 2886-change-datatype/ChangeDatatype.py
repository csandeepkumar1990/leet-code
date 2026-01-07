"""
LeetCode 2886: Change Datatype

Problem:
Write a solution to change the data type of the 'grade' column in a pandas DataFrame to integer.

Input: students (pd.DataFrame)
Output: pd.DataFrame - The same DataFrame with the 'grade' column converted to integer type

Approach: Using pandas Series.astype() Method

Key Insight:
- Use astype() method to convert column data type
- students["grade"].astype(int) converts grade column to integer
- Modifies the column in-place when assigned back
- Return the modified DataFrame

Algorithm:
1. Access the "grade" column
2. Call astype(int) to convert to integer type
3. Assign the result back to the "grade" column
4. Return the modified DataFrame

Formula:
  students["grade"] = students["grade"].astype(int)

Time Complexity: O(n)
  - Where n is the number of rows in the DataFrame
  - Need to convert each value in the column
  - Type conversion is O(n) operation

Space Complexity: O(1) additional space
  - Modifies existing column in-place
  - May create temporary array during conversion
  - Minimal additional space

Example:

  Input DataFrame:
  +----+-------+-------+
  | id | name  | grade |
  +----+-------+-------+
  | 1  | Alice | 85.0  |  ← float
  | 2  | Bob   | 90.0  |  ← float
  | 3  | Charlie| 88.5 |  ← float
  +----+-------+-------+

  After students["grade"] = students["grade"].astype(int):
  +----+-------+-------+
  | id | name  | grade |
  +----+-------+-------+
  | 1  | Alice | 85    |  ← int (truncated)
  | 2  | Bob   | 90    |  ← int
  | 3  | Charlie| 88   |  ← int (truncated)
  +----+-------+-------+

  Result: Grade column converted to integer

Another Example:

  Input DataFrame (string grades):
  +----+------+-------+
  | id | name | grade |
  +----+------+-------+
  | 1  | Alice| "85"  |  ← string
  | 2  | Bob  | "90"  |  ← string
  +----+------+-------+

  After conversion:
  +----+------+-------+
  | id | name | grade |
  +----+------+-------+
  | 1  | Alice| 85    |  ← int
  | 2  | Bob  | 90    |  ← int
  +----+------+-------+

What is astype()?

  - astype() is a method of pandas Series
  - Converts data type of a Series
  - Returns a new Series with converted type
  - Can convert to various types: int, float, str, bool, etc.

Type Conversion:

  - Converts "grade" column to integer type
  - Original values are converted (not preserved)
  - Float values are truncated (not rounded)
  - String numbers are parsed to integers

Why Use astype(int)?

  1. vs float:
     - Removes decimal precision
     - Converts 85.7 → 85 (truncation)
     - Useful when decimal precision not needed

  2. vs string:
     - Converts string "85" → integer 85
     - Enables numerical operations
     - More efficient for calculations

  3. vs object:
     - Converts object type to integer
     - Enables type-specific operations
     - More memory efficient

Alternative Approaches:

  1. Using pd.to_numeric():
     students["grade"] = pd.to_numeric(students["grade"]).astype(int)
     - More robust for string conversion
     - Handles errors better
     - Two-step process

  2. Using convert_dtypes():
     students = students.convert_dtypes()
     - Automatically converts to best type
     - Less control over specific conversion
     - May not convert to int if not appropriate

  3. Using apply() with int():
     students["grade"] = students["grade"].apply(int)
     - Works but less efficient
     - apply() is slower than astype()
     - astype() is preferred for type conversion

  4. Using pd.Int64Dtype():
     students["grade"] = students["grade"].astype("Int64")
     - Uses nullable integer type
     - Preserves NaN values
     - More memory efficient for nullable integers

Edge Cases:

  1. Float values:
     - 85.7 → 85 (truncated, not rounded)
     - 85.9 → 85 (truncated)
     - Decimal part is lost

  2. String values:
     - "85" → 85 (parsed successfully)
     - "85.5" → raises ValueError (cannot convert)
     - Use pd.to_numeric() first if needed

  3. NULL/NaN values:
     - NaN → raises ValueError (cannot convert NaN to int)
     - Use nullable integer type: astype("Int64")
     - Or fill NaN before conversion

  4. Non-numeric strings:
     - "abc" → raises ValueError
     - Cannot convert non-numeric strings
     - Use pd.to_numeric() with errors parameter

  5. Boolean values:
     - True → 1
     - False → 0
     - Converts successfully

  6. Very large values:
     - Works if within int range
     - May overflow if exceeds int limits
     - Use int64 for larger values

Truncation vs Rounding:

  - astype(int) truncates (not rounds)
  - 85.7 → 85 (not 86)
  - 85.9 → 85 (not 86)
  - Use round() first if rounding needed:
    students["grade"] = students["grade"].round().astype(int)

Data Type Options:

  - int or "int64": 64-bit integer (default)
  - "int32": 32-bit integer (smaller memory)
  - "int16": 16-bit integer (even smaller)
  - "Int64": Nullable integer (preserves NaN)
  - Choose based on value range and null handling needs

In-Place Modification:

  - Direct assignment modifies the original DataFrame
  - If original DataFrame should be preserved, use .copy()
  - In-place modification is more memory efficient
  - Problem statement may require modifying original

Return Value:

  - Returns the modified DataFrame
  - Same object reference (not a copy)
  - Type hint ensures return type is pd.DataFrame
  - Function modifies and returns the same DataFrame

Performance Considerations:

  - astype() is optimized for type conversion
  - Vectorized operation (fast)
  - Much faster than row-by-row conversion
  - O(n) time complexity

Memory Considerations:

  - In-place modification doesn't create new DataFrame
  - Only column type is changed
  - Integer type may use less memory than float
  - More memory efficient than creating copy

Error Handling:

  - astype() raises ValueError if conversion fails
  - Common errors:
    - NaN values (use nullable integer)
    - Non-numeric strings (use pd.to_numeric() first)
    - Out of range values (use larger int type)

Import Requirements:

  - import pandas as pd: For DataFrame type
  - Type hints use pd.DataFrame for clarity
  - No additional imports needed

Common Use Cases:

  - Converting float grades to integer
  - Standardizing data types
  - Preparing data for calculations
  - Reducing memory usage
  - Data cleaning and preprocessing
"""

import pandas as pd


def changeDatatype(students: pd.DataFrame) -> pd.DataFrame:
    """
    Change the data type of the 'grade' column to integer.
    
    Args:
        students: A pandas DataFrame with at least a 'grade' column
        
    Returns:
        The same DataFrame with the 'grade' column converted to integer type
        
    Example:
        >>> df = pd.DataFrame({
        ...     'id': [1, 2],
        ...     'name': ['Alice', 'Bob'],
        ...     'grade': [85.5, 90.0]
        ... })
        >>> changeDatatype(df)
           id   name  grade
        0   1  Alice     85
        1   2    Bob     90
    """
    students["grade"] = students["grade"].astype(int)
    return students

