import java.util.Scanner;
/*converts a dog’s age to the corresponding human age. 
 * Assume that a dog’s first year corresponds to a human age of 13, 
 * so convertToHumanAge(1) should return 13. 
 * After that, every three years in a dog’s life correspond 
 * to sixteen years in human life. 
 * The method returns the corresponding human age, rounded to the nearest integer.
 */

public class Ch06_Dogs2Human_student {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.print(" Enter your dog's age (years, -99 to Quit): ");
		int dogYears = kb.nextInt();
		
		while ( dogYears != -99) {
			System.out.println(dogYears + " dog years roughly corresponds to " + convertToHumanAge(dogYears) + " human years.");
			
			System.out.print(" Enter your dog's age (years, -99 to Quit): ");
			dogYears = kb.nextInt();
		}
	}

	private static int convertToHumanAge(int dogYears) {
		double doggoAge = 13.0 - (16.0/3.0);
		doggoAge += dogYears*(16.0/3.0);

		return (int)doggoAge;
	}

}
