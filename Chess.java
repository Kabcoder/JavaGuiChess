import javax.swing.JFrame;

/*
 * Chess.java
 * 
 * $Id: Chess.java,v 1.6 2014/12/11 01:07:21 ask7708 Exp $
 * 
 * $Log: Chess.java,v $
 * Revision 1.6  2014/12/11 01:07:21  ask7708
 * added boolean to know if game is done
 *
 * Revision 1.5  2014/12/11 01:06:00  ask7708
 * action listeners updated
 *
 * Revision 1.4  2014/12/10 21:22:12  ask7708
 * Created colorButtons method to create board scheme
 *
 * Revision 1.3  2014/12/10 21:04:09  ask7708
 * update implemented
 *
 * Revision 1.2  2014/12/10 20:49:53  ask7708
 * constructor implemented
 *
 * Revision 1.1  2014/11/30 06:51:44  ask7708
 * Initial Commit
 *
 * 
 * 
 */
/**
 * The GUI part of the chess game. Creates a frame for the game and
 * is an observer to the chess model. The game is played as a 
 * solitaire chess game.
 * 
 * 
 * @author Arshdeep Khalsa
 *
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * Chess.java
 * 
 * File:
 *	$Id: Chess.java,v 1.6 2014/12/11 01:07:21 ask7708 Exp $
 *
 * Revisions:
 *	$Log: Chess.java,v $
 *	Revision 1.6  2014/12/11 01:07:21  ask7708
 *	added boolean to know if game is done
 *
 *	Revision 1.5  2014/12/11 01:06:00  ask7708
 *	action listeners updated
 *
 *	Revision 1.4  2014/12/10 21:22:12  ask7708
 *	Created colorButtons method to create board scheme
 *
 **	$Log: Chess.java,v $
 **	Revision 1.6  2014/12/11 01:07:21  ask7708
 **	added boolean to know if game is done
 **
 **	Revision 1.5  2014/12/11 01:06:00  ask7708
 **	action listeners updated
 **
 *	Revision 1.3  2014/12/10 21:18:12  ask7708
 *	action listeners done
 *
 **	$Log: Chess.java,v $
 **	Revision 1.6  2014/12/11 01:07:21  ask7708
 **	added boolean to know if game is done
 **
 **	Revision 1.5  2014/12/11 01:06:00  ask7708
 **	action listeners updated
 **
 *	Revision 1.2  2014/11/10 19:05:12  ask7708
 *	Implementing constructor
 *
 **	$Log: Chess.java,v $
 **	Revision 1.6  2014/12/11 01:07:21  ask7708
 **	added boolean to know if game is done
 **
 **	Revision 1.5  2014/12/11 01:06:00  ask7708
 **	action listeners updated
 **
 *	Revision 1.1  2014/11/10 19:00:12  ask7708
 *	***empty log message***
 *
 */

/**
 * The main class for the chess GUI.
 * 
 * @author Arshdeep Khalsa
 */

public class Chess extends JFrame implements Observer {

	/**
	 * The chess model
	 * 
	 */
	private ChessModel theModel;

	/**
	 * The component for the frame for messages that are displayed.
	 * 
	 */
	private JLabel message;

	/**
	 * The JLabel component for the frame for the move counter.
	 * 
	 */
	private JLabel moveCounter;

	/**
	 * The list of buttons for the JFrame
	 * 
	 */
	ArrayList<ChessJButton> buttons = new ArrayList<ChessJButton>();

	/**
	 * The string that holds the message
	 * 
	 */
	private StringBuffer gameInfo = new StringBuffer();

	/**
	 * Variables that checks to see if the game is won.
	 * 
	 */
	private boolean gameWon;

	/**
	 * Creates the chess gui using the JFrame and other components
	 * 
	 * 
	 * @param model
	 */
	public Chess(ChessModel model) {

		this.theModel = model;

		model.addObserver(this);

		gameWon = false;

		this.message = new JLabel();
		this.message.setForeground(Color.RED);

		// Creates buttons for the GUI
		JButton reset = new JButton("Reset");
		JButton bestMove = new JButton("Solve Next Move");
		JButton aboutGame = new JButton("About Game");
		JButton quitGame = new JButton("Quit");

		// Adds actions to corresponding componenets
		quitGame.addActionListener(new ButtonListener(quitGame, this));
		reset.addActionListener(new ButtonListener(reset, this));
		bestMove.addActionListener(new ButtonListener(bestMove, this));
		aboutGame.addActionListener(new ButtonListener(aboutGame, this));

		// create Center Panel of gui
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(model.getRows(), model
				.getColumns(), 0, 0));

		// Create chess buttons for the grid
		for (int i = 0; i < model.getRows() * model.getColumns(); i++) {

			ChessJButton b = new ChessJButton(i);
			b.setBorderPainted(false);
			b.setContentAreaFilled(false);
			b.setOpaque(true);
			b.setVisible(true);
			b.setBackground(Color.BLACK);
			b.addActionListener(new ButtonListener(b, this));
			buttons.add(b);

			// Adds to the center panel
			centerPanel.add(buttons.get(i));
		}

		// Creates the top panel
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 3, 0, 0));
		topPanel.add(createMoveCounter(model.getMoveCount()));
		topPanel.add(this.message);
		topPanel.add(quitGame);

		// Creates the bottom panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 4, 0, 0));
		bottomPanel.add(bestMove);
		bottomPanel.add(reset);
		bottomPanel.add(aboutGame);

		// Adds top, center, and bottom to frame.
		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);

		// Configures frame title and size
		this.setTitle("Chess Solitaire Version - Arshdeep Khalsa - ask7708");
		this.setSize(model.getRows() * 100, model.getColumns() * 100);
		this.setLocation(100, 100);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Creates the string buffer for the information of how the game is
		// played
		gameInfo.append("This game is a one player solitaire chess game.\n"
				+ "All pieces move in the same way they do in regular chess.\n"
				+ "If you want to make a move, select the piece \n"
				+ "and then choose where you want to move it to. \n"
				+ "If the piece doesn't move that is an invalid move and \n"
				+ "there is no move made. The user can also choose to use \n"
				+ "the 'solve next move' button to have the next best \n"
				+ "move made. If the game is over the a message will display so \n"
				+ "as well as invalid moves and if there is no next best move. \n"
				+ "A quit option for the user is also available at the top right.\n");

		
		theModel.reset();
	}

	/**
	 * Creates a panel for the top of the GUI for the counter of moves
	 * 
	 * @param moves
	 * @return JPanel
	 */
	private Component createMoveCounter(int moves) {

		JPanel movePanel = new JPanel();
		moveCounter = new JLabel("Moves: 0");
		movePanel.add(moveCounter);

		return movePanel;

	}

	/**
	 * Overrides the observable updates and updates the gui whenever a button is
	 * clicked.
	 * 
	 * 
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

		HashMap<Integer, ChessPiece> chessBoard = theModel.getPiecesOnBoard();

		// Sets the buttons to null
		for (ChessJButton b : buttons) {

			b.setText(" ");
		}

		// Sets the name of each piece
		for (Integer p : chessBoard.keySet()) {
			this.buttons.get(p).setText(chessBoard.get(p).getPieceName());

		}

		// Updates counter panel based on move count
		this.moveCounter.setText("Move: " + theModel.getMoveCount());

		// If one piece remaining game is done!
		if (chessBoard.size() == 1) {
			this.message.setText("End of Game");
			gameWon = true;
		}

		// Handles the color of the buttons on the board
		if (this.theModel.getRemoveListSize() == 0) {
			this.colorButtons();
		}
	}

	/**
	 * Initialize the GUI object.
	 * 
	 * @param args
	 *            Command line arguments, not used.
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Chess initalBoard.txt");
			System.exit(0);
		}

		new Chess(new ChessModel(args[0]));
	}

	/**
	 * Changes color of buttons to red and white scheme
	 */
	protected void colorButtons() {

		for (int i = 0; i < buttons.size(); i++) {

			if (i % 2 == 0) {
				buttons.get(i).setBackground(Color.WHITE);
			} else {
				buttons.get(i).setBackground(Color.RED);
			}
		}

	}

	/**
	 * EventListener for all the buttons in the GUI
	 * 
	 * @author Arshdeep Khalsa
	 * 
	 */
	private class ButtonListener implements ActionListener {

		/**
		 * Current button in GUI
		 */
		private JButton b;

		/**
		 * Current GUI
		 * 
		 */
		private Chess gui;

		/**
		 * Created button listener using the passed gui and the button being
		 * passed
		 * 
		 * @param b
		 *            button passed
		 */
		public ButtonListener(JButton b, Chess gui) {
			this.b = b;
			this.gui = gui;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			// Check if it's JButton
			if (b instanceof ChessJButton && gameWon != true) {
				message.setText("");
				// Highlights the chosen piece as long as it's on the board
				if (theModel.selectCell(((ChessJButton) b).getPos())) {
					gui.colorButtons();
					b.setBackground(Color.GRAY);
					message.setText("Chosen cell is gray");
				} else {
					message.setText("Invalid Move");
				}

				// Check if it's quit button
			} else if (b.getText().equals("Quit")) {

				System.exit(0);
			}
			// Check if reset chosen
			else if (b.getText().equals("Reset")) {
				theModel.reset();
				message.setText("");
				gameWon = false;
				// Check if solve next move chosen
			} else if (b.getText().equals("Solve Next Move") && gameWon != true) {

				if (!theModel.findBestMove(theModel)) {
					message.setText("No \"Best\" Move");
				}
			} else if (b.getText().equals("About Game")) {

				// Display how the game is played and options explained
				JOptionPane.showMessageDialog(gui, gameInfo.toString(),
						"About Game", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

}
