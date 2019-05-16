package 链表;

/**
 * User: FenDou
 * Date: 2019-05-09 16:56
 * Description: https://leetcode-cn.com/problems/middle-of-the-linked-list/comments/
 */

public class _876_链表的中间结点 {
    /**
     * 快慢指针方式实现
     * 快指针移动速度是慢指针的两倍
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
