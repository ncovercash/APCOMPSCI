import java.lang.StringBuilder;

public class Driver {
    public static void main(String[] args) {
        // quiz2DArrays test = new quiz2DArrays();
        // System.out.println("Creating instance");
        // System.out.println(test);
        // System.out.println("Odd row sum: "+test.getOddRowSum());
        // System.out.println("Even col sum: "+test.getEvenColSum());
        // System.out.println("Minor diag avg: "+test.getMinorDiagonalAverage());
        // System.out.println("Shifting left");
        // test.shiftLeft();
        // System.out.println(test);
        // System.out.println("Reflecting");
        // test.reflectMajorDiagonal();
        // System.out.println(test);
        System.out.println(isPalindrome("()()"));
        System.out.println(isPalindrome(")(()"));

    }
    public static boolean isPalindrome(String str) {
        StringBuilder reversed = new StringBuilder(str);
        reversed.reverse();
        String reversedStr = reversed.toString();
        return str.equals(reversedStr);
    }
}