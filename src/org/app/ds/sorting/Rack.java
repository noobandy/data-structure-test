package org.app.ds.sorting;

import java.util.Arrays;

/**
 * 
 * @className:org.app.ds.sorting.Rack.java
 * @description:TODO
 * @author anandm
 * @date Sep 1, 2015 3:45:56 PM
 */
public class Rack {

    private int[] draws;

    /**
     * 
     */
    public Rack() {
        super();
        draws = new int[0];

    }

    public void add(int draw) {
        int[] second = new int[1];
        second[0] = draw;
        this.draws = merge(draws, second);

        // this.draws = insert(draws, draw);
    }

    /*
     * private int[] insert(int[] draws, int draw) { int[] inserted = new
     * int[draws.length + 1]; for (int i = 0; i < draws.length; i++) {
     * inserted[i] = draws[i]; }
     * 
     * int j = inserted.length - 1; inserted[j] = draw;
     * 
     * if (inserted.length >= 2) { int key = inserted[j]; int keyIndex = j; j =
     * j - 1;
     * 
     * while (key < inserted[j] && j > 0) { // shift right inserted[j + 1] =
     * inserted[j]; j--; } if (keyIndex != j) { inserted[j] = draw; }
     * 
     * }
     * 
     * return inserted; }
     */

    private int[] merge(int[] first, int[] second) {
        int[] merged = new int[first.length + second.length];
        int i = 0, j = 0, k = 0;

        for (; i < first.length && j < second.length;) {
            if (first[i] < second[j]) {
                merged[k++] = first[i++];
            }
            else {
                merged[k++] = second[j++];
            }
        }

        if (i < first.length) {
            for (; i < first.length;) {
                merged[k++] = first[i++];
            }
        }

        if (j < second.length) {
            for (; j < second.length;) {
                merged[k++] = second[j++];
            }
        }

        return merged;
    }

    public int[] draws() {
        return draws;
    }

    public static void main(String[] args) {
        Rack rack = new Rack();

        System.out.println(Arrays.toString(rack.draws));

        rack.add(20);

        System.out.println(Arrays.toString(rack.draws));

        rack.add(10);

        System.out.println(Arrays.toString(rack.draws));

        rack.add(30);

        System.out.println(Arrays.toString(rack.draws));

    }
}
