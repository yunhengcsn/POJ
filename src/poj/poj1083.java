package poj;
import java.util.Scanner;
/**
 *Description:为走廊建长度200的数组，每次移动桌子经过的走廊+10，
 * 找出数组元素最大值输出
 *author:csn
 * */

public class poj1083 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int num=sc.nextInt();
        for(int i=0;i<num;i++){
            int tableNum=sc.nextInt();
            int max=0;
            int[] move=new int[200];
            for(int j=0;j<tableNum;j++){
                int st=sc.nextInt();
                int ed=sc.nextInt();
                if(st>ed){
                    int tmp=st;
                    st=ed;
                    ed=tmp;
                }
                if(st%2==0) st--;
                if(ed%2==1) ed++;
                for(int k=st/2;k<ed/2;k++){
                    move[k]+=10;
                    max=Math.max(max,move[k]);
                }
            }

            System.out.println(max);
        }
    }
}
