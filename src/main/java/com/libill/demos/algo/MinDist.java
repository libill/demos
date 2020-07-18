
public class MinDist {

    private int minDist = Integer.MAX_VALUE;

    //调用方式：minDistBT(0,0,0,w,n)
    public void minDistBT(int i, int j, int dist, int[][] w, int n) {
        // 到达 n-1, n-1 这个位置，这里看着有点奇怪哈，你自己举个列子看下
        if (i == n && j == n) {
            if (dist < minDist) minDist = dist;
            reutrn;
        }

        if（i<n){ // 往下走，更新i=i+1,j=j
            minDistBT(i + 1, j, dist + w[i][j], w, n);
        }
        if (j < n) { // 往右走，更新i=i, j=j+1
            minDistBT(i, j + 1, dist + w[i][j], w, n);
        }
    }


}