package 链表;

/**
 * User: FenDou
 * Date: 2019-05-09 15:47
 * Description: https://leetcode-cn.com/problems/linked-list-cycle/
 */

public class _141_环形链表 {
    /**
     * 判断链表有没有环(快慢指针，相遇则有环)
     */
    public boolean hasCycle(ListNode head) {
        // 判断元素
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            slow = head.next;
            fast = head.next.next;
            if (slow == fast) return true;
        }
        return  false;
    }
}
