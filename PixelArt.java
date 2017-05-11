import java.awt.Color;
import java.io.*;
import java.util.*;
import objectdraw.*;

public class PixelArt extends WindowController {
	FilledRect[][] pixels = new FilledRect[59][62];
	public static void main(String[] args) {
		new PixelArt().startController(1200,1200);
	}

	public void begin() {
		int pixelSize=10;
		int offsetX=0;
		int offsetY=0;
		// takes 3 auto-generated files:
		// x.txt
		// y.txt
		// c.txt
		
		Scanner scanner;
		try {
			scanner = new Scanner(new File("x.txt"));
			int total = 0;
			while(scanner.hasNextInt()) {
				total++;
				scanner.nextInt();
			}
			int[] x = new int[total];
			int[] y = new int[total];
			int[] c = new int[total];
			scanner = new Scanner(new File("x.txt"));
			int i = 0;
			while(scanner.hasNextInt()) {
				x[i++] = scanner.nextInt();
			}
			
			scanner = new Scanner(new File("y.txt"));
			i = 0;
			while(scanner.hasNextInt()) {
				y[i++] = scanner.nextInt();
			}
			
			scanner = new Scanner(new File("c.txt"));
			i = 0;
			while(scanner.hasNextInt()) {
				c[i++] = scanner.nextInt();
			}
			for (i=0; i<x.length; i++) {
				System.out.println(i);
				pixels[x[i]][y[i]] = new FilledRect(offsetX+(x[i]*pixelSize), offsetY+(y[i]*pixelSize), pixelSize, pixelSize, canvas);
				pixels[x[i]][y[i]].setColor(new Color(c[i]));
			}
		} catch (FileNotFoundException e) {
			System.out.println("FNF");
		}
	}
}