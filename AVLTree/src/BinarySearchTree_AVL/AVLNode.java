package BinarySearchTree_AVL;

/**
 * ClassName: BinarySearchTree_AVL.AVLNode
 * Description:
 *
 * @Author agility6
 * @Create 2024/3/19 16:38
 * @Version: 1.0
 */
public class AVLNode {

    public int element; // 值

    public AVLNode left; // 左孩子

    public AVLNode right; // 右孩子

    public AVLNode parent; // 父节点

    public int height = 1; // 记录节点的高度

    /**
     * 必须传入当前节点的值以及父节点
     * @param element
     * @param parent
     */
    public AVLNode(int element, AVLNode parent) {
        this.element = element;
        this.parent = parent;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public boolean NodeDegreeTwo() {
        return this.left != null && this.right != null;
    }

    /**
     * 计算该节点的平衡因子
     * @param
     * @return
     */
    public int balanceFactor() {
        int leftHeight = left == null ? 0 : left.height;
        int rightHeight = right == null ? 0 : right.height;
        return leftHeight - rightHeight;
    }

    /**
     * 返回子树高度较高的节点
     * @return
     */
    public AVLNode tallerChild() {
        int leftHeight = left == null ? 0 : left.height;
        int rightHeight = right == null ? 0 : right.height;
        if (leftHeight > rightHeight) return left;
        if (rightHeight > leftHeight) return right;
        return isLeftChild() ? left : right;
    }

    public void updateHeight() {
        int leftHeight = left == null ? 0 : left.height;
        int rightHeight = right == null ? 0 : right.height;
        height = Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 当前节点是否是左节点
     * @return
     */
    public boolean isLeftChild() {
        return parent != null && this == parent.left;
    }

    /**
     * 当前节点是否是右节点
     * @return
     */
    public boolean isRightChild() {
        return parent != null && this == parent.right;
    }


    @Override
    public String toString() {
        return "BinarySearchTree_AVL.AVLNode{" +
                "element=" + element +
                '}';
    }
}
