package leetcode;

/*372. Super Pow
* Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.

Example 1:

Input: a = 2, b = [3]
Output: 8
Example 2:

Input: a = 2, b = [1,0]
Output: 1024
* */
public class SuperPow {
    public int superPow(int a, int[] b) {
        a %= 1337;
        int result = 1;
        for (int i = b.length - 1; i >= 0; i--) {
            result = result * normalPow(a, b[i]) % 1337;
            a = normalPow(a, 10);
        }
        return result;
    }
    private int normalPow(int a, int b) {
        int result = 1;
        while (b != 0) {
            if (b % 2 != 0)
                result = result * a % 1337;
            a = a * a % 1337;
            b /= 2;
        }
        return result;
    }
}
