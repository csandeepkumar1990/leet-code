/**
 * LeetCode 2648: Generate Fibonacci Sequence
 * 
 * Problem: Write a generator function that returns a generator object which yields
 *          the fibonacci sequence.
 * 
 *          The fibonacci sequence is defined by the relation Xn = Xn-1 + Xn-2.
 *          The first few numbers of the sequence are 0, 1, 1, 2, 3, 5, 8, 13, 21.
 * 
 *          The generator function should be called with no arguments.
 * 
 * Key Insight: Use JavaScript generator functions (function*) with yield to create
 *              a lazy sequence. Generators allow pausing and resuming execution,
 *              making them perfect for infinite sequences like Fibonacci.
 * 
 * How Generators Work:
 *    - function* creates a generator function
 *    - yield pauses execution and returns a value
 *    - Calling the function returns a generator object
 *    - gen.next() resumes execution until next yield
 *    - State is preserved between calls
 * 
 * Fibonacci Sequence:
 *    F(0) = 0
 *    F(1) = 1
 *    F(2) = F(1) + F(0) = 1 + 0 = 1
 *    F(3) = F(2) + F(1) = 1 + 1 = 2
 *    F(4) = F(3) + F(2) = 2 + 1 = 3
 *    F(5) = F(4) + F(3) = 3 + 2 = 5
 *    ...
 * 
 * Examples:
 *   const gen = fibGenerator();
 *   gen.next().value; // 0
 *   gen.next().value; // 1
 *   gen.next().value; // 1
 *   gen.next().value; // 2
 *   gen.next().value; // 3
 *   gen.next().value; // 5
 *   gen.next().value; // 8
 * 
 * Visual Flow:
 *    Initial: current = 0, next = 1
 *    
 *    Call 1: yield 0
 *            [current, next] = [1, 0 + 1] = [1, 1]
 *    
 *    Call 2: yield 1
 *            [current, next] = [1, 1 + 1] = [1, 2]
 *    
 *    Call 3: yield 1
 *            [current, next] = [2, 1 + 2] = [2, 3]
 *    
 *    Call 4: yield 2
 *            [current, next] = [3, 2 + 3] = [3, 5]
 *    
 *    Call 5: yield 3
 *            [current, next] = [5, 3 + 5] = [5, 8]
 *    
 *    ... continues infinitely
 */

/**
 * Generates an infinite sequence of Fibonacci numbers.
 * 
 * @return {Generator<number>} - A generator object that yields Fibonacci numbers
 * 
 * Time Complexity: O(1) per call - constant time to compute next value
 * Space Complexity: O(1) - only storing two variables (current, next)
 * 
 * How It Works:
 *   1. Initialize: current = 0 (F(0)), next = 1 (F(1))
 *   2. Yield current value
 *   3. Update: [current, next] = [next, current + next]
 *      - This calculates the next Fibonacci number
 *   4. Repeat infinitely
 */
var fibGenerator = function*() {
    let current = 0;
    let next = 1;

    while (true) {
        // The yield statement returns the current Fibonacci number and freezes the function's state.
        yield current;
        // Use destructuring to update both values at once
        [current, next] = [next, current + next];
    }
};

/**
 * const gen = fibGenerator();
 * gen.next().value; // 0
 * gen.next().value; // 1
 */

/**
 * Key Concepts:
 * 
 * 1. Generator Functions (function*):
 *    - Special type of function that can pause and resume
 *    - Returns a Generator object when called
 *    - Uses yield to produce values
 *    - Maintains state between calls
 * 
 * 2. yield Keyword:
 *    - Pauses execution and returns a value
 *    - When next() is called, execution resumes after yield
 *    - Returns { value: <yielded_value>, done: false }
 * 
 * 3. Destructuring Assignment:
 *    - [current, next] = [next, current + next]
 *    - Updates both variables simultaneously
 *    - Prevents needing a temporary variable
 *    - Equivalent to:
 *        let temp = current;
 *        current = next;
 *        next = temp + next;
 * 
 * 4. Infinite Loop:
 *    - while (true) creates infinite sequence
 *    - Generator only computes values when requested
 *    - Lazy evaluation: values computed on-demand
 *    - Memory efficient: doesn't store entire sequence
 * 
 * 5. Fibonacci Calculation:
 *    - Start with F(0) = 0, F(1) = 1
 *    - Each number is sum of previous two
 *    - Pattern: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
 */

/**
 * Alternative implementations for learning:
 */

// Using temporary variable (more explicit):
// var fibGenerator = function*() {
//     let current = 0;
//     let next = 1;
//     while (true) {
//         yield current;
//         let temp = current;
//         current = next;
//         next = temp + next;
//     }
// };

// Starting from F(1) = 1, F(2) = 1 (if problem required different starting point):
// var fibGenerator = function*() {
//     let a = 1;
//     let b = 1;
//     yield 0; // F(0) = 0
//     while (true) {
//         yield a;
//         [a, b] = [b, a + b];
//     }
// };

// Using array to track last two values:
// var fibGenerator = function*() {
//     let fib = [0, 1];
//     yield fib[0];
//     while (true) {
//         yield fib[1];
//         fib = [fib[1], fib[0] + fib[1]];
//     }
// };

/**
 * Usage Examples:
 * 
 * // Basic usage
 * const gen = fibGenerator();
 * console.log(gen.next().value); // 0
 * console.log(gen.next().value); // 1
 * console.log(gen.next().value); // 1
 * console.log(gen.next().value); // 2
 * console.log(gen.next().value); // 3
 * 
 * // Get first 10 Fibonacci numbers
 * const gen = fibGenerator();
 * const fib10 = [];
 * for (let i = 0; i < 10; i++) {
 *     fib10.push(gen.next().value);
 * }
 * // fib10 = [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
 * 
 * // Using with for...of (limited iterations)
 * const gen = fibGenerator();
 * let count = 0;
 * for (const value of gen) {
 *     console.log(value);
 *     if (++count >= 10) break; // Stop after 10 values
 * }
 * 
 * // Generator maintains state
 * const gen = fibGenerator();
 * gen.next(); // { value: 0, done: false }
 * gen.next(); // { value: 1, done: false }
 * gen.next(); // { value: 1, done: false }
 * // Each call remembers where it left off
 */

/**
 * Generator Object Methods:
 * 
 * - gen.next(): Returns { value: <yielded_value>, done: false }
 * - gen.return(value): Stops generator and returns { value: <value>, done: true }
 * - gen.throw(error): Throws error at yield point
 * 
 * Example:
 *   const gen = fibGenerator();
 *   gen.next();        // { value: 0, done: false }
 *   gen.next();        // { value: 1, done: false }
 *   gen.return(100);   // { value: 100, done: true }
 *   gen.next();        // { value: undefined, done: true }
 */

