import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.naming.OperationNotSupportedException;

/**
 * 
 * @author This class implements a Game of life board
 * 
 */
public class GoL_Board extends CA {
	/**
	 * 
	 * @param rows: Number of rows
	 * @param cols: Number of columns Init new GoL automaton with an empty board
	 */

	private GoL_Rule rule;

	public GoL_Board(int rows, int cols) {
		board = new char[rows][cols];
		rule = new GoL_Rule();
	}

	@Override
	public Iterator<GoL_Board> iterator() {
		GoL_Board temp = this;
		return new Iterator<GoL_Board>() {
			public boolean hasNext() {
				return nextTurn();
			}

			public void remove() {
				try {
					throw new OperationNotSupportedException();
				} catch (OperationNotSupportedException e) {
					e.printStackTrace();
				}
			}

			@Override
			public GoL_Board next() {
				if (!hasNext())
					throw new NoSuchElementException();
				rule.implementRule(board);
				System.out.println("This turn table:");
				temp.printBoard();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return temp;
			}

		};
	}

	public boolean nextTurn() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '@')
					return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param board: The board to be set. Set parameter board as current board
	 * 
	 */

	public void setCa(char ca, int i, int j) {
		this.board[i][j] = ca;
	}

	/**
	 * 
	 * @return current CA state as a String
	 */
	public String CurrentBoardOutput() {
		return null;
	}

	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
				if (j != board[i].length - 1)
					System.out.print(" ");
			}
			System.out.println();
		}
	}
}
