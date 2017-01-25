import objectdraw.*;
import java.awt.*;

public class GamePiece {
	FilledOval bg;
	Text text;
	public GamePiece(String letter, double x, double y, double w, double h, int fs, Color bgC, DrawingCanvas canvas) {
		bg = new FilledOval(x, y, w, h, canvas);
		bg.setColor(bgC);
		text = new Text(letter, x, y, canvas);
		text.setFontSize(fs);
		text.move((w/2)-(text.getWidth()/2), (h/2)-(text.getHeight()/2));
	}

	public void setColor(Color newColor) {
		bg.setColor(newColor);
	}

	public Color getColor() {
		return bg.getColor();
	}

	public void hide() {
		bg.hide();
		text.hide();
	}

	public void show() {
		bg.show();
		text.show();
	}

	public void removeFromCanvas() {
		bg.removeFromCanvas();
		text.removeFromCanvas();
	}

	public void move(int dx, int dy) {
		bg.move(dx, dy);
		text.move(dx, dy);
	}

	public void moveTo(int dx, int dy) {
		bg.moveTo(dx, dy);
		setLetter(getLetter());
	}

	public boolean contains(Location p) {
		return bg.contains(p);
	}

	public String getLetter() {
		return text.getText();
	}

	public void setLetter(String letter) {
		text.setText(letter);
		text.moveTo(bg.getLocation());
		text.move((bg.getWidth()/2)-(text.getWidth()/2), (bg.getHeight()/2)-(text.getHeight()/2));
	}
}