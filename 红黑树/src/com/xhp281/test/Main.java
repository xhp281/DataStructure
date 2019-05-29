package com.xhp281.test;
import com.xhp281.tree.AVLTree;
import com.xhp281.tree.BST;
import com.xhp281.printer.BinaryTrees;
import com.xhp281.tree.RBTree;

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
        RBTree<Integer> rbTree = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rbTree.add(data[i]);
            System.out.println("----------------------------");
            System.out.println("【"+ data[i] +"】");
            BinaryTrees.println(rbTree);
        }

    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        test1();
    }
}
