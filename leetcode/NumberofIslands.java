package leetcode;

/**
 * @BelongsProject: POJ
 * @BelongsPackage: leetcode
 * @Author: csn
 * @Description: 200.Number of Islands ; Solution : dfs or 并查集
 */
public class NumberofIslands {
    /*
     * @Description: dfs
     * @Param: [grid]
     * @return int
     **/
    public int numIslands1(char[][] grid) {
        int cnt = 0;
        int r = grid.length;
        if(grid.length == 0) return 0;
        int c = grid[0].length;

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(grid[i][j] == '1') {
                    dfs(grid,i,j);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    private void dfs(char[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;
        if(grid[i][j] == '0') return;
        grid[i][j] = '0';
        dfs(grid, i-1,j);
        dfs(grid, i+1,j);
        dfs(grid, i,j-1);
        dfs(grid, i,j+1);
    }


    /*
     * @Description: 并查集
     * @Param: [grid]
     * @return int
     **/
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int n = grid.length;
        int m = grid[0].length;
        int count = 0;
        for (char[] chars : grid) {
            for (int j = 0; j < m; j++) {
                if (chars[j] == '1')
                    count++;
            }
        }

        UF uf = new UF(n * m, count);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '0') continue;
                //合并
                if (i > 0 && grid[i - 1][j] == '1')     uf.union(i * m + j, (i - 1) * m + j);
                if (i < n - 1 && grid[i + 1][j] == '1') uf.union(i * m + j, (i + 1) * m + j);
                if (j > 0 && grid[i][j - 1] == '1')     uf.union(i * m + j, i * m + j - 1);
                if (j < m - 1 && grid[i][j + 1] == '1') uf.union(i * m + j, i * m + j + 1);
            }
        }
        return uf.count;
    }
    class UF {
        int count; // 统计集合的数目
        int[] parent;

        UF(int n, int count) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            this.count = count;
        }
        // find parent
        int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        void union(int a, int b) {
            int aR = find(a);
            int bR = find(b);
            if (aR == bR)
                return;
            count--; // 每次合并一个就减掉一个
            if(aR < bR){
                parent[bR] = aR;
            }else {
                parent[aR] = bR;
            }
        }
    }


}
