/**
 * LeetCode 2703: Return Length of Arguments Passed
 * 
 * Problem: Write a function argumentsLength that returns the count of arguments
 *          passed to it.
 * 
 * Key Insight: Use rest parameters (...args) to capture all arguments into an array,
 *              then return the length of that array.
 * 
 * Rest Parameters:
 *    - ...args collects all arguments into an array
 *    - Works with any number of arguments (0, 1, 2, ...)
 *    - args is a real array (not array-like object)
 *    - Can access args.length directly
 * 
 * Examples:
 *   argumentsLength(5); // 1
 *   Explanation: One argument (5) was passed
 * 
 *   argumentsLength({}, null, "3"); // 3
 *   Explanation: Three arguments were passed: {}, null, "3"
 * 
 *   argumentsLength(); // 0
 *   Explanation: No arguments were passed
 * 
 *   argumentsLength(1, 2, 3, 4, 5); // 5
 *   Explanation: Five arguments were passed
 * 
 * Visual Representation:
 *    argumentsLength(1, 2, 3)
 *    ↓
 *    args = [1, 2, 3]
 *    ↓
 *    args.length = 3
 *    ↓
 *    return 3
 */

/**
 * Returns the number of arguments passed to the function.
 * 
 * @param {...any} args - Variable number of arguments (rest parameters)
 * @return {number} - The count of arguments passed
 * 
 * Time Complexity: O(1) - accessing array length is constant time
 * Space Complexity: O(n) - where n is the number of arguments (stored in args array)
 */
var argumentsLength = function(...args) {
    return args.length;
};

/**
 * Key Concepts:
 * 
 * 1. Rest Parameters (...args):
 *    - Collects all remaining arguments into an array
 *    - Must be the last parameter in function signature
 *    - Creates a real array (not array-like arguments object)
 *    - Can be used with any number of arguments
 * 
 * 2. Arguments Object (old way):
 *    - In older JavaScript, used arguments object
 *    - arguments is array-like but not a real array
 *    - arguments.length works, but arguments is not an array
 *    - Rest parameters are preferred in modern JavaScript
 * 
 * 3. Why Rest Parameters?
 *    - Real array: can use array methods (map, filter, etc.)
 *    - Cleaner syntax: explicit parameter name
 *    - Arrow functions: arguments object not available, but rest works
 *    - More modern: ES6+ feature
 * 
 * 4. Function Signature:
 *    - ...args must be last parameter
 *    - Can have other parameters before rest: function(a, b, ...args)
 *    - Cannot have parameters after rest: function(...args, x) // ERROR
 */

/**
 * Alternative implementations for learning:
 */

// Using arguments object (old way, not recommended):
// var argumentsLength = function() {
//     return arguments.length;
// };
// Note: This doesn't work with arrow functions!

// Using arrow function with rest parameters:
// const argumentsLength = (...args) => args.length;

// More explicit version:
// var argumentsLength = function(...args) {
//     const count = args.length;
//     return count;
// };

// With type checking (if needed):
// var argumentsLength = function(...args) {
//     if (args === undefined || args === null) {
//         return 0;
//     }
//     return args.length;
// };

/**
 * Comparison: Rest Parameters vs Arguments Object
 * 
 * Rest Parameters (...args):
 *   - Real array
 *   - Works in arrow functions
 *   - Explicit parameter name
 *   - Modern ES6+ syntax
 *   - Can use array methods directly
 * 
 * Arguments Object:
 *   - Array-like object (not real array)
 *   - Doesn't work in arrow functions
 *   - Implicit, always called 'arguments'
 *   - Older syntax
 *   - Need Array.from() or spread to use array methods
 * 
 * Example:
 *   function oldWay() {
 *       return arguments.length;  // Works
 *   }
 *   
 *   const arrowOld = () => {
 *       return arguments.length;  // ERROR: arguments not defined
 *   }
 *   
 *   const arrowNew = (...args) => {
 *       return args.length;  // Works!
 *   }
 */

/**
 * Usage Examples:
 * 
 * // Zero arguments
 * argumentsLength();  // 0
 * 
 * // Single argument
 * argumentsLength(42);  // 1
 * argumentsLength("hello");  // 1
 * argumentsLength(null);  // 1
 * argumentsLength(undefined);  // 1
 * 
 * // Multiple arguments
 * argumentsLength(1, 2, 3);  // 3
 * argumentsLength("a", "b", "c", "d");  // 4
 * 
 * // Mixed types
 * argumentsLength(1, "two", true, null, {});  // 5
 * 
 * // With objects and arrays
 * argumentsLength([1, 2], {x: 1}, 3);  // 3
 * 
 * // Many arguments
 * argumentsLength(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);  // 10
 */

/**
 * Edge Cases:
 * 
 * - No arguments: returns 0
 * - One argument: returns 1
 * - Many arguments: returns count
 * - Null/undefined as arguments: still counted
 * - Empty string: still counted as one argument
 * - Functions as arguments: still counted
 */

