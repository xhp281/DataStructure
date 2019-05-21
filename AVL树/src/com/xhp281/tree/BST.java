package com.xhp281.tree;
import java.util.Comparator;

/**
 * User: FenDou
 * Date: 2019-05-17 13:53
 * Description: 二叉搜索树
 */

public class BST<E> extends BinaryTree<E> {

    private Comparator<E> comparator;

    /**
     * 初始化方式
     */
    public BST(){
        this(null);
    }
    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * 添加
     * @param element
     */
    public void add(E element){
        checkElementIsNull(element);

        // 添加第一个节点
        if (root == null){
            root = new Node<>(element,null);
            size++;
            return;
        }

        // 父节点
        Node<E> parent = root;
        // 添加其他节点，首先找到父节点
        Node<E> node = root;
        // 比较结果
        int cmp = 0;
        while (node != null){
            // 比较大小
            cmp = compare(element,node.element);
            // 获取父节点
            parent = node;
            // 新元素 > 父节点：去右边查找
            if (cmp > 0){
                node = node.rightNode;
                // 新元素< 父节点：去左边查找
            }else if (cmp < 0){
                node = node.leftNode;
            }else{
                // 相等直接返回
                node.element = element;
                return;
            }
        }
        // 判断是设置为左子树还是右子树
        // 获取新节点
        Node<E> newNode = new Node<>(element,parent);
        if (cmp > 0){ // 右
            parent.rightNode = newNode;
        }else{
            parent.leftNode  = newNode;
        }
        size++;
    }

    /**
     * 是否包含
     * @param element
     * @return
     */
    public boolean contains(E element){
        return node(element) != null;
    }

    /**
     * 检测是不是为空
     * @param element
     */
    private void checkElementIsNull(E element){
        if (element == null) {
             throw new IllegalArgumentException("element must be not null");
        }
    }

    /**
     * 比较大小
     * @param e1
     * @param e2
     * @return 返回值等于0：e1 = e2 返回值大于0：e1 > e2 返回值小于0: e1 < e2
     */
    private int compare(E e1,E e2){
        // 有判断条件的时候使用判断条件
        if (comparator != null){
            return  comparator.compare(e1, e2);
        }
        return ((Comparable<E>)e1).compareTo(e2);
    }


// ================================= 删除操作
    public void remove(E element){
        remove(node(element));
    }

    /**
     * 删除节点
     * @param node
     */
    private void remove(Node<E> node){
        if (node == null) return;
        size--;
        // 度数为2的节点
        if (node.hasTwoChildren()){
            // 找到后继节点
            Node<E> sNode = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = sNode.element;
            // 删除后继节点
            node = sNode;
        }

        // 删除node节点，度为1或者0
        Node<E> replaceElement = node.leftNode != null ? node.leftNode : node.rightNode;
        // node 是度数为1的节点
        if (replaceElement != null){
            // 更改父节点
            replaceElement.parent = node.parent;
            // 更改 parent 的left,right指向
            if (node.parent == null){
                root = replaceElement;
            }else if (node == node.parent.leftNode){
                node.parent.leftNode  = replaceElement;
            }else{
                node.parent.rightNode = replaceElement;
            }
        }else if(node.parent == null){
            // node 是叶子节点并且是根节点
            root = null;
        }else{
            // node 叶子节点但不是根节点
            if (node == node.parent.leftNode){
                node.parent.leftNode  = null;
            }else{
                node.parent.rightNode = null;
            }
        }

    }

    /**
     * 根据内容获取节点
     * @param element
     * @return
     */
    private Node<E> node(E element){
        Node<E> node = root;
        while (node != null){
            int cmp = compare(element,node.element);
            // 相等的时候返回
            if (cmp == 0) return node;
            // 输入值大于当前节点，从右面找
            if (cmp > 0 ){
                node = node.rightNode;
            }else{
                // 输入值小于当前节点，从左面开始找
                node = node.leftNode;
            }
        }
        return null;
    }

//    // ================================== 打印方法
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        toString(root,sb,"");
//        return sb.toString();
//    }
//    private void toString(Node<E> node,StringBuilder sb,String prefix){
//        if (node == null) return;
//        sb.append(prefix).append(node.element).append("\n");
//        toString(node.leftNode,sb,prefix + "L --> ");
//        toString(node.rightNode,sb,prefix + "R --> ");
//    }


}
