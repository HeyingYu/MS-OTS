package Microsoft.OTS;

import java.util.*;

public class ClientWaitingTime {
    /**
     * There are N clients who have ordered N handmade items. The K-th client ordered exactly one item that takes T[K] hours to make. There is only one employee who makes items for clients, and he/she works in the following manner:
     * spend 1 hour making the first item
     * if the item is finished, deliver it
     * if the item is NOT finished, put it after ALL REMAINING ITEMS for futher work
     * employee then works on next item
     * What is the total time that clients need to wait for all ordered items?
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(waitingTime(new int[]{3, 1, 2})); // 13
        System.out.println(waitingTime(new int[]{1, 2, 3, 4})); // 24
        System.out.println(waitingTime(new int[]{7, 7, 7})); // 60
        System.out.println(waitingTime(new int[]{10000})); // 10000
    }

    public static long waitingTime(int[] T) {
        if (T.length == 1) return T[0];
        long result = 0L, increasedArea = 0L;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> ((a[1] == b[1]) ? a[0] - b[0] : a[1] - b[1]));
        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = 0; i < T.length; i++) {
            pq.offer(new int[]{i, T[i]});
        }

        int lastPolledHeight = 0, rightGap = 0, leftGap = 0;
        long rectangleSizeInTheMiddle = 0L;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            leftGap = cur[0] - ts.headSet(cur[0], false).size() + 1;
            rectangleSizeInTheMiddle = (cur[1] - lastPolledHeight - 1) * (T.length - ts.size());
            increasedArea += (leftGap + rightGap + rectangleSizeInTheMiddle);
            result += increasedArea;
            lastPolledHeight = cur[1];
            rightGap = T.length - 1 - cur[0] - ts.tailSet(cur[0], false).size();
            ts.add(cur[0]);
        }
        return (int) (result % 1000000000L);
    }
}
