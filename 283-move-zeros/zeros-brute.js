const result = (array) => {
    let zeroCount = 0;
    let insertIndex = 0;
    for (let index = 0; index < array.length; index++) {
        const element = array[index];
        if(element != 0) {
            array[insertIndex] = element;
            insertIndex = insertIndex +1;
        } else {
            zeroCount++;
        }
    }
    if(zeroCount > 0) {
        for (let index = insertIndex; index < array.length; index++) {
            array[index] = 0;
        }
    }
    return array;
}

console.log(result([0,1,0,3,12]));