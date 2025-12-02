/**
 * LeetCode 1837: Sum of Digits in Base K (Extended Example)
 * 
 * This class demonstrates the algorithm with n = 134
 * The original constraint was 1 <= n <= 100, but the algorithm
 * works for ANY positive integer regardless of size!
 * 
 * Why? Because we're just repeatedly doing modulo and division,
 * which works for any number. The time complexity scales with
 * the number of digits: O(log_k(n))
 */
class SumBaseExtended {

    public int sumBase(int n, int k) {
        int sum = 0;
        while (n > 0) {
            sum += n % k;
            n /= k;
        }
        return sum;
    }

    /**
     * Verbose version that shows step-by-step conversion
     */
    public int sumBaseVerbose(int n, int k) {
        System.out.println("Converting " + n + " to base " + k + ":");
        System.out.println("----------------------------------------");
        
        int sum = 0;
        int originalN = n;
        StringBuilder digits = new StringBuilder();
        int step = 1;
        
        while (n > 0) {
            int digit = n % k;
            int nextN = n / k;
            
            System.out.println("Step " + step + ": " + n + " % " + k + " = " + digit + 
                             " (digit), " + n + " / " + k + " = " + nextN + " (remaining)");
            
            sum += digit;
            digits.insert(0, digit);  // Prepend digit to build the number
            n = nextN;
            step++;
        }
        
        System.out.println("----------------------------------------");
        System.out.println(originalN + " in base " + k + " = " + digits.toString());
        System.out.println("Sum of digits: " + sum);
        System.out.println();
        
        return sum;
    }

    public static void main(String[] args) {
        SumBaseExtended sol = new SumBaseExtended();
        
        System.out.println("=== EXAMPLE WITH n = 134 ===\n");
        
        // Example 1: 134 in base 10
        // 134 = "134", sum = 1 + 3 + 4 = 8
        sol.sumBaseVerbose(134, 10);
        
        // Example 2: 134 in base 2 (binary)
        // 134 = 128 + 4 + 2 = 2^7 + 2^2 + 2^1 = "10000110"
        // sum = 1+0+0+0+0+1+1+0 = 3
        sol.sumBaseVerbose(134, 2);
        
        // Example 3: 134 in base 6
        // 134 = 3*36 + 4*6 + 2 = 3*6^2 + 4*6^1 + 2*6^0 = "342"
        // sum = 3 + 4 + 2 = 9
        sol.sumBaseVerbose(134, 6);
        
        // Example 4: 134 in base 8 (octal)
        // 134 = 2*64 + 0*8 + 6 = 2*8^2 + 0*8^1 + 6*8^0 = "206"
        // sum = 2 + 0 + 6 = 8
        sol.sumBaseVerbose(134, 8);
        
        // Example 5: 134 in base 16 (hexadecimal)
        // 134 = 8*16 + 6 = "86" (hex)
        // sum = 8 + 6 = 14
        sol.sumBaseVerbose(134, 16);
        
        System.out.println("=== QUICK RESULTS ===");
        System.out.println("sumBase(134, 10) = " + sol.sumBase(134, 10));  // 8
        System.out.println("sumBase(134, 2)  = " + sol.sumBase(134, 2));   // 3
        System.out.println("sumBase(134, 6)  = " + sol.sumBase(134, 6));   // 9
        System.out.println("sumBase(134, 8)  = " + sol.sumBase(134, 8));   // 8
        System.out.println("sumBase(134, 16) = " + sol.sumBase(134, 16));  // 14
    }
}

