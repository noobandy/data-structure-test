import java.util.Scanner;

/**
 * @author ashish
 * @date Sep 7, 2015 11:36:14 AM
 */
public class CeilABPuzzle {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        /*
         * int noOfTestCases = Integer.parseInt(scanner.nextLine());
         * 
         * for (int i = 0; i < noOfTestCases; i++) {
         */

        String inputLine = scanner.nextLine();

        String[] inputLineTokens = inputLine.split("\\s+");

        int a = Integer.parseInt(inputLineTokens[0]);
        int b = Integer.parseInt(inputLineTokens[1]);

        int answer = solve(a, b);

        System.out.println(answer);
        /* } */

    }

    private int solve(int a, int b) {
        int answer = a - b;
        answer = changeUnitDigit(answer);
        return answer;
    }

    private int changeUnitDigit(int answer) {
        int unitDigit = answer % 10;
        if (unitDigit == 9) {
            unitDigit = unitDigit - 1;
        }
        else {
            unitDigit = unitDigit + 1;
        }

        answer = (answer / 10) * 10 + unitDigit;
        return answer;
    }

    public static void main(String[] args) {
        CeilABPuzzle main = new CeilABPuzzle();
        main.run();
    }
}
