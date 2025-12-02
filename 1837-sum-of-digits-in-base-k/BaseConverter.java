/**
 * Base 6 Converter - Convert decimal numbers to base 6
 * 
 * Algorithm:
 * 1. Repeatedly divide the number by 6
 * 2. The remainder at each step gives you a digit (right to left)
 * 3. Continue until the number becomes 0
 * 4. Reverse the collected digits to get the final representation
 */
class BaseConverter {

    /**
     * Convert a decimal number to base 6
     */
    public String convertToBase6(int n) {
        if (n == 0) return "0";
        
        StringBuilder result = new StringBuilder();
        
        // Extract digits from right to left
        while (n > 0) {
            int remainder = n % 6;  // Get rightmost digit in base 6
            result.append(remainder);
            n = n / 6;              // Remove rightmost digit
        }
        
        // Reverse because we collected digits right-to-left
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        BaseConverter converter = new BaseConverter();
        
        // Convert 134 to base 6
        int number = 134;
        String base6 = converter.convertToBase6(number);
        
        System.out.println(number + " in base 6 = " + base6);  // Output: 342
        
        // Step-by-step breakdown:
        // 134 % 6 = 2, 134 / 6 = 22
        //  22 % 6 = 4,  22 / 6 = 3
        //   3 % 6 = 3,   3 / 6 = 0  (done)
        // Digits: 2, 4, 3 -> reversed: "342"
        
        // Verification: 3×36 + 4×6 + 2×1 = 108 + 24 + 2 = 134 ✓
    }
}
