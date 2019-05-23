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
     * 删除测试
     */
    static void removeTest(){
        Integer data[] = new Integer[] {
                100, 16, 48, 39, 21, 76, 51, 97, 4, 2, 35, 93, 66, 54, 71, 17
        };
        AVLTree<Integer> bst = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);

    }


    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {

        removeTest();
    }
}
