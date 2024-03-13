package BinarySearchTree;

/**
 * ClassName: Node
 * Description:
 *
 * @Author agility6
 * @Create 2024/3/13 16:01
 * @Version: 1.0
 */
public class Node {

    public int element; // 值

    public Node left; // 左孩子

    public Node right; // 右孩子

    public Node parent; // 父节点

    /**
     * 必须传入当前节点的值以及父节点
     * @param element
     * @param parent
     */
    public Node(int element, Node parent) {
        this.element = element;
        this.parent = parent;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public boolean NodeDegreeTwo() {
        return this.left != null && this.right != null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "element=" + element +
                '}';
    }
}
