/**
 * LeetCode 2822: Inversion of Object
 * 
 * Problem: Given an object `obj`, return an inverted object `invertedObj`.
 *          The `invertedObj` should have the keys of `obj` as values and
 *          the values of `obj` as keys.
 *          
 *          If there are multiple keys in `obj` that have the same value,
 *          those keys should be grouped into an array.
 *          
 *          It is guaranteed that the values in obj are only strings.
 * 
 * Key Insight: Iterate through each key-value pair. For each value, check if
 *              it already exists as a key in the result. If so, convert to
 *              array (or push to existing array). Otherwise, just set the key.
 * 
 * Examples:
 *   Input: obj = {"a": "1", "b": "2", "c": "3", "d": "4"}
 *   Output: {"1": "a", "2": "b", "3": "c", "4": "d"}
 *   Explanation: Each value becomes a key, each key becomes the value.
 * 
 *   Input: obj = {"a": "1", "b": "1", "c": "1", "d": "1"}
 *   Output: {"1": ["a", "b", "c", "d"]}
 *   Explanation: Multiple keys have same value "1", so they're grouped.
 * 
 *   Input: obj = {"a": "1", "b": "1", "c": "2"}
 *   Output: {"1": ["a", "b"], "2": "c"}
 *   Explanation: "a" and "b" share value "1" (grouped), "c" has unique "2".
 * 
 * Constraints:
 *   - obj is a valid JSON object
 *   - typeof obj[key] === 'string'
 *   - 1 <= JSON.stringify(obj).length <= 10^5
 */

/**
 * Returns an inverted version of the input object.
 * 
 * @param {Object} obj - The object to invert
 * @return {Object} - The inverted object with keys and values swapped
 * 
 * Time Complexity: O(n) where n is the number of keys in obj
 * Space Complexity: O(n) for the result object
 */
var objInvert = function(obj) {
    const invertedObj = {};

    /*
     * Iterate through each key-value pair in the original object
     * 
     * For each pair, we want to:
     * - Use the VALUE as the new KEY
     * - Use the KEY as the new VALUE
     * 
     * Special case: If multiple keys map to the same value,
     * we need to group them in an array.
     */
    
    for (const key in obj) {
        const value = obj[key];
        
        /*
         * Check if this value already exists as a key in our result
         * 
         * Three cases:
         * 1. Value doesn't exist as key yet → just set key = value
         * 2. Value exists and is already an array → push to array
         * 3. Value exists but is a string → convert to array
         */
        
        if (invertedObj[value] === undefined) {
            // Case 1: First time seeing this value
            // Simply set the inverted key-value pair
            invertedObj[value] = key;
        } else if (Array.isArray(invertedObj[value])) {
            // Case 2: Already converted to array (3+ keys with same value)
            // Just push the new key to the existing array
            invertedObj[value].push(key);
        } else {
            // Case 3: Second key with the same value
            // Convert from single string to array of strings
            invertedObj[value] = [invertedObj[value], key];
        }
    }

    return invertedObj;
};

/**
 * Alternative concise solution using reduce
 */
var objInvertConcise = function(obj) {
    return Object.entries(obj).reduce((acc, [key, value]) => {
        /*
         * Object.entries() converts object to array of [key, value] pairs
         * 
         * Example: {"a": "1", "b": "2"} → [["a", "1"], ["b", "2"]]
         * 
         * We destructure each pair and accumulate into result object
         */
        if (acc[value] === undefined) {
            acc[value] = key;
        } else {
            acc[value] = [].concat(acc[value], key);
        }
        return acc;
    }, {});
};

/**
 * Usage Example:
 * 
 * console.log(objInvert({"a": "1", "b": "2", "c": "3"}));
 * // Output: {"1": "a", "2": "b", "3": "c"}
 * 
 * console.log(objInvert({"a": "1", "b": "1", "c": "1"}));
 * // Output: {"1": ["a", "b", "c"]}
 * 
 * ═══════════════════════════════════════════════════════════════
 * OBJECT INVERSION VISUALIZATION
 * ═══════════════════════════════════════════════════════════════
 * 
 * Original Object:
 * ┌─────────────────────────────────────┐
 * │  KEY    │  VALUE                    │
 * ├─────────┼───────────────────────────┤
 * │  "a"    │  "1"                      │
 * │  "b"    │  "1"                      │
 * │  "c"    │  "2"                      │
 * │  "d"    │  "3"                      │
 * └─────────┴───────────────────────────┘
 *            ↓ INVERT ↓
 * 
 * Inverted Object:
 * ┌─────────────────────────────────────┐
 * │  KEY    │  VALUE                    │
 * ├─────────┼───────────────────────────┤
 * │  "1"    │  ["a", "b"]  (grouped!)   │
 * │  "2"    │  "c"                      │
 * │  "3"    │  "d"                      │
 * └─────────┴───────────────────────────┘
 * 
 * ═══════════════════════════════════════════════════════════════
 * STEP-BY-STEP TRACE: obj = {"a":"1", "b":"1", "c":"2"}
 * ═══════════════════════════════════════════════════════════════
 * 
 * Initial: invertedObj = {}
 * 
 * Step 1: key="a", value="1"
 *         invertedObj["1"] doesn't exist
 *         → Set invertedObj["1"] = "a"
 *         invertedObj = {"1": "a"}
 * 
 * Step 2: key="b", value="1"
 *         invertedObj["1"] exists and is string "a"
 *         → Convert to array: ["a", "b"]
 *         invertedObj = {"1": ["a", "b"]}
 * 
 * Step 3: key="c", value="2"
 *         invertedObj["2"] doesn't exist
 *         → Set invertedObj["2"] = "c"
 *         invertedObj = {"1": ["a", "b"], "2": "c"}
 * 
 * Final Result: {"1": ["a", "b"], "2": "c"}
 * 
 * ═══════════════════════════════════════════════════════════════
 * WHY CHECK FOR ARRAY.ISARRAY()?
 * ═══════════════════════════════════════════════════════════════
 * 
 * When we have 3+ keys mapping to the same value:
 * 
 * obj = {"a": "1", "b": "1", "c": "1"}
 * 
 * After processing "a": invertedObj = {"1": "a"}        (string)
 * After processing "b": invertedObj = {"1": ["a", "b"]} (array)
 * After processing "c": invertedObj = {"1": ["a", "b", "c"]}
 *                       ↑ Now we just push, not convert again
 * 
 * Without the Array.isArray check, we would incorrectly
 * wrap the existing array in another array!
 * 
 * ═══════════════════════════════════════════════════════════════
 * EDGE CASES
 * ═══════════════════════════════════════════════════════════════
 * 
 * 1. Empty object: {} → {}
 * 
 * 2. Single key: {"a": "1"} → {"1": "a"}
 * 
 * 3. All same values: {"a":"x", "b":"x", "c":"x"} → {"x": ["a","b","c"]}
 * 
 * 4. All unique values: {"a":"1", "b":"2", "c":"3"} → {"1":"a", "2":"b", "3":"c"}
 * 
 * 5. Mixed: Some duplicates, some unique (shown in visualization above)
 */

module.exports = { objInvert, objInvertConcise };

