package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: POJ
 * @BelongsPackage: leetcode
 * @Author: csn
 * @Description: 57.Insert Interval 【贪心】
 */
public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int i = 0;
        int num = intervals.length;

        // add intervals smaller than newInterval's start
        while(i < num && intervals[i][1] < newInterval[0]) list.add(intervals[i++]);

        //update newInterval
        while(i < num && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        //add
        list.add(newInterval);

        //add intervals larger than newInterval's end
        while(i < num) list.add(intervals[i++]);

        //turn arraylist into two-dimensional array
        return list.toArray(new int[list.size()][]);
    }
}
