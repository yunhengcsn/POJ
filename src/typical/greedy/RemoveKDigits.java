package typical.greedy;

import java.util.Stack;

/**
 * Description:  402. Remove K Digits
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 *
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 * Example 1:
 *
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 *
 * @author csn
 */
public class RemoveKDigits {
    /*
        1. use stack 13ms
     */
    public String removeKdigits1(String num, int k) {
        //corner case
        if(k == num.length()) return "0";
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            //if the previous digit is larger, then pop it
            while(k > 0 && !stack.isEmpty() && stack.peek() > c) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        //"1111"
        while( k > 0) {
            stack.pop();
            k--;
        }
        StringBuilder str = new StringBuilder();
        while(!stack.isEmpty()) str.append(stack.pop());
        str.reverse();
        //remove the leading zeroes
        while(str.length() != 0 && str.charAt(0) == '0') str.deleteCharAt(0);

        if(str.length() == 0) return "0";
        return str.toString();
    }

    /*
        2. use charArray 4ms
     */
    public String removeKdigits2(String num, int k) {
        int digits = num.length() - k;
        char[] stk = new char[num.length()];
        int top = 0;
        // k keeps track of how many characters we can remove
        // if the previous character in stk is larger than the current one
        // then removing it will get a smaller number
        // but we can only do so when k is larger than 0
        for (int i = 0; i < num.length(); ++i) {
            char c = num.charAt(i);
            while (top > 0 && stk[top-1] > c && k > 0) {
                top -= 1;
                k -= 1;
            }
            stk[top++] = c;
        }
        // find the index of first non-zero digit
        int idx = 0;
        while (idx < digits && stk[idx] == '0') idx++;
        return idx == digits? "0": new String(stk, idx, digits - idx);
    }
}
