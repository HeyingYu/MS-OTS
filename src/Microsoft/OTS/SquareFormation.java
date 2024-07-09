package Microsoft.OTS;
import java.util.Arrays;

/**
 * Problem 2. Given an array of length pieces, make a square and return the max len we can have. We don't have to use all the pieces.
 * Ex: [3,3,3,1,2,1] outputs 3. The 4 sides of the square are: 3, 3, 3, (1 + 2). Because 1 + 2 = 3, we can combine those to make the last side of the squar‍‍‍‍‌‍‍‍‌‍‍‍‌‌‍‍‌‌‌e.
 * Ex: [1, 1, 1, 4, 3, 3, 3] returns 4 because (1+3) is a side, (1+3) is another side, (1+3) is another side, and 4 is the last side of the square. the maximum length is 4.
 */
public class SquareFormation {
    public static int maxSquareSide(int[] pieces) {
        int totalLength = 0;
        for (int piece : pieces) {
            totalLength += piece;
        }

        Arrays.sort(pieces); // Sort to optimize the combination process
        int maxSideLength = totalLength / 4; // The side of the square can't be longer than a quarter of the total length

        while (maxSideLength > 0) {
            if (canFormSquare(pieces, new int[4], maxSideLength, 0)) {
                return maxSideLength;
            }
            maxSideLength--; // Decrease and check the next possible length
        }
        return 0;
    }

    private static boolean canFormSquare(int[] pieces, int[] sides, int target, int index) {
        if (index == pieces.length) {
            // Check if all sides are exactly equal to the target
            return sides[0] == target && sides[1] == target && sides[2] == target && sides[3] == target;
        }

        for (int i = 0; i < 4; i++) {
            if (sides[i] + pieces[index] > target) continue; // Skip if adding this piece exceeds the target
            sides[i] += pieces[index];
            if (canFormSquare(pieces, sides, target, index + 1)) {
                return true;
            }
            sides[i] -= pieces[index]; // Backtrack
        }

        return false;
    }

    public static void main(String[] args) {
        int[] pieces1 = {3, 3, 3, 1, 2, 1}; //3
        int[] pieces2 = {1, 1, 1, 4, 3, 3, 3}; //4

        System.out.println("Maximum side length for the square (Example 1): " + maxSquareSide(pieces1));
        System.out.println("Maximum side length for the square (Example 2): " + maxSquareSide(pieces2));
    }
}
