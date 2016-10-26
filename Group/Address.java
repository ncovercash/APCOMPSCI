import java.lang.Math;
public class Address {
	private int houseNumber, zipCode;
	private String streetName, city, state;

	public Address() {
		int strLength = (int)(Math.random()*(11)+5);
		String str = "";
		for (int j = 0; j < strLength; j++)
		{
			str += (char) ((int) (Math.random() * 26 + 'a'));
		} 
		streetName = str.substring(0, 1).toUpperCase() + str.substring(1);

		strLength = (int)(Math.random()*(11)+5);
		str = "";
		for (int j = 0; j < strLength; j++)
		{
			str += (char) ((int) (Math.random() * 26 + 'a'));
		} 
		city = str.substring(0, 1).toUpperCase() + str.substring(1);

		strLength = (int)(Math.random()*(11)+5);
		str = "";
		for (int j = 0; j < strLength; j++)
		{
			str += (char) ((int) (Math.random() * 26 + 'a'));
		} 
		state = str.substring(0, 1).toUpperCase() + str.substring(1);

		houseNumber = (int)(Math.random()*1000);

		zipCode = (int)(Math.random()*100000);
	}

	public Address(int hn, String sn, String c, String s, int zc) {
		houseNumber = hn;
		streetName = sn;
		city = c;
		state = s;
		zipCode = zc;
	}

	public String toString() {
		return houseNumber+" "+streetName+"\n"+city+", "+state+" "+zipCode;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setHouseNumber(int newVal) {
		houseNumber = newVal;
	}

	public void setStreetName(String newVal) {
		streetName = newVal;
	}

	public void setCity(String newVal) {
		city = newVal;
	}

	public void setState(String newVal) {
		state = newVal;
	}

	public void setZipCode(int newVal) {
		zipCode = newVal;
	}
}