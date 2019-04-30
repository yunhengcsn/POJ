package leetcode;

import java.util.Arrays;
/**
 * 503. Next Greater Element II
 * Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.
 *
 * Example 1:
 * Input: [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number;
 * The second 1's next greater number needs to search circularly, which is also 2.
 */

public class NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        Arrays.fill(ans,-1);
        for(int i = nums.length-1;i >= 0;i--) {
            //遍历
            for(int j = i+1;j < i+nums.length;j++) {
                int loc = j%nums.length ;
                //loc在前，还没计算ans[loc]，直接比较大小
                if(loc < i) {
                    if(nums[loc] > nums[i]) {
                        ans[i] = nums[loc];
                        break;
                    }
                }
                //loc在后
                else {
                    if(nums[i] < nums[loc]) {
                        ans[i] = nums[loc];
                        break;
                    }
                    else if(nums[i] == nums[loc]) {
                        ans[i] = ans[loc];
                        break;
                    }
                    else {
                        if(ans[loc] > nums[i]) {
                            ans[i] = ans[loc];
                            break;
                        }
                    }
                }

            }
        }
        return ans;
    }
}
