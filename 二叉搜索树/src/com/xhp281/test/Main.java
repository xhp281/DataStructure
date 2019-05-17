package com.xhp281.test;

import com.xhp281.BinarySearchTree;
import com.xhp281.Comparator;
import com.xhp281.Person;

/**
 * User: FenDou
 * Date: 2019-05-17 18:15
 * Description:
 */

public class Main {
// 创建比较器
    private static class PersonComparator implements Comparator<Person>{
    @Override
    public int compare(Person e1, Person e2) {
        return e1.getAge() - e2.getAge();
    }

    private static class PersonComparator2 implements Comparator<Person>{
        @Override
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }

    public static void main(String[] args) {
//        Integer data[] = new Integer[]{
//            7,4,9,2,5,8,11,3
//        };
//        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
//        for (int i = 0; i < data.length; i++) {
//            bst.add(data[i]);
//        }

        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new PersonComparator());
        bst2.add(new Person(1));
        bst2.add(new Person(2));

        BinarySearchTree<Person> bst3 = new BinarySearchTree<>(new PersonComparator2());
        bst3.add(new Person(1));
        bst3.add(new Person(2));
        return;
 }
}
