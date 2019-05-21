package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

public class _226_翻转二叉树 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

//    /**
//     * 前序遍历
//     * @param root
//     * @return
//     */
//    public TreeNode invertTree(TreeNode root) {
//        if (root == null) return root;
//
//        TreeNode tmp = root.left;
//        root.left    = root.right;
//        root.right   = tmp;
//
//        invertTree(root.left);
//        invertTree(root.right);
//
//        return  root;
//    }

//    /**
//     *  后序遍历
//     * @param root
//     * @return
//     */
//    public TreeNode invertTree(TreeNode root) {
//        if (root == null) return root;
//
//        invertTree(root.left);
//        invertTree(root.right);
//
//        TreeNode tmp = root.left;
//        root.left    = root.right;
//        root.right   = tmp;
//
//        return  root;
//    }
//
//    /**
//     *  中序遍历
//     * @param root
//     * @return
//     */
//    public TreeNode invertTree(TreeNode root) {
//        if (root == null) return root;
//
//        invertTree(root.left);
//
//        TreeNode tmp = root.left;
//        root.left    = root.right;
//        root.right   = tmp;
//
//        invertTree(root.left);
//
//        return  root;
//    }

    /**
     *  层序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root){
        // 层序遍历
        if (root == null) return root;

        Queue<TreeNode> quque = new LinkedList<>();
        quque.offer(root);

        while (!quque.isEmpty()){
            TreeNode node = quque.poll();

            TreeNode tmp  = node.left;
            node.left     = node.right;
            node.right    = tmp;

            if (node.left != null){
                quque.offer(node.left);
            }
            if (node.right != null){
                quque.offer(node.right);
            }
        }

        return  root;
    }

}
