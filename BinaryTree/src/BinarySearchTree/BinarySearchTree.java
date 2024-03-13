package BinarySearchTree;

/**
 * ClassName: BinarySearchTree
 * Description:
 *
 * @Author agility6
 * @Create 2024/3/13 16:01
 * @Version: 1.0
 */
public class BinarySearchTree {

    private Node root; // 根节点

    private int size; //  树的大小

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int element) {

        if (root == null) {
            root = new Node(element, null);
            size++;
            return;
        }

        // 1. 找到插入节点的父节点
        // 2. 判断插入左子树还是右子树

        Node node = root;
        Node insertParentNode = root;
        int cmp = 0; // 记录插入左子树还是右子树

        while (node != null) {
            insertParentNode = node;
            cmp = cmp(node.element, element);
            if (cmp == 1) { // node.element > element 左边
                node = node.left;
            } else { // node.element <= element 右边
                node = node.right;
            }
        }

        if (cmp == 1) insertParentNode.left = new Node(element, insertParentNode);
        else insertParentNode.right = new Node(element, insertParentNode);

        size++;
    }

    /**
     * 不做合法性判断，默认传入的值一定存在与树中
     * 删除节点使用后继节点作为替代
     * @param element
     */
    public void remove(int element) {

         Node node = findNodeByElement(element);

         // node度为2
         if (node.NodeDegreeTwo()) {
             Node p = successor(node);
             node.element = p.element; // 交换值
             node = p; // 处理代替节点
         }

         // 到这一步node节点的度只可能是0或者1
         Node r = node.left != null ? node.left : node.right;

         if (r != null) { // 度为1的情况
             // 下一个节点的父节点指向node.parent
             r.parent = node.parent;
             // node.parent父节点指向下一个节点（判断是左子树还是右子树）
             if (node.parent == null) { // 当node父节点为空的话，根节点
                root = r;
             } else if (node.parent.left == node) { // 是左子树
                 node.parent.left = r;
             } else {
                 node.parent.right = r;
             }
         } else if (node.parent == null) { // 删除的节点是叶子节点，且是root节点
            root = null;
         } else {
             // 判断连接左子树还是右子树即可
             if (node == node.parent.left) node.parent.left = null;
             else node.parent.right = null;
         }

    }

    /**
     * 不做合法性判断，默认传入的值一定存在与树中
     * @param element
     * @return
     */
    public Node findNodeByElement(int element) {

        Node node = root;
        if (node == null) return null;

        while (node.element != element) {
            if (cmp(node.element, element) == 1) node = node.left;
            else node = node.right;
        }

        return node;
    }

    /**
     * 寻找后继节点
     * @param element
     * @return
     */
    public Node successor(int element) {
        return successor(findNodeByElement(element));
    }

    /**
     * 比较规则
     * 遵循：左节点小于父节点，右节点大于父节点
     * @param e1
     * @param e2
     * @return
     */
    private int cmp(int e1, int e2) {
        return Integer.compare(e1, e2);
    }

    private Node successor(Node node) {

        Node p = node.right;

        // 存在右子树的情况
        if (p != null) { // node.right.left.left...
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // 不存在右子树的情况向上寻找
        while (node.parent != null && node != node.parent.left) {
            node = node.parent;
        }

        // 当node == node.parent.left这时候node.parent就是答案节点
        // 当node.parent == null无后继直接返回node.parent也是可以
        return node.parent;
    }
}
