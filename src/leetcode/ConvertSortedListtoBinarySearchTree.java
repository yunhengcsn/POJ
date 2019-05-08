package leetcode;

public class ConvertSortedListtoBinarySearchTree {
    //Definition for a binary tree node.
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    /*109. Convert Sorted List to Binary Search Tree
    * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
    * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
    * Example: Given the sorted linked list: [-10,-3,0,5,9],
    * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
    *       0
    *      / \
    *    -3   9
    *    /   /
    *  -10  5
    * */
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        return toBST(head,null);
    }
    private TreeNode toBST(ListNode head, ListNode tail){
        ListNode slow = head;
        ListNode fast = head;
        if(head==tail) return null;
        //找出head和tail之间的中间节点-->slow
        while(fast!=tail&&fast.next!=tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        //new中间节点作为树根
        TreeNode thead = new TreeNode(slow.val);
        //递归二分更新left
        thead.left = toBST(head,slow);
        //递归二分更新right
        thead.right = toBST(slow.next,tail);
        return thead;
    }
}
