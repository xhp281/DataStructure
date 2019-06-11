package com.xhp281.model;

/**
 * User: FenDou
 * Date: 2019-06-06 10:20
 * Description:
 */

public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private float height;

    public Person(String name, float height, int age) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
//        推荐使用第二种，第一种使用Person的子类也会相等
//        if (obj == null || !(obj instanceof Person)) return false;
        if (obj == null || obj.getClass() != getClass()) return false;

        Person person = (Person) obj;
        return person.age == age
                && person.height == height
                && person == null ? name == null : person.name.equals(name);
    }

    //    31 * i = (2^5 – 1) * i = i * 2^5 – i = (i << 5) – i
    @Override
    public int hashCode() {
        int hasCode = Integer.hashCode(age);
        hasCode = hasCode * 31 + Float.hashCode(height);
        hasCode = hasCode * 31 + (name == null ? 0 :name.hashCode());
        return hasCode;
    }

    // 年龄大的大
    @Override
    public int compareTo(Person o) {
        return age - o.age;
    }
}
