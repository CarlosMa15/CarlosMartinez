/********************************************
 * Author: Carlos Martinez
 * Date: March 6, 2017
 * Assignment 3: 8-Puzzle
 *******************************************/
package puzzleAssignment;

//Import Statements
import java.util.ArrayList;
import java.util.Iterator;

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
public class Board {

	/**
	 * This sorts the values of the board in a double integer(int) Array
	 */
	private final int[][] blockTiles;

	/**
	 * This is the size of the board
	 */
	private int blockTilesSize;

	/**
	 * This Constructor creates a board out of a double integer(int) array
	 * 
	 * @param blocks
	 *            a double integer(int) array that represents the board
	 */
	public Board(int[][] blocks) {
		this.blockTilesSize = blocks.length;
		// this.blockTiles = blocks.clone();
		this.blockTiles = new int[size()][size()];

		for (int row = 0; row < size(); row++) {
			for (int column = 0; column < size(); column++) {
				this.blockTiles[row][column] = blocks[row][column];
			}
		}
	}

	/**
	 * This method returns the size of the board
	 * 
	 * @return the size of the board
	 */
	public int size() {
		return this.blockTilesSize;
	}

	/**
	 * This method checks how many values in the board that are not in the right
	 * location
	 * 
	 * @return the number of values in the board that are not in the right
	 *         location
	 */
	public int hamming() {
		int incorrectValues = 0;
		for (int row = 0; row < size(); row++) {
			for (int column = 0; column < size(); column++) {
				if (blockTiles[row][column] != 0) {
					if (blockTiles[row][column] != (row * size() + column + 1)) {
						incorrectValues += 1;
					}
				}
			}
		}
		return incorrectValues;
	}

	/**
	 * This method finds the sum of Manhattan distance between blocks and its
	 * goal
	 * 
	 * @return The sum of Manhattan distances between blocks and goal
	 */
	public int manhattan() {
		int totalDistance = 0;

		for (int row = 0; row < size(); row++) {
			for (int column = 0; column < size(); column++) {
				if (blockTiles[row][column] != 0) {
					if (blockTiles[row][column] != (row * size() + column + 1)) {

						int goalRow = this.blockTiles[row][column] / size();
						int goalColumn = this.blockTiles[row][column] % size();

						if (goalColumn == 0) {
							goalRow -= 1;
							goalColumn = size() - 1;
						} else {
							goalColumn -= 1;
						}

						totalDistance += Math.abs(row - goalRow) + Math.abs(column - goalColumn);
					}
				}
			}
		}

		return totalDistance;
	}

	/**
	 * This method checks if the board is the goal board that we wish to find
	 * 
	 * @return true is the board is the goal board, returns false if the board
	 *         is not the goal Board
	 */
	public boolean isGoal() {
		if (hamming() != 0) {
			return false;
		}
		return true;

	}

	/**
	 * This method checks if the board is even solvable
	 * 
	 * @return true if you can solve the board, returns false is impossible to
	 *         solve
	 */
	public boolean isSolvable() {
		int s = 0;
		int inversions = 0;
		int blankRow = 0;
		int invariant = 0;
		int[] tileArray = new int[size() * size()];
		boolean evenSize = false;
		boolean evenInversion = false;
		boolean evenInvariant = false;

		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < size(); j++) {
				tileArray[s] = this.blockTiles[i][j];
				if (tileArray[s] == 0)
					blankRow = i;
				for (int k = s; k >= 0; k--) {
					if (tileArray[k] > tileArray[s] && tileArray[k] != 0 && tileArray[s] != 0) {
						inversions++;
					}
				}
				s++;
			}
		}

		// Even sided board?
		if (size() % 2 == 0) {
			evenSize = true;
			invariant = blankRow + inversions;
		}

		// Even number of inversions?
		if (inversions % 2 == 0)
			evenInversion = true;

		// Even invariant
		if (invariant % 2 == 0)
			evenInvariant = true;

		if ((!evenSize && evenInversion) || (evenSize && !evenInvariant)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This Method compare two boards and checks if the two boards are equal
	 * 
	 * @param y
	 *            The item that the object is being compared to
	 * @return true if the two boards are the same size and have the same keys
	 *         in very matching position returns false if the two boards are not
	 *         equal in any way.
	 */
	@Override
	public boolean equals(Object y) {
		if (y == this)
			return true;
		if (y == null || !(y instanceof Board) || ((Board) y).blockTiles.length != size())
			return false;
		for (int row = 0; row < size(); row++) {
			for (int column = 0; column < size(); column++) {
				if (((Board) y).blockTiles[row][column] != this.blockTiles[row][column]) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * This method returns a Iterable of boards of all the possible move sets
	 * boards that you can get if you made one move.
	 * 
	 * @return an Iterable of boards of all the boards that you can get with
	 *         exactly one move in all the possible directions
	 */
	public Iterable<Board> neighbors() {
		ArrayList<Board> neighbors = new ArrayList<>();
		int[][] neighborArray = new int[size()][size()];
		int zeroRow = 0;
		int zeroColumn = 0;

		// Finding Zero and Creating a copy of the Array
		for (int row = 0; row < size(); row++) {
			for (int column = 0; column < size(); column++) {

				neighborArray[row][column] = blockTiles[row][column];

				if (this.blockTiles[row][column] == 0) {
					zeroRow = row;
					zeroColumn = column;
				}
			}
		}

		int topSwitch;
		// Creating the neighbor Boards from the neighbor Array
		if (zeroRow > 0) { // Top Neighbor
			switchingTwoElements(neighborArray, zeroRow, zeroColumn, zeroRow - 1, zeroColumn);
			neighbors.add(new Board(neighborArray));
			switchingTwoElements(neighborArray, zeroRow, zeroColumn, zeroRow - 1, zeroColumn);
		}
		if (zeroColumn > 0) { // Left Neighbor
			switchingTwoElements(neighborArray, zeroRow, zeroColumn, zeroRow, zeroColumn - 1);
			neighbors.add(new Board(neighborArray));
			switchingTwoElements(neighborArray, zeroRow, zeroColumn, zeroRow, zeroColumn - 1);
		}
		if (zeroRow < size() - 1) { // Bottom Neighbor
			switchingTwoElements(neighborArray, zeroRow, zeroColumn, zeroRow + 1, zeroColumn);
			neighbors.add(new Board(neighborArray));
			switchingTwoElements(neighborArray, zeroRow, zeroColumn, zeroRow + 1, zeroColumn);
		}
		if (zeroColumn < size() - 1) { // Right Neighbor
			switchingTwoElements(neighborArray, zeroRow, zeroColumn, zeroRow, zeroColumn + 1);
			neighbors.add(new Board(neighborArray));
		}

		return neighbors;
	}

	/**
	 * This method switches two elements in a doubleArray(int[][])
	 * 
	 * @param neighborArray
	 *            The doubleArray(int[][]) that will have two elements switches
	 * @param zeroRow
	 *            The Row location of the first element
	 * @param zeroColumn
	 *            The Column location of the first element
	 * @param otherRow
	 *            The Row location of the second method
	 * @param otherColumn
	 *            The Column location of the second element
	 */
	private void switchingTwoElements(int[][] neighborArray, int zeroRow, int zeroColumn, int otherRow,
			int otherColumn) {

		int switchedElement = neighborArray[otherRow][otherColumn];
		neighborArray[otherRow][otherColumn] = neighborArray[zeroRow][zeroColumn];
		neighborArray[zeroRow][zeroColumn] = switchedElement;
	}

	/**
	 * This method Overrides the toString method of the board to the form of the
	 * boards length in the top left counter of the board followed by the board
	 */
	@Override
	public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(size() + "\n");
        for (int row = 0; row < size(); row++) {
            for (int column = 0; column < size(); column++) {
                s.append(String.format("%2d ", blockTiles[row][column]));
            }
            s.append("\n");
        }
        return s.toString();
    }

	/**
	 * This is main method used to test everything, especially the neighbor
	 * method
	 * 
	 * @param args
	 */
	public static void main(String[] args) { // unit tests (not graded)
		/****************************************
		 * CHECK LIST ___________________________ BOARD COMPLETED AND CHECKED
		 * SIZE COMPLETED AND CHECKED HAMMING COMPLETED AND CHECKED MANHATTAN
		 * COMPLETED AND CHECKED ISGOAL COMPLETED AND CHECKED EQUALS COMPLETED
		 * AND CHECKED TOSTRING COMPLETED AND CHECKED
		 * 
		 * ISSOLVABLE NEEDS MORE WORK NEIGHBORS COMPLETED AND CHECKED
		 ****************************************/

		int[][] boardDoubleArray = { { 1, 2, 3 }, { 4, 0, 5 }, { 7, 8, 6 } };
		System.out.print("Double Array: ");

		for (int row = 0; row < boardDoubleArray.length; row++) {
			for (int column = 0; column < boardDoubleArray.length; column++) {
				System.out.print(boardDoubleArray[row][column] + ", ");
			}
		}
		System.out.println();
		System.out.println();

		Board board = new Board(boardDoubleArray);

		System.out.println("Board:\n" + board);
		System.out.println();

		System.out.println("Iterator:");
		for( Board el: board.neighbors()) {
			System.out.println(el);
		}
		
		System.out.println("Board:\n" + board + "\n");
		
		System.out.println("Testing BoardTest From UnitTest in Main Method");
		int[][] boardArray = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		Board immutableBoard = new Board(boardArray);
		System.out.println(immutableBoard.toString());
		boardArray[2][2] = 9;
		System.out.println(immutableBoard.toString());
		
		
		System.out.println("Neighbor Test of line 125,126/\nLast two Lines of neighbor Test");
		Board goalBoard = new Board(
				new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 0 } });
		
		System.out.println("goalBoard from Unit Test That Failed:\n" + goalBoard);
		
		System.out.println("Iterator:");
		for( Board el: goalBoard.neighbors()) {
			System.out.println(el);
		}
	}
}