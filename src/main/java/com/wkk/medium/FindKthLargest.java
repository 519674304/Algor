package com.wkk.medium;

import java.util.*;

public class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
        }
        int result = -1;
        for (int i = 0; i < k; i++) {
            result = queue.poll();
        }
        return result;
    }
}
