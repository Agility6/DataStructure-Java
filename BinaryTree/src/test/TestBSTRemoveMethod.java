package test;

import BinarySearchTree.BinarySearchTree;

/**
 * ClassName: TestBSTRemoveMethod
 * Description:
 *
 * @Author agility6
 * @Create 2024/3/13 18:46
 * @Version: 1.0
 */
public class TestBSTRemoveMethod {
    public static void main(String[] args) {

        int[] nums = {20, 15, 25, 13, 16, 24, 30, 12 ,23, 40};
        BinarySearchTree bst = new BinarySearchTree();

        for (int j : nums) {
            bst.add(j);
        }

        bst.remove(20);
        System.out.println(bst);
    }
}
