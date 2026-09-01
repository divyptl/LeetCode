import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int minMoves(String[] C, int E) {
        int m = C.length, n = C[0].length(), d[] = {-1, 0, 1, 0, -1};
        int[][][] me = new int[m][n][1024];
        int lC = 0, sR = 0, sC = 0;
        int[][] lM = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (C[i].charAt(j) == 'L') lM[i][j] = 1 << (lC++);
                else if (C[i].charAt(j) == 'S') { sR = i; sC = j; }
                for (int k = 0; k < 1024; k++) me[i][j][k] = -1;
            }
        }
        int tm = (1 << lC) - 1;
        if (tm == 0) return 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sR, sC, E, 0, 0});
        me[sR][sC][0] = E;
        while (!q.isEmpty()) {
            int[] c = q.poll();
            int r = c[0], cl = c[1], e = c[2], mk = c[3], ds = c[4];
            if (e < me[r][cl][mk] || e == 0) continue;
            for (int i = 0; i < 4; i++) {
                int nr = r + d[i], nc = cl + d[i + 1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && C[nr].charAt(nc) != 'X') {
                    char ch = C[nr].charAt(nc);
                    int ne = e - 1, nmk = mk;
                    if (ch == 'L') nmk |= lM[nr][nc];
                    if (ch == 'R') ne = E;
                    if (nmk == tm) return ds + 1;
                    if (ne > me[nr][nc][nmk]) {
                        me[nr][nc][nmk] = ne;
                        q.add(new int[]{nr, nc, ne, nmk, ds + 1});
                    }
                }
            }
        }
        return -1;
    }
}