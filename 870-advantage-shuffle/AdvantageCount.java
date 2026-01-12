/*
 * 870. Advantage Shuffle
 * 
 * Problem:
 * Given two arrays nums1 and nums2 of equal length, return a permutation of nums1
 * that maximizes the "advantage" of nums1 over nums2.
 * The advantage is the number of indices i where nums1[i] > nums2[i].
 * 
 * Approach: Greedy (Similar to "Tian Ji's Horse Racing" strategy)
 * 
 * Key Insight:
 * - Sort nums1 (ascending) to have options from smallest to largest
 * - Sort nums2 with original indices to know where to place results
 * - For each num in sorted nums1:
 *   - If it can beat the smallest unbeaten nums2 -> use it (maximize advantage)
 *   - Otherwise -> sacrifice it against the largest nums2 (minimize waste)
 * 
 * Example 1: nums1 = [2,7,11,15], nums2 = [1,10,4,11]
 * 
 *   Step 1: Sort nums1 = [2, 7, 11, 15]
 *   
 *   Step 2: Sort nums2 with indices:
 *           [(1,idx0), (4,idx2), (10,idx1), (11,idx3)]
 *           left=0 (smallest=1), right=3 (largest=11)
 *   
 *   Step 3: Process each num in sorted nums1:
 *   | num | smallest | Can beat? | Action                      | Result        |
 *   |-----|----------|-----------|-----------------------------| --------------|
 *   |  2  |    1     |    Yes    | result[idx0]=2, left++      | [2,_,_,_]     |
 *   |  7  |    4     |    Yes    | result[idx2]=7, left++      | [2,_,7,_]     |
 *   | 11  |   10     |    Yes    | result[idx1]=11, left++     | [2,11,7,_]    |
 *   | 15  |   11     |    Yes    | result[idx3]=15, left++     | [2,11,7,15]   |
 *   
 *   Output: [2,11,7,15] -> Advantage = 4 (all positions win)
 * 
 * Example 2: nums1 = [12,24,8,32], nums2 = [13,25,32,11]
 * 
 *   Step 1: Sort nums1 = [8, 12, 24, 32]
 *   
 *   Step 2: Sort nums2 with indices:
 *           [(11,idx3), (13,idx0), (25,idx1), (32,idx2)]
 *           left=0 (smallest=11), right=3 (largest=32)
 *   
 *   Step 3: Process each num:
 *   | num | smallest | largest | Can beat smallest? | Action                   |
 *   |-----|----------|---------|--------------------| -------------------------|
 *   |  8  |   11     |   32    |        No          | Sacrifice: result[idx2]=8, right-- |
 *   | 12  |   11     |   25    |        Yes         | Beat: result[idx3]=12, left++      |
 *   | 24  |   13     |   25    |        Yes         | Beat: result[idx0]=24, left++      |
 *   | 32  |   25     |   25    |        Yes         | Beat: result[idx1]=32, left++      |
 *   
 *   Result array by index: [24, 32, 8, 12]
 *   Compare: nums1'=[24,32,8,12] vs nums2=[13,25,32,11]
 *   Wins at: idx0 (24>13), idx1 (32>25), idx3 (12>11) -> Advantage = 3
 * 
 * Why This Works (Tian Ji's Horse Racing):
 * - If your horse can beat their weakest -> do it (secure a win)
 * - If your horse can't beat their weakest -> sacrifice it against their strongest
 *   (don't waste a potential winner on an unwinnable match)
 * 
 * Time Complexity: O(n log n) - sorting both arrays
 * Space Complexity: O(n) - for nums2WithIndex and result arrays
 */
class Solution {

    public int[] advantageCount(int[] nums1, int[] nums2) {

        int n = nums1.length;
        Arrays.sort(nums1);

        int[][] nums2WithIndex = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums2WithIndex[i][0] = nums2[i];
            nums2WithIndex[i][1] = i;
        }

        Arrays.sort(nums2WithIndex, Comparator.comparingInt(a -> a[0]));

        int left = 0, right = n - 1;
        int[] result = new int[n];

        for (int num : nums1) {
            if (num > nums2WithIndex[left][0]) {
                // Beat the smallest nums2
                result[nums2WithIndex[left][1]] = num;
                left++;
            } else {
                // Sacrifice to the largest nums2
                result[nums2WithIndex[right][1]] = num;
                right--;
            }
        }

        return result;
    }
}

