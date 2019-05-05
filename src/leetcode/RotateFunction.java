package leetcode;

public class RotateFunction {
    /*396. Rotate Function
    * Given an array of integers A and let n to be its length.
    * Assume Bk to be an array obtained by rotating the array A k positions clock-wise,
    * we define a "rotation function" F on A as follow:
    * F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
    * Calculate the maximum value of F(0), F(1), ..., F(n-1).
    * Note:n is guaranteed to be less than 100000.
    * Example:A = [4, 3, 2, 6]
    * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
    * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
    * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
    * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
    * So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
    * */
    public int maxRotateFunction(int[] A) {
        if(A.length == 0) return 0 ;
        int first = A[0];
        int last = A[A.length-1];
        int sum = 0;
        int sum1 = 0;
        for(int i = 1;i < A.length-1;i++) {
            sum += i*A[i];
            sum1 += A[i];
        }
        int max = Integer.MIN_VALUE;
        int tmp = sum + (A.length-1)*last;
        max = Math.max(max,tmp);
        for(int j = 1;j < A.length;j++) {
            tmp = tmp - (A.length-1)*last + first + sum1;//更新这次旋转的和
            last = A[A.length - j -1];//更新最后一个数
            sum1 = first + sum1 -last;//更新中间几个数之和
            first = A[A.length-j];//更新第一个数

            max = Math.max(max,tmp);
        }
        return max;
    }
}
