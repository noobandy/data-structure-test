import java.util.Scanner;

/**
 * @author ashish
 * @date Sep 7, 2015 12:23:41 PM
 */

public class PrimePalindrome {

    public void run() {
        Scanner scanner = new Scanner(System.in);

        int input = Integer.parseInt(scanner.nextLine());

        int firstPrimePalindromeGreaterThanInput = firstPrimePalindromeGreaterThan(input);

        System.out.println(firstPrimePalindromeGreaterThanInput);
    }

    private int firstPrimePalindromeGreaterThan(int input) {

        // if input is prime palindrome return
        if (isPrime(input) && isPalindrome(input)) {
            return input;
        }

        int start = input % 2 == 0 ? input + 1 : input + 2;

        while (!(isPrime(start) && isPalindrome(start))) {
            start = start + 2;
        }

        return start;
    }

    private boolean isPalindrome(int start) {
        String string = Integer.toString(start);
        for (int i = 0, j = string.length() - 1; i < j; i++, j--) {
            if (string.charAt(i) != string.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    private boolean isPrime(int start) {
        int sqrt = (int) Math.sqrt(start);
        // start will never be even
        for (int i = 3; i <= sqrt; i = i + 2) {
            if (start % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PrimePalindrome main = new PrimePalindrome();

        main.run();
    }
}
