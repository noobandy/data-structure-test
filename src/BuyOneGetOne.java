import java.util.Scanner;

/**
 * @author anandm
 * @date Sep 8, 2015 1:50:31 PM
 */

public class BuyOneGetOne {

    public void run() {
        Scanner scanner = new Scanner(System.in);

        int noOfTestCases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < noOfTestCases; i++) {
            String input = scanner.nextLine();

            int minAmountReqeuired = minAmountReqeuired(input);

            System.out.println(minAmountReqeuired);
        }

    }

    private int minAmountReqeuired(String input) {
        int[] itemCounts = new int[52];
        for (char c : input.toCharArray()) {
            int index = charToIndex(c);

            itemCounts[index] = itemCounts[index] + 1;
        }

        int minAmountReqeuired = 0;
        for (int count : itemCounts) {
            minAmountReqeuired = minAmountReqeuired + ((count / 2) * 1)
                    + ((count % 2) * 1);
        }
        return minAmountReqeuired;
    }

    private int charToIndex(char c) {
        if (c < 97) {
            return (c - 65) + 26;
        }
        else {
            return c - 97;
        }
    }

    public static void main(String[] args) {
        BuyOneGetOne main = new BuyOneGetOne();
        main.run();
    }
}
