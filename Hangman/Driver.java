import objectdraw.*;
import java.io.*;
import java.util.*;
import java.awt.*;

public class Driver extends WindowController {
	Man hangman;
	String answer="";
	ArrayList<GamePiece> unguessed, correct, incorrect;
	GamePiece[] blanks;
	final String[] vowels={"A", "E", "I", "O", "U"};
	final String[] alphabet={"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	final Color unguessedColor=new Color(0xffff00), vowelColor=new Color(0xffee00), guessedColor=new Color(0x111111), blankColor=new Color(0xffffff);

	boolean alive=true;

	public static void main(String[] args) {
		new Driver().startController(2000,1000);
	}

	public void begin() {
		newGame();
	}

	public void newGame() {
		canvas.clear();
		hangman = new Man(canvas);
		chooseWord();
		System.out.println("The chosen word is: " + answer);
		createBlanks();
		initArrays();
		createPieces();
		addCopyright();
		alive = true;
	}

	public void addCopyright() {
		Text cp = new Text("a smileytechguy product", 0, 0, canvas);
		cp.moveTo(canvas.getWidth()-cp.getWidth(), canvas.getHeight()-cp.getHeight());
	}

	public void createBlanks() {
		blanks = new GamePiece[answer.length()];
		for (int i=0; i<answer.length(); i++) {
			blanks[i] = new GamePiece("_", canvas.getWidth()/4+(40*i), canvas.getHeight()/2-20, 40, 40, 20, blankColor, canvas);
		}
	}

	public void createPieces() {
		new Text("Letters:", canvas.getWidth()/2+200, 120, canvas).setFontSize(40);
		for (int i=0; i<alphabet.length; i++) {
			unguessed.add(new GamePiece(alphabet[i], canvas.getWidth()/2+200+(40*(i%13)), 200+(40*((int)i/13)), 40, 40, 20, unguessedColor, canvas));
			for (String vowel : vowels) {
				if (vowel.equals(alphabet[i])) {
					unguessed.get(i).setColor(vowelColor);
				}
			}
		}
	}

	public void initArrays() {
		incorrect = new ArrayList<GamePiece>();
		correct = new ArrayList<GamePiece>();
		unguessed = new ArrayList<GamePiece>();
	}

	public void chooseWord() {
		answer = getRandomLineFromTheFile(new File("/usr/share/dict/words"));
	}

	// taken from SO http://stackoverflow.com/questions/9870954/how-to-read-a-random-line-from-a-file-in-java
	public String getRandomLineFromTheFile(File file) {
		try {
			final RandomAccessFile f = new RandomAccessFile(file, "r");
			final long randomLocation = (long) (Math.random() * f.length());
			f.seek(randomLocation);
			f.readLine();
			return f.readLine().toUpperCase();
		} catch (IOException e) {
			return "error getting word!!";
		}
	}

	public void win() {
		alive = false;
		Text win = new Text("YOU WIN!!", 0, 0, canvas);
		win.setFontSize(180);
		win.moveTo(canvas.getWidth()/2-win.getWidth()/2, canvas.getHeight()/2-win.getHeight()/2);
		Text instruct = new Text("Click to restart", 0, 0, canvas);
		instruct.setFontSize(40);
		instruct.moveTo(canvas.getWidth()/2-instruct.getWidth()/2, win.getY()+220);
	}

	public void loss() {
		alive = false;
		for (int i=0; i<answer.length(); i++) {
			blanks[i].setLetter(answer.substring(i, i+1));
		}
		Text l = new Text("L", 0, 0, canvas);
		l.setFontSize(800);
		l.moveTo(canvas.getWidth()/2-l.getWidth()/2, canvas.getHeight()/2-l.getHeight()/2);
		Text instruct = new Text("Click to restart", 0, 0, canvas);
		instruct.setFontSize(40);
		instruct.moveTo(canvas.getWidth()/2-instruct.getWidth()/2, l.getY()+880);
	}

	public void onMouseClick(Location p) {
		if (alive) {
			for (int i=0; i<unguessed.size(); i++) {
				if (unguessed.get(i).contains(p)) {
					unguessed.get(i).setColor(guessedColor);
					if (answer.indexOf(unguessed.get(i).getLetter()) == -1) {
						incorrect.add(unguessed.remove(i));
						if (hangman.showNextPart()) {
							loss();
						}
					} else {
						boolean allFull=true;
						for (int j=0; j<answer.length(); j++) {
							if (String.valueOf(answer.substring(j, j+1)).equals(unguessed.get(i).getLetter())) {
								blanks[j].setLetter(unguessed.get(i).getLetter());
							}
							if (blanks[j].getLetter().equals("_")) {
								allFull=false;
							}
						}
						if (allFull) {
							win();
						}
						correct.add(unguessed.remove(i));
					}
				}
			}
		} else {
			newGame();
		}
	}
}