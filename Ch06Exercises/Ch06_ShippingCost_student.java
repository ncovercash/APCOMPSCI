import java.util.Scanner;

/* A jar of jam weighs 1 lb. 5 oz. (One pound is 16 ounces). 
 * An empty shipping carton weighs 1 lb. 9 oz. and can hold up to 12 jars. 
 * The shipping costs include $1.44 for each full or partial carton 
 * plus $0.96 per pound or fraction of a pound plus a $3.00 service charge.
 */

public class Ch06_ShippingCost_student {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		System.out.print("Enter number of jars(-99 to Quit) : ");
		int n = input.nextInt();
		while (n != -99)
		{
			
			double result = computeShippingCost(n);
			System.out.println("Cost is $ " + result + " for " + n + " jars.");
			
			System.out.print("Enter number of jars(-99 to Quit) : ");
			n = input.nextInt();
		}
	}
 
	public static double computeShippingCost(int nJars)
	{
		 // do not add any other local variables.
		int nCartons = (nJars + 11) / 12; 
		int totalOunces = 0;
		int pounds = 0;

		totalOunces = nJars*21;
		totalOunces += nCartons*25;

		pounds= totalOunces/16;

		if (totalOunces % 16 != 0) {
			pounds++;
		}

		double answer = 3.0+(1.44*nCartons)+(0.96*pounds);
		return answer; 
	} 

}
