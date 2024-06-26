import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 222. 完全二叉树的节点个数
 */
public class Solution222 {
//    通用解法

    /**
     * 递归
     */
    public int countNodes1(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * 迭代
     */
    public int countNodes2(TreeNode root) {
        int count = 0;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        if (root != null) {
            stack.push(root);
        }

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);

            count++;
        }
        return count;
    }

    /**
     * 递归的优化版本
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        TreeNode l = root, r = root;
        int lDepth = 0, rDepth = 0;

        while (l.left != null) {
            l = l.left;
            lDepth++;
        }
        while (r.right != null) {
            r = r.right;
            rDepth++;
        }

        if (lDepth == rDepth) {
            return (2 << lDepth) - 1;
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }


    public int countNodes3(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = 0, rightDepth = 0;
        TreeNode left = root, right = root;

        while (left != null) {
            leftDepth++;
            left = left.left;
        }

        while (right != null) {
            rightDepth++;
            right = right.right;
        }

        if (leftDepth == rightDepth) {
            return (2 << leftDepth) - 1;
        } else {
            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }

    public static void main(String[] args) {
        int res = new Solution222().countNodes3(new TreeNode(1));
        System.out.println(res);

    }
}
