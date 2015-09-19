import java.util.Scanner;

/**
 * 
 * @author anandm
 * @date Sep 8, 2015 10:29:00 AM
 */

public class ElephantAndCandies {

    public void run() {
        Scanner scanner = new Scanner(System.in);

        int noOfTestCases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < noOfTestCases; i++) {
            String firstLine = scanner.nextLine();
            String secondLine = scanner.nextLine();
            String[] firstLineTokens = firstLine.split("\\s+");
            String[] secondLineTokens = secondLine.split("\\s+");

            // int noOfElephants = Integer.parseInt(firstLineTokens[0]);
            int noOfCandies = Integer.parseInt(firstLineTokens[1]);
            int totalCandiesRequired = 0;
            for (String token : secondLineTokens) {
                totalCandiesRequired = totalCandiesRequired
                        + Integer.parseInt(token);
            }

            if (totalCandiesRequired <= noOfCandies) {
                System.out.println("Yes");
            }
            else {
                System.out.println("No");
            }
        }
    }

    public static void main(String[] args) {
        ElephantAndCandies main = new ElephantAndCandies();
        main.run();
    }
}
