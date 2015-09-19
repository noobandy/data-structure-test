import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author anandm
 * @date Sep 19, 2015 12:06:36 PM
 */

public class MSTEP {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        for (int i = 0; i < T; i++) {
            int N = scanner.nextInt();
            int startI = 0;
            int startJ = 0;
            int[][] matrix = new int[N][N];
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    int number = scanner.nextInt();
                    matrix[j][k] = number;
                    if (number == 1) {
                        startI = j;
                        startJ = k;
                    }
                }
            }

            int steps = steps(startI, startJ, matrix);

            System.out.println(steps);
        }

    }

    private int steps(int startI, int startJ, int[][] matrix) {
        int steps = 0;

        for (int i = 2; i <= (matrix.length * matrix.length); i++) {

            int[] countMinStep = countMinStep(startI, startJ, i, matrix);

            steps = steps + Math.abs(startI - countMinStep[0])
                    + Math.abs(startJ - countMinStep[1]);

            startI = countMinStep[0];
            startJ = countMinStep[1];
        }

        return steps;
    }

    private int[] countMinStep(int startI, int startJ, int i, int[][] matrix) {

        int foundStartI = 0;
        int foundStartJ = 0;

        Queue<Integer[]> queue = new LinkedList<Integer[]>();

        List<Integer[]> adjecentsToStart = adjecents(startI, startJ, matrix);

        for (Integer[] adjecent : adjecentsToStart) {

            queue.add(adjecent);

        }

        ArrayList<Integer> visited = new ArrayList<Integer>();

        while (!queue.isEmpty()) {
            Integer[] element = queue.remove();

            visited.add(matrix[element[0]][element[1]]);

            if (matrix[element[0]][element[1]] == i) {
                foundStartI = element[0];
                foundStartJ = element[1];
                break;
            }
            else {
                List<Integer[]> adjecents = adjecents(
                        element[0], element[1], matrix);

                for (Integer[] adjecent : adjecents) {
                    if (!visited.contains(matrix[adjecent[0]][adjecent[1]])) {
                        queue.add(adjecent);
                    }

                }
            }
        }

        return new int[] { foundStartI, foundStartJ };
    }

    private List<Integer[]> adjecents(int startI, int startJ, int[][] matrix) {

        List<Integer[]> adjecents = new ArrayList<Integer[]>();
        if (startI > 0) {
            adjecents.add(new Integer[] { startI - 1, startJ });
        }

        if (startJ > 0) {
            adjecents.add(new Integer[] { startI, startJ - 1 });
        }

        if (startI < (matrix.length - 1)) {
            adjecents.add(new Integer[] { startI + 1, startJ });
        }

        if (startJ < (matrix.length - 1)) {
            adjecents.add(new Integer[] { startI, startJ + 1 });
        }
        return adjecents;
    }

    public static void main(String[] args) {
        MSTEP main = new MSTEP();
        main.run();
    }
}
