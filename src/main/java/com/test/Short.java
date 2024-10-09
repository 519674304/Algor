package com.test;

import java.util.*;

public class Short {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int length = 0; // 可用木板长度
        List<String> list = new ArrayList<>();
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            list.add(str);
        }
        String s0 = list.get(0);
        String[] s = s0.split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        length = n * m;
        String s1 = list.get(1);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (String aStr : s1.split(" ")) {
            priorityQueue.add(Integer.parseInt(aStr));
        }
        for (int i = 0; i < length; i++) {
            Integer poll = priorityQueue.poll();
            poll += 1;
            priorityQueue.add(poll);
        }
        System.out.println(priorityQueue.peek());
    }
}
