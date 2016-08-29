import java.util.Arrays;
import java.lang.Math;

public class A2_006_ArrayStuff_Overcash {
	public static void main(String[] args) {
		// initializeroni
		int[] arr = new int[10];

		// filleroni
		for (int i=0; i < arr.length; i++) {
			arr[i] = (int)((Math.random()*66)+10);
		}

		// testroni the functiononis
		display(arr);
		System.out.println();
		// rotateLeft(arr);
		// rotateRight(arr);
		rotate(arr, 2);
	}

	public static void display(int[] a) {
		System.out.print("Array:\n"+Arrays.toString(a)+"\n");
	}

	public static int[] rotateLeft(int[] a) {
		System.out.print("Rotating the Following ");
		display(a);


		int firstElement = a[0];

		for (int i=0; i<a.length-1; i++) {	// 1 less, we dont want an out of bounds exception
			a[i] = a[i+1];					// This moves to left, however causes last to not really work, RESOLVED below
		}

		// put first in end
		a[a.length-1] = firstElement;
		System.out.print("Result ");
		display(a);
		return a;
	}

	public static int[] rotateRight(int[] a) {
		System.out.print("Rotating the Following ");
		display(a);


		int lastElement = a[a.length-1];

		for (int i=a.length-1; i>0; i--) {	// DESCENDS from 1 below length
			a[i] = a[i-1];
		}

		// put first in end
		a[0] = lastElement;
		System.out.print("Result ");
		display(a);
		return a;
	}

	public static int[] rotate(int[] a, int d) {
		System.out.print("Rotating the Following "+d+" steps ");
		display(a);

		for (int n=0; n < d; n++) {
			int firstElement = a[0];

			for (int i=0; i<a.length-1; i++) {
				a[i] = a[i+1];
			}

			// put first in end
			a[a.length-1] = firstElement;
		}

		System.out.print("Result ");
		display(a);
		return a;
	}
}
