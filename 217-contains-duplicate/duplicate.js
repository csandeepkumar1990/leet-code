const result = (array) => {
    let map = new Map();
    for (let index = 0; index < array.length; index++) {
        const element = array[index];
        if(map.get(element)) {
            return true;
        } else {
            map.set(element, 1);
        }
    }
    return false;
}

console.log(result([1,2,3,1]));