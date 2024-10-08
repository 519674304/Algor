package com.wkk.hard;

import java.util.*;

public class MedianFinder {
    private List<Integer> list = new LinkedList<>();
    public MedianFinder() {

    }

    public void addNum(int num) {
        if (list.isEmpty()) {
            list.add(num);
            return;
        }
        int pos = findPos(num);
        if (pos == list.size() - 1) {
            list.add(num);
        }else {
            list.add(pos + 1, num);
        }
    }

    public double findMedian() {
        int mid = (list.size() - 1) / 2;
        int midNext = (list.size()) / 2;
        return (list.get(midNext) + list.get(mid)) / 2.0;
    }


    public int findPos(int num) {
        int l = 0, r = list.size() - 1;
        while (l < r - 1) {
            int middle = (l + r) / 2;
            if (list.get(middle) == num) {
                return middle;
            } else if (list.get(middle) > num) {
                r = middle;
            }else {
                l = middle;
            }
        }
        return list.get(r) < num ? r : list.get(l) > num ? -1 : l;
    }
}

class MedianFinderOffice {
    PriorityQueue<Integer> queMin;
    PriorityQueue<Integer> queMax;

    public MedianFinderOffice() {
        queMin = new PriorityQueue<>((a, b) -> (b - a));
        queMax = new PriorityQueue<>(Comparator.comparingInt(a -> a));
    }

    public void addNum(int num) {
        if (queMin.isEmpty() || num <= queMin.peek()) {
            queMin.offer(num);
            if (queMax.size() + 1 < queMin.size()) {
                queMax.offer(queMin.poll());
            }
        } else {
            queMax.offer(num);
            if (queMax.size() > queMin.size()) {
                queMin.offer(queMax.poll());
            }
        }
    }

    public double findMedian() {
        if (queMin.size() > queMax.size()) {
            return queMin.peek();
        }
        return (queMin.peek() + queMax.peek()) / 2.0;
    }
}
