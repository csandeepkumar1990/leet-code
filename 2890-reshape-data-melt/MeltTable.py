"""
LeetCode 2890: Reshape Data: Melt

Problem:
Write a solution to melt a pandas DataFrame from wide format to long format.
Transform the data so that:
- 'product' column remains as identifier
- Other columns become rows with 'quarter' as the variable name
- Values are stored in 'sales' column

Input: report (pd.DataFrame)
Output: pd.DataFrame - A melted DataFrame in long format

Approach: Using pandas DataFrame.melt() Method

Key Insight:
- Use melt() method to reshape DataFrame from wide to long format
- id_vars=["product"]: Keeps product as identifier column
- var_name="quarter": Names the new column for variable names (quarter columns)
- value_name="sales": Names the new column for values (sales data)
- Returns a new DataFrame with melted structure

Algorithm:
1. Call melt() with id_vars=["product"]
2. Specify var_name="quarter" for the variable column
3. Specify value_name="sales" for the value column
4. Return the melted DataFrame

Formula:
  result = dataframe.melt(id_vars=["product"], var_name="quarter", value_name="sales")

Time Complexity: O(n * m)
  - Where n is number of rows, m is number of columns to melt
  - Need to transform each value into a row
  - Linear time complexity

Space Complexity: O(n * m)
  - Creates new DataFrame with n * m rows (approximately)
  - Stores all data in long format
  - Additional space for new structure

Example:

  Input DataFrame (wide format):
  +---------+-------+-------+-------+-------+
  | product | Q1    | Q2    | Q3    | Q4    |
  +---------+-------+-------+-------+-------+
  | A       | 100   | 150   | 200   | 180   |
  | B       | 120   | 140   | 160   | 170   |
  +---------+-------+-------+-------+-------+

  After melt(id_vars=["product"], var_name="quarter", value_name="sales"):
  +---------+---------+-------+
  | product | quarter | sales |
  +---------+---------+-------+
  | A       | Q1      | 100   |
  | A       | Q2      | 150   |
  | A       | Q3      | 200   |
  | A       | Q4      | 180   |
  | B       | Q1      | 120   |
  | B       | Q2      | 140   |
  | B       | Q3      | 160   |
  | B       | Q4      | 170   |
  +---------+---------+-------+

  Result: Long format with product, quarter, and sales columns

Another Example:

  Input DataFrame:
  +---------+----+----+
  | product | Q1 | Q2 |
  +---------+----+----+
  | A       | 100| 150|
  +---------+----+----+

  Result:
  +---------+---------+-------+
  | product | quarter | sales |
  +---------+---------+-------+
  | A       | Q1      | 100   |
  | A       | Q2      | 150   |
  +---------+---------+-------+

What is melt()?

  - melt() is a method of pandas DataFrame
  - Reshapes data from wide format to long format
  - Unpivots columns into rows
  - Returns a new DataFrame with melted structure

Parameters:

  1. id_vars: List of column names to keep as identifier columns
     - id_vars=["product"]: Product column remains as identifier
     - These columns are not melted (kept as-is)

  2. var_name: Name for the new column containing variable names
     - var_name="quarter": Column names (Q1, Q2, etc.) become values in "quarter" column
     - Default is "variable"

  3. value_name: Name for the new column containing values
     - value_name="sales": Cell values become values in "sales" column
     - Default is "value"

  4. value_vars: List of columns to melt (optional)
     - If not specified, all columns except id_vars are melted
     - Can specify which columns to melt explicitly

Why Use melt()?

  1. vs wide format:
     - Long format is better for analysis and visualization
     - Easier to filter and group by variables
     - More normalized data structure

  2. vs manual reshaping:
     - melt() is optimized and efficient
     - Handles edge cases automatically
     - Less error-prone than manual code

  3. vs stack():
     - melt() is more flexible with column selection
     - stack() works with MultiIndex
     - melt() is simpler for this use case

Alternative Approaches:

  1. Using stack():
     return report.set_index("product").stack().reset_index()
     - More complex
     - Requires additional steps
     - melt() is more direct

  2. Using pd.melt():
     return pd.melt(report, id_vars=["product"], var_name="quarter", value_name="sales")
     - Function form instead of method
     - Same functionality
     - Method form is more common

  3. Using value_vars parameter:
     return report.melt(id_vars=["product"], value_vars=["Q1", "Q2", "Q3", "Q4"], 
                       var_name="quarter", value_name="sales")
     - Explicitly specifies which columns to melt
     - More control over which columns are melted
     - Useful when not all columns should be melted

Edge Cases:

  1. Empty DataFrame:
     - Returns empty DataFrame with specified structure
     - No rows to melt
     - Result: Empty DataFrame with product, quarter, sales columns

  2. Single product, multiple quarters:
     - Creates multiple rows (one per quarter)
     - Result: Multiple rows with same product

  3. Multiple products, single quarter:
     - Creates one row per product
     - Result: One row per product with same quarter

  4. Missing values:
     - NaN values are preserved in melted format
     - Result: Rows with NaN in sales column

  5. Different data types in columns:
     - All values converted to common type if possible
     - Result: Appropriate data type in sales column

  6. No columns to melt:
     - If only id_vars columns exist, returns original structure
     - Result: Original DataFrame (no melting needed)

Column Selection:

  - id_vars: Columns to keep as identifiers (not melted)
  - All other columns: Automatically melted (unless value_vars specified)
  - value_vars: Explicitly specify which columns to melt
  - Flexible column selection

Index Handling:

  - Original index is not preserved by default
  - New sequential index is created
  - Can use ignore_index parameter to control index
  - Result has clean sequential index

Data Type Preservation:

  - Identifier columns (id_vars) preserve their types
  - Value column may have mixed types if source columns differ
  - melt() handles type conversion automatically
  - Result has appropriate data types

Return Type:

  - melt() returns a new DataFrame
  - Does not modify original DataFrame
  - Type hint ensures return type is pd.DataFrame
  - Original DataFrame remains unchanged

Performance Considerations:

  - melt() is optimized for this operation
  - Efficient reshaping algorithm
  - O(n * m) time complexity
  - Consider memory usage for large DataFrames

Memory Considerations:

  - Creates a new DataFrame
  - Reorganizes data structure
  - Uses O(n * m) space where n=rows, m=columns to melt
  - Original DataFrame is not modified

When to Use melt() vs pivot():

  - melt(): Wide format → Long format (unpivot)
  - pivot(): Long format → Wide format (pivot)
  - melt(): Inverse operation of pivot()
  - Use melt() to normalize wide data

Common Use Cases:

  - Converting wide format to long format
  - Normalizing data structure
  - Preparing data for analysis
  - Reshaping time series data
  - Creating tidy data format

Difference from pivot():

  - melt(): Wide → Long (unpivot, expands rows)
  - pivot(): Long → Wide (pivot, expands columns)
  - melt(): Inverse of pivot()
  - Both reshape data in opposite directions
"""

import pandas as pd


def meltTable(report: pd.DataFrame) -> pd.DataFrame:
    """
    Melt the DataFrame from wide format to long format.
    Transform data so that quarter columns become rows with 'quarter' and 'sales' columns.
    
    Args:
        report: A pandas DataFrame with 'product' column and quarter columns (Q1, Q2, etc.)
        
    Returns:
        A melted DataFrame in long format with 'product', 'quarter', and 'sales' columns
        
    Example:
        >>> df = pd.DataFrame({
        ...     'product': ['A', 'B'],
        ...     'Q1': [100, 120],
        ...     'Q2': [150, 140]
        ... })
        >>> meltTable(df)
          product quarter  sales
        0       A       Q1    100
        1       B       Q1    120
        2       A       Q2    150
        3       B       Q2    140
    """
    return report.melt(id_vars=["product"], 
                       var_name="quarter", 
                       value_name="sales")

