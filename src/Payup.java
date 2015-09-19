import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author anandm
 * @date Sep 8, 2015 11:47:21 AM
 */

public class Payup {

    public void run() throws NumberFormatException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        byte noOfTestCases = Byte.parseByte(reader.readLine());

        for (byte i = 0; i < noOfTestCases; i++) {
            String firstLine = reader.readLine();
            String[] firstLineTokens = firstLine.split("\\s+");
            byte noOfNotesInWallet = Byte.parseByte(firstLineTokens[0]);
            short amountAskedFor = Short.parseShort(firstLineTokens[1]);

            short[] notes = new short[noOfNotesInWallet];

            for (byte j = 0; j < noOfNotesInWallet; j++) {
                notes[j] = Short.parseShort(reader.readLine());
            }

            if (canPayup(notes, amountAskedFor)) {
                System.out.println("Yes");
            }
            else {
                System.out.println("No");
            }
        }
    }

    private boolean canPayup(short[] notes, short amountAskedFor) {
        int powerSetSize = (int) Math.pow(2, notes.length);

        for (int i = 0; i < powerSetSize; i++) {
            int subSetSum = 0;
            for (short j = 0; j < notes.length; j++) {
                if ((i & (1 << j)) != 0) {
                    subSetSum = subSetSum + notes[j];
                }
            }

            if (subSetSum == amountAskedFor) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws NumberFormatException,
            IOException {
        Payup main = new Payup();
        main.run();

    }
}
