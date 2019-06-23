package offer;

import java.util.ArrayList;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 *
 * @author csn
 * @date 2019/6/4 10:46
 */
public class PrintMatrix {

    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> ans = new ArrayList<>();
        int r = matrix.length;
        if(r == 0) return ans;
        int c = matrix[0].length;
        int d = 0;
        //计算圈数
        int b = (Math.min(r,c)+1) / 2 ;
        while(d < b) {
            //向右
            for(int j = d; j < c-d; j++) ans.add(matrix[d][j]);
            //向下
            for(int i = d+1; i < r-d; i++) ans.add(matrix[i][c-1-d]);
            //向左，if条件判断用于避免与向右步骤重复
            if(r-1-d != d) {
                for(int j = c-1-d-1; j >= d; j--) ans.add(matrix[r-1-d][j]);
            }
            //向上，if条件判断用于避免与向下步骤重复
            if(c-1-d != d) {
                for(int i = r-1-d-1; i > d; i--) ans.add(matrix[i][d]);
            }
            d++;
        }
        return ans;
    }
}
