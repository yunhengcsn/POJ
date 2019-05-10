package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SlidingWindowMedian {
    /*480. Sliding Window Median
    * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
    * So the median is the mean of the two middle value.
    * Examples:  [2,3,4] , the median is 3; [2,3], the median is (2 + 3) / 2 = 2.5
    * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
    * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
    * Your job is to output the median array for each window in the original array.
    * For example,
    * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
            Window position                Median
            ---------------               -----
            [1  3  -1] -3  5  3  6  7       1
             1 [3  -1  -3] 5  3  6  7       -1
             1  3 [-1  -3  5] 3  6  7       -1
             1  3  -1 [-3  5  3] 6  7       3
             1  3  -1  -3 [5  3  6] 7       5
             1  3  -1  -3  5 [3  6  7]      6
        Therefore, return the median sliding window as [1,-1,-1,3,5,6].
        * Solution:利用二分查找降低复杂度
    * */
    public double[] medianSlidingWindow(int[] nums, int k) {
        if(nums.length==0 || k < 1)
            return new double[]{};
        List<Long> windows = new ArrayList<>();
        for(int i : Arrays.copyOfRange(nums, 0, k)){
            windows.add((long) i);
        }
        Collections.sort(windows);
        boolean isOdd = (k&1) == 1;
        double[] res = new double[nums.length + 1 - k];
        for(int i = 0; i < res.length; i++){
            maintainWindows(windows, nums, i, k);
            if(isOdd){
                res[i] = windows.get(k/2);
            } else {
                res[i] = (windows.get(k/2)+ windows.get(k/2-1))/2d;
            }
        }
        return res;
    }
    private void maintainWindows(List<Long> windows, int[] nums, int i, int k){
        if(i==0)
            return;
        //remove prev
        long oldElem = nums[i-1], newElem = nums[i + k - 1];
        windows.remove(Collections.binarySearch(windows, oldElem));
        int idx = Collections.binarySearch(windows, newElem);
        if(idx < 0 )
            idx = - idx - 1;
        windows.add(idx,newElem);
    }
}
