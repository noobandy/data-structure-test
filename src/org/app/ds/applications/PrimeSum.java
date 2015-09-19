package org.app.ds.applications;

import java.util.ArrayList;
import java.util.List;

public class PrimeSum {

    private List<Long> knownPrimes;

    /**
     * 
     */
    public PrimeSum() {
        super();
        knownPrimes = new ArrayList<Long>();
        knownPrimes.add(2L);
    }

    public long sum(long upperRange) {
        long sum = 2;
        for (long i = 3; i < upperRange; i = i + 2) {
            if (isPrime(i)) {
                sum = sum + i;
            }
        }

        return sum;
    }

    private boolean isPrime(long i) {
        long sqrt = (long) Math.floor(Math.sqrt(i));
        List<Long> knownPrimesLessThanOrEqualSqrt = getAllPrimesLessThanOrEqual(sqrt);
        for (long prime : knownPrimesLessThanOrEqualSqrt) {
            if (i % prime == 0) {
                return false;
            }
        }
        this.knownPrimes.add(i);

        return true;
    }

    private List<Long> getAllPrimesLessThanOrEqual(long sqrt) {
        List<Long> primes = new ArrayList<Long>();
        for (Long knownPrime : knownPrimes) {
            if (knownPrime <= sqrt) {
                primes.add(knownPrime);
            }
            else {
                break;
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        PrimeSum primeSum = new PrimeSum();
        long startTime = System.currentTimeMillis();
        System.out.println(primeSum.sum(2000000));
        long endTime = System.currentTimeMillis();

        System.out.println("time taken in seconds : " + (endTime - startTime)
                / 1000);
    }
}
