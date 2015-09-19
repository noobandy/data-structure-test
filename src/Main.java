import java.util.Scanner;

/**
 * 
 * 
 * @author anandm
 * @date Sep 5, 2015 3:11:51 PM
 */
public class Main {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int noOfTestCases = Integer.parseInt(scanner.nextLine().trim());

        for (int i = 0; i < noOfTestCases; i++) {
            String input = scanner.nextLine();
            int noOfHoles = countHoles(input.trim());
            System.out.println(noOfHoles);
        }
    }

    private int countHoles(String input) {
        int noOfHoles = 0;
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
            case 'A':
                noOfHoles = noOfHoles + 1;
                break;
            case 'B':
                noOfHoles = noOfHoles + 2;
                break;
            case 'D':
                noOfHoles = noOfHoles + 1;
                break;
            case 'O':
                noOfHoles = noOfHoles + 1;
                break;
            case 'P':
                noOfHoles = noOfHoles + 1;
                break;
            case 'Q':
                noOfHoles = noOfHoles + 1;
                break;
            case 'R':
                noOfHoles = noOfHoles + 1;
                break;
            default:
                // ignore
            }
        }
        return noOfHoles;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();

    }

}
