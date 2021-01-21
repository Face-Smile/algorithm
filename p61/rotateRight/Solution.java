//给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
//
// 示例 1:
//
// 输入: 1->2->3->4->5->NULL, k = 2
//输出: 4->5->1->2->3->NULL
//解释:
//向右旋转 1 步: 5->1->2->3->4->NULL
//向右旋转 2 步: 4->5->1->2->3->NULL
//
//
// 示例 2:
//
// 输入: 0->1->2->NULL, k = 4
//输出: 2->0->1->NULL
//解释:
//向右旋转 1 步: 2->0->1->NULL
//向右旋转 2 步: 1->2->0->NULL
//向右旋转 3 步: 0->1->2->NULL
//向右旋转 4 步: 2->0->1->NULL
// Related Topics 链表 双指针
// 👍 396 👎 0
package algorithm.p61.rotateRight;


/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3 ,4 ,5};
        ListNode root = createListNode(nums);
        printListNode(root);
        printListNode(new Solution().rotateRight(root, 5));
    }

    public static ListNode createListNode(int[] nums) {
        ListNode root = new ListNode();
        ListNode temp = root;
        for (int num : nums) {
            ListNode node = new ListNode(num);
            temp.next = node;
            temp = node;
        }
        return root.next;
    }

    public static void printListNode(ListNode root) {
        while (root != null) {
            System.out.print(root.val + "->");
            root = root.next;
        }
        System.out.println("null");
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode p = head;
        ListNode end;
        // 获取长度
        int len = 1;
        while (p.next != null) {
            len++;
            p = p.next;
        }
        end = p;
        // 首尾相连
        end.next = head;

        // 计算断开位置
        k = len - k % len;
        if (k == 0) return head;

        p = head;
        // 指向断开位置
        while (--k > 0) {
            p = p.next;
        }
        // 头部指向断开位置
        head = p.next;
        // 断开
        p.next = null;
        return head;
    }
}
