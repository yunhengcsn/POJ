package typical.linkedlist;

/**
 * Description: 92. Reverse Linked List II
 *
 * @author csn
 */
public class ReverseLinkedListII {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @param head
     * @param m
     * @param n
     * @return ListNode
     */
    public ListNode reverseBetween1(ListNode head, int m, int n) {
        if(m==n) return head;
        int i = 1;
        ListNode node = head;
        ListNode cut1 = null,cut2 = null,st = null,ed = null;//cut1 is m-1;cut2 is n+1; st is the first node of reversed segment; ed is the last one of ~
        ListNode pre = null, nxt;//pre is the former visited node; nxt is the next node to be visited
        while(node != null) {//node is the current visiting node
            nxt = node.next;
            if(i == m - 1) cut1 = node;
            if(i == n + 1) {
                cut2 = node;
                break;
            }
            if(i == m) {
                ed = node;
                pre = ed;
            }
            if(i > m && i <= n) {
                node.next = pre;
                if(i == n) st = node;
                else  pre = node;
            }
            node = nxt;
            i++;
        }
        ed.next = cut2;
        if(cut1 != null) {
            cut1.next = st;
            return head;
        } else {
            return st;
        }
    }


    /**
     * Description: only use 3 pointers, and have the same performance as the first solution
     * @param head
     * @param m
     * @param n
     * @return ListNode
     */
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if(head == null) return null;
        ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
        dummy.next = head;
        ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
        for(int i = 0; i<m-1; i++) pre = pre.next;

        ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
        ListNode then = start.next; // a pointer to a node that will be reversed

        // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
        // dummy-> 1 -> 2 -> 3 -> 4 -> 5

        for(int i=0; i<n-m; i++)
        {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }

        // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
        // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)

        return dummy.next;

    }
}
