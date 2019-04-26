package leetcode;
/*576. Out of Boundary Paths
* There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball,
*  you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right).
* However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary.
* The answer may be very large, return it after mod 109 + 7.
Example 1:

Input: m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6
* Solution:if we can reach some position in xx moves, we can reach all its adjacent positions in x+1 moves.
* */
public class OutofBoundaryPaths {
    public static void main(String[] args) {
        System.out.println(findPaths(2,2,2,0,0));
    }
    private static int findPaths(int m, int n, int N, int i, int j) {
        if (N <= 0) return 0;

        final int MOD = 1000000007;
        int[][] dp = new int[m][n];
        dp[i][j] = 1;
        int result = 0;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int step = 0; step < N; step++) {
            int[][] temp = new int[m][n];//使用上一次step得到的dp更新temp
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {//对于dp每一个点有四个方向
                    for (int[] d : dirs) {
                        int nr = r + d[0];
                        int nc = c + d[1];
                        if (nr < 0 || nr >= m || nc < 0 || nc >= n) {//出界则更新result
                            result = (result + dp[r][c]) % MOD;
                        }
                        else {
                            temp[nr][nc] = (temp[nr][nc] + dp[r][c]) % MOD;//否则更新temp
                        }
                    }
                }
            }
            dp = temp;//temp赋给dp
        }

        return result;
    }
}
