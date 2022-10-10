
const runningSum = (array) => {
    let results = [];
    results[0] = array[0];
    for (let index = 1; index < array.length; index++) { // O(n)
        results[index] = array[index] + results[index -1];
    }
    return results;
}

console.log(runningSum([3, 1, 2, 10, 1]))