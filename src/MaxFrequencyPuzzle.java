import java.util.Scanner;

/**
 * 
 * @author ashish
 * @date Sep 7, 2015 12:01:13 PM
 */

public class MaxFrequencyPuzzle {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int noOfTestCases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < noOfTestCases; i++) {
            int inputSize = Integer.parseInt(scanner.nextLine());
            String[] inputTokens = scanner.nextLine().split("\\s+");

            int[] inputs = new int[inputSize];

            for (int j = 0; j < inputSize; j++) {
                inputs[j] = Integer.parseInt(inputTokens[j]);
            }

            int[] result = solve(inputs);

            System.out.println(result[0] + " " + result[1]);
        }
    }

    private int[] solve(int[] inputs) {
        int[] frequencies = new int[10001];
        for (int input : inputs) {
            frequencies[input] = frequencies[input] + 1;
        }

        int max = 0;
        int maxCount = 0;

        for (int i = 0; i < 10001; i++) {
            if (frequencies[i] > maxCount) {
                max = i;
                maxCount = frequencies[i];
            }
        }
        return new int[] { max, maxCount };
    }

    public static void main(String[] args) {
        MaxFrequencyPuzzle main = new MaxFrequencyPuzzle();
        main.run();
    }
}
