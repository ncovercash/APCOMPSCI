public class Name {
	private String lastName, firstName, middleName;
	
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
}