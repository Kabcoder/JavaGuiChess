/*
 * 
 * $Id: Pawn.java,v 1.2 2014/12/10 05:33:05 ask7708 Exp $
 * 
 * $Log: Pawn.java,v $
 * Revision 1.2  2014/12/10 05:33:05  ask7708
 * pawn moves complete
 *
 * Revision 1.1  2014/12/10 05:27:34  ask7708
 * Pawn class started
 *
 * 
 */
/**
 * Creates the pawn piece for the chess board.
 * 
 * @author Arshdeep Khalsa
 *
 */
import java.util.ArrayList;

public class Pawn extends ChessPiece {

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
	public Pawn(String name, int r, int c, int pos) {
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

		// left top diagonal
		if (pawnCanMoveTo(tempX - 1, tempY - 1)) {
			moves.add(pos - (this.getCol() + 1));
		}
		// Right top diagonal
		if (pawnCanMoveTo(tempX + 1, tempY - 1)) {
			moves.add(pos - (this.getCol() - 1));
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
	public boolean pawnCanMoveTo(int moveToX, int moveToY) {

		return 0 <= moveToX && moveToX < this.getCol() && 0 <= moveToY
				&& moveToY < this.getRow();

	}

}