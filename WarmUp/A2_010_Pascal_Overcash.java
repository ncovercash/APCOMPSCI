import java.util.Scanner;

public class A2_010_Pascal_Overcash {
	public static void main(String[] args) {
		java.util.Scanner scan = new java.util.Scanner(System.in);
		System.out.println("Enter number of rows of Pascal's Triangle");
		System.out.print("you would like to see displayed");
		int input = scan.nextInt();
		int[][] matrix = {{1},{1,1}};
		while ( input > 1 ) {
			matrix = pascalTriangle(input);
			display(matrix);
			System.out.print("Enter the number of rows: ");
			input = scan.nextInt();
		}
		System.out.println("thank you - goodbye");
	}

	private static int[][] pascalTriangle (int n) {
		int[][] arrToReturn = new int[n][];
		for (int i=0; i<n; i++) {
			arrToReturn[i] = new int[1+i];
			arrToReturn[i][0] = 1;

			for (int j=1; j<arrToReturn[i].length-1; j++) {
				arrToReturn[i][j] = arrToReturn[i-1][j-1]+arrToReturn[i-1][j];
				System.out.println(i+","+j+" ("+arrToReturn[i-1][j-1]+","+arrToReturn[i-1][j]+")");
			}

			arrToReturn[i][arrToReturn[i].length-1] = 1;
		}
		return arrToReturn;
	}

	private static void display (int[][] matrix) {
		for (int i = 0; i<matrix.length; i++) {
			String leadingSpaces = "";
			for (int n = 0; n<matrix.length-1-i; n++) {
				leadingSpaces+="\t";
			}
			System.out.print(leadingSpaces);
			for (int val : matrix[i]) {
				System.out.print(val+"\t\t");
			}
			System.out.println();
		}
	}
}