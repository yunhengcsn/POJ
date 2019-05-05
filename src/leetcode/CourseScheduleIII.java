package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class CourseScheduleIII {
    /*630. Course Schedule III
    * There are n different online courses numbered from 1 to n.
    * Each course has some duration(course length) t and closed on dth day.
    * A course should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.
    * Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.
    * Example:
    * Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
    * Output: 3
    * Explanation:
    * There're totally 4 courses, but you can take 3 courses at most:
    * First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
    * Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
    * Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
    * The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
    * */
    public int scheduleCourse(int[][] courses) {
        //Sort the courses by their deadlines (Greedy! We have to deal with courses with early deadlines first)
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        PriorityQueue< Integer > queue = new PriorityQueue < > ((a, b) -> b - a);
        int time = 0;
        for (int[] c: courses) {
            if (time + c[0] <= c[1]) {
                queue.offer(c[0]);
                time += c[0];
            } else if (!queue.isEmpty() && queue.peek() > c[0]) {
                //If time exceeds, drop the previous course which costs the most time. (That must be the best choice!)
                time += c[0] - queue.poll();
                queue.offer(c[0]);
            }
        }
        return queue.size();
    }
}
