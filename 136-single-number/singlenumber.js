const singleNumber = (array) => {
    let map = new Map();
    for (let index = 0; index < array.length; index++) {
        const element = array[index];
        if(!map.get(element)) {
            map.set(element, 1);
        } else {
            map.delete(element);
        }
    }
    return [...map.keys()][0];
}

singleNumber([4,1,2,1,2]);