package com.xhp281.test;

import com.xhp281.common.Times;
import com.xhp281.file.FileInfo;
import com.xhp281.file.Files;
import com.xhp281.set.LinkSet;
import com.xhp281.set.Set;
import com.xhp281.set.TreeSet;

/**
 * User: FenDou
 * Date: 2019-06-03 17:20
 * Description:
 */

public class Main {

    /* 链表实现集合测试 */
    static void test1(){
        Set<Integer> set = new LinkSet<>();
        set.add(10);
        set.add(11);
        set.add(12);
        set.add(13);
        set.add(14);
        set.add(14);

        set.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

    /* 红黑树实现集合测试 */
    static void test2(){
        Set<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(11);
        set.add(12);
        set.add(13);
        set.add(14);
        set.add(14);
        set.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

    /* 文件读取测试 */
    static void test3(){
        FileInfo fileInfo = Files.read("/Users/FenDou/Code/Java",new String[]{"java"});
        System.out.println("文件数量：" +  fileInfo.getFiles());
        System.out.println("代码行数：" +  fileInfo.getLines());

        String[] words = fileInfo.words();
        System.out.println("单词数量：" +  words.length);
        System.out.println("单词：" +  words);

        // 链表测试
        Times.test("LinkSet", new Times.Task() {
            @Override
            public void execute() {
             setSetContent(new LinkSet<>(),words);
            }
        });

        // 红黑树测试
        Times.test("TreeSet", new Times.Task() {
            @Override
            public void execute() {
                setSetContent(new TreeSet<>(),words);
            }
        });
    }

    static void setSetContent(Set<String> set,String[] words){
        // 添加
        for (int i = 0; i < words.length; i++) {
            set.add(words[i]);
        }
        for (int i = 0; i < words.length; i++) {
            set.contains(words[i]);
        }
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
            set.remove(words[i]);
        }
    }

    public static void main(String[] args){
//      test1();
//        test2();
        test3();
    }

}
