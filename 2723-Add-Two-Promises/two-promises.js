/**
 * LeetCode 2723: Add Two Promises
 * 
 * Given two promises that resolve to numbers, return a new promise
 * that resolves to the sum of those two numbers.
 * 
 * @param {Promise} promise1 - A promise that resolves to a number
 * @param {Promise} promise2 - A promise that resolves to a number
 * @return {Promise} - A promise that resolves to the sum of both values
 * 
 * Time Complexity: O(1) - just waiting for promises and adding
 * Space Complexity: O(1) - only storing two values
 */
var addTwoPromises = async function(promise1, promise2) {
    // The 'async' keyword makes this function return a Promise automatically
    // Any value we return will be wrapped in a resolved Promise
    
    // 'await' pauses execution until promise1 resolves
    // Once resolved, the actual numeric value is extracted and stored in value1
    let value1 = await promise1;
    
    // Same thing - wait for promise2 to resolve and extract its value
    let value2 = await promise2;
    
    // Return the sum - since this is an async function,
    // the result is automatically wrapped in a Promise
    // So returning 5 actually returns Promise.resolve(5)
    return value1 + value2;
};

// Example usage:
// addTwoPromises(Promise.resolve(2), Promise.resolve(2))
//   .then(console.log); // Output: 4

// Note: A more efficient approach using Promise.all to wait in parallel:
// var addTwoPromises = async (p1, p2) => {
//     const [v1, v2] = await Promise.all([p1, p2]);
//     return v1 + v2;
// };