/*
 * 
 * $Id: Queen.java,v 1.2 2014/12/10 06:03:56 ask7708 Exp $
 * 
 * $Log: Queen.java,v $
 * Revision 1.2  2014/12/10 06:03:56  ask7708
 * Queen implemented
 *
 * Revision 1.1  2014/12/10 05:56:09  ask7708
 * Queen class started
 *
 * 
 */
/**
 * Creates a queen piece for the chess board.
 * 
 * @author Arshdeep Khalsa
 * 
 */
import java.util.ArrayList;

public class Queen extends ChessPiece {

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
	public Queen(String name, int r, int c, int pos) {
		super(name, r, c, pos);

	}

	/**
	 * Returns an arraylist of valid moves for the queen piece. Queens can move
	 * in any direction.
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

				if (queenCanMoveTo(i, j, tempX, tempY)) {
					addMove = j * this.getCol() + i;
					moves.add(addMove);
				}

			}

		}

		moves.remove(pos);
		// System.out.println(" Queen Moves: " + moves);
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
	public boolean queenCanMoveTo(int moveToX, int moveToY, int currX, int currY) {

		int diffX = Math.abs(currX - moveToX);
		int diffY = Math.abs(currY - moveToY);

		if (diffX == diffY || (currX == moveToX || currY == moveToY)) {
			return true;
		}

		return false;
	}

}
