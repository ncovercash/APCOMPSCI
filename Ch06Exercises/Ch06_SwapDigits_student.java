import java.util.Scanner;
/* Given a positive integer n, compute a new integer 
 * in which the units and tens digits have swapped places. 
 * For example, if n = 123, the result should be 132; 
 * if n = 3, the tens digit is zero and the result should be 30.
 * */

public class Ch06_SwapDigits_student
{
    

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number (-99 to Quit) : ");
        int n = input.nextInt();
        while (n != -99)
        {
            int tmp;
            tmp = (n-(n%100));
            tmp += ((n%100)-(n%10))/10;
            tmp += (n%10)*10;
            int result = tmp;
            System.out.println(result);
            
            System.out.print("Enter a number (-99 to Quit) : ");
            n = input.nextInt();
        }
        
    }
    
}
