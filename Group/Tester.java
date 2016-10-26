public class Tester {
	public static void main(String[] args) {
		Name nameTest1, nameTest2, nameTest3;
		nameTest1 = new Name();
		nameTest2 = new Name("l", "f", "m");
		nameTest3 = new Name("l", "f", "m");
		System.out.println(nameTest1);
		System.out.println(nameTest2);
		System.out.println(nameTest3.getLastName());
		System.out.println(nameTest3.getMiddleName());
		System.out.println(nameTest3.getLastName());
		nameTest3.setLastName("a");
		nameTest3.setFirstName("b");
		nameTest3.setMiddleName("c");
		System.out.println(nameTest3);
	}
}