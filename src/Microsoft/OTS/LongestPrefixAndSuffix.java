package Microsoft.OTS;

public class LongestPrefixAndSuffix {
    public static int solution(String S) {
        // Step 1: Create the partial match table ("pi table")
        if (S.length() == 0) return 0;
        int N = S.length();
        int[] pi = new int[N];
        int j = 0;

        // Build the table using the KMP preprocessing algorithm
        for (int i = 1; i < N; i++) {
            while (j > 0 && S.charAt(i) != S.charAt(j)) {
                j = pi[j - 1];  // Use the pi table to skip characters
            }
            if (S.charAt(i) == S.charAt(j)) {
                pi[i] = ++j;
            } else {
                pi[i] = j;
            }
        }

        // Step 2: The last value in the pi table is the length of the longest
        // proper prefix which is also a suffix
        int longestProperPrefixSuffix = pi[N - 1];

        // Ensure it's proper; must not be equal to the length of the string
        if (longestProperPrefixSuffix == N) {
            return 0; // No proper prefix which is also a suffix if it matches the entire string length
        }

        return longestProperPrefixSuffix;
    }

    public static void main(String[] args) {
        System.out.println(solution("abbabba"));
        System.out.println(solution("codility"));
    }
}
