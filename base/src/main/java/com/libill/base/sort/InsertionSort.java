package com.libill.base.sort;

public class InsertionSort {

    public void insertionSort(Comparable[] a) {
        int len = a.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j-1])) {
                    exch(a, j, j-1);
                }
            }
        }
    }

    public boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
