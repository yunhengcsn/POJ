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
        /*运行时间过大？
        wrong answer
        int cnt=0;
        if(n==0) return cnt;
        if(n<0){
            cnt++;
            n=-n;
        }
        while(n!=1){
            if(n%2==1) cnt++;
            n=n/2;
        }
        cnt++;
        return cnt;*/
    }
}
