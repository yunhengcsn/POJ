package offer;

import java.util.ArrayList;

/**
 * Description: 输入一颗二叉树的跟节点和一个整数，
 * 打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 *
 * @author csn
 */
public class FindPath {
    
    class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    /**
     * 1.递归回溯
     */
    public ArrayList<ArrayList<Integer>> findPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        backtrack(ans,new ArrayList<>(),root,target);
        return ans;
    }
    private void backtrack(ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> tmpPath, TreeNode root, int target) {
        if(root == null) return;
        if(root.val > target) return;
        if(root.val == target && root.left ==null && root.right == null) {
            tmpPath.add(root.val);
            ans.add(new ArrayList<>(tmpPath));
            tmpPath.remove(tmpPath.size()-1);
            return;
        }
        tmpPath.add(root.val);
        backtrack(ans,tmpPath,root.left,target-root.val);
        backtrack(ans,tmpPath,root.right,target-root.val);
        tmpPath.remove(tmpPath.size()-1);
    }

    /*
     *2.和1思路相同，更为精简
     */
    private ArrayList<ArrayList<Integer>> listAll = new ArrayList<>();
    private ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> findPath2(TreeNode root, int target) {
        if(root == null) return listAll;
        list.add(root.val);
        target -= root.val;
        if(target == 0 && root.left == null && root.right == null)
            listAll.add(new ArrayList<>(list));
        findPath2(root.left, target);
        findPath2(root.right, target);
        list.remove(list.size()-1);
        return listAll;
    }
}
