/*
 * 
 * $Id: Rook.java,v 1.2 2014/12/10 06:07:54 ask7708 Exp $
 * 
 * $Log: Rook.java,v $
 * Revision 1.2  2014/12/10 06:07:54  ask7708
 * canMoveTo implemented
 *
 * Revision 1.1  2014/12/10 06:05:27  ask7708
 * Rook being implemented
 *
 * 
 */
/**
 * Creates rook piece for the chess board.
 * 
 * @author Arshdeep Khalsa
 * 
 */
import java.util.ArrayList;

public class Rook extends ChessPiece {

	/**
	 * Creates a chess piece using the name, row, col and pos it's in one a 1D
	 * grid.
	 * 
	 * 
	 * @param name
	 * @param rowNum
	 * @param colNum
	 * @param pos
	 */
	public Rook(String name, int r, int c, int pos) {
		super(name, r, c, pos);

	}

	/**
	 * Returns an arraylist of valid moves for the rook piece. Rooks can move
	 * horizontally or diagonally.
	 * 
	 */
	@Override
	public ArrayList<Integer> getMoves(Integer pos) {

		ArrayList<Integer> moves = new ArrayList<Integer>();

		int tempX = super.posToX(pos);
		int tempY = super.posToY(pos);
		int addMove = 0;

		for (int i = 0; i < this.getCol(); i++) {
			for (int j = 0; j < this.getRow(); j++) {

				if (rookCanMoveTo(i, j, tempX, tempY)) {
					addMove = j * this.getCol() + i;
					moves.add(addMove);
				}

			}

		}

		moves.remove(pos);
		// System.out.println(" Rook Moves: " + moves);
		return moves;

	}

	/**
	 * Method that checks if the move trying to be made is a valid move with
	 * respect to board boundaries.
	 * 
	 * @param moveToX
	 *            - x position to move to
	 * @param moveToY
	 *            - y position to move to
	 * @param currX
	 *            - current x position
	 * @param currY
	 *            - current y position
	 * @return
	 */
	public boolean rookCanMoveTo(int moveToX, int moveToY, int currX, int currY) {

		if (moveToX == currX || moveToY == currY) {
			return true;
		}

		return false;
	}

}
