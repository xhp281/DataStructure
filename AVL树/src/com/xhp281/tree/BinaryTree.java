package com.xhp281.tree;

import com.xhp281.printer.BinaryTreeInfo;
import java.util.LinkedList;
import java.util.Queue;

/**
 * User: FenDou
 * Date: 2019-05-21 19:03
 * Description: 二叉树基类
 */

public class BinaryTree <E> implements BinaryTreeInfo {

    /* 大小 */
    protected int size;
    /* 根节点 */
    protected Node<E> root;

    public int size(){
        return size;
    }

    public boolean isempry(){
        return size == 0;
    }

    public void  clear(){
        root = null;
        size = 0;
    }


    /**
     * 节点对象
     * @param <E>
     */
    protected static class Node<E>{
        public E element;
        public Node<E> rightNode;
        public Node<E> leftNode;
        public Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent  = parent;
        }

        /**
         * 是不是叶子节点
         * @return
         */
        public boolean isLeaf(){
            return leftNode == null && rightNode == null;
        }

        /**
         * 拥有两个节点
         * @return
         */
        public boolean hasTwoChildren(){
            return leftNode != null && rightNode != null;
        }
    }

    /**
     * 判断是不是二叉树
     * @return
     */
    public Boolean isComplete(){
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        // 是不是叶子节点
        boolean leaf = false;
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            // 如果不是叶子节点返回false
            if (leaf && !node.isLeaf()) return false;

            if (node.hasTwoChildren()){
                queue.offer(node.leftNode);
                queue.offer(node.rightNode);
            }else if (node.leftNode == null && node.rightNode != null){
                return false;
            }else{
                // 到此处的时候是叶子节点
                leaf = true;
            }
        }
        return  true;
    }

    /**
     * 创建节点
     * @param element
     * @param parent
     * @return
     */
    protected Node<E> createNode(E element,Node<E> parent){
        return new Node<>(element,parent);
    }

//======================================= 获取节点
    /**
     * 获取前驱节点
     * @param node
     * @return
     */
    public Node<E> predecessor(Node<E>node){
        if (node == null) return null;
        // 前驱节点在左子树中

        Node<E> p = node.leftNode;
        // 左子树不为空 node.left != null
        if (p != null){
            // 循环遍历右子树
            while (p.rightNode != null){
                p = p.rightNode;
            }
            return p;
        }

        // node.left = null && parent != null
        while (node.parent != null && node == node.parent.leftNode){
            node = node.parent;
        }

        // node.parent == null && node == node.parent.right
        return node.parent;
    }
    /**
     * 获取后驱节点
     * @param node
     * @return
     */
    public Node<E> successor(Node<E>node){
        if (node == null) return null;
        // 前驱节点在右子树中

        Node<E> p = node.rightNode;
        // 右子树不为空 node.right != null
        if (p != null){
            // 循环遍历左子树
            while (p.leftNode != null){
                p = p.leftNode;
            }
            return p;
        }

        // node.right = null && parent != null
        while (node.parent != null && node == node.parent.rightNode){
            node = node.parent;
        }

        // node.parent == null && node == node.parent.left
        return node.parent;
    }

//======================================= 使用访问器接口进行遍历
    /**
     * 访问器接口
     * @param <E>
     */
    public static interface Visitor<E>{
        void visit(E element);
    }

    /**
     * 层序遍历
     * @param visitor
     */
    public void levelOrder(Visitor<E> visitor){
        if (root == null|| visitor == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            visitor.visit(node.element);
            if (node.leftNode != null){
                queue.offer(node.leftNode);
            }
            if (node.rightNode != null){
                queue.offer(node.rightNode);
            }
        }
    }
    /**
     * 后续遍历遍历
     */
    public void postorderOrder(Visitor<E> visitor){
        postorderOrder(root,visitor);
    }

    private void postorderOrder(Node<E> node,Visitor<E> visitor){
        if (node == null || visitor == null) return;

        postorderOrder(node.leftNode,visitor);
        postorderOrder(node.rightNode,visitor);
        visitor.visit(node.element);
    }

    /**
     * 中序遍历
     * 二叉搜索树的遍历结果是可以控制升序还是降序
     * 升序：中序遍历左子树、根节点、中序遍历右子树
     * 降序：中序遍历右子树、根节点、中序遍历左子树
     */
    public void inorderOrder(Visitor<E> visitor){
        inorderOrder(root,visitor);
    }

    private void inorderOrder(Node<E> node,Visitor<E> visitor){
        if (node == null || visitor == null) return;

        inorderOrder(node.leftNode,visitor);
        visitor.visit(node.element);
        inorderOrder(node.rightNode,visitor);
    }

    /**
     * 前序遍历
     */
    public void preorderOrder(Visitor<E> visitor){
        preorderOrder(root,visitor);
    }

    private void preorderOrder(Node<E> node,Visitor<E> visitor){
        if (node == null || visitor == null) return;

        visitor.visit(node.element);
        preorderOrder(node.leftNode,visitor);
        preorderOrder(node.rightNode,visitor);
    }

// ===================================== 计算二叉树高度
    /**
     * 方式1
     * 获取树的高度，非递归方式
     * @return
     */
    public int height(){
        if (root == null) return 0;

        // 树的高度
        int height    = 0;
        // 每层的数量
        int levelSize = 1;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            // 访问的时候数量减少
            levelSize--;

            if (node.leftNode != null){
                queue.offer(node.leftNode);
            }
            if (node.rightNode != null){
                queue.offer(node.rightNode);
            }

            // 遍历完一层，将要遍历下一层的时候获取下一层的数量
            if (levelSize == 0){
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }
    /**
     * 方式2
     * @return
     */
    public int height2(){
        return  height(root);
    }

    /**
     * 递归方式获取高度
     * @param node
     * @return
     */
    public int height(Node<E> node){
        if (node == null) return 0;
        return  1 + Math.max(height(node.leftNode),height(node.rightNode));
    }

// ================================= 二叉树打印接口设置
    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).leftNode;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).rightNode;
    }

    @Override
    public Object string(Object node) {
        // 获取父节点内容
        Node<E> currNode =  (Node<E>)node;
        String parentString = "null";
        if (currNode.parent != null){
            parentString = currNode.parent.element.toString();
        }
        return currNode.element + "(" + parentString + ")";
    }
}
