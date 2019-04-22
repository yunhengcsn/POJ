package leetcode;
/*
* 983. Minimum Cost For Tickets
* In a country popular for train travel, you have planned some train travelling one year in advance.
* The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.
* */
public class MinimumCostForTickets {
    public int mincostTickets(int[] days, int[] costs) {
        int[] tic = {1,7,30};
        int[] mem = new int[days.length];
        return dp(0,days,costs,tic,mem);
    }
    private int dp(int i,int[] days,int[] costs,int[] tic,int[] mem) {
        if(i >= days.length) return 0;
        if(mem[i] != 0) return mem[i];
        int min = Integer.MAX_VALUE;
        for(int k = 0;k <3 ;k++) {
            int j;
            for(j = i;j < days.length;j++) {
                if(days[j] >= days[i]+tic[k]) break;
            }
            min = Math.min(min,dp(j,days,costs,tic,mem)+costs[k]);
        }
        mem[i] = min;
        return mem[i];
    }
}
