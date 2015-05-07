import javax.swing.JButton;

/*
 * 
 * $Id: ChessJButton.java,v 1.1 2014/12/10 20:19:08 ask7708 Exp $
 * 
 * $Log: ChessJButton.java,v $
 * Revision 1.1  2014/12/10 20:19:08  ask7708
 * Created helper class for JButtons
 *
 * 
 */

/**
 * Adds a JButton to a 1D grid. This class takes in position and treats it as a
 * corresponding position on a 1D grid.
 * 
 * @author Arshdeep Khalsa
 * 
 */
public class ChessJButton extends JButton {

	/**
	 * The position of the button on the 1D grid.
	 * 
	 */
	Integer pos;

	/**
	 * Sets the given position to the corresponding button's position.
	 * 
	 * @param location
	 */
	public ChessJButton(int location) {

		this.pos = location;
	}

	/**
	 * Returns the position of the button.
	 * 
	 * @return position on 1D grid.
	 */
	public Integer getPos() {
		return pos;
	}
}
