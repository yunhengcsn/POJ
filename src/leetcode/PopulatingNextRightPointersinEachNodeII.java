package leetcode;

import java.util.LinkedList;
import java.util.Queue;

class Node {
     private int val;
     Node left;
     Node right;
     Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
/*
117. Populating Next Right Pointers in Each Node II
* Given a binary tree

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.
* */
public class PopulatingNextRightPointersinEachNodeII {
    public Node connect(Node root) {
        if(root == null) return root;
        //2个队列轮流存储父亲层和儿子层
        Queue<Node> q1 = new LinkedList<>();
        Queue<Node> q2 = new LinkedList<>();
        q1.add(root);
        while(q1.size() != 0 ) {
            while(q1.size() != 0) {
                Node curr = q1.poll();
                //curr的next设为null或者同层的下一个节点
                if(q1.size() != 0 ) curr.next = q1.peek();
                else curr.next = null;
                //把curr的left和right放入另一个队列
                if(curr.left != null) q2.add(curr.left);
                if(curr.right != null) q2.add(curr.right);
            }
            while(q2.size() != 0) {
                Node curr = q2.poll();
                if(q2.size() != 0 ) curr.next = q2.peek();
                else curr.next = null;
                if(curr.left != null) q1.add(curr.left);
                if(curr.right != null) q1.add(curr.right);
            }
        }
        return root;

    }


    public Node connect2(Node root) {
        if(root == null) return root;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(q.size() != 0) {
            int size = q.size();
            for(int i = 1;i <= size ;i++) {
                Node curr = q.poll();
                if(i != size) {
                    curr.next = q.peek();
                }
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
            }
        }
        return root;
    }
}
