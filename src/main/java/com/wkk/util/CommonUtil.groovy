package com.wkk.util

class CommonUtil {

  public static List<List<String>> arrayToList(String[][] strs) {
    List<List<String>> resultList = new ArrayList<>();
    for (String[] array : strs) {
      resultList.add(new ArrayList<>(Arrays.asList(array)));
    }
    resultList
  }

  /**
   * 将整型数组转换为链表
   * @param arr 输入的整型数组
   * @return 链表的头结点
   */
  public static ListNode arrayToList(int[] arr) {
    if (arr == null || arr.length == 0) {
      return null;
    }

    ListNode head = new ListNode(arr[0]);
    ListNode current = head;

    for (int i = 1; i < arr.length; i++) {
      ListNode newNode = new ListNode(arr[i]);
      current.next = newNode;
      current = newNode;
    }

    return head;
  }

  /**
   * 打印链表中的所有值
   * @param head 链表的头结点
   */
  public static void printList(ListNode head) {
    ListNode current = head;
    while (current != null) {
      System.out.print(current.val + " ");
      current = current.next;
    }
    System.out.println(); // 换行
  }
}
