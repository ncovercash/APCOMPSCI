// #4

public class Successor {
	// #4a
	public static Position getPosition(int[][] intArr, int value) {
		for (int row=0; row<intArr.length; row++) {
			for (int col=0; col<intArr[row].length; col++) {
				if (intArr[row][col] == value) {
					return new Position(row, col);
				}
			}
		}
		return null;
	}

	// #4b
	public static Position[][] getSuccessorArray(int[][] intArr) {
		Position[][] successorArray = new Position[intArr.length][intArr[0].length];

		for (int row=0; row<intArr.length; row++) {
			for (int col=0; col<intArr[row].length; col++) {
				successorArray[row][col] = getPosition(intArr, intArr[row][col] + 1);
			}
		}

		return successorArray;
	}




	public static void main(String[] args) {
		int[][] testArr = {{ 9, 13, 11, 14},
		                   {15, 10, 12,  8},
		                   {18,  7, 16, 17}}; // 7 - 18

		System.out.println(getPosition(testArr, 12));
		System.out.println(getPosition(testArr, 1));

		Position[][] result = getSuccessorArray(testArr);

		for (Position[] row : result) {
			for (Position pos : row) {
				System.out.print(pos+", ");
			}
			System.out.println();
		}
	}
}
