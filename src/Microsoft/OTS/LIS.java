package Microsoft.OTS;
import java.util.*;
public class LIS {//least increasing sequence
    public static int lengthOfLIS(int[] nums) { //O(N ^ 2)
        if (nums.length <= 1) return nums.length;

        Arrays.sort(nums); // Sort the array
        int n = nums.length;
        int longest = 1;

        // Map to hold lengths of arithmetic subsequences where map[i] corresponds to the subsequences ending at index i
        Map<Integer, Integer>[] dp = new HashMap[n];

        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long diff = (long) nums[i] - (long) nums[j]; // Use long to avoid integer overflow

                if (diff > Integer.MAX_VALUE || diff < Integer.MIN_VALUE) continue; // Skip if the difference is too large

                int diffInt = (int) diff;
                int lengthJ = dp[j].getOrDefault(diffInt, 1);
                int lengthI = dp[i].getOrDefault(diffInt, 1);

                // Update the length of the sequence ending at i
                dp[i].put(diffInt, Math.max(lengthI, lengthJ + 1));
                longest = Math.max(longest, dp[i].get(diffInt));
            }
        }

        return longest;
    }
    public static void main(String[] args) {
        int[] nums = {0, 4, 3, 5, 1, 4, 12};
        System.out.println("the longest length possible: " + lengthOfLIS(nums)); //3
        // Example 2
        int[] nums1 = {8, -5, 1, 4, 0, 6, 0, 2, 4};
        System.out.println("the longest length possible: " + lengthOfLIS(nums1)); //5
        // Example 3
        int[] nums2 = {0, 0, 0};
        System.out.println("the longest length possible: " + lengthOfLIS(nums2)); //3
    }
}
