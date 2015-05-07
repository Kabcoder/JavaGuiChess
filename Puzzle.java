/*
 * Puzzle.java
 * 
 * $Id: Puzzle.java,v 1.1 2014/11/30 06:03:07 ask7708 Exp $
 * 
 * $Log: Puzzle.java,v $
 * Revision 1.1  2014/11/30 06:03:07  ask7708
 * Initial Check in
 *
 * Revision 1.6  2014/11/24 05:06:10  ask7708
 * puzzle interface compelete for part 2
 *
 * Revision 1.5  2014/11/23 07:59:51  ask7708
 * getGoal return type changed to boolean
 *
 * Revision 1.4  2014/11/22 19:33:55  ask7708
 * changed to generics
 *
 * Revision 1.3  2014/11/22 19:32:54  ask7708
 * added Puzzle interface
 *
 * Revision 1.2  2014/11/04 19:56:33  ask7708
 * getGoal() and getNeighbors() method created
 *
 * Revision 1.1  2014/11/04 19:54:26  ask7708
 * Intitial blue print
 *
 * 
 */
/**
 * This program is the interface for the puzzle.
 * 
 * 
 * 
 * @author Arshdeep Khalsa
 *
 */
public interface Puzzle<E> {

	/**
	 * Gets the starting config for the puzzle
	 * 
	 * 
	 * @return the starting config
	 */
	public E getStart();

	/**
	 * getGoal checks whether the config passed is the config that matches the
	 * goal amount
	 * 
	 * @return boolean - true or false
	 */
	public boolean getGoal(E config);

	/**
	 * For an incoming config, generate and return all direct neighbors to this
	 * config
	 * 
	 * 
	 * @param config
	 *            - incoming config
	 * @return the collection of neighbor configs
	 */
	public java.util.ArrayList<E> getNeighbors(E config);

}
