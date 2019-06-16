package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
*47. Permutations II
* Given a collection of numbers that might contain duplicates, return all possible unique permutations.
* Example:
* Input: [1,1,2]
* Output:
* [
* [1,1,2],
* [1,2,1],
* [2,1,1]
* ]
* */
public class PermutationII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        Arrays.sort(nums);//排序以去除重复项
        int[] used=new int[nums.length];
        backtrack(list,new ArrayList<>(),nums,used);
        return list;
    }

    private void backtrack(List<List<Integer>> list,List<Integer> temp,int[] nums,int[] used){
        if(temp.size()==nums.length) list.add(new ArrayList<>(temp));
        else{
            for(int i=0;i<nums.length;i++){
                if(i>0&&nums[i-1]==nums[i]&&used[i-1]==0) continue;//只有当另一个相同元素访问过才添加
                if(used[i]==0){
                    temp.add(nums[i]);
                    used[i]=1;
                }
                else continue;
                backtrack(list,temp,nums,used);
                used[i]=0;
                temp.remove(temp.size()-1);
            }
        }
    }
}
