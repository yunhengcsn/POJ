package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
 /*51. N-Queens
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * Example:
 * Input: 4
 * Output: [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 *  */
public class NQueens {
    //1.性能较差
    public  List<List<String>> solveNQueens(int n) {
        List<List<String>> list = new ArrayList<>();
        if(n == 0) return list;
        backtrack(list,new ArrayList<>(),0,n,new HashMap<>());
        return list;
    }
    private  void backtrack(List<List<String>> list ,ArrayList<String> tmplist,int st,int n,HashMap<Integer,Integer> map ) {
        if(tmplist.size() == n){//递归出口
            list.add(new ArrayList<>(tmplist));
            return;
        }
        //第st行的String
        StringBuilder sb = new StringBuilder();
        for(int i =0;i < n;i++) sb.append('.');

        //第st行每列
        for(int i = 0;i < n;i++){
            if(map.size() == 0) {
                map.put(st,i);
            }
            else if(!map.containsValue(i) && !isXie(map,st,i)) {//判断是否同一列或斜线
                map.put(st,i);
            }
            else continue;
            sb.setCharAt(i,'Q');
            tmplist.add(sb.toString());
            backtrack(list,tmplist,st+1,n,map);//回溯
            tmplist.remove(tmplist.size()-1);
            sb.setCharAt(i,'.');
            map.remove(st);
        }
    }
    private  boolean isXie(HashMap<Integer,Integer> map,int x, int y) {
        boolean ans = false;
        for(HashMap.Entry e : map.entrySet()) {
            Integer key = (Integer)e.getKey();
            Integer val = (Integer)e.getValue();
            if((double)Math.abs(key-x) / Math.abs(val-y) == 1) {
                ans = true;
                break;
            }
        }
        return ans;
    }


    //2.时间复杂度小
    public List<List<String>> solveNQueensII(int n) {
        List<List<String>> result = new ArrayList<>();
        helper(result, new ArrayList<>(), 0, new boolean[n], new boolean[2*n], new boolean[2*n], n);
        return result;
    }

     private void helper(List<List<String>> result, List<String> board, int row, boolean[] cols, boolean[] d1, boolean[] d2, int n){
         if (row == n) {
             result.add(new ArrayList<>(board));//遍历完所有行
         }
         for (int col=0; col<n; col++){
             int id1 = col - row + n;//检验右下角
             int id2 = col + row;//检验左下角
             if (!cols[col] && !d1[id1] && !d2[id2]){//列不重复，右下不重复，左下不重复
                 char[] r = new char[n];
                 Arrays.fill(r, '.');
                 r[col] = 'Q';
                 board.add(new String(r));
                 cols[col] = true;
                 d1[id1] = true;
                 d2[id2] = true;
                 helper(result, board, row+1, cols, d1, d2, n);//回溯 dfs
                 board.remove(board.size()-1);
                 cols[col] = false;
                 d1[id1] = false;
                 d2[id2] = false;
             }
         }
     }
}
