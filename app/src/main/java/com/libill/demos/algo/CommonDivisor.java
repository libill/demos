package com.libill.demos.algo;

/**
 * Created by liqiongwei on 2019/04/19
 */
public class CommonDivisor {

    public static void main(String[] args) {

    }

    /**
     * 最大公约数
     *
     * @param p
     * @param q
     * @return
     */
    public int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }


    public static int rank(int key, int[] a) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (key < a[middle]) high = middle - 1;
            else if (key > a[middle]) low = middle + 1;
            else return middle;
        }
        return -1;
    }

}
