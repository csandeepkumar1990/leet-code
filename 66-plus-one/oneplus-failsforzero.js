// convert array to number
const arrayToNumber = (array) => {
    let position = 1;
    let number = 0;
    for (let index = array.length -1; index >= 0; index--) {
        let element = array[index];
        element = element * position;  // fails for 0 need to check
        number = number + element;
        //console.log(number);
        if(position >= 1) {
            position = 10 * position;
        }
    }
    
    return numberToArray(number+1);
}

// convert number to array
const numberToArray = (num) => {
    let arr = new Array();
    while (num > 0){
        const element = num % 10;
        num = Math.floor(num / 10);
        arr.push(element);
    }
    //console.log(num % 10);
    return arr.reverse();
}

console.log(arrayToNumber([4,3,2,1]));