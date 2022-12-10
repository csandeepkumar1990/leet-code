const fizzbuzz = (length) => {
    let array = new Array(length)
    for (let i = 1; i <= length; i++) {
        let x = "";
        if(i % 3 == 0) {
            x = "Fizz"
        } 

        if(i % 5 == 0) {
            x = "Buzz"
        }
        
        if(i % 3 == 0 && i % 5 == 0) {
            x = "FizzBuzz"
        }


        if(x != "") {
            array[i-1] = x;
        } else {
            array[i-1] = ""+i;
        }
       
    }
    return array;
}

console.log(fizzbuzz(3))