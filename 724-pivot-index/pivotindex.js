const pivot = (array) => {
    let sum = 0;
    for (let index = 0; index < array.length; index++) {
        sum += array[index];
    }
    let leftsum = 0;
    for (let i = 0; i < array.length; i++) {
        let rightsum = sum - leftsum - array[i];
        if(leftsum == rightsum) {
            return i;
        }
        leftsum += array[i];
    }
    return -1;
}

console.log(pivot([1,7,3,6,5,6]));