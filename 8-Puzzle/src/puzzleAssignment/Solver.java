/********************************************
 * Author: Carlos Martinez
 * Date: March 6, 2017
 * Assignment 3: 8-Puzzle
 *******************************************/
package puzzleAssignment;

// Import statements
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import edu.princeton.cs.algs4.MinPQ;

/**
 * This class solves an 8-puzzle board in the minimal amount of moves
 * @author Carlos Martinez
 */
public class Solver {
	
	/**
	 * This is an array of boards that stores the boards from start to
	 * finish of solving a board
	 */
	private final ArrayList<Board> boards;
	
	/**
	 * This stores the number of the minimal amount of moves used to
	 * solve the 8- puzzle board
	 */
	private int moves;
	
	/**
	 * This is the goal board after it has been found, used to retraced
	 * your steps and amount of moves used to solve the board
	 */
	private SearchNode goalNode;
	
	/**
	 * This is a class of of SearchNode to be used in a MinPQ based
	 * of the SearchNode's priority.
	 * @author Carlos Martinez
	 */
	private class SearchNode implements Comparable<SearchNode> {
		
		/**
		 * This is the board that is going to  be analysis
		 */
		private Board board;
		
		/**
		 * This is the number of moves that were made to get to this board
		 */
		private int moves;
		
		/**
		 * This is the SearchNode of the board that came before this board in moves
		 */
		private SearchNode previous;
		
		/**
		 * This is the number of moves + the hamming of the board
		 */
		private int priority;
		
		/**
		 * This constructor creates an new SearchNode
		 * @param board The board that is going to be analysis
		 * @param moves The number of moves that were made to get to this board
		 * @param previous The SearchNode of the board that came before this board in moves
		 */
		public SearchNode(Board board, int moves, SearchNode previous) {
			this.board = board;
			this.moves = moves;
			this.previous = previous;
			this.priority = moves + board.hamming();
		}
		
		/**
		 * This is an over ride method of comparable to let the SearchNode
		 * know how the SearchNodes should be compared
		 */
		@Override
		public int compareTo(SearchNode other) {
			if (this.priority < other.priority) {
				return -1;
			}
			if (this.priority > other.priority) {
				return 1;
			}
			return 0;
		}
	}
	
	/**
	 *  finds a solution to the initial board (using the A* algorithm)
	 * @param initial The board that were will solve
	 */
	public Solver(Board initial) {
		
		//Exception being thrown
		if(initial == null) {
			throw new NullPointerException("board can't be null");
		}
		
		if (!initial.isSolvable()) {
			throw new IllegalArgumentException("Board is not solvable");
		}
		
		MinPQ<SearchNode> searchNodeMinPQ = new MinPQ<SearchNode>();
		this.boards = new ArrayList<Board>();
		this.moves = 0;
		searchNodeMinPQ.insert(new SearchNode(initial,0,null));
		
		// Continues till a solution has been found
		while (true) { 
			SearchNode node = searchNodeMinPQ.delMin();
			
			// Checks if the board is the goal board to store the information
			// and to to end the loop once the goal board has been found
			if (node.board.isGoal()) {
				this.goalNode = node;
				this.moves = node.moves;
				break;
			}
			
			// creates a SearchNode out of the neighbor boards and adds it to MinPQ
			for (Board nextBoard: node.board.neighbors()) {
				if((node.previous == null) ||(!nextBoard.equals(node.previous.board))) {
					searchNodeMinPQ.insert(new SearchNode(nextBoard,node.moves + 1, node));
				}
			}
			
		}
	}
	
	/** 
	 * this method returns the number of minimal amount of moves used to solve
	 * the 8-puzzle board
	 * @return The number of minimal amount of moves used to solve
	 */
	public int moves() {
		return this.moves;
	}
	
	/**
	 * This method returns an arrayList of the sequence of boards in a shortest solution
	 * @return An arrayList of the sequence of boards in a shortest solution
	 */
	public Iterable<Board> solution() {
		this.boards.add(this.goalNode.board);
		SearchNode node = this.goalNode.previous;
		
		while (node != null) {
			this.boards.add(node.board);
			node = node.previous;
		}
		
		Collections.reverse(this.boards);
		return this.boards;
	}
	
	/**
	 * This is a main method used to test the Solver and solve a board 
	 * @param args
	 */
	public static void main(String[] args) { // unit tests (not graded)
		
		int[][] blocks = { {0,1,3}, {4,2,5}, {7,8,6} };
		
		// create initial board from file
	    Board initial = new Board(blocks);
	    System.out.println(initial);

	    // check if puzzle is solvable; if so, solve it and output solution
	    if (initial.isSolvable()) {
	        Solver solver = new Solver(initial);
	        System.out.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            System.out.println(board);
	    }

	    // if not, report unsolvable
	    else {
	        System.out.println("Unsolvable puzzle");
	    }
	    
	    System.out.println("_____________________________________");
	    
	    Board goalBoard = new Board(
				new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 0 } });
	    System.out.println(goalBoard);
	    Solver goalBoardSolver = new Solver(goalBoard);
	    
	    System.out.println("Minimum number of moves = " + goalBoardSolver.moves);
	    for (Board board : goalBoardSolver.solution()) {
            System.out.println(board);
	    }
	}	
}