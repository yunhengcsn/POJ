package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class KSimilarStrings {
    /*854. K-Similar Strings
    * Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A exactly K times so that the resulting string equals B.
    * Given two anagrams A and B, return the smallest K for which A and B are K-similar.
    * Example 1:
    * Input: A = "ab", B = "ba" Output: 1
    * Example 2: Input: A = "abc", B = "bca" Output: 2
    * Note:
    * 1 <= A.length == B.length <= 20
    * A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}
    *
    * Solution:  bfs
    * */
    public int kSimilarity(String A, String B) {
        if (A.equals(B)) return 0;
        Set<String> vis= new HashSet<>();
        Queue<String> q= new LinkedList<>();
        q.add(A);
        vis.add(A);
        int res=0;
        while(!q.isEmpty()){
            res++;
            for (int sz=q.size(); sz>0; sz--){
                String s= q.poll();
                int i=0;
                //找到s和B字母不同的位置i
                while (s.charAt(i)==B.charAt(i)) i++;
                //遍历  i+1到末尾
                for (int j=i+1; j<s.length(); j++){
                    //若第j个字母位置匹配或s的第i个字母不等于B的第j个字母，continue
                    if (s.charAt(j)==B.charAt(j) || s.charAt(i)!=B.charAt(j) ) continue;
                    //交换s中的第i个字母和第j个字母，使得s和B的第j个字母相同
                    String temp= swap(s, i, j);
                    if (temp.equals(B)) return res;
                    if (vis.add(temp)) q.add(temp);//vis用来判断temp是否加入过
                }
            }
        }
        return res;
    }
    private String swap(String s, int i, int j){
        char[] ca=s.toCharArray();
        char temp=ca[i];
        ca[i]=ca[j];
        ca[j]=temp;
        return new String(ca);
    }
}
