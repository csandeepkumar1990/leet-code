/**
 * LeetCode 2804: Array Prototype ForEach
 * 
 * Problem: Implement the Array.prototype.forEach method that executes a provided
 *          callback function once for each array element, with support for a
 *          custom context (this binding).
 * 
 * Key Insight: The forEach method iterates through each element of the array and
 *              calls the callback function with the element, index, and array.
 *              The context parameter allows setting the 'this' value inside the callback.
 * 
 * Array.prototype.forEach:
 *    - Executes callback for each element in the array
 *    - Does not return a value (returns undefined)
 *    - Does not mutate the array (unless callback does)
 *    - Skips empty slots in sparse arrays
 *    - Cannot be stopped (unlike for...of with break)
 * 
 * Callback Function Signature:
 *    callback(element, index, array)
 *    - element: current element being processed
 *    - index: index of current element
 *    - array: the array forEach was called upon
 * 
 * Context Parameter:
 *    - Optional second parameter
 *    - Sets the 'this' value inside the callback function
 *    - Uses Function.prototype.call() to bind context
 * 
 * Examples:
 * 
 *   Basic Usage:
 *     const arr = [1, 2, 3];
 *     arr.forEach((val, i) => console.log(val, i));
 *     // Output: 1 0
 *     //         2 1
 *     //         3 2
 * 
 *   With Context:
 *     const arr = [1, 2, 3];
 *     const context = { multiplier: 2 };
 *     const callback = function(val) {
 *         console.log(val * this.multiplier);
 *     };
 *     arr.forEach(callback, context);
 *     // Output: 2
 *     //         4
 *     //         6
 * 
 *   Modifying Array (from example):
 *     const arr = [1, 2, 3];
 *     const callback = (val, i, arr) => arr[i] = val * 2;
 *     const context = {"context": true};
 *     arr.forEach(callback, context);
 *     console.log(arr); // [2, 4, 6]
 * 
 * Visual Representation:
 * 
 *   arr = [1, 2, 3]
 *   callback = (val, i, arr) => arr[i] = val * 2
 * 
 *   Iteration 0:
 *     callback.call(context, arr[0], 0, arr)
 *     callback.call(context, 1, 0, [1,2,3])
 *     arr[0] = 1 * 2 = 2
 *     arr = [2, 2, 3]
 * 
 *   Iteration 1:
 *     callback.call(context, arr[1], 1, arr)
 *     callback.call(context, 2, 1, [2,2,3])
 *     arr[1] = 2 * 2 = 4
 *     arr = [2, 4, 3]
 * 
 *   Iteration 2:
 *     callback.call(context, arr[2], 2, arr)
 *     callback.call(context, 3, 2, [2,4,3])
 *     arr[2] = 3 * 2 = 6
 *     arr = [2, 4, 6]
 * 
 *   Final: [2, 4, 6]
 * 
 * Key Concepts:
 * 
 * 1. Array.prototype Extension:
 *    - Adding method to Array.prototype makes it available on all arrays
 *    - This is how built-in methods like forEach, map, filter work
 *    - Can be called on any array: [1,2,3].forEach(...)
 * 
 * 2. Function.prototype.call():
 *    - Calls a function with a specified 'this' value
 *    - Syntax: func.call(thisArg, arg1, arg2, ...)
 *    - First argument is the 'this' context
 *    - Remaining arguments are passed to the function
 * 
 * 3. Context Binding:
 *    - Without context: callback's 'this' is undefined (strict mode) or global (non-strict)
 *    - With context: callback's 'this' is set to the context object
 *    - Useful for object-oriented callbacks
 * 
 * 4. Callback Arguments:
 *    - Standard forEach passes: (element, index, array)
 *    - All three arguments are always passed (even if callback doesn't use them)
 *    - Callback can use any combination of these arguments
 * 
 * Edge Cases:
 * 
 * 1. Empty Array:
 *    const arr = [];
 *    arr.forEach((val) => console.log(val));
 *    // No output, callback never called
 * 
 * 2. Sparse Array:
 *    const arr = [1, , 3]; // sparse array with empty slot
 *    arr.forEach((val, i) => console.log(val, i));
 *    // Output: 1 0
 *    //         3 2
 *    // Note: index 1 is skipped (empty slot)
 * 
 * 3. Undefined Context:
 *    const arr = [1, 2];
 *    const callback = function() { console.log(this); };
 *    arr.forEach(callback); // this is undefined (strict mode)
 *    arr.forEach(callback, null); // this is null
 * 
 * 4. Modifying Array During Iteration:
 *    const arr = [1, 2, 3];
 *    arr.forEach((val, i) => {
 *        if (i === 0) arr.push(4);
 *        console.log(val);
 *     });
 *     // Output: 1, 2, 3
 *     // Note: forEach uses initial array length, new elements not processed
 * 
 * 5. Deleting Elements:
 *    const arr = [1, 2, 3];
 *    arr.forEach((val, i) => {
 *        if (i === 0) delete arr[1];
 *        console.log(val);
 *     });
 *     // Output: 1, undefined, 3
 *     // Deleted elements become empty slots
 * 
 * Differences from Built-in forEach:
 * 
 *   This implementation is a simplified version. The built-in forEach:
 *   - Handles sparse arrays more carefully
 *   - Has optimizations for performance
 *   - Handles edge cases with deleted/added elements
 *   - Works with array-like objects when called via Array.prototype.forEach.call()
 * 
 * Why Use .call()?
 * 
 *   - callback.call(context, ...) allows us to set the 'this' value
 *   - Without .call(), we'd use callback(...) but 'this' would be undefined
 *   - .call() explicitly binds the context as the first argument
 *   - This matches the behavior of the native forEach method
 * 
 * Alternative: Using .apply()
 * 
 *   Could also use:
 *   ```javascript
 *   callback.apply(context, [this[i], i, this]);
 *   ```
 *   But .call() is more direct when we know the exact arguments.
 * 
 * Arrow Functions and Context:
 * 
 *   Arrow functions don't have their own 'this' binding:
 *   ```javascript
 *   const arr = [1, 2];
 *   const context = { x: 10 };
 *   const arrow = (val) => console.log(this.x); // 'this' is from outer scope
 *   const regular = function(val) { console.log(this.x); };
 *   
 *   arr.forEach(arrow, context); // 'this' not bound to context
 *   arr.forEach(regular, context); // 'this' bound to context
 *   ```
 * 
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(1) - only using a few variables
 */

/**
 * Custom implementation of Array.prototype.forEach
 * 
 * Executes a provided callback function once for each array element.
 * 
 * @param {Function} callback - Function to execute for each element
 *                             Signature: callback(element, index, array)
 * @param {Object} context - Optional. Value to use as 'this' when executing callback
 * @return {void} - Always returns undefined
 * 
 * Time Complexity: O(n) where n is the array length
 * Space Complexity: O(1)
 */
Array.prototype.forEach = function(callback, context) {
    // Iterate through each element in the array
    for (let i = 0; i < this.length; i++) {
        // Use .call() to set the 'this' context inside the callback to the 'context' argument
        // Pass three arguments to the callback: current value, index, and the original array
        // 
        // callback.call(context, this[i], i, this) means:
        // - Call callback with 'this' set to context
        // - Pass this[i] as first argument (element)
        // - Pass i as second argument (index)
        // - Pass this as third argument (the array itself)
        callback.call(context, this[i], i, this);
    }
};

/**
 * Usage Example:
 * 
 * const arr = [1, 2, 3];
 * const callback = (val, i, arr) => arr[i] = val * 2;
 * const context = {"context": true};
 * 
 * arr.forEach(callback, context);
 * 
 * console.log(arr); // [2, 4, 6]
 * 
 * Explanation:
 * - callback is called for each element: 1, 2, 3
 * - Each call: callback.call(context, val, i, arr)
 * - Inside callback, 'this' refers to context object
 * - Callback modifies arr[i] = val * 2
 * - Result: [2, 4, 6]
 */

