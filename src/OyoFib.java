import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author anandm
 * @date Sep 25, 2015 12:56:12 PM
 */
public class OyoFib {

    private static final Map<Long, Long> fibStore = new HashMap<Long, Long>();

    static {
        fibStore.put(0L, 0L);
        fibStore.put(1L, 1L);

    }

    private static final long fib(long number) {
        Long fib = fibStore.get(number);
        if (fib == null) {
            fib = fib(number - 1) + fib(number - 2);
            fibStore.put(number, fib);
        }

        return fib;
    }

    public static int calculate_sum(int input1, int input2) {
        long sum = 0;
        for (int i = input1; i <= input2; i++) {
            sum = sum + fib(i);
        }

        return (int) (sum % 100000007);

    }

    public static void main(String[] args) {
        System.out.println(OyoFib.calculate_sum(10, 19));
    }
}
