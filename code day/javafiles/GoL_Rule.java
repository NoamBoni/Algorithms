
/**
 * 
 * @author This class implements the CA rule for a standard 'Game of Life'
 *         simulation
 *
 */
public class GoL_Rule implements CA_Rule {

	@Override
	public void implementRule(char[][] board) {

		for (int i = 0; i < board.length-1; i++) {
			for (int j = 0; j < board[0].length-1; j++) {
				int aliveNeighbors = 0;
				for (int x = -1; x < 2; x++) {
					for (int y = -1; y < 2; y++) {
						if ((x == 0 && y == 0) || (i == 0 && x == -1) || (j == 0 && y == -1) || (j == board[0].length-1 && y == 1) || (j == board.length-1 && x == 1))
							continue;
						if (board[i + x][j + y] == '@')
						aliveNeighbors++;
					}
				}
				if (board[i][j] == '@') {
					if (aliveNeighbors < 2 || aliveNeighbors > 3)
						board[i][j] = '.';
				}
				if (board[i][j] == '.') {
					if (aliveNeighbors == 3)
						board[i][j] = '@';
				}
			}
		}
	}

}
