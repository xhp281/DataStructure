package com.xhp281.test;
import com.xhp281.BinarySearchTree;
import com.xhp281.Person;
import com.xhp281.printer.BinaryTrees;

import java.util.Comparator;

/**
 * User: FenDou
 * Date: 2019-05-17 18:15
 * Description:
 */

public class Main {
    // 创建比较器
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
                return e1.getAge() - e2.getAge();
            }
        });
        for (int i = 0; i < data.length; i++) {
            bst.add(new Person(data[i]));
        }
        BinaryTrees.println(bst);
    }

    /**
     * 打印数字
     */
    static void test3() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < 30; i++) {
            bst.add((int)(Math.random() * 100));
        }

        BinaryTrees.println(bst);
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
 }

}
