package typical.recursion_division_backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:递归   39. Combination Sum
 *
 * @author csn
 */
public class CombinationSum {
    private int[] cand;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        cand = candidates;
        help(0,ans,new ArrayList<>(),target);
        return ans;
    }

    private void help(int start, List<List<Integer>> ans, ArrayList<Integer> tmp, int remain) {
        if(remain == 0) {
            ans.add(new ArrayList<>(tmp));
        }
        if(remain < 0) {
            return;
        }
        for(int i = start; i < cand.length; i++) {
            tmp.add(cand[i]);
            remain -= cand[i];
            help(i,ans,tmp,remain);
            remain += cand[i];
            tmp.remove(tmp.size()-1);
        }
    }
}
