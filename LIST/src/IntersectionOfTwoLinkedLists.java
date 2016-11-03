import java.util.HashSet;

/**
 * Author: zhangxin
 * Time: 2016/11/3 0003.
 * Desc:寻找两个链表的交点
 * <p>
 * A:          a1 → a2
 * ↘
 * c1 → c2 → c3
 * ↗
 * B:     b1 → b2 → b3
 */
public class IntersectionOfTwoLinkedLists {
    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    //方案一: 用 HashSet存HashCode,如果有重复,那么直接返回,但是效率太慢!!!,不推荐使用
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<Integer> set = new HashSet<Integer>();
        while (headA != null) {
            set.add(headA.hashCode());
            headA = headA.next;
        }
        if (set.isEmpty()) {
            return null;
        }
        while (headB != null) {
            if (!set.add(headB.hashCode())) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    //采用双指针的方法,如果两者存在公共的节点,那么后续的几个节点就一样了,所以采用的大体思路是,
    // 让长的先走多出的几步,短的还在头,此时两个链表长度相等,然后共同走一步,看是否相等;
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        int lenA = 0, lenB = 0;
        ListNode headAA = headA;
        ListNode headBB = headB;

        while (headAA != null) {
            lenA++;
            headAA = headAA.next;
        }
        if (lenA == 0) return null;
        while (headBB != null) {
            lenB++;
            headBB = headBB.next;
        }
        if (lenB == 0) return null;

        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; i++) {
                headA = headA.next;
            }

        } else if (lenB > lenA) {
            for (int i = 0; i < lenB - lenA; i++) {
                headB = headB.next;
            }
        }
        while (headA != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    //基于上一版本的改进;思路巧妙,但是效率不如上一个版本高;待优化;
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        ListNode p = headA, q = headB;
        while (p != null && q != null) {
            if (p == q) {
                return q;
            }

            p = p.next;
            q = q.next;

            if (q == null && p == null) {
                return null;
            } else if (q == null) {
                q = headA;
            } else if (p == null) {
                p = headB;
            }
        }
        return null;
    }
}
