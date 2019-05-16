package 链表;

/**
 * User: FenDou
 * Date: 2019-05-09 10:54
 * Description: https://leetcode-cn.com/problems/reverse-linked-list/
 */


public class _206_反转链表 {

    /*  递归
     *  执行用时 : 1 ms
     *  内存消耗 : 35.7 MB
     */
    public ListNode reverseList(ListNode head) {
          if (head == null || head.next == null) return head;
          ListNode newHead   = reverseList(head.next);
          head.next.next    = head;
          head.next         = null;
          return newHead;
      }

   /*  迭代
    *  执行用时 : 1 ms
    *  内存消耗 : 35.5 MB
    */
//    public ListNode reverseList(ListNode head) {
//            ListNode newHead = null;
//            while (head != null){
//                ListNode tmp = head.next;
//                head.next    = newHead;
//                newHead      = head;
//                head         = tmp;
//            }
//            return newHead;
//    }
}
