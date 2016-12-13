import objectdraw.*;
import java.awt.*;

public abstract class Ornament extends ActiveObject implements Resizable2DInterface, Comparable<Ornament> {
	FilledOval outer, middle, inner;
	DrawingCanvas canvas;
	boolean active=true;
	int pauseTime=100;
	public Ornament(double x, double y, double w, double h, Color c1, Color c2, Color c3, DrawingCanvas c) {
		outer = new FilledOval(x, y, w, h, c);
		outer.setColor(c1);
		middle = new FilledOval(x+(w/3), y, w/3, h, c);
		middle.setColor(c2);
		inner = new FilledOval(x+(w/3), y+(h/3), w/3, h/3, c);
		inner.setColor(c3);
		this.canvas = c;
		start();
	}

	public Ornament(Ornament o, DrawingCanvas c) {
		this(o.getX(), o.getY(), o.getWidth(), o.getHeight(), o.getColour1(), o.getColour2(), o.getColour3(), c);
	}

	public double getX() {
		return outer.getX();
	}

	public double getY() {
		return outer.getY();
	}

	public Color getColor() {
		return outer.getColor();
	}

	public Color getColour1() {
		return outer.getColor();
	}

	public Color getColour2() {
		return middle.getColor();
	}

	public Color getColour3() {
		return inner.getColor();
	}

	public DrawingCanvas getCanvas() {
		return canvas;
	}

	public Location getLocation() {
		return new Location(getX(), getY());
	}

	public double getHeight() {
		return outer.getHeight();
	}

	public double getWidth() {
		return outer.getWidth();
	}

	public double getArea() {
		return getWidth()*getHeight();
	}

	public void setColor(Color c) {
		outer.setColor(c);
	}

	public void setColour1(Color c) {
		outer.setColor(c);
	}

	public void setColour2(Color c) {
		middle.setColor(c);
	}

	public void setColour3(Color c) {
		inner.setColor(c);
	}

	public void setHeight(double h) {
		outer.setHeight(h);
		middle.setHeight(h);
		inner.setHeight(h/3);
	}

	public void setWidth(double w) {
		outer.setWidth(w);
		middle.setWidth(w/3);
		inner.setWidth(w/3);
	}

	public void setSize(double w, double h) {
		setWidth(w);
		setHeight(h);
	}

	public boolean overlaps(Drawable2DInterface o) {
		return o.contains(new Location(outer.getX(), outer.getY())) ||
			o.contains(new Location(outer.getX()+outer.getWidth(), outer.getY())) ||
			o.contains(new Location(outer.getX(), outer.getY()+outer.getHeight())) ||
			o.contains(new Location(outer.getX()+outer.getWidth(), outer.getY()+outer.getHeight()));
	}

	public boolean contains(Location p) {
		return outer.contains(p);
	}

	public void sendBackward() {
		outer.sendBackward();
		middle.sendBackward();
		inner.sendBackward();
	}

	public void sendToBack() {
		inner.sendToBack();
		middle.sendToBack();
		outer.sendToBack();
	}

	public void sendToFront() {
		outer.sendToFront();
		middle.sendToFront();
		inner.sendToFront();
	}

	public void sendForward() {
		inner.sendForward();
		middle.sendForward();
		outer.sendForward();
	}

	public void move(double dx, double dy) {
		outer.move(dx, dy);
		middle.move(dx, dy);
		inner.move(dx, dy);
	}

	public void moveTo(double x, double y) {
		outer.moveTo(x, y);
		middle.moveTo(x, y);
		inner.moveTo(x, y);
	}

	public void moveTo(Location xy) {
		outer.moveTo(xy);
		middle.moveTo(xy);
		inner.moveTo(xy);
	}

	public boolean isHidden() {
		return outer.isHidden();
	}

	public void removeFromCanvas() {
		outer.removeFromCanvas();
		middle.removeFromCanvas();
		inner.removeFromCanvas();
	}

	public void addToCanvas(DrawingCanvas newC) {
		outer.addToCanvas(newC);
		middle.addToCanvas(newC);
		inner.addToCanvas(newC);
	}

	public void show() {
		outer.show();
		middle.show();
		inner.show();
	}

	public void hide() {
		outer.hide();
		middle.hide();
		inner.hide();
	}

	public int compareTo(Ornament that) {
		return (int)(this.getArea() - that.getArea());
	}

	public int compare(Ornament dis, Ornament that) {
		return (int)(dis.getArea() - that.getArea());
	}

	public boolean equals(Ornament that) {
		return this.getArea() - that.getArea() == 0;
	}

	public abstract void run();
}