public class Tester {
	public static void main(String[] args) {
		Name nameTest1, nameTest2, nameTest3;
		nameTest1 = new Name();
		nameTest2 = new Name("l", "f", "m");
		nameTest3 = new Name("l", "f", "m");
		System.out.println(nameTest1);
		System.out.println(nameTest2);
		System.out.println(nameTest3.getLastName());
		System.out.println(nameTest3.getFirstName());
		System.out.println(nameTest3.getMiddleName());
		nameTest3.setLastName("a");
		nameTest3.setFirstName("b");
		nameTest3.setMiddleName("c");
		System.out.println(nameTest3);
		System.out.println();
		System.out.println();

		Date dateTest1, dateTest2, dateTest3;
		dateTest1 = new Date();
		dateTest2 = new Date(11, 16, 2001);
		dateTest3 = new Date(11, 16, 2001);
		System.out.println(dateTest1);
		System.out.println(dateTest2);
		System.out.println(dateTest3.getMonth());
		System.out.println(dateTest3.getDay());
		System.out.println(dateTest3.getYear());
		dateTest3.setMonth(2);
		dateTest3.setDay(15);
		dateTest3.setYear(1998);
		System.out.println(dateTest3);
		System.out.println();
		System.out.println();
	}
}