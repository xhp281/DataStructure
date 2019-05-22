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
                7,4,2,1,3,5,9,8,11,10,12,
//                7,4,9,2,5
        };
        AVLTree<Integer> bst = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
//        bst.remove(1);
//        bst.remove(3);
//        bst.remove(10);
//        bst.remove(12);
        bst.remove(11);
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
