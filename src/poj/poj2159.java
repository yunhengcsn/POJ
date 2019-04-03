package poj;
/**Description:替换加密（偏移）和乱序加密（交换位置）
 * 比较明文和密文加密前后的共有特征：
 * 1）等长
 * 2）对明文和密文分别求得每个字母的出现频次，排序，配对则频次数列相同
 * @author csn
 *
 * */

import java.util.Arrays;
import java.util.Scanner;
public class poj2159 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String encrypted=sc.next();
        String original=sc.next();
        int[] alphaEn=new int[26];
        int[] alphaOr=new int[26];
        for(int i=0;i<original.length();i++){
            alphaOr[original.charAt(i)-'A']++;
        }
        for(int i=0;i<encrypted.length();i++){
            alphaEn[encrypted.charAt(i)-'A']++;
        }
        Arrays.sort(alphaEn);
        Arrays.sort(alphaOr);
        int i=0,j=0;
        while(i<alphaEn.length&&j<alphaOr.length){
            if(alphaEn[i]==0){
                i++;
                continue;
            }
            else if(alphaOr[j]==0){
                j++;
                continue;
            }
            else{
                if(alphaEn[i]==alphaOr[j]){
                    i++;j++;
                }
                else break;
            }
        }
        if(i==alphaEn.length&&j==alphaEn.length) System.out.println("YES");
        else System.out.println("NO");
    }
}
