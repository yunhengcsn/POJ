package offer;

/**
 * @BelongsProject: POJ
 * @BelongsPackage: offer
 * @Author: csn
 * @Description: 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
public class Merge {

    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    //非递归
    private ListNode merge1(ListNode list1,ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode n = head;
        while(list1 != null && list2 != null) {
            if(list1.val <= list2.val) {
                n.next = list1;
                list1 = list1.next;
            } else {
                n.next = list2;
                list2 = list2.next;
            }
            n = n.next;
        }
        if(list1 == null) n.next = list2;
        if(list2 == null) n.next = list1;
        return head.next;
    }

    //递归
    private ListNode merge2(ListNode list1,ListNode list2) {
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        if(list1.val <= list2.val){
            list1.next = merge2(list1.next, list2);
            return list1;
        }else{
            list2.next = merge2(list1, list2.next);
            return list2;
        }
    }
}

