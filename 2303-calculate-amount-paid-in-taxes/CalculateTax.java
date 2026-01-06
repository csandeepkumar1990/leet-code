/*
 * LeetCode 2303: Calculate Amount Paid in Taxes
 * 
 * Problem:
 * You are given a 2D array brackets where brackets[i] = [upper_i, percent_i] means that
 * the i-th tax bracket has an upper bound of upper_i and is taxed at a rate of percent_i.
 * The brackets are sorted by upper bound (i.e., upper_i-1 < upper_i for 0 < i < brackets.length).
 * 
 * Tax is calculated as follows:
 * - The first upper_0 dollars earned are taxed at a rate of percent_0
 * - The next upper_1 - upper_0 dollars earned are taxed at a rate of percent_1
 * - The next upper_2 - upper_1 dollars earned are taxed at a rate of percent_2
 * - And so on.
 * 
 * You are also given an integer income representing the amount of money you earned.
 * Return the amount of money you have to pay in taxes. Answers within 10^-5 of the
 * actual answer will be accepted.
 * 
 * Approach: Progressive Tax Bracket Calculation
 * 
 * Key Insight:
 * - Progressive tax system: different portions of income are taxed at different rates
 * - Each bracket applies to the income within its range
 * - For bracket i: taxable amount = min(income, upper_i) - previousUpper
 * - Tax for bracket i = taxableAmount * (percent_i / 100.0)
 * - Stop early if income <= previousUpper (no more income to tax)
 * 
 * Algorithm:
 * 1. Initialize tax = 0.0 and previousUpper = 0
 * 2. For each bracket [upper, percent]:
 *    a. If income <= previousUpper, break (no income left to tax)
 *    b. Calculate taxable amount = min(income, upper) - previousUpper
 *    c. Add tax: taxableAmount * (percent / 100.0)
 *    d. Update previousUpper = upper
 * 3. Return total tax
 * 
 * Time Complexity: O(n)
 *   - Single pass through brackets: O(n) where n is number of brackets
 *   - Each iteration does constant work
 * 
 * Space Complexity: O(1)
 *   - Only using a few variables (tax, previousUpper, etc.)
 *   - No additional data structures
 * 
 * Example:
 * 
 *   Input: brackets = [[3,50],[7,10],[12,25]], income = 10
 * 
 *   Bracket 0: [upper=3, percent=50]
 *     previousUpper = 0
 *     taxableAmount = min(10, 3) - 0 = 3 - 0 = 3
 *     tax += 3 * (50 / 100.0) = 3 * 0.5 = 1.5
 *     previousUpper = 3
 *     tax = 1.5
 * 
 *   Bracket 1: [upper=7, percent=10]
 *     previousUpper = 3
 *     taxableAmount = min(10, 7) - 3 = 7 - 3 = 4
 *     tax += 4 * (10 / 100.0) = 4 * 0.1 = 0.4
 *     previousUpper = 7
 *     tax = 1.9
 * 
 *   Bracket 2: [upper=12, percent=25]
 *     previousUpper = 7
 *     taxableAmount = min(10, 12) - 7 = 10 - 7 = 3
 *     tax += 3 * (25 / 100.0) = 3 * 0.25 = 0.75
 *     previousUpper = 12
 *     tax = 2.65
 * 
 *   Result: 2.65
 * 
 * Visual Representation:
 * 
 *   Income: $10
 *   Brackets: [0-3: 50%], [3-7: 10%], [7-12: 25%], [12+: 25%]
 * 
 *   Tax calculation:
 *     $0-$3:    $3 * 50% = $1.50
 *     $3-$7:    $4 * 10% = $0.40
 *     $7-$10:   $3 * 25% = $0.75
 *     ──────────────────────────
 *     Total:              $2.65
 * 
 * Another Example:
 * 
 *   Input: brackets = [[1,0],[4,25],[5,50]], income = 2
 * 
 *   Bracket 0: [upper=1, percent=0]
 *     previousUpper = 0
 *     taxableAmount = min(2, 1) - 0 = 1 - 0 = 1
 *     tax += 1 * (0 / 100.0) = 1 * 0.0 = 0.0
 *     previousUpper = 1
 *     tax = 0.0
 * 
 *   Bracket 1: [upper=4, percent=25]
 *     previousUpper = 1
 *     taxableAmount = min(2, 4) - 1 = 2 - 1 = 1
 *     tax += 1 * (25 / 100.0) = 1 * 0.25 = 0.25
 *     previousUpper = 4
 *     tax = 0.25
 * 
 *   Bracket 2: [upper=5, percent=50]
 *     previousUpper = 4
 *     income (2) <= previousUpper (4)? No, but min(2, 5) - 4 = 2 - 4 = -2
 *     Wait, this is wrong. Let me recalculate...
 * 
 *   Actually, when income = 2:
 *     Bracket 0: taxableAmount = min(2, 1) - 0 = 1, tax = 0
 *     Bracket 1: taxableAmount = min(2, 4) - 1 = 1, tax = 0.25
 *     Bracket 2: income (2) <= previousUpper (4)? No, but min(2, 5) = 2, and 2 - 4 = -2
 *     
 *   The issue is that we should check if income <= previousUpper before calculating.
 *   Actually, the code does check this: "if (income <= previousUpper) break;"
 *   So for bracket 2, income (2) <= previousUpper (4) is true, so we break.
 *   Result: 0.25
 * 
 * Edge Cases:
 * 
 * 1. Income is 0:
 *    brackets = [[3,50]], income = 0
 *    Bracket 0: income (0) <= previousUpper (0)? No, but min(0, 3) - 0 = 0
 *    tax = 0 * 0.5 = 0.0
 *    Result: 0.0
 * 
 * 2. Income exceeds all brackets:
 *    brackets = [[3,50],[7,10]], income = 20
 *    Bracket 0: taxableAmount = 3 - 0 = 3, tax = 1.5
 *    Bracket 1: taxableAmount = 7 - 3 = 4, tax = 1.9
 *    But wait, income is 20, so we need to handle the remaining income.
 *    Actually, the last bracket typically extends to infinity, but if not,
 *    we'd need to check. In this problem, brackets are sorted and we process
 *    all brackets. For the last bracket, if income > upper, we'd calculate
 *    taxableAmount = upper - previousUpper, which is correct.
 *    
 *    Actually, let me reconsider: if income = 20 and brackets = [[3,50],[7,10]],
 *    then:
 *      Bracket 0: min(20, 3) - 0 = 3, tax = 1.5
 *      Bracket 1: min(20, 7) - 3 = 4, tax = 1.9
 *    But what about income from 7 to 20? The problem states brackets are sorted,
 *    so typically the last bracket's upper bound would be >= income, or we'd
 *    need to handle it. Let me assume the last bracket covers all remaining income.
 * 
 * 3. Single bracket:
 *    brackets = [[100,10]], income = 50
 *    taxableAmount = min(50, 100) - 0 = 50
 *    tax = 50 * 0.1 = 5.0
 *    Result: 5.0
 * 
 * 4. Zero percent tax bracket:
 *    brackets = [[10,0],[20,25]], income = 15
 *    Bracket 0: taxableAmount = 10 - 0 = 10, tax = 0.0
 *    Bracket 1: taxableAmount = min(15, 20) - 10 = 5, tax = 1.25
 *    Result: 1.25
 * 
 * Why Use Double for Tax?
 * 
 *   - Tax calculations involve percentages and can result in decimal values
 *   - Using double ensures precision for fractional currency amounts
 *   - The problem accepts answers within 10^-5, indicating floating-point precision
 * 
 * Why Check income <= previousUpper?
 * 
 *   - Early termination optimization: if income is fully taxed, no need to check
 *     remaining brackets
 *   - Prevents unnecessary calculations when income falls within lower brackets
 *   - Example: income = 5, brackets go up to 100, 200, etc. - we can stop early
 * 
 * Progressive Tax System:
 * 
 *   This implements a progressive tax system where:
 *   - Lower income portions are taxed at lower rates (or same rates)
 *   - Higher income portions may be taxed at higher rates
 *   - Each bracket only applies to income within its range
 *   - The taxable amount for each bracket is the portion of income that falls
 *     within that bracket's range
 */

class Solution {
    /**
     * Calculates the total tax amount based on progressive tax brackets.
     * 
     * @param brackets 2D array where brackets[i] = [upper_i, percent_i]
     *                 represents the upper bound and tax rate for bracket i
     * @param income The total income to calculate tax for
     * @return The total tax amount to be paid
     * 
     * Time Complexity: O(n) where n is the number of brackets
     * Space Complexity: O(1)
     */
    public double calculateTax(int[][] brackets, int income) {
        double tax = 0.0;
        int previousUpper = 0;

        for (int i = 0; i < brackets.length; i++) {
            int upper = brackets[i][0];   // Current bracket's upper bound
            int percent = brackets[i][1]; // Tax rate for this bracket

            // Early termination: if income is fully taxed, no need to check remaining brackets
            if (income <= previousUpper) break;

            // Calculate the taxable amount for this bracket
            // This is the portion of income that falls within this bracket's range
            // min(income, upper) ensures we don't tax beyond the income or bracket upper bound
            // Subtract previousUpper to get only the portion in this bracket
            int taxableAmount = Math.min(income, upper) - previousUpper;

            // Add tax for this portion: taxableAmount * (percent / 100.0)
            // Divide by 100.0 to convert percentage to decimal (e.g., 50% -> 0.50)
            tax += taxableAmount * (percent / 100.0);

            // Move previousUpper to current upper for next bracket
            // This tracks where the previous bracket ended
            previousUpper = upper;
        }

        return tax;
    }
}

