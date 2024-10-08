package com.wkk.medium;

import org.junit.Test;

import java.util.*;

public class KSmallestPairs {
    @Test
    public void test7(){
        List<List<Integer>> lists = kSmallestPairs(new int[]{1,2,4,5,6}, new int[]{3,5,7,9}, 3);
        lists.forEach(v -> System.out.println(Arrays.toString(v.toArray())));
    }
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (k == 0) {
            return res;
        }
        Queue<int[]> queue = new PriorityQueue<>((v1, v2) -> nums1[v1[0]] + nums2[v1[1]] - nums1[v2[0]] - nums2[v2[1]]);
        queue.add(new int[]{0, 0});
        Set<String> set = new HashSet<>();
        while (k > 0) {
            int[] poll = queue.poll();
            if (!set.contains((poll[0] + 1) + "_" + poll[1])) {
                if (poll[0] + 1 < nums1.length) {
                    set.add((poll[0] + 1) + "_" + poll[1]);
                    queue.add(new int[]{poll[0] + 1, poll[1]});
                }
            }
            if (!set.contains(poll[0] + "_" + (poll[1] + 1))) {
                if (poll[1] + 1 < nums2.length) {
                    set.add(poll[0] + "_" + (poll[1] + 1));
                    queue.add(new int[]{poll[0], poll[1] + 1});
                }
            }
            res.add(Arrays.asList(nums1[poll[0]], nums2[poll[1]]));
            k--;
        }
        return res;
    }

    class Solution {
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2)-> nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]]);
            List<List<Integer>> ans = new ArrayList<>();
            int m = nums1.length;
            int n = nums2.length;
            for (int i = 0; i < Math.min(m, k); i++) {
                pq.offer(new int[]{i,0});
            }
            while (k-- > 0 && !pq.isEmpty()) {
                int[] idxPair = pq.poll();
                List<Integer> list = new ArrayList<>();
                list.add(nums1[idxPair[0]]);
                list.add(nums2[idxPair[1]]);
                ans.add(list);
                if (idxPair[1] + 1 < n) {
                    pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
                }
            }

            return ans;
        }
    }

}
