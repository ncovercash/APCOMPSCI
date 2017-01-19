import objectdraw.*;

public class Driver extends WindowController {
	Man hangman;
	public static void main(String[] args) {
		new Driver().startController(2000,1000);
	}

	public void begin() {
		hangman = new Man(canvas);
	}
}