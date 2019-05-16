package 链表;

/**
 * User: FenDou
 * Date: 2019-05-09 16:16
 * Description: https://leetcode-cn.com/problems/remove-linked-list-elements/
 */

/**
 * 删除链表中等于给定值 val 的所有节点
 */
public class _203_移除链表元素 {
    public ListNode removeElements(ListNode head, int val) {

        if (head == null) return null;

        head.next = removeElements(head.next,val);

        return head.val == val ? head.next : head;
    }
}
