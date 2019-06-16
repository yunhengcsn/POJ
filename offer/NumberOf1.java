package offer;
/**
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 * Solve：将 1与数位与
 * @author csn
 */


public class NumberOf1 {
    public static void main(String[] args){
        int n=-168;
        System.out.println(numberOf1(n));
        System.out.println(Integer.toBinaryString(n));
    }
    private static int numberOf1(int n) {
        int cnt=0;
        int fg=1;
        while(fg!=0){
            if((n&fg)!=0) cnt++;
            fg=fg<<1;
        }
        return cnt;
        /*把一个整数减去1，再和原整数与，会最右边一个1变成0.那么有多少个1就可以与多少次
        * int count = 0;
        while (n != 0) {
            ++count;
            n = (n - 1) & n;
        }
        return count;
        * */
    }
}
