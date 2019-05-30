package com.xhp281.test;
import com.xhp281.tree.AVLTree;
import com.xhp281.tree.BST;
import com.xhp281.printer.BinaryTrees;
import com.xhp281.tree.BinaryTree;
import com.xhp281.tree.BinaryTree.Visitor;

/**
 * User: FenDou
 * Date: 2019-05-20 10:58
 * Description:
 */

public class Main {// 创建比较器

    /**
     * 测试
     */
    static void test1(){
        Integer data[] = new Integer[] {
                9, 80, 33, 5, 67, 17, 11
        };
//        RBTree<Integer> rbTree = new RBTree<>();
//        for (int i = 0; i < data.length; i++) {
//            rbTree.add(data[i]);
//        }
//        BinaryTrees.println(rbTree);
        AVLTree<Integer> rbTree = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            rbTree.add(data[i]);
        }
        BinaryTrees.println(rbTree);
        System.out.println(rbTree.isComplete());
    }

    static void test2(){
        Integer data[] = new Integer[] {
                9, 80, 33, 5, 67, 17, 11
        };
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);

        // 前序遍历
        System.out.println("** 前序遍历 **");
        bst.preorderOrder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(" " + element);
                return element == 80 ? true : false;
            }
        });

        System.out.println();
        System.out.println("** 中序遍历 **");
        bst.inorderOrder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(" " + element);
                return element == 17 ? true : false;
            }
        });

        System.out.println();
        System.out.println("** 后序遍历 **");
        bst.postorderOrder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(" " + element);
                return element == 67 ? true : false;
            }
        });

        System.out.println();
        System.out.println("** 层序遍历 **");
        bst.levelOrder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(" " + element);
                return element == 67 ? true : false;
            }
        });
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        test2();
    }
}
