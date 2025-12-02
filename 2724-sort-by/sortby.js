/**
 * LeetCode 2724: Sort By
 * 
 * Given an array and a function fn, return a sorted array sorted by fn.
 * The sort is in ascending order based on the return value of fn(element).
 * 
 * @param {Array} arr - The array to be sorted
 * @param {Function} fn - A function that returns a numeric value used for comparison
 * @returns {Array} - The sorted array in ascending order based on fn's return values
 * 
 * Time Complexity: O(n log n) - standard comparison sort
 * Space Complexity: O(1) - sorts in place (though JS engines may vary)
 */
const sortBy = (arr, fn) => {
    // Use Array.sort() with a custom comparator
    // fn(a) - fn(b) returns:
    //   - negative if fn(a) < fn(b) → a comes before b
    //   - zero if fn(a) === fn(b) → order unchanged
    //   - positive if fn(a) > fn(b) → b comes before a
    return arr.sort((a, b) => fn(a) - fn(b));
}

// Example 1: Sort numbers in ascending order
// fn is identity function (x) => x, so numbers are compared directly
console.log(sortBy([5, 4, 1, 2, 3], (x) => x));
// [1, 2, 3, 4, 5]

// Example 2: Sort objects by their 'x' property
// fn extracts the 'x' value from each object for comparison
console.log(sortBy([{"x": 1}, {"x": 0}, {"x": -1}], (d) => d.x));
// [{"x": -1}, {"x": 0}, {"x": 1}]

// Example 3: Sort nested arrays by their second element (index 1)
// fn extracts element at index 1 from each sub-array
console.log(sortBy([[3, 4], [5, 2], [10, 1]], (x) => x[1]));
// [[10, 1], [5, 2], [3, 4]]

