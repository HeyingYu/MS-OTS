package Microsoft.OTS;
import java.util.*;

public class FixTableHoles {
    /**
     * https://leetcode.com/discuss/interview-question/4944166/Microsoft-Software-Engineer-I-OA/
     * 非常簡單 秒解 以每個洞當分界點 更新需要的長度 loop過array一遍即可得到答案
     * Binary search starting with low as always 1 and high = max hole - min hole value.
     * Mid value is a possible length of the board that could be the answer.
     * For every mid, verify if the number of board is satisfied.
     * @param A
     * @return
     */
    public static int solution(int[] A) {
        //One hole will need only 1
        if(A.length == 1)
            return 1;
        Arrays.sort(A);
        int lo = 1;
        int hi = A[A.length - 1] - A[0];
        int result = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (isPossible(A, mid)) {
                result = mid;
                hi = mid - 1;
                //board lengths
            } else {
                lo = mid + 1;
            }
        }

        return result;
    }
    private static boolean isPossible(int[] A, int boardLength) {
        int lastPosition = A[0];
        int boardsNeeded = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] - lastPosition > boardLength) {
                lastPosition = A[i];
                boardsNeeded++;
            }
        }
// Check if at most 2 boards are needed
        return boardsNeeded <= 2 && A[A.length - 1] - lastPosition <= boardLength;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{11, 20, 15}));  // Expected 4
        System.out.println(solution(new int[]{15, 20, 9, 11}));  // Expected 5
        System.out.println(solution(new int[]{0, 44, 32, 30, 42, 18, 34, 16, 35}));  // Expected 18
        System.out.println(solution(new int[]{9}));  // Expected 1
    }
}
