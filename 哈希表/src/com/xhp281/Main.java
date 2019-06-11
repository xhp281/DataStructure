package com.xhp281;

import com.xhp281.common.Asserts;
import com.xhp281.model.Key;
import com.xhp281.model.Person;
import com.xhp281.map.HashMap;
import com.xhp281.map.Map;

/**
 * User: FenDou
 * Date: 2019-06-06 10:19
 * Description:
 */

public class Main {

    static void test1(){
        String string = "jack";
        System.out.println(string.hashCode());
    }

    static void test2(){
        Integer a = 100;
        Float b = 10.6f;
        Long c = 156l;
        Double d = 10.9;
        String e = "rose";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
        System.out.println(d.hashCode());
        System.out.println(e.hashCode());

    }

    static void test3(){
        Person p1 = new Person("jakc",1.67f,10);
        Person p2 = new Person("jakc",1.67f,10);

        Map<Object,Object> map = new HashMap<>();
        map.put(p1,"001");
        map.put(p2,"002");
        map.put("jack","1");
        map.put("rose","1");
        map.put("jack","3");

        map.remove(p2);
        System.out.println(map.size());
        System.out.println(map.get(p2));
        System.out.println(map.containsValue("001"));
    }

    /* 极端测试 */
    static void test4(){
        HashMap<Object,Integer> map = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            map.put(new Key(i),i);
        }
//        map.traversal(new Map.Visitor<Object, Integer>() {
//            @Override
//            public boolean visit(Object key, Integer value) {
//                System.out.println(key + "_" + value);
//                return false;
//            }
//        });
        map.print();
//        System.out.println(map.size());
        System.out.println(map.get(new Key(1)));

    }

    /* 比较的bug修复测试 */
    static void test5(){
        HashMap<Object,Integer> map = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            map.put(new Key(i),i);
        }
        map.put(new Key(4),100);
        Asserts.test(map.size() == 9);
        Asserts.test(map.get(new Key(4)) == 100);
        Asserts.test(map.get(new Key(8)) == 8);
    }
    public static void main(String[]args){
        test5();
    }

}
