package com.libill.demos.test;

public class BubbleSort {

    public static void bubbleSort(Comparable[] a) {
        int n = a.length;
        if (n < 1) return;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n - i - 1; j++) {
                if (less(a[j + 1], a[j])) {
                    exch(a, j, j + 1);
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

class InsertSort {

    public static void insertSort(Comparable[] a) {
        int n = a.length;
        if (n < 1) return;
        // TODO:
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n - i - 1; j++) {
                if (less(a[j + 1], a[j])) {
                    exch(a, j, j + 1);
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
