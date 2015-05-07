/*
 * 
 * $Id: Knight.java,v 1.3 2014/12/10 05:55:10 ask7708 Exp $
 * 
 * $Log: Knight.java,v $
 * Revision 1.3  2014/12/10 05:55:10  ask7708
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
 * Creates the Knight for a chess board.
 * 
 * @author Arshdeep Khalsa
 * 
 */
import java.util.ArrayList;

public class Knight extends ChessPiece {

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
	public Knight(String name, int r, int c, int pos) {
		super(name, r, c, pos);

	}

	/**
	 * Returns an arraylist of valid moves for the knight piece.
	 * 
	 */
	@Override
	public ArrayList<Integer> getMoves(Integer pos) {

		ArrayList<Integer> moves = new ArrayList<Integer>();

		int tempX = super.posToX(pos);
		int tempY = super.posToY(pos);
		int addMove = 0;
		// System.out.println("Temp"+tempX+" "+tempY);
		for (int i = 0; i < this.getCol(); i++) {
			for (int j = 0; j < this.getRow(); j++) {

				if (knightCanMoveTo(i, j, tempX, tempY)) {

					addMove = j * this.getCol() + i;
					// System.out.println("Add Move"+addMove);
					moves.add(addMove);
				}

			}

		}

		moves.remove(pos);
		// System.out.println(" Knight Moves: " + moves);
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
	public boolean knightCanMoveTo(int moveToX, int moveToY, int currX,
			int currY) {

		int diffX = Math.abs(currX - moveToX);
		int diffY = Math.abs(currY - moveToY);

		if ((diffX == 2 && diffY == 1) || (diffX == 1 && diffY == 2)) {
			return true;
		}

		return false;
	}

}
