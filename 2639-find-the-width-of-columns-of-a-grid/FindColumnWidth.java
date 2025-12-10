class Solution {

    public int[] findColumnWidth(int[][] grid) {

        int[] arr = new int[grid[0].length];

        for (int i = 0; i < grid[0].length; i++) {

            int maxValue = Integer.MIN_VALUE;

            for (int j = 0; j < grid.length; j++) {

                int num = grid[j][i];

                int length = String.valueOf(num).length();

                maxValue = Math.max(maxValue, length);

            }

            arr[i] = maxValue;

        }

        return arr;

    }

}

