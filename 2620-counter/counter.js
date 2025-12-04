/**
 * LeetCode 2620: Counter
 * 
 * Problem: Given an integer n, return a counter function. This counter function
 *          initially returns n and then returns 1 more than the previous value
 *          every subsequent time it is called (n, n+1, n+2, etc).
 * 
 * Key Insight: This problem tests understanding of closures in JavaScript.
 *              The returned function "closes over" the variable n, maintaining
 *              its state between calls.
 * 
 * Examples:
 *   Input: n = 10, calls = ["call","call","call"]
 *   Output: [10, 11, 12]
 *   Explanation: 
 *     counter() // 10 (first call returns n)
 *     counter() // 11 (second call returns n+1)
 *     counter() // 12 (third call returns n+2)
 * 
 *   Input: n = -2, calls = ["call","call","call","call","call"]
 *   Output: [-2, -1, 0, 1, 2]
 *   Explanation: Each call increments from -2
 * 
 * What is a Closure?
 *   A closure is a function that has access to variables from its outer scope,
 *   even after the outer function has returned. Here, the inner function 
 *   "remembers" the value of n.
 */

/**
 * Creates a counter function that returns incrementing values starting from n.
 * 
 * @param {number} n - The starting value for the counter
 * @return {Function} - A function that returns n, then n+1, n+2, etc.
 * 
 * Time Complexity: O(1) - each call is constant time
 * Space Complexity: O(1) - only storing one variable in closure
 */
var createCounter = function(n) {
    // Return a function that:
    // 1. Returns the current value of n
    // 2. Then increments n for the next call
    // Using post-increment (n++) returns n first, then increments
    return function() {
        return n++;
    };
};

/**
 * Alternative implementations for learning:
 */

// Using pre-increment with adjustment:
// var createCounter = function(n) {
//     let count = n - 1;  // Start one less
//     return function() {
//         return ++count;  // Pre-increment then return
//     };
// };

// Using arrow function (more concise):
// var createCounter = (n) => () => n++;

/**
 * Usage Example:
 * 
 * const counter = createCounter(10);
 * console.log(counter()); // 10
 * console.log(counter()); // 11
 * console.log(counter()); // 12
 */

