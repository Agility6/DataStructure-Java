package test;

import BinarySearchTree.BinarySearchTree;

/**
 * ClassName: TestBSTAddMethod
 * Description:
 *
 * @Author agility6
 * @Create 2024/3/13 16:17
 * @Version: 1.0
 */
public class TestBSTAddMethod {

    public static void main(String[] args) {

        int[] nums = {12, 13, 15, 16, 20, 23, 24, 25, 30, 40};
        BinarySearchTree bst = new BinarySearchTree();

        for (int j : nums) {
            bst.add(j);
        }
    }
}
