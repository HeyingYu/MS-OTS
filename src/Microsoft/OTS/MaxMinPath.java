package Microsoft.OTS;

public class MaxMinPath {
    public static long solution(int[] A, int[] B) {
        int n = A.length;
        if (n == 0) return 0;

        // Grid to store the maximum values encountered on the minimum path to each cell
        long[][] dp = new long[2][n];

        // Initialize the first cell with the value of A[0] since that's where we start
        dp[0][0] = A[0];
        dp[1][0] = B[0];

        // Fill the first row (only possible to come from the left)
        for (int i = 1; i < n; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], A[i]);
            dp[1][i] = Math.max(dp[1][i - 1], B[i]);
        }

        // Update the grid for all columns
        for (int i = 1; i < n; i++) {
            // Update dp for the top row (can only move right)
            dp[0][i] = Math.min(dp[0][i], Math.max(dp[0][i - 1], A[i]));

            // Update dp for the bottom row, considering moves from the top row or left in the same row
            dp[1][i] = Math.min(Math.max(dp[1][i - 1], B[i]), Math.max(dp[0][i], B[i]));
        }

        // The answer is the minimum of the last column of both rows
        return Math.min(dp[0][n - 1], dp[1][n - 1]);
    }

    public static void main(String[] args) {
        int[] A1 = {3, 4, 6};
        int[] B1 = {6, 5, 4};
        System.out.println("Example 1: " + solution(A1, B1));  // Output: 5

        int[] A2 = {1, 2, 1, 1, 1, 4};
        int[] B2 = {1, 1, 3, 1, 1, 1};
        System.out.println("Example 2: " + solution(A2, B2));  // Output: 2

        int[] A3 = {-5, -1, -3};
        int[] B3 = {-5, 5, -2};
        System.out.println("Example 3: " + solution(A3, B3));  // Output: -1
    }
}
