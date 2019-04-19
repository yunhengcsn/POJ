package leetcode;
/*
 * Description:Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 * */

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergekSortedLists {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
   }
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length,new Comparator<ListNode>() {
            @Override
            public int compare(ListNode n1,ListNode n2) {
                return n1.val-n2.val;
            }
        });
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for(ListNode node : lists) {
            if(node != null) pq.add(node);
        }
        while(!pq.isEmpty()) {
            tail.next = pq.poll();
            tail=tail.next;
            if(tail.next != null) pq.add(tail.next);

        }
        return dummy.next;
    }
}
