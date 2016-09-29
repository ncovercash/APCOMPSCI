import java.util.Scanner;
/*curHour and curMin represent the current time, 
 * and depHour, depMin represent the departure time of a bus.
 *  Suppose all these variables are initialized with some values; 
 *  both times are between 1 p.m. and 11 p.m. of the same day. 
 *  Calculate the remaining waiting time in hours and minutes.
 */

public class Ch06_WaitTime_student
{
    public static void main(String[] args)
    {
      Scanner kb = new Scanner(System.in);
      System.out.print(" Enter current hour( -99 to Quit): ");
      int curHour = kb.nextInt();
      
      while ( curHour != -99)
      {
          System.out.print(" Enter current min: ");
          int curMin = kb.nextInt();
          System.out.print(" Enter departure hour: ");
          int depHour = kb.nextInt();
          System.out.print(" Enter departure min: ");
          int depMin = kb.nextInt();
          
          System.out.println(" Your wait time is: " + waitTime(curHour, curMin, depHour, depMin));
          
          System.out.println();
          
          System.out.print(" Enter current hour( -99 to Quit): ");
          curHour = kb.nextInt();
      }
    }

    private static String waitTime(int curHour, int curMin, int depHour, int depMin)
    {
         
      int mins = 0;// can that be right? yes
      mins = ((depHour*60)+depMin)-((curHour*60)+curMin);
      return ( ((int)(mins/60)) + " hours and " + (mins%60) + " minutes.");
    }
    
}
