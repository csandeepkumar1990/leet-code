// 3345. Smallest Divisible Digit Product I
// Find the smallest number >= n whose digit product is divisible by t.
// The digit product is the product of all digits in the number.
//
// Examples:
// Input: n = 10, t = 2 -> Output: 10
//   Digit product of 10 = 1 * 0 = 0, which is divisible by 2
//
// Input: n = 15, t = 3 -> Output: 16
//   Digit product of 15 = 1 * 5 = 5, not divisible by 3
//   Digit product of 16 = 1 * 6 = 6, divisible by 3
//
// Key Insight: The answer is guaranteed to be within n to n+9 range
// because any number with digit 0 has digit product 0, divisible by any t.
//
// Time Complexity: O(10 * d) where d is number of digits - at most 10 iterations
// Space Complexity: O(1) - only using constant extra space

class Solution {

    public int smallestNumber(int n, int t) {

        int result = 0;

        for (int num = n; num < n + 10; ++num) {

            if (getDigitProd(num) % t == 0){

                result = num;

                break;

            }

                

        }

        return result;

    }

    private int getDigitProd(int num) {

        int digitProd = 1;

        while (num > 0) {

            digitProd *= num % 10;

            num /= 10;

        }

        return digitProd;

    }

}

