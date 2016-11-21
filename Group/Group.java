import java.util.ArrayList;
public class Group {
	ArrayList<Person> list;
	public Group() {
		list = new ArrayList<Person>();
	}

	public Group(int n) {
		list = new ArrayList<Person>(n);
	}

	public String toString() {
		String s = "";

		for (int i=0; i<list.size(); i++) {
			s+="*******************\n";
			s+=(i+1)+". "+list.get(i).getName()+"\n";
			s+="   "+list.get(i).getAddress()+"\n";
			s+="   Birthday: "+list.get(i).getBirthday()+"\n";
			s+="   Age: "+list.get(i).getAge()+"\n";
		}

		s+="#############/end of list";

		return s;
	}

	public int averageAge() {
		int sum=0;
		for (Person p : list) {
			sum += p.getAge();
		}
		return sum/list.size();
	}

	public void add(Person p) {
		list.add(p);
	}

	public int size() {
		return list.size();
	}

	public boolean remove(Person p) {
		for (int i=0; i<list.size(); i++) {
			if (p == list.get(i)) {
				list.remove(i);
				return true;
			}
		}

		return false;
	}

	public boolean contains(Person p) {
		return list.contains(p);
	}

	public ArrayList<Person> getList() {
		return list;
	}

	public void setList(ArrayList<Person> list) {
		this.list = list;
	}

	public ArrayList<Person> sortByName() {
		ArrayList<Person> sorted = new ArrayList<Person>(list.size());

		for (int i=0; i<list.size(); i++) {
			sorted.add(list.get(i));
		}

		for (int i=0; i<sorted.size(); i++) {
			int minI = i;
			for (int j=i; j<sorted.size(); j++) {
				if (sorted.get(minI).getName().compareTo(sorted.get(j).getName()) > 0) {
					minI = j;
				}
			}
			Person tmp = sorted.get(minI);
			sorted.set(minI, sorted.get(i));
			sorted.set(i, sorted.get(minI));
		}
		return sorted;
	}

	public ArrayList<Person> sortByAddress() {
		ArrayList<Person> sorted = new ArrayList<Person>(list.size());

		for (int i=0; i<list.size(); i++) {
			sorted.add(list.get(i));
		}

		for (int i=0; i<sorted.size(); i++) {
			int minI = i;
			for (int j=i; j<sorted.size(); j++) {
				if (sorted.get(minI).getAddress().compareTo(sorted.get(j).getAddress()) > 0) {
					minI = j;
				}
			}
			Person tmp = sorted.get(minI);
			sorted.set(minI, sorted.get(i));
			sorted.set(i, sorted.get(minI));
		}
		return sorted;
	}

	public ArrayList<Person> sortByBirthDate() {
		ArrayList<Person> sorted = new ArrayList<Person>(list.size());

		for (int i=0; i<list.size(); i++) {
			sorted.add(list.get(i));
		}

		for (int i=0; i<sorted.size(); i++) {
			int minI = i;
			for (int j=i; j<sorted.size(); j++) {
				if (sorted.get(minI).getBirthday().compareTo(sorted.get(j).getBirthday()) > 0) {
					minI = j;
				}
			}
			Person tmp = sorted.get(minI);
			sorted.set(minI, sorted.get(i));
			sorted.set(i, sorted.get(minI));
		}
		return sorted;
	}

	public void selectionSort() {
		for (int i=0; i<list.size(); i++) {
			int minI = i;
			for (int j=i; j<list.size(); j++) {
				if (list.get(minI).compareTo(list.get(j)) > 0) {
					minI = j;
				}
			}
			Person tmp = list.get(minI);
			list.set(minI, list.get(i));
			list.set(i, list.get(minI));
		}
	}

	public void insertionSort() {
		for (int unsorted0=1; unsorted0<list.size(); unsorted0++) {
			// sorted bit it [0,unsorted0)
			Person valToInsert = list.get(unsorted0);
			int placeToInsertVal = unsorted0-1;
			for (int i=unsorted0-1; i>=0; i--) {
				if (valToInsert.compareTo(list.get(i)) < 0) {
					placeToInsertVal = i;
				}
			}
			list.add(placeToInsertVal, valToInsert);
			list.remove(unsorted0+1);
		}
	}
}