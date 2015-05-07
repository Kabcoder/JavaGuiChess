/*
 * 
 * $Id: Bishop.java,v 1.3 2014/12/10 05:54:58 ask7708 Exp $
 * 
 * $Log: Bishop.java,v $
 * Revision 1.3  2014/12/10 05:54:58  ask7708
 * getMoves updated
 *
 * Revision 1.2  2014/12/10 02:34:27  ask7708
 * getMoves being implemented few bugs
 *
 * Revision 1.1  2014/12/10 02:30:27  ask7708
 * Constructor Created
 *
 * 
 * 
 */
/**
 * Creates the Bishop for a chess board.
 * 
 * @author Arshdeep Khalsa
 * 
 */
import java.util.ArrayList;

public class Bishop extends ChessPiece {

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
	public Bishop(String name, int r, int c, int pos) {
		super(name, r, c, pos);

	}

	/**
	 * Returns an arraylist of valid moves for the bishop piece. Bishops can
	 * move diagonally in any way.
	 * 
	 */
	@Override
	public ArrayList<Integer> getMoves(Integer pos) {

		ArrayList<Integer> moves = new ArrayList<Integer>();

		int tempX = super.posToX(pos);
		int tempY = super.posToY(pos);
		int addMove = 0;

		// Traverse through the whole board and add valid moves
		for (int i = 0; i < this.getCol(); i++) {
			for (int j = 0; j < this.getRow(); j++) {

				if (bishCanMoveTo(i, j, tempX, tempY)) {
					addMove = j * this.getCol() + i;
					moves.add(addMove);
				}

			}

		}

		moves.remove(pos);

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
	public boolean bishCanMoveTo(int moveToX, int moveToY, int currX, int currY) {

		int diffX = Math.abs(currX - moveToX);
		int diffY = Math.abs(currY - moveToY);

		if (diffX == diffY) {
			return true;
		}

		return false;
	}

}
