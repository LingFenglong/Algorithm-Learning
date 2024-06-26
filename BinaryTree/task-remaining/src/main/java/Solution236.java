import java.util.ArrayList;
import java.util.List;

/**
 * 236. 二叉树的最近公共祖先
 * 注意：一个节点也可以是它自己的祖先
 */
public class Solution236 {
    List<List<TreeNode>> paths = new ArrayList<>();
    List<TreeNode> path = new ArrayList<>();

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        getPathOf(root, p, q);
        List<TreeNode> path1 = paths.get(0);
        List<TreeNode> path2 = paths.get(1);

        int index = 0;
        TreeNode res = null;
        while (index < path1.size() && index < path2.size() && path1.get(index) == path2.get(index)) {
            res = path1.get(index);
            index++;
        }
        return res;
    }

    private void getPathOf(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return;

        path.add(root);

        if (root == p || root == q) {
            paths.add(new ArrayList<>(path));
        }

        getPathOf(root.left, p, q);
        getPathOf(root.right, p, q);

        path.remove(path.size() - 1);
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) {
            // 自己是自己的祖先
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        } else {
            return null;
        }
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root; // return value = null or p or q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        TreeNode node5 = root.left;
        TreeNode node4 = root.left.right.right;
        TreeNode node1 = root.right;

        TreeNode res = new Solution236().lowestCommonAncestor2(root, node5, node1);
        System.out.println(res);
    }
}
