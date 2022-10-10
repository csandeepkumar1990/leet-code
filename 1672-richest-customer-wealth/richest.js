const maxWealth = (accounts) => {
    let max = 0;
    for (let index = 0; index < accounts.length; index++) {
        const customer = accounts[index];
        let sum = 0;
        for (let ci = 0; ci < customer.length; ci++) {
            sum = sum + customer[ci];
        }
        max = Math.max(max, sum);
    }
    return max;
}

console.log(maxWealth([[1,2,3],[3,2,1]]));