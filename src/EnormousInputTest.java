import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author anandm
 * @date Sep 9, 2015 11:19:05 AM
 */
public class EnormousInputTest {

    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        String[] inputs = reader.readLine().split("\\s+");
        int n = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);
        int answer = 0;

        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(reader.readLine());
            if (input % k == 0) {
                answer++;
            }
        }
        reader.close();
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        EnormousInputTest main = new EnormousInputTest();
        main.run();
    }
}
