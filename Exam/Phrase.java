// #3

public class Phrase {
	private String phrase;

	// provided
	public Phrase(String str) { phrase = str; }

	// provided
	public int getNthOccurence(String str, int num) {
		int index = -1;
		while (num > 0) {
			if (phrase.indexOf(str, index) == -1) {
				return -1;
			}
			index = phrase.indexOf(str, index+1);
			num--;
		}
		return index;
	}

	// #3a
	public void replaceNthOccurence(String str, int n, String repl) {
		int index = getNthOccurence(str, n);
		if (index != -1) {
			int needleLength = str.length();
			phrase = phrase.substring(0, index)+repl+phrase.substring(index+needleLength);
		}
	}

	// #3b
	public int getLastOccurence(String str) {
		int currentN = 1;
		while (getNthOccurence(str, currentN) != -1) {
			currentN += 1;
		}
		if (currentN == 1) { // never occurred
			return -1;
		}
		return getNthOccurence(str, currentN-1);
	}


	public static void main(String[] args) {
		Phrase test1 = new Phrase("the quick brown fox jumped over the lazy doggo");
		System.out.println(test1.getLastOccurence("the"));
		test1.replaceNthOccurence("the", 2, "that");
		test1.replaceNthOccurence("the", 1, "dat");
		test1.replaceNthOccurence("the", 1, "test");
		System.out.println(test1.phrase);

	}
}
