import java.util.Arrays;
import java.util.Scanner;

/**
 * @author ashish
 * @date Sep 7, 2015 10:37:52 AM
 */

public class CoolingPie {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int noOfTestCases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < noOfTestCases; i++) {
            int noOfPies = Integer.parseInt(scanner.nextLine());
            int[] weightsOfPies = new int[noOfPies];
            int[] weightLimitsOfCoolingRacks = new int[noOfPies];
            String secondLine = scanner.nextLine();
            String thirdLine = scanner.nextLine();

            String[] secondLineTokens = secondLine.split("\\s+");
            String[] thirdLineTokens = thirdLine.split("\\s+");

            for (int j = 0; j < noOfPies; j++) {
                weightsOfPies[j] = Integer.parseInt(secondLineTokens[j].trim());
                weightLimitsOfCoolingRacks[j] = Integer
                        .parseInt(thirdLineTokens[j].trim());
            }

            int maxPiesThatCanBecooled = maxPiesThatCanBecooled(
                    noOfPies, weightsOfPies, weightLimitsOfCoolingRacks);

            System.out.println(maxPiesThatCanBecooled);
        }

    }

    private int maxPiesThatCanBecooled(int noOfPies,
                                       int[] weightsOfPies,
                                       int[] weightLimitsOfCoolingRacks) {

        // sort
        Arrays.sort(weightsOfPies);
        Arrays.sort(weightLimitsOfCoolingRacks);
        int maxPiesThatCanBeCooled = 0;

        for (int i = 0, j = 0; i < weightsOfPies.length
                && j < weightLimitsOfCoolingRacks.length;) {

            if (weightsOfPies[i] <= weightLimitsOfCoolingRacks[j]) {
                i++;
                j++;
                maxPiesThatCanBeCooled++;
            }
            else {
                j++;
            }

        }

        return maxPiesThatCanBeCooled;
    }

    public static void main(String[] args) {
        CoolingPie main = new CoolingPie();
        main.run();
    }
}
