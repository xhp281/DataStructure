package com.xhp281;

import java.util.HashMap;
import java.util.Map;

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
        map.put("test","003");
        System.out.println(map.size());

    }

    public static void main(String[]args){
        test3();
    }

}
