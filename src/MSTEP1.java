import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 
 * @author anandm
 * @date Sep 19, 2015 12:06:36 PM
 */

public class MSTEP1 {

    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(reader.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(reader.readLine().trim());
            int[] matrix = new int[N * N];
            for (int j = 0; j < N; j++) {
                String[] inputs = reader.readLine().split("\\s");
                for (int k = 0; k < inputs.length; k++) {
                    int number = Integer.parseInt(inputs[k]);
                    matrix[number - 1] = (j * N ) + k;
                }
            }


            int steps = steps(N,matrix);

            System.out.println(steps);
        }

    }

    private int steps(int N ,int[] matrix) {
        int steps = 0;

        int startX = matrix[0] / N;
        int startY = matrix[0] % N;

        for (int i = 1; i < matrix.length; i++) {
            int destinationX = matrix[i] / N;
            int destinationY = matrix[i] % N;



            steps = steps
                    + Math.abs(startX - destinationX)
                    + Math.abs(startY - destinationY);

            startX = destinationX;
            startY = destinationY;
        }

        return steps;
    }

    public static void main(String[] args) throws IOException {
        MSTEP1 main = new MSTEP1();
        main.run();
    }
}
