import java.lang.Math;
public class Date {
	private int month, date, year;

	public Date() {
		month = (int)(Math.random()*12)+1;
		date = (int)(Math.random()*30)+1;
		year = (int)(Math.random()*2017);
	}

	public Date(int m, int d, int y) {
		month = m;
		date = d;
		year = y;
	}

	public int getMonth() {
		return month;
	}

	public int getDate() {
		return date;
	}

	public int getYear() {
		return year;
	}

	public void setMonth(int newMonth) {
		month = newMonth;
	}

	public void setDate(int newDate) {
		date = newDate;
	}

	public void setYear(int newYear) {
		year = newYear;
	}

	public String toString() {
		String returnString = "";
		if (month < 10) {
			returnString += "0";
		}
		returnString += month;

		returnString += "/";
		
		if (date < 10) {
			returnString += "0";
		}
		returnString += date;

		returnString += "/";

		if (year == 0) {
			returnString += "000";
		} else if (year < 10) {
			returnString += "000";
		} else if (year < 100) {
			returnString += "00";
		} else if (year < 1000) {
			returnString += "0";
		}
		returnString += year;

		return returnString;
	}
}