package Microsoft.OTS;

/**
 * That, given the string moves, returns true if the robot's path will form a rectangle or false otherwise.
 * You need to consider change in direction in moves along with your updown and rightleft conditions.
 * Examples:
 * Given moves "^^^<<<<>>>>", the function should return true.
 * Given moves ">^<", the function should return true.
 * https://leetcode.com/discuss/interview-question/4463490/Microsoft-OTS-Dec-2023/
 */
public class robotRectangle { //???
    public boolean robotRectangle(String moves){
        int x = 0, y = 0;
        int rightTurns = 0;
        int n = moves.length();

        // First pass: Count right-angle turns
        for (int i = 0; i < n; i++) {
            char current = moves.charAt(i);
            char next = moves.charAt((i + 1) % n);
            if ((current == '>' || current == '<') && (next == '^' || next == 'v')) {
                rightTurns++;
            } else if ((current == '^' || current == 'v') && (next == '>' || next == '<')) {
                rightTurns++;
            }
        }

        // Second pass: Compute the final position
        for (int i = 0; i < n; i++) {
            char move = moves.charAt(i);
            switch (move) {
                case '>': x++; break;
                case '<': x--; break;
                case '^': y++; break;
                case 'v': y--; break;
            }
        }

        // Check final conditions: returns to origin and exactly four right-angle turns
        return x == 0 && y == 0 && (rightTurns == 4 || rightTurns == 3);
    }
    public static void main(String[] args) {
        robotRectangle test= new robotRectangle();
        System.out.println(test.robotRectangle("^^>>><<vvv^"));
        System.out.println(test.robotRectangle("^^<vvv^"));
    }
}
