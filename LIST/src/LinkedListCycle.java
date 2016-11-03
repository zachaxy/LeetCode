import java.util.HashSet;

/**
 * Author: zhangxin
 * Time: 2016/11/3 0003.
 * Desc: 检测一个链表是否有环;
 */


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}


public class LinkedListCycle {
    //方案一:用一个list存储每个节点的 hashCode,如果有相同的,那么直接返回true;但是效率低,beat~3%
    public boolean hasCycle(ListNode head) {
        HashSet<Integer> set = new HashSet<Integer>();
        while (head != null) {
            if (!set.add(head.hashCode())) {
                //添加不成功,说明有重复的点,返回 true
                System.out.println("当前head 已存在");
                return true;
            }
            head = head.next;
        }
        return false;
    }

    //方案二:不使用额外的空间;利用node 的额外信息,val,下面的方法是该程序的一个bug,如果链表中的本身原本就包含Integer.MAX_VALUE,那么无效;
    public boolean hasCycle2(ListNode head) {
        while (head != null) {
            if (head.val != Integer.MAX_VALUE) {
                head.val = Integer.MAX_VALUE;
            } else {
                return true;
            }
            head = head.next;
        }

        return false;
    }

    //利用两个指针,一个指针移动的慢,一个指针移动的快,这就变成了追击问题,如果有环的话,移动的快的虽然开始在前面,但是经过环,又绕回到慢的后面,最后追上慢的指针;
    public boolean hasCycle3(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast != slow && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == slow) return true;
        return false;
    }

    //上述版本的更简洁的代码方式;
    public boolean hasCycle4(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) return true;
        }
        return false;
    }


}
