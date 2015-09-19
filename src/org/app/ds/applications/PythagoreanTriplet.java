package org.app.ds.applications;

public class PythagoreanTriplet {

    public static void main(String[] args) {

        a: for (int a = 1; a < 1000; a++) {
            for (int b = 1; b < 1000; b++) {
                for (int c = 1; c < 1000; c++) {
                    if ((a + b + c) == 1000) {
                        if (((a * a) + (b * b)) == (c * c)) {
                            System.out.println("a : " + a + ", b : " + b
                                    + ", c : " + c + ", abc : " + a * b * c);
                            break a;
                        }
                    }
                }
            }
        }
    }

}
