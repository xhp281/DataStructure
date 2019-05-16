package 链表;

/**
 * User: FenDou
 * Date: 2019-05-09 16:55
 * Description: https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/comments/
 */

public class _83_删除排序链表中的重复元素 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode currNode = head;

        while (currNode != null && currNode.next != null){
              if (currNode.val == currNode.next.val){
                  // 跳过重复的
                  currNode.next = currNode.next.next;
              }else{
                  // 切换下一个
                  currNode = currNode.next;
              }
        }
        return head;
    }
}
