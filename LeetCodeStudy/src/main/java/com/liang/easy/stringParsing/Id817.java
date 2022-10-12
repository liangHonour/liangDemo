package com.liang.easy.stringParsing;

import java.util.HashSet;
import java.util.Set;

public class Id817 {
    public static void main(String[] args) {
    }

    static class ListNode {
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

    static class Solution  {
        public int numComponents(ListNode head, int[] nums) {
            int count = 0, left = nums.length;
            int[] set = new int[10000];
//        Set<Integer> set = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
//            set.add(nums[i]);
                set[nums[i]]++;
            }

            while (head != null) {
                if (set[head.val] != 0) {
                    while (head != null && set[head.val] != 0) {
                        set[head.val] = 0;
                        head = head.next;
                        left--;
                    }
                    count++;
                    if (left==0) break;
                }
                head = head.next;
            }
            return count;
        }
    }
}
