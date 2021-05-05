import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File gameStats = new File("GliderGunSmall.txt");
        try (Scanner sc = new Scanner(gameStats)) {
            String[] boardSize = sc.nextLine().split(" ");
            GoL_Board board = new GoL_Board(Integer.parseInt(boardSize[0]), Integer.parseInt(boardSize[1]));
            String[] filler = new String[Integer.parseInt(boardSize[0])];
            int i = 0;
            while (sc.hasNextLine()) {
                filler[i] = sc.nextLine();
                i++;
            }
            formatBoard(filler, board);
            board.printBoard();
            System.out.println();
            Iterator<GoL_Board> it = board.iterator();
            while(it.hasNext()){
                it.next();  
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void formatBoard(String[] arr, GoL_Board board) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr[i].length() - 1; j++) {
                if (arr[i].charAt(j) == '*')
                    board.setCa('@', i, j);
                else
                    board.setCa('.', i, j);
            }
        }
    }
}
