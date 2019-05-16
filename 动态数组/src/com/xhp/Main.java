package com.xhp;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> list = new ArrayList<>();
		list.add(10);
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		list.add(null);
		list.add(0,99);
		list.set(0,100);
//		System.out.println(list.remove(5));
//		list.set(20,100);
//		Assert.test(list.get(0) == 9);
//		list.clear();
		System.out.println(list.toString());

		ArrayList<Person> personList = new ArrayList<>();
		personList.add(new Person(10,"Robin"));
		personList.add(new Person(11,"Robin1"));
		personList.add(new Person(22,"Robin2"));
		personList.add(null);
//		personList.clear();
		System.out.println(personList.indexOf(null));
		System.out.println(personList.toString());
	}
}
