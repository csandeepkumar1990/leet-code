"""
LeetCode 2887: Fill Missing Values

Problem:
Write a solution to fill missing (NULL/NaN) values in the 'quantity' column of a pandas DataFrame
with 0.

Input: products (pd.DataFrame)
Output: pd.DataFrame - The same DataFrame with missing quantity values filled with 0

Approach: Using pandas Series.fillna() Method

Key Insight:
- Use fillna() method to replace missing values
- products["quantity"].fillna(0) replaces NULL/NaN with 0
- Modifies the column in-place when assigned back
- Return the modified DataFrame

Algorithm:
1. Access the "quantity" column
2. Call fillna(0) to replace missing values with 0
3. Assign the result back to the "quantity" column
4. Return the modified DataFrame

Formula:
  products["quantity"] = products["quantity"].fillna(0)

Time Complexity: O(n)
  - Where n is the number of rows in the DataFrame
  - Need to check each value in the column
  - Linear time complexity

Space Complexity: O(1) additional space
  - Modifies existing column in-place
  - No new columns created
  - Only temporary space for calculation

Example:

  Input DataFrame:
  +----+----------+----------+
  | id | name     | quantity |
  +----+----------+----------+
  | 1  | Product1 | 10       |
  | 2  | Product2 | NULL     |  ← missing
  | 3  | Product3 | 20       |
  | 4  | Product4 | NULL     |  ← missing
  +----+----------+----------+

  After products["quantity"] = products["quantity"].fillna(0):
  +----+----------+----------+
  | id | name     | quantity |
  +----+----------+----------+
  | 1  | Product1 | 10       |
  | 2  | Product2 | 0        |  ← filled
  | 3  | Product3 | 20       |
  | 4  | Product4 | 0        |  ← filled
  +----+----------+----------+

  Result: Missing quantity values replaced with 0

Another Example:

  Input DataFrame (no missing values):
  +----+----------+----------+
  | id | name     | quantity |
  +----+----------+----------+
  | 1  | Product1 | 10       |
  | 2  | Product2 | 20       |
  +----+----------+----------+

  Result: Same DataFrame (no changes needed)

What is fillna()?

  - fillna() is a method of pandas Series
  - Replaces missing values (NULL/NaN/None) with specified value
  - Returns a new Series with filled values
  - Can fill with constant value, forward fill, backward fill, etc.

Value Replacement:

  - Replaces NULL/NaN/None with 0
  - Non-missing values remain unchanged
  - Only missing values are affected
  - Returns Series with same length

Why Use fillna(0)?

  1. vs dropna():
     - fillna() keeps all rows (fills missing values)
     - dropna() removes rows with missing values
     - fillna() preserves data structure

  2. vs leaving NULL:
     - Filled values enable calculations
     - NULL in calculations often results in NULL
     - 0 allows arithmetic operations

  3. vs other fill values:
     - 0 is common for quantity (no items)
     - Could use mean, median, or other values
     - 0 makes sense for quantity context

Alternative Approaches:

  1. Using fillna() with method parameter:
     products["quantity"] = products["quantity"].fillna(method="ffill")
     - Forward fill: uses previous value
     - Not applicable for this problem (need 0)

  2. Using fillna() with inplace:
     products["quantity"].fillna(0, inplace=True)
     - Modifies column in-place
     - Doesn't return Series
     - More memory efficient

  3. Using replace():
     products["quantity"] = products["quantity"].replace({np.nan: 0})
     - More verbose
     - Requires numpy import
     - fillna() is more direct

  4. Using where():
     products["quantity"] = products["quantity"].where(
         products["quantity"].notna(), 0
     )
     - More complex syntax
     - fillna() is simpler and clearer

  5. Using fillna() with different value:
     products["quantity"] = products["quantity"].fillna(mean_value)
     - Could fill with mean, median, etc.
     - 0 is specified for this problem

Edge Cases:

  1. No missing values:
     - Returns original column unchanged
     - All values remain the same
     - Result: No changes

  2. All values missing:
     - All values replaced with 0
     - Entire column becomes 0
     - Result: Column of zeros

  3. Empty DataFrame:
     - No rows to process
     - Result: Empty DataFrame (unchanged)

  4. Mixed missing and non-missing:
     - Only missing values are replaced
     - Non-missing values preserved
     - Result: Mixed values (original + 0)

  5. Different missing value types:
     - NULL, NaN, None all treated as missing
     - All replaced with 0
     - Consistent behavior

  6. Zero vs missing:
     - 0 is a valid value (not missing)
     - Only NULL/NaN/None are replaced
     - Existing 0 values remain 0

Data Type Considerations:

  - If quantity is int, fillna(0) keeps int type
  - If quantity is float, fillna(0) keeps float type
  - fillna() preserves data type when possible
  - 0 fits both int and float types

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

  - fillna() is optimized for this operation
  - Vectorized operation (fast)
  - Much faster than row-by-row iteration
  - O(n) time complexity

Memory Considerations:

  - In-place modification doesn't create new DataFrame
  - Only column values are modified
  - More memory efficient than creating copy
  - Original DataFrame structure is reused

Other fillna() Options:

  1. Forward fill: fillna(method="ffill")
     - Uses previous non-null value
     - Useful for time series data

  2. Backward fill: fillna(method="bfill")
     - Uses next non-null value
     - Useful for time series data

  3. Fill with mean: fillna(products["quantity"].mean())
     - Fills with average value
     - Useful for numerical data

  4. Fill with median: fillna(products["quantity"].median())
     - Fills with median value
     - More robust to outliers

Import Requirements:

  - import pandas as pd: For DataFrame type
  - Type hints use pd.DataFrame for clarity
  - No additional imports needed

Common Use Cases:

  - Handling missing data in datasets
  - Preparing data for calculations
  - Data cleaning and preprocessing
  - Ensuring data completeness
  - Default value assignment

Difference from dropna():

  - fillna(): Replaces missing values (keeps all rows)
  - dropna(): Removes rows with missing values
  - fillna(): Preserves data structure
  - dropna(): Reduces data size
"""

import pandas as pd


def fillMissingValues(products: pd.DataFrame) -> pd.DataFrame:
    """
    Fill missing (NULL/NaN) values in the 'quantity' column with 0.
    
    Args:
        products: A pandas DataFrame with at least a 'quantity' column
        
    Returns:
        The same DataFrame with missing quantity values replaced with 0
        
    Example:
        >>> df = pd.DataFrame({
        ...     'id': [1, 2, 3],
        ...     'name': ['Product1', 'Product2', 'Product3'],
        ...     'quantity': [10, None, 20]
        ... })
        >>> fillMissingValues(df)
           id      name  quantity
        0   1  Product1      10.0
        1   2  Product2       0.0
        2   3  Product3      20.0
    """
    products["quantity"] = products["quantity"].fillna(0)
    return products

