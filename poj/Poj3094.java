package poj;
import java.util.Scanner;
/**
 * Description: QuickSum
 * */
public class Poj3094 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(!sc.hasNext("#")){
            String s=sc.nextLine();
            int ans=0;
            for(int i=0;i<s.length();i++){
                int val;
                if(s.charAt(i)==' ') val=0;
                else val=s.charAt(i)-'A'+1;
                ans+=(i+1)*val;
            }
            System.out.println(ans) ;
        }
    }

}
