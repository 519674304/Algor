package com.wkk.medium;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LongestConsecutive {
    @Test
    public void test9(){
        System.out.println(longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> consecutiveMap = new HashMap<>();
        Map<Integer, Integer> parentMap = new HashMap<>();
        int length = nums.length;
        int i = 0;
        while (i < length) {
            Integer oldVal = consecutiveMap.get(nums[i]);
            if (Objects.nonNull(oldVal)) {
                int temp = nums[length - 1];
                nums[length - 1] = nums[i];
                nums[i] = temp;
                length--;
            }else {
                consecutiveMap.put(nums[i], 1);
                parentMap.put(nums[i], nums[i]);
                i++;
            }
        }
        int max = 1;
        for (int j = 0; j < length; j++) {
            int findVal = nums[j];
            List<Integer> changeList = new ArrayList<>();
            int childVal = findVal;
            if (parentMap.get(findVal) == findVal) {
                findVal--;
            }
            if (Objects.nonNull(parentMap.get(findVal))) {
                while (findVal != parentMap.get(findVal)) {
                    changeList.add(findVal);
                    findVal = parentMap.get(findVal);
                }
            }
            if (Objects.nonNull(consecutiveMap.get(findVal))) {
                int conLen = consecutiveMap.get(findVal) + consecutiveMap.get(childVal);
                consecutiveMap.put(findVal, conLen);
                max = Math.max(conLen, max);
                parentMap.put(childVal, findVal);
            }
            for (Integer l : changeList) {
                parentMap.put(l, findVal);
            }
        }
        return max;
    }

    public int longestConsecutive1(int[] nums) {
        Set<Integer> num_set = new HashSet<>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
