package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Group {
    private static int count = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        List<String> rs = new ArrayList<>();
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            rs.add(in.nextLine());
        }
        String s = rs.get(0);
        String[] split = s.split(",");

        int[] groupArray = new int[split.length];
        int capacity;
        try {
            for (int i = 0; i < split.length; i++) {
                try {
                    groupArray[i] = Integer.parseInt(split[i]);
                } catch (Exception e) {

                }
            }
            capacity = Integer.parseInt(rs.get(1));
        } catch (Exception e) {
            System.out.println(count);
            return;
        }
        getResult(groupArray, 0, capacity);
        System.out.println(count);
    }

    public static void getResult(int[] groupArray, int index, int capacity) {
        if (index >= groupArray.length) {
            return;
        }
        if (capacity < 0) {
            return;
        }
        if (capacity == 0) {
            count++;
            return;
        }
        getResult(groupArray, index + 1, capacity - groupArray[index]);
        getResult(groupArray, index, capacity - groupArray[index]);
        getResult(groupArray, index + 1, capacity);
    }
}
