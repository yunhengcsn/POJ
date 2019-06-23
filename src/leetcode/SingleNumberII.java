package leetcode;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @BelongsProject: POJ
 * @BelongsPackage: leetcode
 * @Author: csn
 * @Description: 137.Single Number II  solution: bit manipulation
 */
public class SingleNumberII {

    //sort
    public int singleNumber1(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i += 3) {
            if(i+2 < nums.length && nums[i+2] != nums[i]) return nums[i];
        }
        return nums[nums.length-1];
    }

    //set
    public int singleNumber2(int[] nums){
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(set1.contains(nums[i])){
                set2.add(nums[i]);
            }
            else{
                set1.add(nums[i]);
            }
        }
        int i = 0;
        for(Object o : set1){
            if(!set2.contains(o)){
                return (Integer)o;
            }
        }
        return i;
    }

    //bit manipulation I
    // 对每个数的32位都进行一下统计，统计所有数的每一位中1出现的次数，累加然后%3，然后送回原来对应的位。
    public int singleNumber3(int[] nums) {
        int res = 0;
        for(int i = 0; i < 32; i++){
            int sum = 0;
            for(int num : nums) {
                sum += (num >> i) & 1; // 第i位的1的个数
                sum %= 3;
            }
            res |= (sum << i); // 将sum的结果返回给第i位(赋值)
        }
        return res;
    }

    //bit manipulation II
    public int singleNumber4(int[] nums) {
        int one = 0, two = 0, three = 0;
        for(int num : nums){
            two |= (one & num);
            one ^= num;
            three = ~(one & two);
            one &= three;
            two &= three;
        }
        return one;
    }
}
