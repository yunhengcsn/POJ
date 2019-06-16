package offer;
/**
 * Description:输入一个链表，输出该链表中倒数第k个结点。
 * */

public class FindKthToTail {
    public class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode findKthToTail(ListNode head,int k) {
        if(head==null) return null;
        ListNode front=head;
        ListNode end=head;
        for(int i=0;i<k;i++){
            if(front==null) return null;
            front=front.next;
        }
        while(front!=null){
            front=front.next;
            end=end.next;
        }
        return end;

    }

}
