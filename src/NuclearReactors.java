import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author anandm
 * @date Sep 9, 2015 10:10:55 AM
 */

public class NuclearReactors {

    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        String[] inputs = reader.readLine().split("\\s+");
        int totalParticalsBombarded = Integer.parseInt(inputs[0]);
        int maxParticlesLimitPerReactor = Integer.parseInt(inputs[1]);
        int noOfReactors = Integer.parseInt(inputs[2]);

        reader.close();

        int[] reactors = new int[noOfReactors];

        reactors = simulateBomBarding(
                totalParticalsBombarded, maxParticlesLimitPerReactor, reactors);
        StringBuilder outputBuilder = new StringBuilder();
        for (int particles : reactors) {
            outputBuilder.append(particles);
            outputBuilder.append(" ");
        }

        System.out.println(outputBuilder.toString().trim());
    }

    private int[] simulateBomBarding(int totalParticalsBombarded,
                                     int maxParticlesLimitPerReactor,
                                     int[] reactors) {
        for (int i = 0; i < totalParticalsBombarded; i++) {
            reactors = bombard(maxParticlesLimitPerReactor, reactors);
        }
        return reactors;
    }

    private int[] bombard(int maxParticlesLimitPerReactor, int[] reactors) {

        for (int i = 0; i < reactors.length; i++) {

            if (reactors[i] < maxParticlesLimitPerReactor) {
                reactors[i] = reactors[i] + 1;
                break;
            }
            else {
                reactors[i] = 0;
            }
        }
        return reactors;
    }

    public static void main(String[] args) throws IOException {
        NuclearReactors main = new NuclearReactors();
        main.run();
    }
}
