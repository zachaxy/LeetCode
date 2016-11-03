import java.util.ArrayList;
import java.util.List;

/**
 * Author: zhangxin
 * Time: 2016/11/3 0003.
 * Desc:
 * Given a linked list, remove the nth node from the end of list and return its head.
 * 给出 n,从单向链表的结尾开始数 n,删除该节点,假设所给的n 是有效的
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 */
public class RemoveNthNodeFromEndOfList {
    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //方案一:用一个list来盛放,直接删除倒数第n个,然后重新组建链表;
    public ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> list = new ArrayList<ListNode>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }

        if (n == list.size()) {
            if (n == 1) {
                return null;
            }
            return list.get(1);
        }

        ListNode nodePre = list.get(list.size() - n - 1);
        ListNode node = list.get(list.size() - n);
        nodePre.next = node.next;
        return list.get(0);
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {

        ListNode start = new ListNode(0);
        ListNode slow = start, fast = start; //初始化,slow,fast指向新键的节点
        slow.next = head;  //将新键的节点插在 head 之前;

        //Move fast in front so that the gap between slow and fast becomes n
        //将 fast 节点移动到原链表的 n+1 的位置;
        for (int i = 1; i <= n + 1; i++) {
            fast = fast.next;
        }
        //Move fast to the end, maintaining the gap
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        //使用两个节点,保持两个节点的之间的间隔为n;
        //此时 slow 在倒数第 n 个节点之前
        //Skip the desired node
        slow.next = slow.next.next;
        return start.next;
    }
}
