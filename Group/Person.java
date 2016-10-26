import java.lang.Math;

public class Person {
	private Name name;
	private Address address;
	private Date birthday;
	private int age;

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
}