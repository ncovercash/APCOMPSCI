import java.util.ArrayList;

// #1

public class Digits {
	private ArrayList<Integer> digitList;

	// #1a
	public Digits(int num) {
		digitList = new ArrayList<Integer>();

		while (num/10 != 0) {
			digitList.add(0, num % 10);
			num /= 10;
		}

		digitList.add(0, num);
	}

	// #1b
	public boolean isStrictlyIncreasing() {
		for (int i=0; i<digitList.size()-1; i++) {
			if (digitList.get(i) >= digitList.get(i+1)) {
				return false;
			}
		}
		return true;
	}



	public static void main(String[] args) {
		Digits test = new Digits(15027);
		System.out.println(test.digitList);
        System.out.println(test.isStrictlyIncreasing());
        Digits test2 = new Digits(1234);
        System.out.println(test2.isStrictlyIncreasing());
        Digits test3 = new Digits(0);
        System.out.println(test2.digitList);
	}
}
