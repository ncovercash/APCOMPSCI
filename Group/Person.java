import java.lang.Math;

public class Person implements Comparable<Person> {
	protected Name name;
	protected Address address;
	protected Date birthday;
	protected int age;

	public Person() {
		name = new Name();
		address = new Address();
		birthday = new Date();
		age = (int)(Math.random()*100);
	}

	public Person(Name name, Address address, Date birthday, int age) {
		this.name = name;
		this.address = address;
		this.birthday = birthday;
		this.age = age;
	}

	public String toString() {
		return name+"\n"+address+"\nBirthdate: "+birthday+"\nAge: "+age;
	}

	public Name getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public int getAge() {
		return age;
	}

	public void setName(Name newVal) {
		name = newVal;
	}

	public void setAddress(Address newVal) {
		address = newVal;
	}

	public void setBirthday(Date newVal) {
		birthday = newVal;
	}

	public void setAge(int newVal) {
		age = newVal;
	}

	public int compareTo(Person d) {
		if (this.name.compareTo(d.name) != 0) {
			return this.name.compareTo(d.name);
		}
		if (this.birthday.compareTo(d.birthday) != 0) {
			return this.birthday.compareTo(d.birthday);
		}
		if (this.address.compareTo(d.address) != 0) {
			return this.address.compareTo(d.address);
		}
		return 0;
	}

	public static int compare(Person c, Person d) {
		if (c.name.compareTo(d.name) != 0) {
			return c.name.compareTo(d.name);
		}
		if (c.birthday.compareTo(d.birthday) != 0) {
			return c.birthday.compareTo(d.birthday);
		}
		if (c.address.compareTo(d.address) != 0) {
			return c.address.compareTo(d.address);
		}
		return 0;
	}

	public boolean equals(Person in) {
		return this.compareTo(in) == 0;
	}
}