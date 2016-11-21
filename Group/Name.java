public class Name implements Comparable<Name> {
	protected String lastName, firstName, middleName;
	
	public Name() {
		lastName = "last";
		middleName = "middle";
		firstName = "first";
	}

	public Name(String ln, String fn, String mn) {
		lastName = ln;
		firstName = fn;
		middleName = mn;
	}

	public String toString() {
		return lastName+", "+firstName+", "+middleName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String fn) {
		firstName = fn;
	}

	public void setMiddleName(String mn) {
		middleName = mn;
	}

	public void setLastName(String ln) {
		lastName = ln;
	}

	public int compareTo(Name ext) {
		if (lastName.compareTo(ext.lastName) != 0) {
			return lastName.compareTo(ext.lastName);
		}
		if (firstName.compareTo(ext.firstName) != 0) {
			return firstName.compareTo(ext.firstName);
		}
		if (middleName.compareTo(ext.middleName) != 0) {
			return middleName.compareTo(ext.middleName);
		}
		return 0;
	}

	public static int compare(Name in, Name ext) {
		if (in.lastName.compareTo(ext.lastName) != 0) {
			return in.lastName.compareTo(ext.lastName);
		}
		if (in.firstName.compareTo(ext.firstName) != 0) {
			return in.firstName.compareTo(ext.firstName);
		}
		if (in.middleName.compareTo(ext.middleName) != 0) {
			return in.middleName.compareTo(ext.middleName);
		}
		return 0;
	}

	public boolean equals(Name in) {
		return this.compareTo(in) == 0;
	}
}