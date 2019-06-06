package com.xhp281;

/**
 * User: FenDou
 * Date: 2019-06-06 10:20
 * Description:
 */

public class Person {
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

        if (obj == null || !(obj instanceof Person)) return false;
//        if (obj == null || obj.getClass() != getClass()) return false;

        Person person = (Person) obj;
        return person.age == age
                && person.height == height
                && person == null ? name == null : person.name.equals(name);
    }

    @Override
    public int hashCode() {
        int hasCode = Integer.hashCode(age);
        hasCode = hasCode * 31 + Float.hashCode(height);
        hasCode = hasCode * 31 + (name == null ? 0 :name.hashCode());
        return hasCode;
    }
}
