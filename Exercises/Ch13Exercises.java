import java.util.ArrayList;

public class Ch13Exercises {

	static ArrayList<String> list1 = new ArrayList<String>();
	
	public static void main(String[] args) {
		// fill list1 with {"A", "A", "A", "B", "C", "C", "A", "A"};
		for (String addMe : new String[]{"A", "A", "A", "B", "C", "C", "A", "A"}) {
			list1.add(addMe);
		}

		// dipllay list1
		System.out.print("list1: {");
		for (String item : list1) {
			System.out.print("\""+item+"\" ");
		}
		System.out.print("}\n");

		// removeConsecutiveDuplicates(list1);
		removeConsecutiveDuplicates(list1);

		//	display list1
		System.out.print("list1: {");
		for (String item : list1) {
			System.out.print("\""+item+"\" ");
		}
		System.out.print("}\n");
		
		//	make list2, should be similar to list1
		ArrayList<String> list2 = new ArrayList<String>();

		for (String addMe : new String[]{"A", "A", "C", "D", "C", "C", "A", "A"}) {
			list2.add(addMe);
		}
		
		// there is a bug in the line below!	
		// ArrayList<String> list3 = filter(list1, list1);
		ArrayList<String> list3 = filter(list1, list2);
		
		//display list1 and list3
		System.out.print("list1: {");
		for (String item : list1) {
			System.out.print("\""+item+"\" ");
		}
		System.out.print("}\n");
		System.out.print("list3: {");
		for (String item : list3) {
			System.out.print("\""+item+"\" ");
		}
		System.out.print("}\n");

	}
	/**
	 * removes consecutive duplicate values from an ArrayList of strings. 
	 * For example, if letters contains ["A", "A", "A", "B", "C", "C","A", "A"], 
	 * after a call to removeConsecutiveDuplicates(letters) letters should 
	 * contain ["A", "B", "C", "A"].
	 * @param lst
	 */
	public static void removeConsecutiveDuplicates(ArrayList<String> lst )
	{
		for (int i=lst.size()-1; i>0; i--) { // i>0 because of IOOB errs
			if (lst.get(i) == lst.get(i-1)) {
				lst.remove(i);
			}
		}


	//	for (________________________________________________)

	//		if (_________________________________________________)

	//		lst.remove(_______________);
				// hint:	traverse the collection backwards
		
	}
	
	
	/**
	 * returns a list that is the same as l1, 
	 * but with all objects that are also in l2 removed.	
	 * Do NOT alter l1.	Return a new list
	 * @param l1
	 * @param l2
	 * @return
	 */
	// 
	public static ArrayList<String> filter(ArrayList<String> l1, ArrayList<String> l2)
	{
		ArrayList<String> newAList = new ArrayList<String>();
		for (String item : l1) {
			newAList.add(item);
		}

		for (int i=newAList.size()-1; i>=0; i--) {
			for (String testItem : l2) {
				if (newAList.get(i) == testItem) {
					newAList.remove(i);
					break;
				}
			}
		}
		
		return newAList;
	}

}
