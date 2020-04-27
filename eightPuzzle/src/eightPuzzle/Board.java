package eightPuzzle;

import java.util.Stack;
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
    private final int boardSize;	//board dimensions
    private final int[][] tiles;	//this board

    /**
	 * construct a board from an N-by-N array of blocks
	 * @param blocks
	 */
    public Board(int[][] blocks) {
        boardSize = blocks.length;
        tiles = new int[boardSize][boardSize];
        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                tiles[row][column] = blocks[row][column];
            }
        }
    }

    /**
	 * board size N
	 * @return
	 */
    public int size() {
        return this.boardSize;
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
				if (tiles[row][column] != 0) {
					if (tiles[row][column] != (row * size() + column + 1)) {
						incorrectValues += 1;
					}
				}
			}
		}
		return incorrectValues;
	}

    /**
	 * sum of Manhattan distances between blocks and goal
	 * @return
	 */
    public int manhattan() {
		int totalDistance = 0;

		for (int row = 0; row < size(); row++) {
			for (int column = 0; column < size(); column++) {
				if (tiles[row][column] != 0) {
					if (tiles[row][column] != (row * size() + column + 1)) {

						int goalRow = this.tiles[row][column] / size();
						int goalColumn = this.tiles[row][column] % size();

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
	 * is this board the goal board?
	 * @return
	 */
    public boolean isGoal() {
        return tilesEquals(this.tiles, createSolvedBoard());
    }

    /**
	 * is this board solvable?
	 * @return
	 */
    public boolean isSolvable(){
    	int s = 0;
    	int inversions = 0;
    	int blankRow = 0;
    	int invariant = 0;
    	int arraySize = boardSize * boardSize;
    	int[] tileArray = new int[arraySize];
    	boolean evenSize = false;
    	boolean evenInversion = false;
    	boolean evenInvariant = false;
    	
    	for (int row = 0; row < boardSize; row++) {
    		for (int column = 0; column < boardSize; column++) {
    			tileArray[s] = tiles[row][column];
    			if (tileArray[s] == 0) blankRow = row;
				for (int i = s; i >= 0; i--){
					if(tileArray[i] > tileArray[s] && tileArray[i] != 0 && tileArray[s] != 0){
						inversions++;
					}
				}
    			s++;
    		}
    	}
    	
    	//Even sided board?
    	if (boardSize % 2 == 0){
    		evenSize = true;
    		invariant = blankRow + inversions;
    	} 
    	
    	//Even number of inversions?
    	if (inversions % 2 == 0) evenInversion = true;

    	//Even invariant
    	if (invariant % 2 == 0) evenInvariant = true;
    	
    	if ( (!evenSize && evenInversion) || (evenSize && !evenInvariant) ){
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
        if (y == null)
            return false;
        if (y.getClass() != this.getClass())
            return false;

        Board that = (Board) y;
        return this.size() == that.size() && tilesEquals(this.tiles, that.tiles);
    }


    /**
	 * all neighboring boards
	 * @return
	 */
    public Iterable<Board> neighbors() {
        int zeroRow = 0, zeroColumn = 0;
        boolean found = false;
        for (int row = 0; row < size(); row++) {
            for (int j = 0; j < size(); j++) {
                if (tiles[row][j] == 0) {
                    zeroRow = row;
                    zeroColumn = j;
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        Stack<Board> boards = new Stack<Board>();
        Board board = new Board(tiles);
        boolean isNeighbor = board.swap(zeroRow, zeroColumn, zeroRow - 1, zeroColumn);
        if (isNeighbor) {
            boards.push(board);
        }
        board = new Board(tiles);
        isNeighbor = board.swap(zeroRow, zeroColumn, zeroRow, zeroColumn - 1);
        if (isNeighbor) {
            boards.push(board);
        }
        board = new Board(tiles);
        isNeighbor = board.swap(zeroRow, zeroColumn, zeroRow + 1, zeroColumn);
        if (isNeighbor) {
            boards.push(board);
        }
        board = new Board(tiles);
        isNeighbor = board.swap(zeroRow, zeroColumn, zeroRow, zeroColumn + 1);
        if (isNeighbor) {
            boards.push(board);
        }

        return boards;
    }

    /**
	 * string representation of this board
	 * ex: solved 3-by-3 board. 0 represents the blank square
	 * 3
	 *  1  2  3
	 *  4  5  6
	 *  7  8  0
	 */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(boardSize + "\n");
        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                s.append(String.format("%2d ", tiles[row][column]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    
    /*********************************************************************
     * Private methods
     *********************************************************************/
    
    /**
     * Create the solved board
     * @return
     */
    private int[][] createSolvedBoard() {
        int[][] array = new int[boardSize][boardSize];
        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                array[row][column] = distanceToGoal(row, column);
            }
        }

        return array;
    }
    
    /**
     * How far away is a piece from its final goal
     * @param row
     * @param column
     * @return
     */
    private int distanceToGoal(int row, int column) {
        if (endPiece(row, column)) {
            return 0;
        }
        return (row * size()) + column + 1;
    }

    /**
     * Is this an end piece?
     * @param column
     * @param column
     * @return
     */
    private boolean endPiece(int row, int column) {
        return row == size() - 1 && column == size() - 1;
    }
    
    /**
     * Swap the pieces (move the piece to the blank spot)
     * @param row1
     * @param column1
     * @param row2
     * @param column2
     * @return
     */
    private boolean swap(int row1, int column1, int row2, int column2) {
        if (row2 < 0 || row2 >= boardSize || column2 < 0 || column2 >= boardSize) {
            return false;
        }
        int temp = tiles[row1][column1];
        tiles[row1][column1] = tiles[row2][column2];
        tiles[row2][column2] = temp;
        return true;
    }
    
    /**
     * Are the tiles in the same place between boards?
     * @param first
     * @param second
     * @return
     */
    private boolean tilesEquals(int[][] first, int[][] second) {
        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                if (first[row][column] != second[row][column]) {
                    return false;
                }
            }
        }
        return true;
    }

}
