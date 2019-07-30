package typical.recursion_division_backtracking;

import java.util.*;

/**
 * Description:
 * 51. N-Queens
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *
 *
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * @author csn
 */
public class NQueens {
    /*
        1. csn solution poor performance
     */
    private List<Loc> locs = new ArrayList<>();
    private Set<Integer> ySet = new HashSet<>();
    public List<List<String>> solveNQueens1(int n) {
        List<List<String>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>() ,n);
        return res;
    }
    private void backtrack(List<List<String>> res , List<String> bd, int n) {
        if(bd.size() == n) {
            res.add(new ArrayList<>(bd));
            return;
        }
        int y = bd.size();
        char[] chs = new char[n];
        Arrays.fill(chs,'.');
        for(int i = 0; i < n; i++) {
            chs[i] = 'Q';
            if( !ySet.contains(y) && !isXie(locs,i,y)) {
                locs.add(new Loc(i,y));
                bd.add(new String(chs));
                ySet.add(y);
                backtrack(res,bd,n);
                bd.remove(bd.size() - 1);
                locs.remove(locs.size() - 1);
                ySet.remove(y);
            }
            chs[i] = '.';
        }
    }
    private boolean isXie(List<Loc> locs, int x, int y) {
        for(Loc l : locs) {

            if((l.x - x == l.y - y) || (l.x - x == y - l.y)) return true;
        }
        return false;
    }
    class Loc {
        private int x;
        private int y;
        Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    /*
        2. 2ms
     */
    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> result = new ArrayList<>();
        helper(result, new ArrayList<>(), 0, new boolean[n], new boolean[2*n], new boolean[2*n], n);
        return result;
    }

    private void helper(List<List<String>> result, List<String> board, int row, boolean[] cols, boolean[] d1, boolean[] d2, int n){
        if (row == n) {
            result.add(new ArrayList<>(board));
        }
        for (int col = 0; col < n; col++){
            int id1 = col - row + n;
            int id2 = col + row;
            //如果列不重复，左斜不重复，右斜不重复
            if (!cols[col] && !d1[id1] && !d2[id2]){
                char[] r = new char[n];
                Arrays.fill(r, '.');
                r[col] = 'Q';
                board.add(new String(r));
                cols[col] = true;
                d1[id1] = true;
                d2[id2] = true;
                helper(result, board, row+1, cols, d1, d2, n);
                board.remove(board.size()-1);
                cols[col] = false;
                d1[id1] = false;
                d2[id2] = false;
            }
        }
    }
}
