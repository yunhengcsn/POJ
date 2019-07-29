package typical.dp;

/**
 * Description:
 * 53. Maximum Subarray
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * Example:
 *
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 * @author csn
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        //pre用来保存之前的最大值
        int pre = 0;
        for (int curr : nums) {
            //更新pre
            if (pre >= 0) pre += curr;
            else pre = curr;
            maxSum = Math.max(pre, maxSum);
        }
        return maxSum;
    }
}
