package offer;
/**
 * 输入一个链表，反转链表后，输出新链表的表头。
 * Description:使用三个节点前中后，遍历
 * */
class ListNode {
    private int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode pre=head;
        ListNode curr=head.next;
        ListNode after;
        while(curr!=null){

            after=curr.next;
            curr.next=pre;
            if(pre==head) pre.next=null;
            pre=curr;
            curr=after;
        }
        return pre;

    }
}