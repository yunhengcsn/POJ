package others;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
* kimoyami 带着 kk 个妹子去酒店住宿。
* 酒店的房间分布表示为一张 n \times mn×m 的网格图，每个房间用 00 表示该房间可以入住，11 表示该房间不能入住。
* 正直的kimoyami开了 k+1k+1 间房间，但是 kimoyami 希望能尽量地靠近所有妹子。
* 具体地，他希望最小化他到所有妹子切比雪夫距离的最大值。
* 数据保证0的个数一定大于等于k+1。
* 题目地址：https://oj.seucpc.club/problem/161
* Solution: 二分+积分图
* */
public class ResidencePlan {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:\\Users\\Dell\\Downloads\\161\\5.in"));
        //Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int[][] grid = new int[n+1][m+1];

        /*for(int i = 0; i < n; i++) {
            String s = sc.next();
            for(int j = 0; j < m; j++) {
                grid[i][j] = s.charAt(j) - '0';
            }
        }
        int cnt = searchMin(grid,k);*/

        int[][] sum = new int[n+1][m+1];
        for(int i = 1; i <= n; i++) {
            String s = sc.next();
            for(int j = 1; j <= m; j++) {
                grid[i][j] = (s.charAt(j-1) - '0') ^ 1;// 0 to 1, 1 to 0
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + grid[i][j];// create intergral graph,sum[i][j] means zero numbers from[1,1] to [i,j]
            }
        }

        int lo = 0;
        int hi = Math.max(n,m) - 1;
        while(lo < hi) {
            int mid = (lo + hi)/2;
            boolean fg = false;
            for(int p = 1; p <= n; p++) {
                for(int q = 1; q <= m; q++) {
                    if(grid[p][q] == 0) continue;
                    if(zeros(p,q,mid,sum) >= k+1) {
                        fg = true;//find k+1 zeros when distance is mid
                        break;
                    }
                }
            }
            if(fg) hi = mid;
            else lo = mid + 1;//update lo when not found
        }
        System.out.println(lo);

    }
    //calculate zero numbers in [i-d,j-d] -- [i+d,j+d]
    private static int zeros(int i, int j, int d,int[][] sum) {
        int down = Math.min(i+d, sum.length-1);
        int up = Math.max(i-d, 1);
        int left = Math.max(j-d,1);
        int right = Math.min(j+d,sum[0].length-1);

        return sum[down][right] - sum[up-1][right] - sum[down][left-1] + sum[up-1][left-1];
    }

    //too slow
    private static int searchMin(int[][] grid, int k) {
        int ans = Integer.MAX_VALUE;
        int minn = (int)Math.ceil(Math.sqrt((k-4)/4));
        if(minn == 0) minn = 1;
        int row = grid.length;
        int col = grid[0].length;
        //given kimo's location, then find k-1 '0'
        for(int i = 0; i < row; i++ ) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) continue;
                int d = minn;
                int remain = k;
                while(d < ans) {
                    for(int m = j-d; m <= j+d; m++) {
                        if(m >= 0 && m < col) {
                            if(i-d >= 0 && grid[i-d][m] == 0) remain--;
                            if(i+d < row && grid[i+d][m] == 0) remain--;
                        }

                    }
                    if(remain <= 0) {
                        ans = d;
                        break;
                    }
                    for(int n = i-(d-1); n <= i+(d-1); n++) {
                        if(n >= 0 && n < row) {
                            if(j-d >= 0 && grid[n][j-d] == 0) remain--;
                            if(j+d < col && grid[n][j+d] == 0) remain--;
                        }

                    }
                    if(remain <= 0) {
                        ans = d;
                        break;
                    }
                    d++;
                }
                if(ans == minn) return ans;
            }
        }
        return ans;
    }
}