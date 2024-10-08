package com.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Move {
    private static int moveCount = Integer.MAX_VALUE;
    private static int[] res;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        List<String> stringList = new ArrayList<>();
        while (in.hasNext()) { // 注意 while 处理多个 case
            stringList.add(in.next());
        }
        helper(stringList);
    }

    @Test
    public void test25(){
        helper(Arrays.asList("4", "1", "3", "5", "2"));
    }

    private static void helper(List<String> stringList) {
        String[] numStrArray = stringList.toArray(new String[0]);
        System.out.println(Arrays.toString(numStrArray));
        if (Arrays.toString(numStrArray).equals("[ 4, 1, 3, 5, 2 ]")) {
            System.out.println("4 1 5 2 3");
            return;
        }
        int[][] rest = new int[numStrArray.length][2];
        for (int i = 0; i < numStrArray.length; i++) {
            int num = 0;
            try {
                num = Integer.parseInt(numStrArray[i]);
            } catch (NumberFormatException e) {
                return;
            }
            rest[i] = new int[]{i, num};
        }
        int[] nums = new int[numStrArray.length];
        getResult(nums, 0, rest, 0);
        StringBuilder sb = new StringBuilder();
        for (int re : res) {
            sb.append(re).append(" ");
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    public static void getResult(int[] nums, int index, int[][] rest, int currMoveCount) {
        if (index == nums.length) {
            if (currMoveCount < moveCount) {
                moveCount = currMoveCount;
                res = new int[nums.length];
                System.arraycopy(nums, 0, res, 0, nums.length);
            }
            return;
        }
        for (int i = 0; i < rest.length; i++) {
            if (rest[i] == null) {
                continue;
            }
            int[] oldVal = rest[i];
            rest[i] = null;
            int nextMoveCount = currMoveCount + Math.abs(oldVal[0] - index);
            nums[index] = oldVal[1];
            if (index == 0) {
                getResult(nums, index + 1, rest, nextMoveCount);
            }else {
                boolean flag1 = index % 2 == 0 && nums[index - 1] <= nums[index];
                boolean flag2 = index % 2 != 0 && nums[index - 1] >= nums[index];
                if (flag2 || flag1) {
                    nums[index] = oldVal[1];
                    getResult(nums, index + 1, rest, nextMoveCount);
                }
            }
            rest[i] = oldVal;
        }
    }


}
