package com.libill.base.sort;

public class BubbleSort {

    public void bubbleSort(Comparable[] a) {
        if (a == null) {
            return;
        }
        int len = a.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (less(a[j + 1], a[j])) {
                    exch(a, j + 1, j);
                }
            }
        }
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
