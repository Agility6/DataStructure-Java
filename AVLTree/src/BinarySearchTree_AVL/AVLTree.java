package BinarySearchTree_AVL;

import java.util.*;


/**
 * ClassName: BinarySearchTree_AVL.AVLTree
 * Description:
 *
 * @Author agility6
 * @Create 2024/3/19 16:37
 * @Version: 1.0
 */
public class AVLTree {

    private AVLNode root;

    private int size;

    private final boolean flagAutoPrint;

    private printAVL printAVL = null;


    public AVLTree(boolean flagAutoPrint) {
        this.flagAutoPrint = flagAutoPrint;
        this.printAVL = new printAVL();
    }

    public void add(int element) {

        if (root == null) {
            root = new AVLNode(element, null);
            size++;

            afterAdd(root);
            if (flagAutoPrint) printAVL.print(root);
            return;
        }

        AVLNode node = root;
        AVLNode insertNodeParent = root;
        int cmp = 0;

        while (node != null) {
            cmp = cmp(node.element, element);
            insertNodeParent = node;
            if (cmp == 1) { // node.element > element 左边
                node = node.left;
            } else {
                node = node.right;
            }
        }

        AVLNode newCreateAVLNode = new AVLNode(element, insertNodeParent);
        if (cmp == 1) insertNodeParent.left = newCreateAVLNode;
        else insertNodeParent.right = newCreateAVLNode;

        afterAdd(newCreateAVLNode);
        if (flagAutoPrint) printAVL.print(root);
    }

    /**
     * 添加后进行平衡操作
     * @param node
     */
    private void afterAdd(AVLNode node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) { // 平衡，更新高度
                updateHeight(node);
            } else { // 恢复平衡
                rebalance(node);
                break;
            }

        }
    }

    /**
     *
     * @param g 高度最低的那个不平衡节点
     */
    private void rebalance(AVLNode g) {

        AVLNode parent = g.tallerChild(); // p节点g子树较高的节点
        AVLNode node = parent.tallerChild();

        if (parent.isLeftChild()) { // p节点是g节点的左边 L
            if (node.isLeftChild()) { // n节点是p节点的左边 L
                rotateRight(g); // LL
            } else { // n节点是p节点的右边 R
                // LR
                rotateLeft(parent);
                rotateRight(g);
            }
        } else { // p节点是g节点的右边 R
            if (node.isLeftChild()) { // n节点是p节点的左边 L
                // RL
                rotateRight(parent);
                rotateLeft(g);
            } else { // n节点是p节点的右边 R
                rotateLeft(g); // RR
            }
        }

    }

    /**
     * 右旋转
     * @param g
     */
    private void rotateRight(AVLNode g) {
        AVLNode parent = g.left;
        AVLNode child = parent.right;
        g.left = child; // g的左节点指向，parent节点的右节点
        parent.right = g;
        afterRotate(g, parent, child); // 统一处理其余指向
    }

    /**
     * 左旋转
     * @param g
     */
    private void rotateLeft(AVLNode g) {
        AVLNode parent = g.right;
        AVLNode child = parent.left;
        g.right = child;
        parent.left = g;
        afterRotate(g, parent, child);
    }

    /**
     * 更新g,parent,child指向
     * @param g
     * @param parent
     * @param child
     */
    private void afterRotate(AVLNode g, AVLNode parent, AVLNode child) {

        // 1. parent节点的父节点
        parent.parent = g.parent;

        // 2. 原先指向g的改为指向parent
        if (g.isLeftChild()) { // 原先g是在左子树
            g.parent.left = parent;
        } else if (g.isRightChild()) { // 原先g是在右子树
            g.parent.right = parent;
        } else { // 原先g是root
            root = parent;
        }

        // 3. 更新child的parent
        if (child != null) {
            child.parent = g;
        }

        // 4. 更新g的parent
        g.parent = parent;

        // 更新高度
        updateHeight(g);
        updateHeight(parent);

    }


    private void updateHeight(AVLNode node) {
        node.updateHeight();
    }


    private int cmp(int e1, int e2) {
        return Integer.compare(e1, e2);
    }

    private boolean isBalanced(AVLNode node) {
        return Math.abs(node.balanceFactor()) <= 1;
    }

}
