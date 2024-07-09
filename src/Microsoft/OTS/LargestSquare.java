package Microsoft.OTS;

public class LargestSquare {
    public static int solution(int M, int N) {
        // Calculate the maximum possible area with the given tiles
        long maxArea = (long) M + 4L * N;

        // Find the largest possible side length of the square
        int maxSide = (int) Math.sqrt(maxArea);

        // Check the largest side length that can actually be formed with available tiles
        while (maxSide > 0) {
            long neededArea = (long) maxSide * maxSide;

            // Check if we have enough tiles to form a square of this side length
            if (neededArea <= maxArea) {
                // Check if we can form the square with the given distribution of 1x1 and 2x2 tiles
                long max2x2Coverage = (long) Math.min(N, (neededArea / 4)); // Maximum area 2x2 tiles can cover in this square
                long remainingArea = neededArea - 4 * max2x2Coverage;      // Remaining area to be covered by 1x1 tiles

                if (remainingArea <= M && remainingArea + 4 * max2x2Coverage == neededArea) {
                    return maxSide; // Found the largest possible square that can be formed
                }
            }

            // Decrease the side length and check again
            maxSide--;
        }

        return 0; // No square can be formed if we exit the loop
    }

    public static void main(String[] args) {
        System.out.println("Example 1 (M=8, N=0): " + solution(8, 0));
        System.out.println("Example 2 (M=4, N=3): " + solution(4, 3));
        System.out.println("Example 3 (M=0, N=18): " + solution(0, 18));
        System.out.println("Example 4 (M=13, N=3): " + solution(13, 3));
    }
}
