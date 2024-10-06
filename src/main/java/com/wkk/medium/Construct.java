package com.wkk.medium;


import org.junit.Test;

public class Construct {

    @Test
    public void test7(){
        int[][] grid = {{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0}};
        Node construct = construct(grid);
        System.out.println(construct);
    }

    public Node construct(int[][] grid) {
        return helper(grid, 0, grid.length - 1, 0, grid[0].length -1);
    }

    public Node helper(int[][] grid, int ru, int rd, int cl, int cr) {
        if (ru == rd && cl == cr) {
            Node node = new Node();
            node.isLeaf = true;
            node.val = grid[ru][cl] == 1;
            return node;
        }
        Node root = new Node();
        root.topLeft = helper(grid, ru, (ru + rd) / 2, cl, (cl + cr) / 2);
        root.topRight = helper(grid, ru, (ru + rd) / 2, (cl + cr) / 2 + 1, cr);
        root.bottomLeft = helper(grid, (ru + rd) / 2 + 1, rd, cl, (cl + cr) / 2);
        root.bottomRight = helper(grid, (ru + rd) / 2 + 1, rd, (cl + cr) / 2 + 1, cr);

        boolean leafFlag = root.topLeft.isLeaf && root.topRight.isLeaf && root.bottomLeft.isLeaf && root.bottomRight.isLeaf;
        boolean flag1 = root.topLeft.val && root.topRight.val && root.bottomLeft.val && root.bottomRight.val;
        boolean flag2 = !root.topLeft.val && !root.topRight.val && !root.bottomLeft.val && !root.bottomRight.val;
        if (leafFlag) {
            if (flag1 || flag2) {
                root.isLeaf = true;
                root.val = flag1;
                root.topLeft = null;
                root.topRight = null;
                root.bottomLeft = null;
                root.bottomRight = null;
            }
        }
        return root;
    }

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;
    }
}
