package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @BelongsProject: POJ
 * @BelongsPackage: leetcode
 * @Author: csn
 * @Description: 56.Merge Intervals
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0]-b[0]);
        int id = 0;
        for(int[] interval : intervals) {
            if(id == 0 || intervals[id-1][1] < interval[0]) {
                intervals[id++] = interval;
            } else {
                intervals[id-1][1] = Math.max(interval[1],intervals[id-1][1]);
            }
        }
        return Arrays.copyOfRange(intervals,0,id);
    }
}
