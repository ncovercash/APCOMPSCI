import java.util.Arrays;
import java.util.Random;
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
		// rotate(arr, 2);
		// moveLargestToLast(arr);
		display(createWedge(17));
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

	public static int largest(int[] a) {
		int largest=a[0]; // to be set to largest in fore

		for (int el : a) {
			if (el > largest) {
				largest = el;
			}
		}

		return largest;
	}

	public static int smallest(int[] a) {
		int smallest=a[0]; // to be set to largest in fore

		for (int el : a) {
			if (el < smallest) {
				smallest = el;
			}
		}

		return smallest;
	}

	public static void moveLargestToLast(int[] a) {
		int largestI=0; // to be set to largest in fore

		for (int i=0;i<a.length;i++) {
			if (a[largestI] < a[i]) {
				largestI = i;
			}
		}

		int largest=a[largestI];

		for (int i=largestI+1;i<a.length;i++) {
			a[i-1] = a[i];
		}

		a[a.length-1] = largest;
	}

	public static int[] shuffle(int[] a) {
		// Fisher-Yates shuffle
		Random rnd = new Random();
		for (int i = a.length - 1; i > 0; i--)
		{
			int index = rnd.nextInt(i + 1);
			int aa = a[index];
			a[index] = a[i];
			a[i] = aa;
		}

		return a;
	}

	public static double average2Largest(int[] a) {
		int largest=0;
		int secLargest=0; // incase a[0] is largest

		for (int el : a) {
			if (el > largest) {
				secLargest = largest;
				largest = el;
			}
		}

		return (largest+secLargest)/2.0;
	}

	public static int[] removeSmallest(int[] a) {
		int smallestI=0;

		for (int i=0;i<a.length;i++) {
			if (a[smallestI] > a[i]) {
				smallestI = i;
			}
		}

		int[] newArr = new int[a.length-1];

		for (int i=0; i<a.length; i++) {
			if (i == smallestI) {
				// pass
			} else if (i > smallestI) {
				newArr[i-1] = a[i];
			} else {
				newArr[i] = a[i];
			}
		}

		return newArr;
	}

	public static int[] createWedge(int n) {
		int[] arr = new int[(n*2)-1];
		for (int i=0;i<n;i++) {
			// System.out.println(i+", "+(i+1));
			arr[i] = i+1;
			arr[arr.length-1-i] = i+1;
		}
		return arr;
	}
}
