"""
LeetCode 2889: Reshape Data: Pivot

Problem:
Write a solution to pivot a pandas DataFrame.
Transform the data from long format to wide format, where:
- Rows are indexed by 'month'
- Columns are the unique values from 'city'
- Values are taken from 'temperature'

Input: weather (pd.DataFrame)
Output: pd.DataFrame - A pivoted DataFrame with months as rows and cities as columns

Approach: Using pandas DataFrame.pivot() Method

Key Insight:
- Use pivot() method to reshape DataFrame from long to wide format
- index="month": Sets months as row indices
- columns="city": Sets cities as column names
- values="temperature": Fills cells with temperature values
- Returns a new DataFrame with pivoted structure

Algorithm:
1. Call pivot() with index="month", columns="city", values="temperature"
2. Transform long format (one row per month-city combination) to wide format
3. Return the pivoted DataFrame

Formula:
  result = dataframe.pivot(index="month", columns="city", values="temperature")

Time Complexity: O(n)
  - Where n is the number of rows in the DataFrame
  - Need to reorganize data into pivot structure
  - Linear time complexity

Space Complexity: O(m * c)
  - Where m is number of unique months, c is number of unique cities
  - Creates new DataFrame with m rows and c columns
  - Stores all temperature values

Example:

  Input DataFrame (long format):
  +-------+--------+-------------+
  | month | city   | temperature |
  +-------+--------+-------------+
  | Jan   | NY     | 32          |
  | Jan   | LA     | 68          |
  | Feb   | NY     | 35          |
  | Feb   | LA     | 70          |
  | Mar   | NY     | 40          |
  | Mar   | LA     | 72          |
  +-------+--------+-------------+

  After pivot(index="month", columns="city", values="temperature"):
  +-------+----+----+
  | month | NY | LA |
  +-------+----+----+
  | Jan   | 32 | 68 |
  | Feb   | 35 | 70 |
  | Mar   | 40 | 72 |
  +-------+----+----+

  Result: Wide format with months as rows, cities as columns

Another Example:

  Input DataFrame:
  +-------+--------+-------------+
  | month | city   | temperature |
  +-------+--------+-------------+
  | Jan   | NY     | 32          |
  | Jan   | Chicago| 25          |
  | Jan   | LA     | 68          |
  +-------+--------+-------------+

  Result:
  +-------+----+---------+----+
  | month | NY | Chicago | LA |
  +-------+----+---------+----+
  | Jan   | 32 | 25      | 68 |
  +-------+----+---------+----+

What is pivot()?

  - pivot() is a method of pandas DataFrame
  - Reshapes data from long format to wide format
  - Creates a pivot table structure
  - Returns a new DataFrame with reshaped structure

Parameters:

  1. index: Column name to use as row index
     - index="month": Months become row indices
     - Creates one row per unique month value

  2. columns: Column name to use as column names
     - columns="city": Cities become column names
     - Creates one column per unique city value

  3. values: Column name to use as cell values
     - values="temperature": Temperature values fill the cells
     - Must be a numeric or categorical column

Why Use pivot()?

  1. vs long format:
     - Wide format is easier to compare across categories
     - Better for visualization and analysis
     - More compact representation

  2. vs manual reshaping:
     - pivot() is optimized and efficient
     - Handles edge cases automatically
     - Less error-prone than manual code

  3. vs groupby().unstack():
     - pivot() is more direct for this use case
     - groupby().unstack() is more flexible but verbose
     - pivot() is cleaner for simple pivots

Alternative Approaches:

  1. Using groupby().unstack():
     return weather.groupby(["month", "city"])["temperature"].first().unstack()
     - More verbose
     - Handles duplicates differently
     - pivot() is simpler

  2. Using pivot_table():
     return weather.pivot_table(index="month", columns="city", values="temperature", aggfunc="first")
     - More flexible (can aggregate)
     - Handles duplicate index-column combinations
     - pivot() is simpler when no aggregation needed

  3. Using set_index().unstack():
     return weather.set_index(["month", "city"])["temperature"].unstack()
     - More steps
     - pivot() is more direct

Edge Cases:

  1. Duplicate month-city combinations:
     - pivot() raises ValueError (duplicate entries)
     - Use pivot_table() with aggfunc to handle duplicates
     - pivot() requires unique index-column pairs

  2. Missing combinations:
     - If a month-city combination doesn't exist, cell is NaN
     - Result has NaN for missing combinations
     - Can use fill_value parameter in pivot_table()

  3. Empty DataFrame:
     - Returns empty DataFrame with specified structure
     - No rows to pivot
     - Result: Empty DataFrame

  4. Single month, multiple cities:
     - Creates one row with multiple columns
     - Result: Single row with city columns

  5. Multiple months, single city:
     - Creates multiple rows with one column
     - Result: Multiple rows with single city column

  6. All same month:
     - Creates one row with multiple columns
     - Result: Single row with all cities

Index and Column Names:

  - Row index: Uses unique values from "month" column
  - Column names: Uses unique values from "city" column
  - Index name: Preserved from original "month" column
  - Column index name: "city" (from columns parameter)

Data Type Preservation:

  - Temperature values are preserved
  - Data types of values column are maintained
  - Index and columns may have different types
  - Result DataFrame has appropriate types

Return Type:

  - pivot() returns a new DataFrame
  - Does not modify original DataFrame
  - Type hint ensures return type is pd.DataFrame
  - Original DataFrame remains unchanged

Performance Considerations:

  - pivot() is optimized for this operation
  - Efficient reshaping algorithm
  - O(n) time complexity
  - Consider memory usage for large pivots

Memory Considerations:

  - Creates a new DataFrame
  - Reorganizes data structure
  - Uses O(m * c) space where m=months, c=cities
  - Original DataFrame is not modified

When to Use pivot() vs pivot_table():

  - pivot(): When index-column pairs are unique (no duplicates)
  - pivot_table(): When duplicates exist and need aggregation
  - pivot(): Simpler syntax for unique pairs
  - pivot_table(): More flexible with aggregation functions

Common Use Cases:

  - Reshaping time series data
  - Creating comparison tables
  - Preparing data for visualization
  - Converting long format to wide format
  - Creating cross-tabulation tables

Difference from pivot_table():

  - pivot(): Requires unique index-column pairs
  - pivot_table(): Can aggregate duplicate pairs
  - pivot(): Simpler for unique data
  - pivot_table(): More flexible with duplicates
"""

import pandas as pd


def pivotTable(weather: pd.DataFrame) -> pd.DataFrame:
    """
    Pivot the DataFrame from long format to wide format.
    Transform data so that months are rows, cities are columns, and temperatures are values.
    
    Args:
        weather: A pandas DataFrame with columns 'month', 'city', and 'temperature'
        
    Returns:
        A pivoted DataFrame with months as rows, cities as columns, and temperatures as values
        
    Example:
        >>> df = pd.DataFrame({
        ...     'month': ['Jan', 'Jan', 'Feb', 'Feb'],
        ...     'city': ['NY', 'LA', 'NY', 'LA'],
        ...     'temperature': [32, 68, 35, 70]
        ... })
        >>> pivotTable(df)
        city   LA  NY
        month        
        Feb    70  35
        Jan    68  32
    """
    return weather.pivot(index="month", columns="city", values="temperature")

