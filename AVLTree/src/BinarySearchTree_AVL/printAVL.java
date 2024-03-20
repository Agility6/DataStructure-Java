package BinarySearchTree_AVL;

import java.util.*;

/**
 * ClassName: printAVL
 * Description:
 *
 * @Author agility6
 * @Create 2024/3/20 18:57
 * @Version: 1.0
 */
public class printAVL {

    /**
     * 传入根节点
     * @param root
     */
    public void print(AVLNode root) {

        System.out.println("------------------------------");

        Stack<AVLNode> stack = new Stack<>();
        AVLNode current = root;
        // 获取每一个节点在第几层
        Map<AVLNode, Integer> nodeLevel = getAllNodeLevel(root);


        // 中序遍历
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();

            // 获取当前元素在第几层，对应的就是与根节点的距离
            int blank = nodeLevel.get(current);
            // 获取当前节点的父节点离根根节点的距离
            int parentBlank = nodeLevel.getOrDefault(current.parent, 0);
            for (int i = 1; i <= blank - 1; i++) {
                if (parentBlank == i) System.out.print("|");
                if (parentBlank <= i)System.out.print("—————");
                else System.out.print("     ");
            }
            System.out.println(current.element);
            current = current.right;
        }
    }

    private Map<AVLNode, Integer> getAllNodeLevel(AVLNode root) {
        Map<AVLNode, Integer> ans = new HashMap<>();

        Queue<AVLNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelCnt = 1; // 当前层有多少元素
        int level = 1; // 当前在第几层

        while (!queue.isEmpty()) {
            AVLNode node = queue.poll();

            // 设置每一个节点位于第几层
            ans.put(node, level);

            levelCnt--;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);

            if (levelCnt == 0) {
                levelCnt = queue.size();
                level++;
            }
        }

        return ans;
    }
}
