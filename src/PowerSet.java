/**
 * 
 * @author anandm
 * @date Sep 8, 2015 10:00:30 AM
 */
public class PowerSet {

    public void printPowerSet(int[] set) {
        int pwerSetSize = (int) Math.pow(2, set.length);

        for (int i = 0; i < pwerSetSize; i++) {
            for (int j = 0; j < set.length; j++) {
                // if jth bit is set in i
                // then jth element will be member of this subset
                // shift 1L j bit right then do a bitwise and
                // then all bit will be zero except jth bit if jth
                // bit is 1 in i
                if ((i & (1L << j)) != 0) {
                    System.out.print(set[j]);
                    System.err.print(" ");
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        PowerSet powerSet = new PowerSet();

        powerSet.printPowerSet(new int[] { 1, 2, 3 });
    }
}
