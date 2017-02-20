import objectdraw.*;
import java.awt.Color;

public class World extends ActiveObject {
	final int WIDTH, HEIGHT;

	final DrawingCanvas canvas;

	final boolean COMPUTER_GOES_FIRST = false;

	boolean gamePlaying = true, userHasMoved = true;

	TextBox scoreController;

	TextBox[] pieces = new TextBox[9];

	FilledRect vertical1, vertical2, horizontal1, horizontal2;

	TicTacToeBoard main = new TicTacToeBoard();

	public World(DrawingCanvas canvas) {
		this.canvas = canvas;

		WIDTH = canvas.getWidth();
		HEIGHT = canvas.getHeight();

		scoreController = new TextBox(0, 0, WIDTH/4, HEIGHT/15, new Color(0xffff00), "Score: ", canvas);
		scoreController.setText("0");

		vertical1 = new FilledRect((WIDTH/10)+((WIDTH-(WIDTH/5))/3)-5, HEIGHT/10, 10, HEIGHT-(HEIGHT/5), canvas);
		vertical2 = new FilledRect((WIDTH/10)+((WIDTH-(WIDTH/5))/1.5)-5, HEIGHT/10, 10, HEIGHT-(HEIGHT/5), canvas);

		horizontal1 = new FilledRect(WIDTH/10, (HEIGHT/10)+((HEIGHT-(HEIGHT/5))/3)-5, WIDTH-(WIDTH/5), 10, canvas);
		horizontal2 = new FilledRect(WIDTH/10, (HEIGHT/10)+((HEIGHT-(HEIGHT/5))/1.5)-5, WIDTH-(WIDTH/5), 10, canvas);

		for (int row=0; row<3; row++) {
			for (int col=0; col<3; col++) {
				double px=0, py=0, pw, ph;

				pw = vertical2.getX() - vertical1.getX()-10;
				ph = horizontal2.getY() - horizontal1.getY()-10;

				switch (row) {
					case 0:
						py = vertical1.getY();
					break;
					case 1:
						py = horizontal1.getY()+10;
					break;
					case 2:
						py = horizontal2.getY()+10;
					break;
				}
				switch (col) {
					case 0:
						px = horizontal1.getX();
					break;
					case 1:
						px = vertical1.getX()+10;
					break;
					case 2:
						px = vertical2.getX()+10;
					break;
				}

				pieces[row*3+col] = new TextBox((int)px, (int)py, pw, ph, new Color(0xffffff), "", canvas);
				// new FilledRect((int)px, (int)py, pw, ph, canvas);

				pieces[row*3+col].setText("");				
			}
		}

		start();
	}

	public void makeAIMove() {
		if (main.availableSpots().length == 1) {
			pieces[main.availableSpots()[0]].setText(main.pieces[2]);
			main.setSpot(main.availableSpots()[0], 2);
			return;
		}
		int highestScore = -1000, bestSpot=-1;
		for (int i=0; i<main.availableSpots().length; i++) {
			TicTacToeBoard tmpBoard = new TicTacToeBoard(main);
			tmpBoard.setSpot(main.availableSpots()[i], 2);
			int score=genAIMoves(tmpBoard, 1);
			System.out.println(main.availableSpots()[i] + " returns a score of "+score);
			if (score > highestScore) {
				highestScore = score;
				bestSpot = main.availableSpots()[i];
			}
		}
		pieces[bestSpot].setText(main.pieces[2]);
		main.setSpot(bestSpot, 2);

		System.out.println("AI fills spot "+bestSpot);
		System.out.println();
	}

	public int genAIMoves(TicTacToeBoard board, int player) {
		if (board.availableSpots().length == 1 || board.winState() != 0) {
			if (board.winState() == 2) {
				return 10;
			} else if (board.winState() == 1) {
				return -10;
			}
			board.setSpot(board.availableSpots()[0], player);
			if (board.winState() == 2) {
				return 10;
			} else if (board.winState() == 1) {
				return -10;
			} else {
				return 0;
			}
		}
		int lowest=1000, highest = -1000;
		for (int i=0; i<board.availableSpots().length; i++) {
			TicTacToeBoard tmpBoard = new TicTacToeBoard(board);
			tmpBoard.setSpot(board.availableSpots()[i], player);

			int res;

			if (player == 1) {
				res = genAIMoves(tmpBoard, 2);
			} else {
				res = genAIMoves(tmpBoard, 1);
			}

			if (res < lowest) {
				lowest = res;
			}
			if (res > highest) {
				highest = res;
			}
		}
		if (player == 1) {
			return lowest;
		}
		return highest;
	}

	public void handleClick(double x, double y) {
		if (!userHasMoved) {
			for (int i=0; i<main.availableSpots().length; i++) {
				if (pieces[main.availableSpots()[i]].contains(x, y)) {
					pieces[main.availableSpots()[i]].setText(main.pieces[1]);
					System.out.println("Human fills spot "+main.availableSpots()[i]);
					System.out.println();
					main.setSpot(main.availableSpots()[i], 1);
					userHasMoved=true;
					return;
				}
			}
		}
	}

	public void checkWin() {
		if (main.winState() != 0) {
			if (main.winState() == 1) {
				scoreController.setText(""+(Integer.parseInt(scoreController.getText())+10));
			} else {
				scoreController.setText(""+(Integer.parseInt(scoreController.getText())-10));
			}
			pause(1000);
			reset();
		}
		if (main.availableSpots().length == 0) {
			pause(1000);
			reset();
		}
	}

	public void run() {
		while (gamePlaying) {
			if (main.availableSpots().length == 9) {
				if (COMPUTER_GOES_FIRST) {
					makeAIMove();
				}
			}

			// main.setBoard(new int[] {0,1,2,2,1,1,0,0,0});

			userHasMoved = false;

			while (!userHasMoved) {
				pause(10);
			}

			checkWin();

			makeAIMove();

			checkWin();
		}
	}

	public void reset() {
		for (int i=0; i<pieces.length; i++) {
			pieces[i].setText("");
		}

		main.setBoard(new int[] {0,0,0,0,0,0,0,0,0});
	}
}