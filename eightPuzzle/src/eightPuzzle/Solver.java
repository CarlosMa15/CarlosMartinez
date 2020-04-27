package eightPuzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
/**
 * SLCC: CSIS-2420
 * A04 - 8 Puzzle 
 * @authors Carlos Martinez and Andrew Rose
 *
 * This program is based on the puzzle invented and popularized
 * by Noyes Palmer Chapman in the 1870s.
 * The puzzle is originally build on a 3-by-3 grid with 8 squares (numbered 1 - 8)
 * with one blank square. 
 * The object is to arrange the blocks in correct number order with the blank in the bottom right corner. 
 * 
 * This program will solve puzzles larger than the 3-by-3 grid as well; but processing time and resources requirement
 * increase also as the board size increases.
 *
 * The solution is to use the AI methodology "A* search algorithm" to find the least number or moves to reach the goal.
 * 
 * The method isSolvable will detect an unsolvable puzzle based on the number of inversions of the puzzle loaded into an array.
 * An inversion is defined as any pair of blocks i and j where i < j but i appears after j in the array.
 * The formula states: 
 *   (a) If the board size N is odd, then a solvable puzzle will have an even number of inversions.
 *   (b) If the board size N is even, then the sum of the inversions plus the row number of the blank square (counting from 
 *       the top as row 0) will be odd for a solvable puzzle
 * This method will determine if the puzzle is solvable or not without having to process all the possible solutions first. If
 * the puzzle is solvable, then the program will work to find the solution.      
 *   
 */

public class Solver {
    private final Stack<Board> boards;
    private int moves;
    
    /**
	 * find a solution to the initial board (using the A* algorithm
	 * @param initial
	 */
    public Solver(Board initial) {
        boards = new Stack<Board>();
        if (initial == null){
        	throw new java.lang.NullPointerException("Inital Board is null");
        }
        if (!initial.isSolvable()){
        	throw new java.lang.IllegalArgumentException("Unsolvable puzzle");
        }
        Board board = initial;
        if (initial.isGoal()) {
            this.boards.push(initial);
            return;
        }
        moves = 0;
        MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>();
        SearchNode node = new SearchNode(board, 0, null);
        minPQ.insert(node);
        while (moves < Math.pow(board.size(), 3)) {
        	node = minPQ.delMin();
        	board = node.board;
        	if (board.isGoal()) {
        		this.boards.push(board);
        		while (node.previous != null) {
        			node = node.previous;
        			this.boards.push(node.board);
        			this.moves++;
        		}
        		return;
        	}
        	node.moves++;
        	//this.moves = node.moves;
        	Iterable<Board> neighbors = board.neighbors();
        	for (Board neighbor : neighbors) {
        		if (node.previous != null
        				&& neighbor.equals(node.previous.board)) {
        			continue;
        		}
        		SearchNode newNode = new SearchNode(neighbor, node.moves, node);
        		minPQ.insert(newNode);
        	}
        }
    }


	/**
	 * min number of moves to solve initial board
	 * @return
	 */
    public int moves() {
    	return this.moves;
    }

	/**
	 * sequence of boards in a shortest solution
	 * @return
	 */
    public Iterable<Board> solution() {
        return boards;
    }

	/**
	 * solve a slider puzzle
	 * @param args
	 */
    public static void main(String[] args) {
    	
		int[][] blocks = { {0,1,3}, {4,2,5}, {7,8,6} };
		
		// create initial board from file
	    Board initial = new Board(blocks);

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
    }
    
    /*****************************************************************************************
     * Private classes and methods
     ******************************************************************************************/
    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int moves;
        private SearchNode previous;
        private int cachedPriority = -1;

        SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
        }

        private int priority() {
            if (cachedPriority == -1) {
                cachedPriority = moves + board.manhattan();
            }
            return cachedPriority;
        }

        @Override
        public int compareTo(SearchNode that) {
            if (this.priority() < that.priority()) {
                return -1;
            }
            if (this.priority() > that.priority()) {
                return +1;
            }
            return 0;
        }
    }

}
