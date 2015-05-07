import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class ChessModelTest {
	public static void main(String[] args) {
		Solver<HashMap<Integer, ChessPiece>> s = new Solver<HashMap<Integer, ChessPiece>>();
		
		ChessModel cm = new ChessModel(args[0]);
		ArrayList<HashMap<Integer, ChessPiece>> solution = s.puzzleSolve(cm);

		if (solution != null) {
			for (HashMap<Integer, ChessPiece> b : solution) {
				System.out.println(b);
			}
		} else {
			System.out.println("No solution");
		}
	}
}