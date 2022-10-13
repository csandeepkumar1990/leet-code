const bestTime = (arr) => {
    let min = Number.MAX_SAFE_INTEGER;
    let profit = 0;
    for (let index = 0; index < arr.length; index++) {
        const price = arr[index];     
        if(price < min) {
            min = price; // for every element, we are calculating the difference between that element and the minimum of all the values before that element
        } else if (price - min > profit)  { // check for profit
            profit = price  - min;
        }
    }
    return profit;
}

console.log(bestTime([7,1,5,3,6,4]));