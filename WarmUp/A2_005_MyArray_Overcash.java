import java.util.Scanner;
import java.util.Random;
import java.util.Date;
import java.util.TimeZone;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.time.DateTimeException;

public class A2_005_MyArray_Overcash {
	public static int[] arr;
	static java.util.Random random = new Random();

	public static void main(String[] args) {
		initialize();
		fill();
		display();
		System.out.print("\nSum: "+sum());
		System.out.print("\nSize: "+size());
		System.out.print("\nAverage: "+average()+"\n");
	}

	public static void initialize() {
		arr = new int[11+random.nextInt(29)];
	}

	public static void fill() {
		for (int i=0; i<arr.length; i++) {
			arr[i] = 10+random.nextInt(90);
		}
	}

	public static void display() {
		for (int i=0; i<arr.length; i++) {
			if (i%3 == 0) {
				System.out.print(arr[i]+"\t");
			} else if (i%3 == 1) {
				System.out.print(arr[i]+"\t");
			} else {
				System.out.print(arr[i]+"\n");
			}
		}
		System.out.print("\n");
	}

	public static int sum() {
		int totalVal = 0;
		for (int i=0; i<arr.length; i++) {
			totalVal = totalVal + arr[i];
		}
		return totalVal;
	}

	public static int size() { // yay wrappers
		return arr.length;
	}

	public static double average() {
		return ((double)sum()) / ((double)size());
	}
}
