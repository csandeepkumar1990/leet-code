const secondSmallest = (array) => {
    let max = array[0];
    let secondMax = -999999;
    let min = array[0];
    let secondMin = 999999;
    for (let index = 0; index < array.length; index++) {
        const element = array[index];
        if(element > max) {
            secondMax = max;
            max = element;
        } else if(element > secondMax && element < max) {
            secondMax = element;
        }

        if(element < min) {
            secondMin = min;
            min = element;
        } else if(element < secondMin && element > min) {
            secondMin = element;
        }
    }
    console.log('secondMax: ', secondMax);
    console.log('secondMin: ', secondMin);
    console.log('----------------------')
    //return 
}

secondSmallest([1,2,3]);
secondSmallest([2,3]);
secondSmallest([4,3, 1, 2]);