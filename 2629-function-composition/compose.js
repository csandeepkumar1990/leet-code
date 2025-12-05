/**
 * LeetCode 2629: Function Composition
 * 
 * Problem: Given an array of functions [f1, f2, f3, ..., fn], return a new function fn
 *          that is the function composition of the array of functions.
 * 
 *          The function composition of [f(x), g(x), h(x)] is fn(x) = f(g(h(x))).
 *          The function composition of an empty list of functions is the identity 
 *          function f(x) = x.
 * 
 * Key Insight: Functions are applied from RIGHT to LEFT (last to first).
 *              This is the mathematical definition of function composition.
 * 
 * Examples:
 *   Input: functions = [x => x + 1, x => x * x, x => 2 * x], x = 4
 *   Output: 65
 *   Explanation:
 *     Starting from right to left:
 *     2 * 4 = 8
 *     8 * 8 = 64
 *     64 + 1 = 65
 * 
 *   Input: functions = [x => 10 * x, x => 10 * x, x => 10 * x], x = 1
 *   Output: 1000
 *   Explanation: 10 * 10 * 10 * 1 = 1000
 * 
 *   Input: functions = [], x = 42
 *   Output: 42
 *   Explanation: Empty array returns identity function f(x) = x
 */

/**
 * Creates a composed function from an array of functions.
 * 
 * @param {Function[]} functions - Array of functions to compose
 * @return {Function} - A function that applies all functions right to left
 * 
 * Time Complexity: O(n) - where n is the number of functions
 * Space Complexity: O(1) - only storing the accumulated value
 */
var compose = function(functions) {
    return function(x) {
        // reduceRight iterates from right to left
        // acc holds the accumulated result, fn is the current function
        // Start with x as initial value, apply each function to result
        return functions.reduceRight((acc, fn) => fn(acc), x);
    };
};

/**
 * Alternative implementations for learning:
 */

// Using a for loop (more explicit):
// var compose = function(functions) {
//     return function(x) {
//         let result = x;
//         // Iterate from the last function to the first
//         for (let i = functions.length - 1; i >= 0; i--) {
//             result = functions[i](result);
//         }
//         return result;
//     };
// };

// Using arrow functions (more concise):
// const compose = (functions) => (x) => functions.reduceRight((acc, fn) => fn(acc), x);

// Using reverse and reduce:
// var compose = function(functions) {
//     return function(x) {
//         return functions.reverse().reduce((acc, fn) => fn(acc), x);
//     };
// };
// Note: This mutates the original array, not recommended!

/**
 * Usage Example:
 * 
 * const functions = [x => x + 1, x => x * x, x => 2 * x];
 * const fn = compose(functions);
 * console.log(fn(4)); // 65
 * 
 * // Step by step:
 * // Start: x = 4
 * // Apply x => 2 * x:  2 * 4 = 8
 * // Apply x => x * x:  8 * 8 = 64
 * // Apply x => x + 1:  64 + 1 = 65
 */


