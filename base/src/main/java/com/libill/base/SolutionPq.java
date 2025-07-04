package com.libill.base;

import java.util.*;
import java.lang.*;

/*
 * numCities is the number of cities (N).
cityA is the first city.
cityB is the second city.
numMagic is the number of times the magician can perform the magic spell.
cityConn is a grid where each row represents the first city, second city, and the length of the bidirectional road between the two cities.
 */
public class SolutionPq
{
    public static int  shortRoute(int numCities, int cityA, int cityB, int numMagic, int[][] cityConn)
    {
        // Write your code here
        List<int[]>[] adj = new ArrayList[numCities];
        for (int i = 0; i < numCities; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] road : cityConn) {
            int u = road[0], v = road[1], w = road[2];
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w});
        }

        // 距离数组
        int[][] dist = new int[numCities][numCities + 1];
        for (int i = 0; i < numCities; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[cityA][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, cityA, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentDist = current[0], u = current[1], kUsed = current[2];

            if (u == cityB) {
                return currentDist;
            }

            if (currentDist > dist[u][kUsed]) {
                continue;
            }

            for (int[] edge : adj[u]) {
                int v = edge[0], w = edge[1];

                if (dist[v][kUsed] > currentDist + w) {
                    dist[v][kUsed] = currentDist + w;
                    pq.offer(new int[]{dist[v][kUsed], v, kUsed});
                }

                if (kUsed < numMagic && dist[v][kUsed + 1] > currentDist) {
                    dist[v][kUsed + 1] = currentDist;
                    pq.offer(new int[]{dist[v][kUsed + 1], v, kUsed + 1});
                }
            }


        }


        return -1;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        // input for numCities
        int numCities = in.nextInt();

        // input for cityA
        int cityA = in.nextInt();

        // input for cityB
        int cityB = in.nextInt();

        // input for numMagic
        int numMagic = in.nextInt();

        // input for cityConn
        int cityConn_row = in.nextInt();
        int cityConn_col = in.nextInt();
        int cityConn[][] = new int[cityConn_row][cityConn_col];
        for(int idx = 0; idx < cityConn_row; idx++)
        {
            for(int jdx = 0; jdx < cityConn_col; jdx++)
            {
                cityConn[idx][jdx] = in.nextInt();
            }
        }

        int result = shortRoute(numCities, cityA, cityB, numMagic, cityConn);
        System.out.print(result);

    }
}

