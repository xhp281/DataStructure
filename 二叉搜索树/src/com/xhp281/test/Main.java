package com.xhp281.test;
import com.xhp281.Person;
import java.util.Comparator;
import com.xhp281.BinarySearchTree;
import com.xhp281.file.Files;
import com.xhp281.printer.BinaryTrees;


/**
 * User: FenDou
 * Date: 2019-05-20 10:58
 * Description:
 */

public class Main {// 创建比较器
    private static class PersonComparator implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }

    private static class PersonComparator2 implements Comparator<Person>{
        @Override
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }
    }

    /**
     * 利用创建比较器方式比较
     */
    static void test1(){
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };
        BinarySearchTree<Person> bst1 = new BinarySearchTree<>(new PersonComparator());
        for (int i = 0; i < data.length; i++) {
            bst1.add(new Person(data[i]));
        }
        BinaryTrees.println(bst1);


        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new PersonComparator2());
        for (int i = 0; i < data.length; i++) {
            bst2.add(new Person(data[i]));
        }
        BinaryTrees.println(bst2);
    }

    /**
     * 匿名函数方式比较
     */
    static void test2(){
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };
        BinarySearchTree<Person> bst = new BinarySearchTree<>(new Comparator<Person>() {
            @Override
            public int compare(Person e1, Person e2) {
                return e2.getAge() - e1.getAge();
            }
        });
        for (int i = 0; i < data.length; i++) {
            bst.add(new Person(data[i]));
        }
       BinaryTrees.println(bst);
    }

    /**
     * 默认函数方式比较
     */
    static void test3(){
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };
        BinarySearchTree<Person> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(new Person(data[i]));
        }
        BinaryTrees.println(bst);
    }
    /**
     * 打印数字
     */
    static void test4() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < 30; i++) {
            bst.add((int)(Math.random() * 100));
        }
        BinaryTrees.println(bst);
        // 写入文件操作
//        String str = BinaryTrees.printString(bst);
//        Files.writeToFile("/Users/FenDou/Desktop/fileTest/1.txt",str);
    }
    /**
     * 覆盖方式测试
     */
    static void test5(){
        BinarySearchTree<Person> bst = new BinarySearchTree<>();
        bst.add(new Person(10, "p1"));
        bst.add(new Person(9, "p2"));
        bst.add(new Person(30, "p3"));
        bst.add(new Person(30, "p4"));

        BinaryTrees.println(bst);
    }

    /**
     * 遍历测试
     */
    static void traversalTest(){
        Integer data[] = new Integer[] {
                7,4,2,1,3,5,9,8,11,10,12
        };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);
        bst.preorderTraversal();   // 前序遍历
        bst.preorderOrder(new BinarySearchTree.Visitor<Integer>() {
            public void visit(Integer element) {
              System.out.print("_" + element + "_ ");
            }
        });
//        bst.inorderTraversal();    // 中序遍历
//        bst.inorderOrder(new BinarySearchTree.Visitor<Integer>() {
//            public void visit(Integer element) {
//              System.out.print("_" + element + "_ ");
//            }
//        });
//        bst.postorderTraversal();  // 后续遍历
//        bst.postorderOrder(new BinarySearchTree.Visitor<Integer>() {
//           public void visit(Integer element) {
//             System.out.print("_" + element + "_ ");
//           }
//        });
//        bst.levelOrderTraversal(); // 层序遍历
//        bst.levelOrder(new BinarySearchTree.Visitor<Integer>() {
//            public void visit(Integer element) {
//               System.out.print("_" + element + "_ ");
//            }
//        });
    }

    /**
     * 遍历测试
     */
    static void toStringTest(){
        Integer data[] = new Integer[] {
//                7,4,2,1,3,5,9,8,11,10,12,
        7,4,9,2,5
        };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
        System.out.println(bst);
        System.out.println("高度: " + bst.height());
        System.out.println("是不是完全二叉树：" + bst.isComplete());

//        System.out.println(bst.predecessor(2));
    }

    /**
     * 删除测试
     */
    static void removeTest(){
        Integer data[] = new Integer[] {
                7,4,2,1,3,5,9,8,11,10,12,
//                7,4,9,2,5
        };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
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
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        traversalTest();
//        toStringTest();
        removeTest();
    }
}
