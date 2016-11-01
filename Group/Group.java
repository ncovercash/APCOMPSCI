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
}