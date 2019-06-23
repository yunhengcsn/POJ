package leetcode;

public class DiagonalTraverse {
    /*498. Diagonal Traverse
    * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
    * Example:
    * Input:
    * [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ]]
    * Output:  [1,2,4,7,5,3,6,8,9]
    *
    * */
    public int[] findDiagonalOrder(int[][] matrix) {
        int row = matrix.length;
        if(row == 0) return new int[0];
        int col = matrix[0].length;
        int[] ans = new int[row*col];
        boolean fg = true;//fg为true右上，为false左下
        int i = 0, j = 0, k = 0;
        ans[k++] = matrix[i][j];
        while(i != (row-1) || j != (col-1)) {
            int ni,nj;
            if(fg) {
                ni = i-1;
                nj = j+1;
                if(ni < 0) {
                    ni = 0;
                    fg = false;
                }
                if(nj >= col) {//列超出换下一行
                    nj = col-1;
                    ni = i+1;
                    fg = false;
                }
            } else {
                ni = i+1;
                nj = j-1;
                if(nj < 0) {
                    nj = 0;
                    fg = true;
                }
                if(ni >= row) {
                    ni = row-1;
                    nj = j + 1;
                    fg = true;
                }
            }
            ans[k++] = matrix[ni][nj];
            i = ni;
            j = nj;
        }
        return ans;
    }


    //So Elegant!
    public int[] findDiagonalOrderII(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int r = 0;
        int c = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] arr = new int[m * n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = matrix[r][c];
            if ((r + c) % 2 == 0) { // moving up
                if      (c == n - 1) { r++; }
                else if (r == 0)     { c++; }
                else            { r--; c++; }
            } else {                // moving down
                if      (r == m - 1) { c++; }
                else if (c == 0)     { r++; }
                else            { r++; c--; }
            }
        }
        return arr;
    }
}
