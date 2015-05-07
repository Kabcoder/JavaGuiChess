import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;
import java.util.Scanner;

/*
 * ChessModel.java
 * 
 * $Id: ChessModel.java,v 1.3 2014/12/11 01:04:12 ask7708 Exp $
 * 
 * $Log: ChessModel.java,v $
 * Revision 1.3  2014/12/11 01:04:12  ask7708
 * getter methods added to class
 *
 * Revision 1.2  2014/12/07 07:12:25  ask7708
 * Created constructor
 *
 * Revision 1.1  2014/12/07 06:47:33  ask7708
 * *** empty log message ***
 *
 * 
 * 
 * 
 */
/**
 * Model part of the MVC for the Chess game.
 * Creates a model of the chess game. The observable
 * part of the GUI.
 * 
 * @author Arshdeep Khalsa
 *
 */
public class ChessModel extends Observable implements
Puzzle<HashMap<Integer, ChessPiece>> {

	/**
	 * Keeps count of the number of moves being made
	 * 
	 */
	private int moveCounter;

	/**
	 * Number of rows on board
	 * 
	 */
	private int rows;

	/**
	 * Number of columns on board
	 * 
	 */
	private int cols;

	/**
	 * A hashmap of all the positions and the pieces on the board.
	 * 
	 */
	HashMap<Integer, ChessPiece> piecesOnBoard = new HashMap<Integer, ChessPiece>();

	/**
	 * A hashmap of the starting config of the board.
	 * 
	 */
	HashMap<Integer, ChessPiece> startingConfig = new HashMap<Integer, ChessPiece>();

	/**
	 * Pieces to remove from
	 * 
	 */
	private ArrayList<Integer> removeList = new ArrayList<Integer>();

	/**
	 * The puzzleSolver from the solver
	 * 
	 */
	private Solver<HashMap<Integer, ChessPiece>> puzzleSolver = new Solver<HashMap<Integer, ChessPiece>>();

	/**
	 * Construct a Chess Model object.
	 * 
	 * @param filename
	 *            name of the input file containing game specifications
	 * @throws FileNotFoundException
	 * 
	 */
	public ChessModel(String fileName) {

		File chessFile = new File(fileName);
		try {
			Scanner in = new Scanner(chessFile);
			this.rows = in.nextInt();
			this.cols = in.nextInt();

			int i = 0;
			String piece = "";
			while (in.hasNext()) {

				if (isPiece(piece = in.next()) == true) {

					piecesOnBoard.put(i, getChessPiece(piece, rows, cols, i));

				}
				i++;

			}

			// Saves the starting config from the file
			startingConfig.putAll(piecesOnBoard);
			in.close();

		} catch (FileNotFoundException e) {
			System.out.println("{input-file} not found.");
			System.exit(0);
		}

		setChanged();
		notifyObservers(this.piecesOnBoard);

	}

	/**
	 * Returns the number of moves made so far
	 * 
	 * @return int - number of moves
	 */
	public int getMoveCount() {

		return this.moveCounter;
	}

	/**
	 * Returns the amount of rows on the board.
	 * 
	 * @return
	 */
	public int getRows() {

		return this.rows;
	}

	/**
	 * Returns the amount of columns of the board.
	 * 
	 * @return int - cols of board
	 */
	public int getColumns() {

		return this.cols;
	}

	/**
	 * Returns all the pieces currently on the board.
	 * 
	 * @return
	 */
	public HashMap<Integer, ChessPiece> getPiecesOnBoard() {

		return this.piecesOnBoard;
	}

	/**
	 * Returns the starting config of the file.
	 * 
	 */
	@Override
	public HashMap<Integer, ChessPiece> getStart() {

		return this.piecesOnBoard;
	}

	/**
	 * Returns the size of the list of pieces to be removed
	 * 
	 * @return int - size of pieces to be reomved
	 */
	public int getRemoveListSize() {

		return this.removeList.size();
	}

	/**
	 * 
	 * The method uses solver and gets the next "best" move and then notifies
	 * the observer of the action.
	 * 
	 * 
	 * @param temp
	 *            - model
	 * @return Returns true if a "best" move can be made false otherwise.
	 */
	public boolean findBestMove(ChessModel temp) {
		// run the solver and set board to the second step since the first is
		// the current board.
		try {
			if (this.piecesOnBoard.size() == 1) {
				return false;
			}
			this.piecesOnBoard = this.puzzleSolver.puzzleSolve(temp).get(1);
			this.moveCounter++;
			setChanged();
			notifyObservers(this.piecesOnBoard);

			return true;
		} catch (NullPointerException e) {

			return false;
		}
	}

	/**
	 * Checks to see if the given config is goal. Since this game is only won
	 * when there is one piece left it checks to see if the config is of size 1,
	 * meaning last piece left thus winning the game.
	 * 
	 * @return - true if config is of size 1 false otherwise
	 */
	@Override
	public boolean getGoal(HashMap<Integer, ChessPiece> config) {

		if (config.size() == 1) {

			return true;
		}
		return false;
	}

	/**
	 * Gets the neighbors of the given config.
	 * 
	 * @return ArrayList<HashMap<Integer, ChessPiece>> - list of neighbors
	 */
	public ArrayList<HashMap<Integer, ChessPiece>> getNeighbors(
			HashMap<Integer, ChessPiece> config) {

		//The neighbor list
		ArrayList<HashMap<Integer, ChessPiece>> nList = new ArrayList<HashMap<Integer, ChessPiece>>();

		for (Integer taker : config.keySet()) {

			ArrayList<Integer> listOfMoves = config.get(taker).getMoves(taker);

			for (Integer toBeTaken : config.keySet()) {

				if (listOfMoves.contains(toBeTaken)) {

					//N corresponds to a neighbor
					HashMap<Integer, ChessPiece> n = new HashMap<Integer, ChessPiece>();

					n.putAll(config);

					n.remove(toBeTaken);

					n.put(toBeTaken, config.get(taker));

					n.remove(taker);

					nList.add(n);
				}
			}
		}
		return nList;
	}

	/**
	 * Checks to see if a given string is valid piece. This works as a helper
	 * method for the getChessPiece.
	 * 
	 * 
	 * @param piece
	 *            - name of piece
	 * @return - true if piece found false otherwise
	 */
	public boolean isPiece(String piece) {

		if (piece.toString().toUpperCase().equals("B")
				|| piece.toString().toUpperCase().equals("K")
				|| piece.toString().toUpperCase().equals("N")
				|| piece.toString().toUpperCase().equals("P")
				|| piece.toString().toUpperCase().equals("Q")
				|| piece.toString().toUpperCase().equals("R")) {

			return true;

		}

		return false;
	}

	/**
	 * Returns a chess piece object based on the pieces name.
	 * 
	 * 
	 * @param pieceName
	 * @param r
	 *            - number of rows on board
	 * @param c
	 *            - number of cols on board
	 * @param i
	 *            - 1D position on board
	 * @return - Chess piece object
	 */
	public ChessPiece getChessPiece(String pieceName, int r, int c, int i) {

		if (pieceName.toString().toUpperCase().equals("B")) {
			Bishop b = new Bishop(pieceName, r, c, i);
			return b;
		}
		if (pieceName.toString().toUpperCase().equals("K")) {
			King b = new King(pieceName, r, c, i);

			return b;
		}
		if (pieceName.toString().toUpperCase().equals("N")) {
			Knight b = new Knight(pieceName, r, c, i);

			return b;
		}
		if (pieceName.toString().toUpperCase().equals("P")) {
			Pawn b = new Pawn(pieceName, r, c, i);

			return b;
		}
		if (pieceName.toString().toUpperCase().equals("Q")) {
			Queen b = new Queen(pieceName, r, c, i);
			
			return b;
		}
		if (pieceName.toString().toUpperCase().equals("R")) {
			Rook b = new Rook(pieceName, r, c, i);
			
			return b;
		}

		return null;
	}

	/**
	 * Resets everything in the model
	 */
	public void reset() {
		this.moveCounter = 0;
		this.piecesOnBoard.putAll(startingConfig);
		this.removeList.clear();
		setChanged();
		notifyObservers(this.piecesOnBoard);
	}

	/**
	 * Checks if a piece is at a position. If there is two pieces in the
	 * removelist the second piece is removed. The attacker is the first one in
	 * the list and the attacked is the second
	 * 
	 * @param c
	 *            - selected positon
	 * @return boolean - true or false
	 */
	public boolean selectCell(Integer c) {

		boolean removed = false;

		if (this.piecesOnBoard.containsKey(c)) {
			removed = true;

			this.removeList.add(c);

			if (this.removeList.size() == 2) {

				Integer taker = this.removeList.get(0);
				Integer toBeTaken = this.removeList.get(1);

				/**
				 * Checks to see if the the first piece contains the position of
				 * the second piece if this is true then the piece can overtake
				 * it
				 * 
				 */
				if (this.piecesOnBoard.get(taker).getMoves(taker)
						.contains(toBeTaken)) {

					this.piecesOnBoard.remove(toBeTaken);
					this.piecesOnBoard.put(toBeTaken,
					this.piecesOnBoard.get(taker));
					this.piecesOnBoard.remove(taker);
					this.moveCounter++;
					this.removeList.clear();
					removed = true;

				} else {

					this.removeList.remove(0);
				}
			}
		}

		setChanged();
		notifyObservers(this.piecesOnBoard);
		return removed;
	}

	/**
	 * Prints a text Representation of the board used for testing purposes.
	 * 
	 */
	public void printBoard() {

		for (int i = 0; i < rows * cols; i++) {

			if (piecesOnBoard.get(i) == null) {
				System.out.print("." + " ");
			} else {
				System.out.print(piecesOnBoard.get(i) + " ");
			}
			if (((i + 1) % cols) == 0) {
				System.out.println();
			}
		}

	}

	/*
	 * Test of chess model public static void main(String[] args) {
	 * 
	 * // System.out.println(args[0].toString()); ChessModel theModel = new
	 * ChessModel(args[0]); HashMap<Integer, ChessPiece> start = new
	 * HashMap<Integer, ChessPiece>(); start = theModel.getStart(); //
	 * System.out.println(start);
	 * 
	 * }
	 */

}
