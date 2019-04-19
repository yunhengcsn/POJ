package leetcode;

import java.util.ArrayList;
/*
Write a program to find the n-th ugly number.
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
Example:
Input: n = 10 Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
Note:1 is typically treated as an ugly number.n does not exceed 1690.
 */
/*
Solution:
The ugly-number sequence is 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, …
because every number can only be divided by 2, 3, 5, one way to look at the sequence is to split the sequence to three groups as below:

(1) 1×2, 2×2, 3×2, 4×2, 5×2, …
(2) 1×3, 2×3, 3×3, 4×3, 5×3, …
(3) 1×5, 2×5, 3×5, 4×5, 5×5, …
We can find that every subsequence is the ugly-sequence itself (1, 2, 3, 4, 5, …) multiply 2, 3, 5.

Then we use similar merge method as merge sort, to get every ugly number from the three subsequence.

Every step we choose the smallest one, and move one step after,including nums with same value.
 */
public class UglyNumberII {
    public int nthUglyNumber(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        int f2 = 2;
        int f3 = 3;
        int f5 = 5;
        int p2 = 0, p3 = 0,p5 = 0;
        while(list.size() <= n) {
            int min =Math.min(f2,Math.min(f3,f5));
            if(min == f2) {
                if(list.get(list.size()-1) != f2) {
                    list.add(f2);
                }
                p2++;
                f2 = list.get(p2)*2;
            }
            if(min == f3) {
                if(list.get(list.size()-1) != f3) {
                    list.add(f3);
                }
                p3++;
                f3 = list.get(p3)*3;
            }
            if(min == f5) {
                if(list.get(list.size()-1) != f5) {
                    list.add(f5);
                }
                p5++;
                f5 = list.get(p5)*5;
            }
        }
        return list.get(n-1);
    }
}
