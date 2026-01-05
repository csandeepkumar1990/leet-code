/**
 * LeetCode 2635: Apply Transform Over Each Element in Array
 * 
 * Problem: Given an integer array arr and a mapping function fn, return a new array
 *          with a transformation applied to each element.
 * 
 *          The returned array should be created such that:
 *          returnedArray[i] = fn(arr[i], i)
 * 
 *          Please solve it without the built-in Array.map method.
 * 
 * Key Insight: Map creates a new array by applying a function to each element.
 *              The function receives both the element value and its index.
 * 
 * How Map Works (Visual):
 *    arr = [1, 2, 3]
 *    fn = (val, idx) => val * 2
 * 
 *    arr[0] = 1 ──┐
 *                 ├── fn(1, 0) = 2 ──→ newArr[0] = 2
 *    arr[1] = 2 ──┼── fn(2, 1) = 4 ──→ newArr[1] = 4
 *    arr[2] = 3 ──┘── fn(3, 2) = 6 ──→ newArr[2] = 6
 * 
 *    Result: [2, 4, 6]
 * 
 * Examples:
 *   Input: arr = [1,2,3], fn = function plusone(n) { return n + 1; }
 *   Output: [2,3,4]
 *   Explanation: 
 *     const newArray = map(arr, plusone); // [2,3,4]
 *     The function increases each value in the array by one.
 * 
 *   Input: arr = [1,2,3], fn = function plusI(n, i) { return n + i; }
 *   Output: [1,3,5]
 *   Explanation: 
 *     The function increases each value by the index it resides in.
 *     fn(1, 0) = 1 + 0 = 1
 *     fn(2, 1) = 2 + 1 = 3
 *     fn(3, 2) = 3 + 2 = 5
 * 
 *   Input: arr = [10,20,30], fn = function constant() { return 42; }
 *   Output: [42,42,42]
 *   Explanation: 
 *     The function always returns 42 regardless of the input.
 */

/**
 * Applies a transformation function to each element of the array.
 * 
 * @param {number[]} arr - The input array of numbers
 * @param {Function} fn - Transformation function that takes (element, index) and returns transformed value
 * @return {number[]} - A new array with transformed elements
 * 
 * Time Complexity: O(n) - iterate through each element once
 * Space Complexity: O(n) - create a new array of same length
 */
var map = function(arr, fn) {
    let newArr = [];
    for (let i = 0; i < arr.length; i++) {
        newArr[i] = fn(arr[i], i); // Apply fn to element and index, store in new array
    }
    return newArr; // Return the transformed array
};

/**
 * Alternative implementations for learning:
 */

// Using for...of loop with index tracking:
// var map = function(arr, fn) {
//     let newArr = [];
//     let index = 0;
//     for (const element of arr) {
//         newArr.push(fn(element, index++));
//     }
//     return newArr;
// };

// Using forEach (if allowed):
// var map = function(arr, fn) {
//     let newArr = [];
//     arr.forEach((element, index) => {
//         newArr.push(fn(element, index));
//     });
//     return newArr;
// };

// Using push() instead of direct assignment:
// var map = function(arr, fn) {
//     let newArr = [];
//     for (let i = 0; i < arr.length; i++) {
//         newArr.push(fn(arr[i], i));
//     }
//     return newArr;
// };

// Pre-allocating array size (slightly more efficient):
// var map = function(arr, fn) {
//     let newArr = new Array(arr.length);
//     for (let i = 0; i < arr.length; i++) {
//         newArr[i] = fn(arr[i], i);
//     }
//     return newArr;
// };

/**
 * Key Concepts:
 * 
 * 1. Map Function:
 *    - Creates a new array (doesn't modify original)
 *    - Applies function to each element
 *    - Returns array of same length
 *    - Function receives (element, index) as parameters
 * 
 * 2. Why Create New Array?
 *    - Map is a pure function: doesn't mutate input
 *    - Original array remains unchanged
 *    - Functional programming principle
 * 
 * 3. Function Parameters:
 *    - First parameter: element value (arr[i])
 *    - Second parameter: element index (i)
 *    - Both are passed to fn, even if fn doesn't use index
 * 
 * 4. Direct Assignment vs Push:
 *    - newArr[i] = value: Direct assignment (faster)
 *    - newArr.push(value): Adds to end (slightly slower)
 *    - Both work, direct assignment is more efficient
 */

/**
 * Usage Examples:
 * 
 * // Add 1 to each element
 * map([1, 2, 3], (n) => n + 1);  // [2, 3, 4]
 * 
 * // Multiply by index
 * map([1, 2, 3], (n, i) => n * i);  // [0, 2, 6]
 * 
 * // Square each element
 * map([1, 2, 3, 4], (n) => n * n);  // [1, 4, 9, 16]
 * 
 * // Return constant value
 * map([1, 2, 3], () => 42);  // [42, 42, 42]
 * 
 * // Transform to strings
 * map([1, 2, 3], (n) => n.toString());  // ["1", "2", "3"]
 * 
 * // Filter-like behavior (but map keeps all elements)
 * map([1, 2, 3, 4], (n) => n % 2 === 0 ? n : 0);  // [0, 2, 0, 4]
 */

