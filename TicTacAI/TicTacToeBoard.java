import java.util.ArrayList;

public class TicTacToeBoard {
	final String[] pieces = {" ", "X", "O"};
	protected int[] board = {
		0, 0, 0,
		0, 0, 0,
		0, 0, 0
	};

	public TicTacToeBoard() {}

	public TicTacToeBoard(int[] board) {
		this.board = board;
	}

	public TicTacToeBoard(TicTacToeBoard old) {
		board[0] = old.board[0];
		board[1] = old.board[1];
		board[2] = old.board[2];
		board[3] = old.board[3];
		board[4] = old.board[4];
		board[5] = old.board[5];
		board[6] = old.board[6];
		board[7] = old.board[7];
		board[8] = old.board[8];
	}

	public String toString() {
		return
		" "+pieces[board[0]]+" | "+pieces[board[1]]+" | "+pieces[board[2]]+" \n"+
		"-----------\n"+
		" "+pieces[board[3]]+" | "+pieces[board[4]]+" | "+pieces[board[5]]+" \n"+
		"-----------\n"+
		" "+pieces[board[6]]+" | "+pieces[board[7]]+" | "+pieces[board[8]]+" \n";
	}

	public boolean movesPossible() {
		for (int spot : board) {
			if (spot == 0) {
				return true;
			}
		}
		return false;
	}

	public int[] availableSpots() {
		ArrayList<Integer> arrList = new ArrayList<Integer>();

		// int curSpot = 0;
		for (int i=0; i<board.length; i++) {
			if (board[i] == 0) {
				arrList.add(i);
			}
		}

		int[] returnArr = new int[arrList.size()];

		for (int i=0; i<arrList.size(); i++) {
			returnArr[i] = arrList.get(i);
		}

		return returnArr;
	}

	public int winState() {
		// horizontal		
		if (board[0] == board[1] && board[1] == board[2] && board[0] != 0) {
			return board[0];
		}
		if (board[3] == board[4] && board[4] == board[5] && board[3] != 0) {
			return board[3];
		}
		if (board[6] == board[7] && board[7] == board[8] && board[6] != 0) {
			return board[6];
		}

		// vertical
		if (board[0] == board[3] && board[3] == board[6] && board[0] != 0) {
			return board[0];
		}
		if (board[1] == board[4] && board[4] == board[7] && board[1] != 0) {
			return board[1];
		}
		if (board[2] == board[5] && board[5] == board[8] && board[2] != 0) {
			return board[2];
		}

		// diagonal
		if (board[0] == board[4] && board[4] == board[8] && board[0] != 0) {
			return board[0];
		}
		if (board[2] == board[4] && board[4] == board[6] && board[2] != 0) {
			return board[2];
		}

		return 0;
	}

	public void setBoard(int[] board) {
		this.board = board;
	}

	public void setSpot(int spot, int val) {
		board[spot] = val;
	}
}