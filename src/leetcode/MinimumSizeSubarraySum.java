package leetcode;

/**
 * @BelongsProject: POJ
 * @BelongsPackage: leetcode
 * @Author: csn
 * @Description: 209.Minimum Size Subarray Sum,solution:滑动窗口
 */
public class MinimumSizeSubarraySum {

    //O(N2)
    public int minSubArrayLen1(int s, int[] nums) {
        int[] mem = new int[nums.length];
        for(int size = 1; size <= nums.length; size++) {
            for(int i = nums.length-1; i >= size-1; i--) {
                if(mem[i] != 0) mem[i] = mem[i-1] + nums[i];
                else mem[i] = nums[i];
                if(mem[i] >= s) return size;
            }
        }
        return 0;
    }

    //O(NlogN)
    public int minSubArrayLen2(int s, int[] nums) {
        int[] sum = new int[nums.length+1];
        int currSum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum[i+1] = currSum + nums[i];
            currSum += nums[i];
        }
        if(sum[nums.length] < s) return 0;
        int ans = Integer.MAX_VALUE;
        //二分查找使得区间[l,x]和第一个大于s的右端点x
        for(int l = 0; l < nums.length; l++) {
            int r = nums.length;
            int ll = l+1;
            boolean fg = false;
            while(ll <= r) {
                int mid = (ll + r) / 2;
                if(sum[mid] - sum[l] >= s) {
                    fg = true;
                    r = mid -1 ;
                }
                else {
                    ll = mid + 1;
                }
            }
            if(fg) ans = Math.min(ans,ll-l);
        }
        return ans;
    }

    //O(N),sliding window
    public int minSubArrayLen3(int s, int[] nums) {
        int size = nums.length + 1;
        int l = 0, r = -1;
        int sum = 0;

        while(r < nums.length) {
            if(sum < s) {
                r++;
                if(r < nums.length) sum += nums[r];
            }else {
                //System.out.println(r-l+1);
                size = Math.min(size,r-l+1);
                sum -= nums[l];
                l++;

            }
        }
        if(size == nums.length + 1) return 0;
        else return size;
    }
}
