import java.util.ArrayList;

/*
 * ChessPiece.java
 * 
 * $Id: ChessPiece.java,v 1.7 2014/12/10 20:28:20 ask7708 Exp $
 * 
 * $Log: ChessPiece.java,v $
 * Revision 1.7  2014/12/10 20:28:20  ask7708
 * position conversions updated
 *
 * Revision 1.6  2014/12/10 20:23:33  ask7708
 * getRow and getCol updated
 *
 * Revision 1.5  2014/12/10 20:13:47  ask7708
 * commenting updated
 *
 * Revision 1.4  2014/12/10 03:18:01  ask7708
 * get position function added
 *
 * Revision 1.3  2014/12/09 21:38:54  ask7708
 * Variables created
 *
 * Revision 1.2  2014/12/07 07:52:28  ask7708
 * variables declared
 *
 * Revision 1.1  2014/12/07 07:50:09  ask7708
 * Initial Commit
 *
 * 
 */
/**
 * An abstract class for the chess pieces on the board.
 * 
 * @author Arshdeep Khalsa
 *
 */
public abstract class ChessPiece {

	/**
	 * Name of the chess piece
	 * 
	 */
	private String pieceName;

	/**
	 * Rows on board
	 * 
	 */
	private int row;

	/**
	 * Number of cols on board
	 * 
	 */
	private int col;

	/**
	 * Position of piece on 1 dimensional grid
	 * 
	 */
	private int piecePos;

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
	public ChessPiece(String name, int rowNum, int colNum, int pos) {

		this.pieceName = name;
		this.row = rowNum;
		this.col = colNum;
		this.piecePos = pos;
	}

	/**
	 * Returns the piece name for a given chess piece
	 * 
	 * @return - name
	 */
	public String getPieceName() {

		return this.pieceName;
	}

	/**
	 * Abstract method to get moves for a certain piece
	 * 
	 * @param pos
	 * @return
	 */
	public abstract ArrayList<Integer> getMoves(Integer pos);

	/**
	 * Overrides toString returns the name of given piece
	 * 
	 */
	public String toString() {

		return this.pieceName;
	}

	/**
	 * Returns number of rows of board
	 * 
	 * @return
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Returns number of columns of board
	 * 
	 * @return
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Returns position on 1D grid.
	 * 
	 * @return
	 */
	public int getPos() {

		return piecePos;
	}

	/**
	 * Convert 1D position to it's corresponding x value. The algorithim used is
	 * the y value multiplied by the width of the grid and then added to the x
	 * value.
	 * 
	 * @param pos
	 * @return
	 */
	public int posToX(int pos) {

		int tempPos = 0;

		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {

				tempPos = (i * this.col) + j;

				if (tempPos == pos) {
					return j;
				}

			}
		}

		return 0;
	}

	/**
	 * Convert 1D position to it's corresponding y value. The algorithim used is
	 * the y value multiplied by the column of the grid and then added to the
	 * corresponding grid.
	 * 
	 * @param pos
	 * @return
	 */
	public int posToY(int pos) {

		int tempPos = 0;

		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {

				tempPos = (i * this.col) + j;
				if (tempPos == pos) {

					return i;
				}

			}
		}

		return 0;
	}

}
