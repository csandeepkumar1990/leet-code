/**
 * LeetCode 2626: Array Reduce Transformation
 * 
 * Problem: Given an integer array nums, a reducer function fn, and an initial 
 *          value init, return the final result after applying the reducer to 
 *          each element. The result is calculated as:
 *          
 *          val = fn(init, nums[0]), val = fn(val, nums[1]), val = fn(val, nums[2]), ...
 *          
 *          until every element has been processed. Return the final value of val.
 *          If the array is empty, return init.
 *          
 *          Solve it WITHOUT using the built-in Array.reduce method.
 * 
 * Key Insight: Reduce "accumulates" a single value by processing each element
 *              with the previous result. Start with init, then apply fn repeatedly.
 * 
 * How Reduce Works (Visual):
 *    init ──┐
 *           ├── fn(init, nums[0]) = val1
 *           │
 *   val1 ───┼── fn(val1, nums[1]) = val2
 *           │
 *   val2 ───┼── fn(val2, nums[2]) = val3
 *           │
 *          ... 
 *           │
 *   valN ───┴── Final Result
 * 
 * Examples:
 *   Input: nums = [1,2,3,4], fn = (accum, curr) => accum + curr, init = 0
 *   Output: 10
 *   Explanation: 
 *     fn(0, 1) = 1
 *     fn(1, 2) = 3
 *     fn(3, 3) = 6
 *     fn(6, 4) = 10
 *     Sum of all elements!
 * 
 *   Input: nums = [1,2,3,4], fn = (accum, curr) => accum + curr * curr, init = 100
 *   Output: 130
 *   Explanation:
 *     fn(100, 1) = 100 + 1 = 101
 *     fn(101, 2) = 101 + 4 = 105
 *     fn(105, 3) = 105 + 9 = 114
 *     fn(114, 4) = 114 + 16 = 130
 *     Sum of squares + initial value!
 * 
 *   Input: nums = [], fn = (accum, curr) => 0, init = 25
 *   Output: 25
 *   Explanation: Empty array, return init directly.
 */

/**
 * Applies a reducer function to each element of the array, accumulating a result.
 * 
 * @param {number[]} nums - The input array of numbers
 * @param {Function} fn - Reducer function that takes (accumulator, currentValue) and returns new accumulator
 * @param {number} init - The initial value for the accumulator
 * @return {number} - The final accumulated value
 * 
 * Time Complexity: O(n) - iterate through each element once
 * Space Complexity: O(1) - only storing the accumulator variable
 */
var reduce = function(nums, fn, init) {
    // Initialize accumulator with the starting value
    let accumulator = init;
    
    // Process each element in the array
    for (const num of nums) {
        // Apply reducer function: combine current accumulator with current element
        // The result becomes the new accumulator for the next iteration
        accumulator = fn(accumulator, num);
    }
    
    // Return the final accumulated value
    // If array was empty, this returns init (loop never executed)
    return accumulator;
};

/**
 * Alternative implementations for learning:
 */

// Using forEach:
// var reduce = function(nums, fn, init) {
//     let acc = init;
//     nums.forEach(num => {
//         acc = fn(acc, num);
//     });
//     return acc;
// };

// Using traditional for loop with index:
// var reduce = function(nums, fn, init) {
//     let acc = init;
//     for (let i = 0; i < nums.length; i++) {
//         acc = fn(acc, nums[i]);
//     }
//     return acc;
// };

/**
 * Usage Examples:
 * 
 * // Sum of array
 * reduce([1, 2, 3, 4], (acc, curr) => acc + curr, 0);  // 10
 * 
 * // Product of array
 * reduce([1, 2, 3, 4], (acc, curr) => acc * curr, 1);  // 24
 * 
 * // Find maximum
 * reduce([3, 1, 4, 1, 5], (acc, curr) => Math.max(acc, curr), -Infinity);  // 5
 * 
 * // Count elements
 * reduce([1, 2, 3], (acc, curr) => acc + 1, 0);  // 3
 */

