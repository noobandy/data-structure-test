import java.util.Scanner;

/**
 * 
 * @author ashish
 * @date Sep 5, 2015 4:19:26 PM
 */
public class DoubleString {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int noOfTestCases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < noOfTestCases; i++) {
            int input = Integer.parseInt(scanner.nextLine());
            int maxDoubleStringSize = maxDoubleStringSize(input);

            System.out.println(maxDoubleStringSize);
        }
    }

    private int maxDoubleStringSize(int input) {

        return isEven(input) ? input : input - 1;
    }

    private boolean isEven(int input) {

        return input % 2 == 0;
    }

    public static void main(String[] args) {

        DoubleString doubleString = new DoubleString();

        doubleString.run();
    }

}
