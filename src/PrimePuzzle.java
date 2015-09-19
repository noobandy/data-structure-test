import java.util.Scanner;

/**
 * @author ashish
 * @date Sep 5, 2015 4:42:50 PM
 */

public class PrimePuzzle {

    public void run() {
        Scanner scanner = new Scanner(System.in);

        int noOfTestCases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < noOfTestCases; i++) {
            int input = Integer.parseInt(scanner.nextLine());

            String winner = getWinner(input);

            System.out.println(winner);

        }
    }

    private String getWinner(int input) {
        // optimal move is 1
        if (input % 2 == 0) {
            return "ALICE";
        }
        else {
            return "BOB";
        }
    }

    public static void main(String[] args) {

        PrimePuzzle main = new PrimePuzzle();
        main.run();

    }
}
