/**
 * LeetCode 812: Largest Triangle Area
 * 
 * Given points on X-Y plane, find largest triangle area.
 * Uses cross product formula: Area = |v1 × v2| / 2
 * Try all combinations of 3 points.
 */

class Solution {

    public double largestTriangleArea(int[][] points) {
        double maxArea = 0;

        // Check all combinations of three points
        for (int[] point1 : points) {
            int x1 = point1[0];
            int y1 = point1[1];

            for (int[] point2 : points) {
                int x2 = point2[0];
                int y2 = point2[1];

                for (int[] point3 : points) {
                    int x3 = point3[0];
                    int y3 = point3[1];

                    // Vectors from point1 to point2 and point1 to point3
                    int vectorx1 = x2 - x1;
                    int vectory1 = y2 - y1;
                    int vectorx2 = x3 - x1;
                    int vectory2 = y3 - y1;

                    // Cross product formula: Area = |v1 × v2| / 2
                    double currentArea = Math.abs(vectorx1 * vectory2 - vectorx2 * vectory1) / 2.0;
                    maxArea = Math.max(maxArea, currentArea);
                }
            }
        }

        return maxArea;
    }
}

