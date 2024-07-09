package Microsoft.OTS;
import java.util.*;
public class MaxStringBuilder {
    //dp
    private static Map<String, Integer> memo = new HashMap<>();

    public static int solution(String[] S, int K) {
        if (K == 0 || S.length == 0) return 0;

        // Create a unique key for memoization
        String key = Arrays.toString(S) + "," + K;
        if (memo.containsKey(key)) return memo.get(key);

        int maxCount = 0;
        Set<Character> uniqueChars = new HashSet<>();
        for (String s : S) {
            for (char c : s.toCharArray()) {
                uniqueChars.add(c);
            }
        }

        int excludeChar = (K > 0) ? solution(S, K - 1) : 0; // Recalculate with reduced K, if possible.

        for (char c : uniqueChars) {
            List<String> remaining = new ArrayList<>();
            int emptyCount = 0;

            for (String s : S) {
                String filtered = s.replace(String.valueOf(c), "");
                if (filtered.isEmpty()) {
                    emptyCount++;  // This string can be completely built by this character
                } else {
                    remaining.add(filtered);
                }
            }

            int includeChar = emptyCount + solution(remaining.toArray(new String[0]), K - 1);
            maxCount = Math.max(maxCount, Math.max(includeChar, excludeChar));
        }

        memo.put(key, maxCount);
        return maxCount;
    }
    //dfs
    public static int solution1(String[] S, int K) {
        Set<Character> uniqueChars = new HashSet<>();
        for (String s : S) {
            for (char c : s.toCharArray()) {
                uniqueChars.add(c);
            }
        }

        List<Character> chars = new ArrayList<>(uniqueChars);
        int maxCount = 0;

        // Generate all combinations of characters up to size k
        maxCount = getMaxCountForCombination(chars, K, S);

        return maxCount;
    }

    private static int getMaxCountForCombination(List<Character> chars, int len, String[] S) {
        int maxCount = 0;
        List<List<Character>> combinations = new ArrayList<>();
        generateCombinations(chars, len, 0, new ArrayList<>(), combinations);

        // Check each combination to see how many strings can be built
        for (List<Character> combination : combinations) {
            Set<Character> currentSet = new HashSet<>(combination);
            int count = 0;
            for (String s : S) {
                if (canBuildString(s, currentSet)) {
                    count++;
                }
            }
            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }

    private static void generateCombinations(List<Character> chars, int len, int start, List<Character> current, List<List<Character>> combinations) {
        if (current.size() == len) {
            combinations.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < chars.size(); i++) {
            current.add(chars.get(i));
            generateCombinations(chars, len, i + 1, current, combinations);
            current.remove(current.size() - 1);
        }
    }

    private static boolean canBuildString(String s, Set<Character> availableChars) {
        for (char c : s.toCharArray()) {
            if (!availableChars.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] S = {"abc", "abb", "cb", "a", "bbb"};
        int K = 2;
        System.out.println("Maximum number of strings that can be built: " + solution(S, K));
        // Example 2
        String[] S2 = {"adf", "jjbh", "jcgj", "eijj", "adf"};
        int K2 = 3;
        System.out.println("Example 2: Maximum number of strings that can be built: " + solution(S2, K2));

        // Example 3
        String[] S3 = {"abcd", "efgh"};
        int K3 = 3;
        System.out.println("Example 3: Maximum number of strings that can be built: " + solution(S3, K3));

        // Example 4
        String[] S4 = {"bc", "edf", "fde", "dge", "abcd"};
        int K4 = 4;
        System.out.println("Example 4: Maximum number of strings that can be built: " + solution(S4, K4));

    }
}
