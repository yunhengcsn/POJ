package leetcode;

import java.util.Arrays;
/*
Given an integer array, return the k-th smallest distance among all the pairs.
The distance of a pair (A, B) is defined as the absolute difference between A and B.
Example 1:
Input:
nums = [1,3,1]
k = 1
Output: 0
Explanation:
Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
* */
public class FindKthSmallestPairDistance {
    //1.性能差，桶排序
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int[] arr = new int[nums[nums.length-1] + 1];

        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j<nums.length;j++){
                arr[Math.abs(nums[i] - nums[j])]++;
            }
        }
        //arr[i]存放差为i的整数对有多少个
        int count = 0;
        for(int i=0; i<arr.length; i++){
            count += arr[i];
            if(count >= k) {
                return i;
            }
        }
        return 0;
    }

    // 2.二分，Returns number of pairs with absolute difference less than or equal to mid.
    public int smallestDistancePairII(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, low = 0, hi = nums[n-1] - nums[0];//计算最大差值和最小差值
        while (low < hi) {
            int cnt = 0, j = 0, mid = (low + hi)/2;
            //差值小于等于mid的整数对数量
            for (int i = 0; i < n; ++i) {
                while (j < n && nums[j] - nums[i] <= mid) ++j;
                cnt += j - i-1;
            }
            //更新
            if (cnt >= k)
                hi = mid;

            else low = mid + 1;
        }

        return low;
    }
}
