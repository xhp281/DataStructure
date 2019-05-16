package 链表;

/**
 * User: FenDou
 * Date: 2019-05-09 10:43
 * Description: https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 */

public class _237_删除链表中的节点 {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
