package com.xhp281.test;
import com.xhp281.tree.AVLTree;
import com.xhp281.tree.BST;
import com.xhp281.printer.BinaryTrees;

/**
 * User: FenDou
 * Date: 2019-05-20 10:58
 * Description:
 */

public class Main {// 创建比较器

    /**
     * 测试
     */
    static void avlTest(){
        Integer data[] = new Integer[] {
                100, 16, 48, 39, 21, 76, 51, 97, 4, 2, 35, 93, 66, 54, 71, 17
        };
        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
        }
        BinaryTrees.println(avl);

        System.out.println("开始删除操作");
        avl.remove(2);
        BinaryTrees.println(avl);
    }

    static void countTest(){
        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < 110; i++) {
            avl.add(i);
        }
        BinaryTrees.println(avl);

    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {

//        avlTest();
        countTest();
    }
}
