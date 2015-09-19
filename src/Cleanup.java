import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author anandm
 * @date Sep 8, 2015 10:42:08 AM
 */

public class Cleanup {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        byte noOfInputes = Byte.parseByte(scanner.nextLine());

        for (byte i = 0; i < noOfInputes; i++) {
            String firstLine = scanner.nextLine();

            String[] firstLineTokens = firstLine.split("\\s+");

            short totalJobs = Short.parseShort(firstLineTokens[0]);

            short completedJobCount = Short.parseShort(firstLineTokens[1]);

            short[] completedJobs = new short[completedJobCount];

            String secondLine = scanner.nextLine();

            String[] secondLineTokens = secondLine.split("\\s+");

            for (short j = 0; j < completedJobCount; j++) {
                completedJobs[j] = Short.parseShort(secondLineTokens[j]);
            }

            solve(totalJobs, completedJobs);
        }
    }

    private void solve(short totalJobs, short[] completedJobs) {
        boolean chefsTurn = true;

        Arrays.sort(completedJobs);

        StringBuilder chefsJobs = new StringBuilder();
        StringBuilder assistantsJobs = new StringBuilder();

        for (short i = 1; i <= totalJobs; i++) {
            if (Arrays.binarySearch(completedJobs, i) < 0) {
                if (chefsTurn) {

                    chefsJobs.append(i).append(" ");
                    chefsTurn = false;
                }
                else {
                    assistantsJobs.append(i).append(" ");
                    chefsTurn = true;
                }
            }
        }

        System.out.println(chefsJobs.toString().trim());
        System.out.println(assistantsJobs.toString().trim());
    }

    public static void main(String[] args) {
        Cleanup main = new Cleanup();
        main.run();
    }
}
