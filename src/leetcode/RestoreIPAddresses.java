package leetcode;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {
    /**
     *Description:Given a string containing only digits, restore it by returning all possible valid IP address combinations.
     * */
    public static List<String> restoreIpAddresses(String s) {
        ArrayList<ArrayList<Integer>> list=new ArrayList<>();
        helper(s,list,new ArrayList<>(),4);

        ArrayList<String> res=new ArrayList<>(list.size());
        for(int i=0;i<list.size();i++){
            int a1=list.get(i).get(0),a2=list.get(i).get(1),a3=list.get(i).get(2),a4=list.get(i).get(3);
            res.add(a1+"."+a2+"."+a3+"."+a4);
        }
        return res;
    }
    private static void helper(String s,ArrayList<ArrayList<Integer>> list,ArrayList<Integer> tmp,int remain){
        if(remain==0){
            list.add(new ArrayList<>(tmp));
            return;
        }
        if(s.length()>3*remain) return;
        if(remain==1){
            if(s.length()>1&&s.charAt(0)=='0') return;
            int val=Integer.parseInt(s);
            if(val<256){
                tmp.add(val);
                list.add(new ArrayList<>(tmp));
                tmp.remove(tmp.size()-1);
            }
            return;
        }
        for(int i=1;i<Math.min(4,s.length());i++){
            if(i>1&&s.charAt(0)=='0') return;
            int val=Integer.parseInt(s.substring(0,i));
            if(val<256){
                tmp.add(val);
                helper(s.substring(i),list,tmp,remain-1);
                tmp.remove(tmp.size()-1);
            }
        }
    }
    /*More Efficient Solution
    * public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        int len = s.length();
        for(int i = 1; i<4 && i<len-2; i++){
            for(int j = i+1; j<i+4 && j<len-1; j++){
                for(int k = j+1; k<j+4 && k<len; k++){
                    String s1 = s.substring(0,i), s2 = s.substring(i,j), s3 = s.substring(j,k), s4 = s.substring(k,len);
                    if(isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)){
                        res.add(s1+"."+s2+"."+s3+"."+s4);
                    }
                }
            }
        }
        return res;
    }
    public boolean isValid(String s){
        if(s.length()>3 || s.length()==0 || (s.charAt(0)=='0' && s.length()>1) || Integer.parseInt(s)>255)
            return false;
        return true;
    }*/
}
