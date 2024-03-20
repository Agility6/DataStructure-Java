package test;

import BinarySearchTree_AVL.AVLTree;

/**
 * ClassName: TestAVLAddMethod
 * Description:
 *
 * @Author agility6
 * @Create 2024/3/20 17:13
 * @Version: 1.0
 */
public class TestAVLAddMethod {

    public static void main(String[] args) {

        int[] values = {13, 14, 15, 12, 11, 17, 16, 8, 9, 1};

        AVLTree avlTree = new AVLTree(true);

        for (int value : values) {
            avlTree.add(value);
        }

    }
}
