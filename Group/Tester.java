import java.util.ArrayList;

public class Tester {
	public static void main(String[] args) {
		Name nameTest1, nameTest2, nameTest3, nameTest4, nameTest5;
		nameTest1 = new Name();
		nameTest2 = new Name("l", "f", "m");
		nameTest3 = new Name("l", "f", "m");
		nameTest4 = new Name("a", "a", "a");
		nameTest5 = new Name("a", "a", "b");
		System.out.println(nameTest1);
		System.out.println(nameTest2);
		System.out.println(nameTest3.getLastName());
		System.out.println(nameTest3.getFirstName());
		System.out.println(nameTest3.getMiddleName());
		nameTest3.setLastName("a");
		nameTest3.setFirstName("b");
		nameTest3.setMiddleName("c");
		System.out.println(nameTest3);
		System.out.println(nameTest1);
		System.out.println(nameTest4);
		System.out.println(nameTest1.compareTo(nameTest4));
		System.out.println(nameTest1.equals(nameTest4));
		System.out.println(Name.compare(nameTest1, nameTest4));
		System.out.println(nameTest2.compareTo(nameTest2));
		System.out.println(nameTest2.equals(nameTest2));
		System.out.println(Name.compare(nameTest2, nameTest2));
		System.out.println(nameTest4.compareTo(nameTest5));
		System.out.println(nameTest4.equals(nameTest5));
		System.out.println(Name.compare(nameTest4, nameTest5));
		System.out.println();
		System.out.println();

		Date dateTest1, dateTest2, dateTest3;
		dateTest1 = new Date();
		dateTest2 = new Date(11, 16, 2001);
		dateTest3 = new Date(11, 16, 2001);
		System.out.println(dateTest1);
		System.out.println(dateTest2);
		System.out.println(dateTest3.getMonth());
		System.out.println(dateTest3.getDate());
		System.out.println(dateTest3.getYear());
		dateTest3.setMonth(2);
		dateTest3.setDate(15);
		dateTest3.setYear(2001);
		System.out.println(dateTest1);
		System.out.println(dateTest2);
		System.out.println(dateTest3);
		System.out.println(dateTest2.compareTo(dateTest1));
		System.out.println(dateTest2.compareTo(dateTest2));
		System.out.println(dateTest2.compareTo(dateTest3));
		System.out.println(Date.compare(dateTest2, dateTest3));
		System.out.println(dateTest2.equals(dateTest2));
		System.out.println(dateTest2.equals(dateTest3));
		System.out.println();
		System.out.println();

		Address addressTest1, addressTest2, addressTest3;
		addressTest1 = new Address();
		addressTest2 = new Address(1400, "Old Tamah Rd", "Irmo", "South Carolina", 29063);
		addressTest3 = new Address(1400, "Old Tamah Rd", "Irmo", "South Carolina", 29063);
		System.out.println(addressTest1);
		System.out.println(addressTest2);
		System.out.println(addressTest3.getHouseNumber());
		System.out.println(addressTest3.getStreetName());
		System.out.println(addressTest3.getCity());
		System.out.println(addressTest3.getState());
		System.out.println(addressTest3.getZipCode());
		addressTest3.setHouseNumber(2);
		addressTest3.setCity("Lexington");
		System.out.println(addressTest3);
		System.out.println();
		System.out.println();

		Person personTest1, personTest2, personTest3;
		personTest1 = new Person();
		personTest2 = new Person(nameTest2, addressTest1, dateTest1, 27);
		System.out.println(personTest1);
		System.out.println(personTest2);
		System.out.println(personTest2.getBirthday());
		personTest2.setBirthday(new Date());
		System.out.println(personTest2);
		System.out.println();
		System.out.println();

		Group groupTest1;
		groupTest1 = new Group();
		groupTest1.add(new Person());
		System.out.println(groupTest1);
		groupTest1.add(new Person());
		groupTest1.add(new Person());
		groupTest1.add(new Person());
		groupTest1.add(new Person());
		groupTest1.add(personTest1);
		System.out.println(groupTest1);
		groupTest1.remove(personTest1);
		System.out.println(groupTest1);
		groupTest1.insertionSort();
		System.out.println(groupTest1);

	}
}