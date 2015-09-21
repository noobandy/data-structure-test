import java.util.Scanner;

/**
 * 
 * @author anandm
 * @date Sep 19, 2015 12:06:36 PM
 */

public class MSTEP1 {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        for (int i = 0; i < T; i++) {
            int N = scanner.nextInt();
            String[] matrix = new String[N * N];
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    int number = scanner.nextInt();
                    matrix[number - 1] = new StringBuilder().append(j)
                            .append(",").append(k).toString();
                }
            }

            int steps = steps(matrix);

            System.out.println(steps);
        }

    }

    private int steps(String[] matrix) {
        int steps = 0;

        String[] startCoordinates = matrix[0].split(",");

        for (int i = 1; i < matrix.length; i++) {
            String[] endCoordinates = matrix[i].split(",");

            steps = steps
                    + Math.abs(Integer.valueOf(startCoordinates[0])
                            - Integer.valueOf(endCoordinates[0]))
                    + Math.abs(Integer.valueOf(startCoordinates[1])
                            - Integer.valueOf(endCoordinates[1]));

            startCoordinates = endCoordinates;
        }

        return steps;
    }

    public static void main(String[] args) {
        MSTEP1 main = new MSTEP1();
        main.run();
    }
}
