import java.lang.Math;

public class quiz2DArrays {
	private int[][] arr = new int[5][5];

	public quiz2DArrays() {
		for (int row=0; row<arr.length; row++) {
			for (int col=0; col<arr[row].length; col++) {
				if (row == col) {
					arr[row][col] = 0;
				} else {
					arr[row][col] = (int)(Math.random()*9)+1;
				}
			}
		}
	}

	public int getOddRowSum() {
		int sum=0;
		for (int row=1; row<arr.length; row+=2) {
			for (int col=0; col<arr[row].length; col++) {
				sum += arr[row][col];
			}
		}
		return sum;
	}

	public int getEvenColSum() {
		int sum=0;
		for (int row=0; row<arr.length; row++) {
			for (int col=0; col<arr[row].length; col+=2) {
				sum += arr[row][col];
			}
		}
		return sum;
	}

	public int getMinorDiagonalAverage() {
		int total=0, numNodes=0;
		for (int row=0; row<arr.length; row++) {
			int col=arr[row].length-1-row;
			total += arr[row][col];
			numNodes++;
		}
		return (int)(total/numNodes);
	}

	public void shiftLeft() {
		for (int row=0; row<arr.length; row++) {
			int firstVal=arr[row][0];
			for (int col=0; col<arr[row].length-1; col++) {
				arr[row][col] = arr[row][col+1];
			}
			arr[row][arr[row].length-1]=firstVal;
		}
	}

	public void reflectMajorDiagonal() {
		int[][] newArr = new int[arr.length][arr.length];
		for (int row=0; row<arr.length; row++) {
			for (int col=0; col<arr[row].length; col++) {
				newArr[col][row] = arr[row][col];
			}
		}
		arr = newArr;
	}

	public String toString() {
		String str = "";
		for (int row=0; row<arr.length; row++) {
			for (int col=0; col<arr[row].length; col++) {
				str += arr[row][col];
			}
			str += "\n";
		}
		return str;
	}
}