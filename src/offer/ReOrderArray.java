package offer;
/**
 * Description:输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * */

public class ReOrderArray {
    public void reOrderArray(int [] array) {
        /*另建数组，最坏情况遍历两边数组
        int[] even=new int[array.length];
        int oddloc=0;
        int j=0;
        for(int i=0;i<array.length;i++){
            if(array[i]%2==0){
                even[j++]=array[i];
            }
            else{
                if(oddloc<i) array[oddloc++]=array[i];
                else oddloc=i+1;
            }
        }
        for(int k=0;k<j;k++) array[oddloc++]=even[k];
        */
        //类似插入排序思想,稳定
        int oddNum=0;
        for(int i=0;i<array.length;i++){
            if(array[i]%2==1){
                int j=i;
                int tmp=array[j];
                while(j>oddNum){
                    array[j]=array[j-1];
                    j--;
                }
                array[oddNum]=tmp;
                oddNum++;
            }
        }
    }
}
