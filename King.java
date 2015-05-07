/*
 *
 * $Id: King.java,v 1.5 2014/12/10 20:28:57 ask7708 Exp $
 * 
 * $Log: King.java,v $
 * Revision 1.5  2014/12/10 20:28:57  ask7708
 * constructor updated
 *
 * Revision 1.4  2014/12/10 20:25:57  ask7708
 * kingCanMoveTo updated
 *
 * Revision 1.3  2014/12/10 05:22:18  ask7708
 * getMoves() tested
 *
 * Revision 1.2  2014/12/10 03:08:32  ask7708
 * canMoveTo() implemented
 *
 * Revision 1.1  2014/12/10 03:07:11  ask7708
 * King constructor complete
 *
 * 
 */
/**
 * Creates a king piece on a chess Board.
 * 
 * @author Arshdeep Khalsa
 * 
 */
import java.util.ArrayList;

public class King extends ChessPiece {

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
	public King(String name, int r, int c, int pos) {
		super(name, r, c, pos);

	}

	/**
	 * Returns an arraylist of valid moves for the king piece. Kings can move
	 * one cell in any direction.
	 * 
	 */
	@Override
	public ArrayList<Integer> getMoves(Integer pos) {

		ArrayList<Integer> moves = new ArrayList<Integer>();

		int tempX = super.posToX(pos);
		int tempY = super.posToY(pos);

		// left top diagonal
		if (kingCanMoveTo(tempX - 1, tempY - 1)) {
			moves.add(pos - (this.getCol() + 1));
		}
		// Right top diagonal
		if (kingCanMoveTo(tempX + 1, tempY - 1)) {
			moves.add(pos - (this.getCol() - 1));
		}
		// left bottom diagonal
		if (kingCanMoveTo(tempX - 1, tempY + 1)) {
			moves.add(pos + (this.getCol() - 1));
		}
		// right bottom diagonal
		if (kingCanMoveTo(tempX + 1, tempY + 1)) {
			moves.add(pos + (this.getCol() + 1));
		}
		// Top Straight
		if (kingCanMoveTo(tempX, tempY - 1)) {
			moves.add(pos - (this.getCol()));
		}
		// Top Behind
		if (kingCanMoveTo(tempX, tempY + 1)) {
			moves.add(pos + (this.getCol()));
		}
		// Right
		if (kingCanMoveTo(tempX + 1, tempY)) {
			moves.add(pos + 1);
		}
		// Left
		if (kingCanMoveTo(tempX - 1, tempY)) {
			moves.add(pos - 1);
		}

		// System.out.println("King Moves:" + moves);
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
	public boolean kingCanMoveTo(int moveToX, int moveToY) {

		return 0 <= moveToX && moveToX < this.getCol() && 0 <= moveToY
				&& moveToY < this.getRow();

	}

}
