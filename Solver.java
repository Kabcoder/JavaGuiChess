import java.util.ArrayList;
import java.util.HashSet;

/*
 * 
 * Solver.java
 * 
 * $Id: Solver.java,v 1.1 2014/11/30 06:03:11 ask7708 Exp $
 * 
 * $Log: Solver.java,v $
 * Revision 1.1  2014/11/30 06:03:11  ask7708
 * Initial Check in
 *
 * Revision 1.4  2014/11/23 07:57:40  ask7708
 * solver updated for both clock and water puzzles
 *
 * Revision 1.3  2014/11/06 20:15:44  ask7708
 * Commented algorithim
 *
 * Revision 1.2  2014/11/06 20:07:51  ask7708
 * Algorithim updated
 *
 * Revision 1.1  2014/11/04 20:36:31  ask7708
 * Class created
 *
 * 
 * 
 * 
 */
/**
 * The Solver class that uses a BFS algorithm to solve the clock object passed
 * to the function. The algorithm will produce the shortest path from the start
 * to goal.
 * 
 * 
 * 
 * @author Arshdeep Khalsa
 *
 */

public class Solver<E> {

	/**
	 * Uses the BFS algorithim to solve a puzzle that is passed in as a clock
	 * object. Each configuration contains the entire path from the starting
	 * config to the current one. The last element in the config is the one used
	 * to generate the next neighbors. A config whose last element is the goal
	 * is the solution
	 * 
	 * 
	 * 
	 * @param puzzleToSolve
	 *            - clock puzzle
	 * @return ArrayList<Integer> solved clock puzzles
	 */
	public ArrayList<E> puzzleSolve(Puzzle<E> puzzleToSolve) {
		// Create queue
		ArrayList<ArrayList<E>> queue = new ArrayList<ArrayList<E>>();
		// Start config arraylist
		ArrayList<E> startConfig = new ArrayList<E>();
		startConfig.add(puzzleToSolve.getStart());
		queue.add(startConfig);
		// Visited List
		HashSet<E> visited = new HashSet<E>();
		// boolean to see if goal is found
		boolean found = false;
		ArrayList<E> curr = null;
		visited.add(puzzleToSolve.getStart());
		// Goal is found return true else remain false
		if (puzzleToSolve.getGoal(puzzleToSolve.getStart())) {
			curr = queue.remove(0);
			found = true;
		}
		// Traverse through queue and create the path
		while (!queue.isEmpty() && found == false) {
			curr = queue.remove(0);
			ArrayList<E> lastElemNeigh = puzzleToSolve.getNeighbors(curr
					.get(curr.size() - 1));

			// for each neighbor of the last element in current:
			for (E neighbor : lastElemNeigh) {
				if (!visited.contains(neighbor)) {
					ArrayList<E> nextConfig = new ArrayList<E>();
					for (E item : curr) {
						nextConfig.add(item);
					}
					nextConfig.add(neighbor);

					if (puzzleToSolve
							.getGoal(nextConfig.get(nextConfig.size() - 1))) {
						curr = nextConfig;
						found = true;
						break;

					} else {

						queue.add(nextConfig);
					}

					visited.add(neighbor);
				}

			}
		}
		if (found == true) {
			return curr;
		} else {

			return null;
		}

	}
}


