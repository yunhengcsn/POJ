package poj;

import java.util.Scanner;

public class Poj1322 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            double ans ;
            int c = sc.nextInt();
            if(c == 0) return;
            int n = sc.nextInt();
            int m = sc.nextInt();
            if((m > c)||(m > n)||((n-m)%2 == 1)) ans = 0.0;
            else {
                if(n > 2000) n = 2000 - (n&1);
                ans = cal(c,n,m);
            }
            System.out.printf("%.3f\n",ans);
        }
    }
    private static double cal(int c,int n, int m) {
        double[][] dp = new double[2][c+1];
        int curr = 1;
        dp[0][0] = 1.0;
        dp[1][1] = 1.0;
        for(int i = 2;i <= n;i++) {
            dp[1-curr][0] = dp[curr][1]/c;
            dp[1-curr][c] = dp[curr][c-1]/c;
            for(int j = 1;j < c && j <= i;j++) {

                dp[1-curr][j] = dp[curr][j-1]*(c-j+1.0)/c + dp[curr][j+1]*(j+1.0)/c;
            }
            curr = 1-curr;
        }
        return dp[n%2][m];
    }
}
