package typical.bfsdfs;

import java.util.Arrays;

/**
 * Description: 473. Matchsticks to Square
 *
 * @author csn
 */
public class MatchstickstoSquare {
    //dfs
    private int[] bucket = new int[4];
    public boolean makesquare(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }
        if(nums.length == 0 || sum % 4 != 0 || sum / 4 < nums[0]) return false;
        int target = sum / 4;
        return dfs(nums, target, nums.length - 1);
    }
    private boolean dfs(int[] nums, int target, int st) {
        if(st == -1) return true;

        for(int j = 0; j < 4; j++) {
            if(bucket[j] + nums[st] > target || (j > 0 && bucket[j] == bucket[j - 1])) continue;
            bucket[j] += nums[st];
            if(dfs(nums, target, st-1)) return true;
            bucket[j] -= nums[st];
        }
        return false;
    }
}
