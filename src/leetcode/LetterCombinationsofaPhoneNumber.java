package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LetterCombinationsofaPhoneNumber {
    /*
    * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
    * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
    * Example:
    * Input: "23"
    * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    * Solution: 递归
    * */

    //数字和字母对应的二维数组
    private String[][] cs = {{"a","b","c"},{"d","e","f"},{"g","h","i"},{"j","k","l"},{"m","n","o"},{"p","q","r","s"},{"t","u","v"},{"w","x","y","z"}};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();

        if(digits.length() == 0) return res;
        int num = digits.charAt(0) - '0';
        int id = num-2;
        //只有1个数字时
        if(digits.length() == 1) {
            Collections.addAll(res, cs[id]);
            return res;
        }
        //除去第一个字符后，其他字符能组成的String list
        List<String> sublist = letterCombinations(digits.substring(1));
        //遍历第0个数字能表示的字母们
        for(int i = 0; i < cs[id].length; i++) {
            String ci = cs[id][i];
            //遍历sublist中所有String
            for (String s : sublist) {
                res.add(ci + s);
            }
        }
        return res;
    }
}
