import java.lang.Math;

public class SuperArray {
	private String[] dullArray;
	private int size=0;
	
	public SuperArray() {
		dullArray = new String[10];
	}

	public SuperArray(int n) {
		dullArray = new String[n];
	}

	public SuperArray(int n, String s) {
		dullArray = new String[n];
		for (int i=0; i<n; i++) {
			dullArray[i] = s;
		}
		size=n;
	}

	public SuperArray(String[] s) {
		dullArray = new String[s.length];
		for (int i=0; i<s.length; i++) {
			dullArray[i] = s[i];
		}
		size=s.length;
	}

	public int size() {
		return size;
	}

	public int capacity() {
		return dullArray.length;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(String e) {
		for (String s : dullArray) {
			if (s == e) {
				return true;
			}
		}
		return false;
	}

	public int indexOf(String e) {
		for (int i=0; i<size; i++) {
			if (dullArray[i] == e) {
				return i;
			}
		}
		return -1;
	}

	public int indexOf(String e, int n) {
		if (n >= size) {
			throw new IndexOutOfBoundsException("Supplied index of \""+n+"\" is above or equal to SuperArray of size "+size);
		}
		for (int i=n+1; i<size; i++) {
			if (dullArray[i] == e) {
				return i;
			}
		}
		return -1;
	}

	public int lastIndexOf(String e) {
		for (int i=size-1; i>=0; i--) {
			if (dullArray[i] == e) {
				return i;
			}
		}
		return -1;
	}

	public String toString() {
		if (this.isEmpty()) {
			return "[]";
		} else {
			String returnString = "[";
			for (int i=0; i<this.size()-1; i++) {
				returnString += " "+dullArray[i]+",";
			}
			returnString += " "+dullArray[size-1]+" ]";

			return returnString;
		}
	}

	public String get(int i) {
		if (i<0) {
			throw new IndexOutOfBoundsException("Supplied index of \""+i+"\" is less than 0");
		} else if (i >= size) {
			throw new IndexOutOfBoundsException("Supplied index of \""+i+"\" is above or equal to SuperArray of size "+size);
		}
		return dullArray[i];
	}

	public String set(int i, String e) {
		if (i<0) {
			throw new IndexOutOfBoundsException("Supplied index of \""+i+"\" is less than 0");
		} else if (i >= size) {
			throw new IndexOutOfBoundsException("Supplied index of \""+i+"\" is above or equal to SuperArray of size "+size);
		}

		String original = dullArray[i];

		dullArray[i] = e;

		return original;
	}

	public void shuffle() {
		for (int i=size-1; i>=0; i--) {
			String temp = dullArray[i];
			int swapIndex = (int)(Math.random()*i);
			dullArray[i] = dullArray[swapIndex];
			dullArray[swapIndex] = temp;
		}
	}

	public boolean add(String e) {
		if (dullArray.length == size) {
			String[] tmpArray = new String[dullArray.length];
			for (int i=0; i<dullArray.length; i++) {
				tmpArray[i] = dullArray[i];
			}
			dullArray = new String[dullArray.length*2];
			for (int i=0; i<tmpArray.length; i++) {
				dullArray[i] = tmpArray[i];
			}
		}

		dullArray[size] = e;

		size++;

		return true;
	}

	public void add(int index, String e) {
		if (dullArray.length == size) {
			String[] tmpArray = new String[dullArray.length];
			for (int i=0; i<dullArray.length; i++) {
				tmpArray[i] = dullArray[i];
			}
			dullArray = new String[dullArray.length*2];
			for (int i=0; i<tmpArray.length; i++) {
				dullArray[i] = tmpArray[i];
			}
		}

		String[] tmpArray = new String[dullArray.length+1];

		for (int i=0; i<index; i++) {
			tmpArray[i] = dullArray[i];
		}

		tmpArray[index] = e;

		for (int i=index+1; i<tmpArray.length; i++) {
			tmpArray[i] = dullArray[i-1];
		}

		dullArray = new String[tmpArray.length];

		for (int i=0; i<dullArray.length; i++) {
			dullArray[i] = tmpArray[i];
		}

		size++;
	}

	public String remove(int index) {
		if (index<0) {
			throw new IndexOutOfBoundsException("Supplied index of \""+index+"\" is less than 0");
		} else if (index >= size) {
			throw new IndexOutOfBoundsException("Supplied index of \""+index+"\" is above or equal to SuperArray of size "+size);
		}

		String[] tmpArray = new String[dullArray.length];
		String returnString = dullArray[index];

		for (int i=0; i<index; i++) {
			tmpArray[i] = dullArray[i];
		}

		for (int i=index; i<tmpArray.length-1; i++) {
			tmpArray[i] = dullArray[i+1];
		}

		dullArray = new String[tmpArray.length];

		for (int i=0; i<dullArray.length; i++) {
			dullArray[i] = tmpArray[i];
		}

		size--;

		return returnString;
	}

	public boolean equals(SuperArray other) {
		if (other.size() != size) {
			return false;
		}

		for (int i=0; i<size; i++) {
			if (dullArray[i] != other.get(i)) {
				return false;
			}
		}

		return true;
	}
}