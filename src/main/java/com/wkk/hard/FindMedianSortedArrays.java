package com.wkk.hard;

import org.junit.Test;

public class FindMedianSortedArrays {

    @Test
    public void test6(){
        System.out.println(findMedianSortedArrays(new int[]{2}, new int[]{1, 3, 4}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return 0;
        }
        int totalNum = nums2.length + nums1.length;
        boolean evenFlag = totalNum % 2 == 0;
        int middleCount = totalNum / 2;
        int ul = -1, dl = -1;
        while (ul < nums1.length - 1 && dl < nums2.length - 1) {
            int currMidCount = middleCount / 2;
            if (middleCount == 1) {
                if (nums1[ul + 1] < nums2[dl + 1]) {
                    ul++;
                }else {
                    dl++;
                }
                middleCount--;
                break;
            }
            int nextUl = Math.min(ul + currMidCount, nums1.length - 1);
            int nextDl = Math.min(dl + currMidCount, nums2.length - 1);
            if (nums1[nextUl] <= nums2[nextDl]) {
                middleCount -= (nextUl - ul);
                ul = nextUl;
            } else {
                middleCount -= (nextDl - dl);
                dl = nextDl;
            }
        }

        double midResult;
        if (ul == nums1.length - 1 && middleCount != 0) {
            int middleIndex = dl + middleCount;
            int nextMidResult = nums2[dl + middleCount + 1];
            if (evenFlag) {
                midResult = middleIndex == -1 ? nums1[ul] : nums2[middleIndex];
                midResult = (midResult + nextMidResult) / 2.0;
            } else {
                midResult = nextMidResult;
            }
        } else if (dl == nums2.length - 1 && middleCount != 0) {
            int middleIndex = ul + middleCount;
            int nextMidResult = nums1[ul + middleCount + 1];
            if (evenFlag) {
                midResult = middleIndex == -1 ? nums2[dl] : nums1[middleIndex];
                midResult = (midResult + nextMidResult) / 2.0;
            } else {
                midResult = nextMidResult;
            }
        } else {
            int nextUVal = ul + 1 == nums1.length ? Integer.MAX_VALUE : nums1[ul + 1];
            int nextDVal = dl + 1 == nums2.length ? Integer.MAX_VALUE : nums2[dl + 1];
            int nextMidResult = Math.min(nextUVal, nextDVal);
            if (evenFlag) {
                midResult = Math.max(ul == -1 ? Integer.MIN_VALUE : nums1[ul], dl == -1 ? Integer.MIN_VALUE : nums2[dl]);
                midResult = (midResult + nextMidResult) / 2.0;
            } else {
                midResult = nextMidResult;
            }
        }
        return midResult;
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) {
            return getKth(nums2, start2, end2, nums1, start1, end1, k);
        }
        if (len1 == 0) {
            return nums2[start2 + k - 1];
        }

        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }
}
