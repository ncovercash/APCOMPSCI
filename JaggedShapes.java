import java.awt.Color;
import java.util.ArrayList;
import objectdraw.*;

public class JaggedShapes extends WindowController {
	Resizable2DInterface[][] shapes = new Resizable2DInterface[4][];

	public static void main(String[] args) {
		new JaggedShapes().startController(1200,1200);
	}

	public void begin() {
		this.generateShapes(40);
		System.out.println(this.toString());
		this.sort();
		System.out.println(this.toString());
	}

	public void generateShapes(int num) {
		// arraylist declarations - will be split up and added to main array later
		ArrayList<Resizable2DInterface> tmpShapes = new ArrayList<Resizable2DInterface>();

		// store number of each shape
		int filledRectsCounter = 0;
		int framedRectsCounter = 0;
		int filledOvalsCounter = 0;
		int framedOvalsCounter = 0;

		// make shapes
		for (int i=0; i<num; i++) {
			// choose shape to make
			int randomShape = (int)(Math.random()*4);

			// declare shape to be made with switch/case
			Resizable2DInterface tmpShape;

			// geometry
			int w 	= (int)(Math.random()*this.canvas.getWidth())+1; 	// from 1 to canvas width
			int h 	= (int)(Math.random()*this.canvas.getHeight())+1; 	// from 1 to canvas height
			int x	= (int)(Math.random()*(this.canvas.getWidth()-w)); 	// ensures that the shape doesn't go off screen
			int y	= (int)(Math.random()*(this.canvas.getHeight()-h));	// ensures that the shape doesn't go off screen

			// color
			int r 	= (int)(Math.random()*256);
			int g 	= (int)(Math.random()*256);
			int b 	= (int)(Math.random()*256);

			// make shape
			switch (randomShape) {
				case 0:
					tmpShape = new FilledRect(x, y, w, h, canvas);
					tmpShape.setColor(new Color(r, g, b));
					tmpShapes.add(tmpShape);
					filledRectsCounter++;
					break;
				case 1:
					tmpShape = new FramedRect(x, y, w, h, canvas);
					tmpShape.setColor(new Color(r, g, b));
					tmpShapes.add(tmpShape);
					framedRectsCounter++;
					break;
				case 2:
					tmpShape = new FilledOval(x, y, w, h, canvas);
					tmpShape.setColor(new Color(r, g, b));
					tmpShapes.add(tmpShape);
					filledOvalsCounter++;
					break;
				case 3:
					tmpShape = new FramedOval(x, y, w, h, canvas);
					tmpShape.setColor(new Color(r, g, b));
					tmpShapes.add(tmpShape);
					framedOvalsCounter++;
					break;
			}
		}

		// add to master array

		// initialize each row in 2D array
		this.shapes[0] = new FilledRect[filledRectsCounter];
		this.shapes[1] = new FramedRect[framedRectsCounter];
		this.shapes[2] = new FilledOval[filledOvalsCounter];
		this.shapes[3] = new FramedOval[framedOvalsCounter];

		// counter for index in each row
		int filledRectsRowIndex = 0;
		int framedRectsRowIndex = 0;
		int filledOvalsRowIndex = 0;
		int framedOvalsRowIndex = 0;

		// copy
		for (Resizable2DInterface shape : tmpShapes) {
			// determine what the shape is and copy appropriately
			if (shape instanceof FilledRect) {
				this.shapes[0][filledRectsRowIndex++] = shape;
			} else if (shape instanceof FramedRect) {
				this.shapes[1][framedRectsRowIndex++] = shape;
			} else if (shape instanceof FilledOval) {
				this.shapes[2][filledOvalsRowIndex++] = shape;
			} else {
				this.shapes[3][framedOvalsRowIndex++] = shape;
			}
		}
	}

	// helper method, gets sum of color values
	public static int getTotalColorVals(java.awt.Color color) {
		return color.getRed()+color.getGreen()+color.getBlue();
	}

	public int compare(Resizable2DInterface a, Resizable2DInterface b) {
		return getTotalColorVals(a.getColor())-getTotalColorVals(b.getColor());
	}

	public void sort() {
		// insertion sort based on getTotalColorVals
		for (int row=0; row<shapes.length; row++) {
			for (int i=1; i<shapes[row].length; i++) {
				int j=i;
				while (j > 0 && compare(shapes[row][j], shapes[row][j-1]) <= 1) {
					Resizable2DInterface tmp = shapes[row][j];
					shapes[row][j] = shapes[row][j-1];
					shapes[row][j-1] = tmp;
					j--;
				}
			}
		}
	}

	public static String expandNumToDigits(int in, int digits) {
		String stringIn = ""+in;
		while (stringIn.length() < digits) {
			stringIn = "0"+stringIn;
		}
		return stringIn;
	}

	public String toString() {
		String str = "";
		for (int row=0; row<shapes.length; row++) {
			str += "[ ";
			for (int col=0; col<shapes[row].length; col++) {
				if (shapes[row][col] instanceof FilledRect) {
					str += "FilledRect-"+expandNumToDigits(getTotalColorVals(shapes[row][col].getColor()), 3)+" "; // FilledRect-055 e.g.
				} else if (shapes[row][col] instanceof FramedRect) {
					str += "FramedRect-"+expandNumToDigits(getTotalColorVals(shapes[row][col].getColor()), 3)+" "; // FilledRect-055 e.g.
				} else if (shapes[row][col] instanceof FilledOval) {
					str += "FilledOval-"+expandNumToDigits(getTotalColorVals(shapes[row][col].getColor()), 3)+" "; // FilledRect-055 e.g.
				} else {
					str += "FramedOval-"+expandNumToDigits(getTotalColorVals(shapes[row][col].getColor()), 3)+" "; // FilledRect-055 e.g.
				}
			}
			str += "]\n";
		}
		return str;
	}
}