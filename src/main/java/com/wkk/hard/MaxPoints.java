package com.wkk.hard;

import org.junit.Test;

import java.util.*;

public class MaxPoints {

    @Test
    public void test9() {
        int[][] points = {{7, 3}, {19, 19}, {-16, 3}, {13, 17}, {-18, 1}, {-18, -17}, {13, -3}, {3, 7}, {-11, 12}, {7, 19}, {19, -12}, {20, -18}, {-16, -15}, {-10, -15}, {-16, -18}, {-14, -1}, {18, 10}, {-13, 8}, {7, -5}, {-4, -9}, {-11, 2}, {-9, -9}, {-5, -16}, {10, 14}, {-3, 4}, {1, -20}, {2, 16}, {0, 14}, {-14, 5}, {15, -11}, {3, 11}, {11, -10}, {-1, -7}, {16, 7}, {1, -11}, {-8, -3}, {1, -6}, {19, 7}, {3, 6}, {-1, -2}, {7, -3}, {-6, -8}, {7, 1}, {-15, 12}, {-17, 9}, {19, -9}, {1, 0}, {9, -10}, {6, 20}, {-12, -4}, {-16, -17}, {14, 3}, {0, -1}, {-18, 9}, {-15, 15}, {-3, -15}, {-5, 20}, {15, -14}, {9, -17}, {10, -14}, {-7, -11}, {14, 9}, {1, -1}, {15, 12}, {-5, -1}, {-17, -5}, {15, -2}, {-12, 11}, {19, -18}, {8, 7}, {-5, -3}, {-17, -1}, {-18, 13}, {15, -3}, {4, 18}, {-14, -15}, {15, 8}, {-18, -12}, {-15, 19}, {-9, 16}, {-9, 14}, {-12, -14}, {-2, -20}, {-3, -13}, {10, -7}, {-2, -10}, {9, 10}, {-1, 7}, {-17, -6}, {-15, 20}, {5, -17}, {6, -6}, {-11, -8}};
        System.out.println(maxPoints(points));
    }

    private Map<String, Set<String>> map = new HashMap<>();

    public int maxPoints(int[][] points) {
        helper(points, 0);
        int max = 1;
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            max = Math.max(max, entry.getValue().size());
        }
        return max;
    }

    public void helper(int[][] points, int s) {
        if (s == points.length - 1) {
            return;
        }
        for (int i = s + 1; i < points.length; i++) {
            String key = getKey(points, s, i);
            Set<String> v = map.computeIfAbsent(key, mk -> new HashSet<>());
            v.add(getKey(points[s][0], points[s][1]));
            v.add(getKey(points[i][0], points[i][1]));
        }
        helper(points, s + 1);
    }

    public String getKey(int x, int y) {
        return x + "+" + y;
    }

    public String getKey(int[][] points, int s, int i) {
        double xDis = points[s][0] - (double) points[i][0];
        int yDis = points[s][1] - points[i][1];
        if (xDis == 0) {
            return "x" + "+" + points[s][0];
        }
        if (yDis == 0) {
            return "y" + "+" + points[s][1];
        }

        double k = yDis / (xDis);
        double c = points[s][1] - (k * points[s][0]);
        return k + "+" + c;
    }
}
